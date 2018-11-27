package net.ariflaksito.mynotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static net.ariflaksito.mynotification.NotificationUtils.ANDROID_CHANNEL_ID;

public class MainActivity extends AppCompatActivity {

    public static final int NOTIF_ID = 1;
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://dicoding.com"));
        pendingIntent = PendingIntent
                .getActivity(MainActivity.this, 0, intent, 0);

        Button btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                    showNotifOreo();
                else showNotifDefault();
            }
        });

    }

    public void showNotifDefault(){
        NotificationCompat.Builder notifBuilder = new NotificationCompat.Builder(MainActivity.this)
                .setSmallIcon(R.drawable.ic_access_alarm_black_36dp)
                .setContentIntent(pendingIntent)
                .setLargeIcon(BitmapFactory.decodeResource(getResources()
                        , R.drawable.ic_access_alarm_black_36dp))
                .setContentTitle(getResources().getString(R.string.content_title))
                .setContentText(getResources().getString(R.string.content_text))
                .setSubText(getResources().getString(R.string.subtext))
                .setAutoCancel(true);

        NotificationManagerCompat notifManager = NotificationManagerCompat.from(getApplicationContext());
        notifManager.notify(NOTIF_ID, notifBuilder.build());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void showNotifOreo(){
        Notification.Builder notifBuilder = new Notification.Builder(MainActivity.this, ANDROID_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_access_alarm_black_36dp)
                .setContentIntent(pendingIntent)
                .setLargeIcon(BitmapFactory.decodeResource(getResources()
                        , R.drawable.ic_access_alarm_black_36dp))
                .setContentTitle(getResources().getString(R.string.content_title))
                .setContentText(getResources().getString(R.string.content_text))
                .setSubText(getResources().getString(R.string.subtext))
                .setAutoCancel(true);

        NotificationUtils utils = new NotificationUtils(MainActivity.this);
        utils.getManager().notify(NOTIF_ID, notifBuilder.build());
    }
}
