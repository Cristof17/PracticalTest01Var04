package practicaltest01var04.eim.systems.cs.pub.ro.practicaltest01var04;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.StringTokenizer;

public class PracticalTest01Var04MainActivity extends AppCompatActivity implements View.OnClickListener{


    public final int myRequestCode = 100;

    Button A,B,C,D,E, next;
    TextView text, result;
    int apasari = 0;

    String action = "broadcast_receiver_action";

    MyBroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var04_main);

        A = (Button)findViewById(R.id.left_up);
        B = (Button)findViewById(R.id.right_up);
        C = (Button)findViewById(R.id.center);
        D = (Button)findViewById(R.id.left_down);
        E = (Button)findViewById(R.id.right_down);
        next = (Button)findViewById(R.id.next_activity);
        text = (TextView)findViewById(R.id.number_text_field);
        result = (TextView)findViewById(R.id.result_text);

        A.setOnClickListener(this);
        B.setOnClickListener(this);
        C.setOnClickListener(this);
        D.setOnClickListener(this);
        E.setOnClickListener(this);
        next.setOnClickListener(this);

        receiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter(action);
        registerReceiver(receiver, intentFilter);
    }

    @Override
    public void onClick(View view) {

        if (view == A){
            String currText = text.getText().toString();
            String newText = currText + "A,";
            text.setText(newText);
        } else if (view == B){
            String currText = text.getText().toString();
            String newText = currText + "B,";
            text.setText(newText);

        } else if (view == C){
            String currText = text.getText().toString();
            String newText = currText + "C,";
            text.setText(newText);
        } else if (view == D){
            String currText = text.getText().toString();
            String newText = currText + "D,";
            text.setText(newText);
        } else if (view == E){
            String currText = text.getText().toString();
            String newText = currText + "E,";
            text.setText(newText);
        } else if (view == next){
            Intent secondaryActivityIntent = new Intent(getApplicationContext(), PracticalTest01Var04SecondaryActivity.class);
            secondaryActivityIntent.putExtra("text", text.getText().toString());
            startActivityForResult(secondaryActivityIntent, myRequestCode);
        }
        apasari++;
        Toast.makeText(getApplicationContext(), "Nr apasari = " + apasari, Toast.LENGTH_SHORT).show();
        if (apasari == 4){
            //TODO Start service
            Intent serviceIntent = new Intent(getApplicationContext(), PracticalTest01Var04Service.class);
            serviceIntent.putExtra("text", text.getText().toString());
            startService(serviceIntent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        apasari = 0;
        text.setText("");
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == myRequestCode){
            if (resultCode == RESULT_OK){
                //set another text view to the text sent from the second activitiy
                result.setText("Verifiy pressed");
                Toast.makeText(getApplicationContext(), "Verifiy  pressed", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED){
                result.setText("Cancel pressed");
                Toast.makeText(getApplicationContext(), "Cancel  pressed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent stopServiceIntent = new Intent(getApplicationContext(), PracticalTest01Var04Service.class);
        stopService(stopServiceIntent);
        if (receiver != null) {
            unregisterReceiver(receiver);
        }

    }

    private static class  MyBroadcastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String text = intent.getStringExtra("text");
            Log.d("BROADCAST RECEIVER", "Received " + text);

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d("MAIN ACTIVITY", "onSaveInstanceState");
        super.onSaveInstanceState(outState);
        outState.putString("text", text.getText().toString());
        outState.putInt("apasari", apasari);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("MAIN ACTIVITY", "onRestoreSavedInstanceState");
        if (savedInstanceState == null){
            Log.d("MAIN ACTIVITY", "Saved instance state is null");
        } else {
            if (text != null){
                text.setText(savedInstanceState.getString("text"));
                apasari = savedInstanceState.getInt("apasari");
            }
        }
    }
}
