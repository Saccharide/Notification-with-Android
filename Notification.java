package edu.wm.cs.rtang.homework_1_tang;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
public class MainActivity extends AppCompatActivity {

    private Handler _handler;
    private int _delay = 30000;  // 30 seconds.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // The first time the app is opened. It should send one Notification.
        sendNotification();

        // Setting up a Handler that runs every 30 seconds and calls sendNotification.
        _handler = new Handler();
        _handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                sendNotification();
                _handler.postDelayed(this, _delay);
            }
        }, _delay);

    }

    /*
        Sends a Notification to Android
     */
    private void sendNotification(){

        // Setting up the Notification Manager and Notification Channel
        String id = "main";
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel channel = new NotificationChannel(id, "HW -1", NotificationManager.IMPORTANCE_DEFAULT);
        manager.createNotificationChannel(channel);

        // Setting up a Notification Builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, id);
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setContentTitle("Hello World!");
        builder.setContentText("By: Ruhao (Tony) Tang");
        builder.setWhen(System.currentTimeMillis());

        // Sends the Notification to Android
        manager.notify(1000, builder.build());
    }



}
