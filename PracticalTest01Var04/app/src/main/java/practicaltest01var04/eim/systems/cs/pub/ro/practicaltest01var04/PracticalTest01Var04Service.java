package practicaltest01var04.eim.systems.cs.pub.ro.practicaltest01var04;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.StringTokenizer;

/**
 * Created by cristof on 03.04.2017.
 */

public class PracticalTest01Var04Service extends Service {

    Intent receivedIntent;
    String action = "broadcast_receiver_action";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("SERVICE", "Started service");
        if (intent != null){
            receivedIntent  = intent;
            String extra = receivedIntent.getStringExtra("text");
            StringTokenizer tokenizer = new StringTokenizer(extra, ",");
            while (tokenizer.hasMoreElements()){
                Intent broadcastIntent = new Intent(action);
                broadcastIntent.putExtra("text", tokenizer.nextToken());
                sendBroadcast(broadcastIntent);
            }
        }
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(getApplicationContext(), "Service started ", Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
