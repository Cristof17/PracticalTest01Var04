package practicaltest01var04.eim.systems.cs.pub.ro.practicaltest01var04;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by cristof on 03.04.2017.
 */

public class PracticalTest01Var04SecondaryActivity extends Activity implements View.OnClickListener{

    Button verify, cancel;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var04_secondary);

        verify = (Button)findViewById(R.id.verify);
        cancel = (Button)findViewById(R.id.cancel);
        text = (TextView)findViewById(R.id.secondary_activity_text);

        Intent returnIntent = getIntent();
        String textToShow = returnIntent.getExtras().getString("text");
        text.setText(textToShow);

        verify.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Intent intent = getIntent();

        if (view == verify){
            setResult(Activity.RESULT_OK, intent);
        } else if (view == cancel){
            setResult(Activity.RESULT_CANCELED, intent);
        }
        finish();
    }
}
