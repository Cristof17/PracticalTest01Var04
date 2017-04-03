package practicaltest01var04.eim.systems.cs.pub.ro.practicaltest01var04;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PracticalTest01Var04MainActivity extends AppCompatActivity implements View.OnClickListener{


    public final int myRequestCode = 100;

    Button A,B,C,D,E, next;
    TextView text;
    int apasari = 0;

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

        A.setOnClickListener(this);
        B.setOnClickListener(this);
        C.setOnClickListener(this);
        D.setOnClickListener(this);
        E.setOnClickListener(this);
        next.setOnClickListener(this);
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
        if (apasari == 4){
            //TODO Start service
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == myRequestCode){
            if (resultCode == RESULT_OK){
                //set another text view to the text sent from the second activitiy
            }
        }
    }
}
