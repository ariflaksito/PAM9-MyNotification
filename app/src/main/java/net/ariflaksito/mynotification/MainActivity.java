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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://dicoding.com"));
                PendingIntent pendingIntent = PendingIntent
                        .getActivity(MainActivity.this, 0, intent, 0);

                NotificationCompat.Builder notifBuilder = new NotificationCompat.Builder(MainActivity.this)
                        .setSmallIcon(R.drawable.ic_access_alarm_black_36dp)
                        .setContentIntent(pendingIntent)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources()
                                        , R.drawable.ic_access_alarm_black_36dp))
                        .setContentTitle(getResources().getString(R.string.content_title))
                        .setContentText(getResources().getString(R.string.content_text))
                        .setSubText(getResources().getString(R.string.subtext))
                        .setAutoCancel(true);

                NotificationManagerCompat ncm = NotificationManagerCompat.from(getApplicationContext());
                ncm.notify(NOTIF_ID, notifBuilder.build());
            }
        });

    }
}
