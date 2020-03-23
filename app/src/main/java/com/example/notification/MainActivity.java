package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public EditText editText = (EditText)findViewById(R.id.editText);
    Button button = (Button)findViewById(R.id.button);

    public static final String CHANNEL_ID = "channel";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    CharSequence name = getString(R.string.channel_name);

                    //String description = getString(R.string.channel_description);
                    String description = "Hello "+editText.getText()+" welcome to the MAD team!";

                    int importance = NotificationManager.IMPORTANCE_DEFAULT;
                    NotificationChannel channel = new NotificationChannel(CHANNEL_ID,name,importance);
                    channel.setDescription(description);

                    NotificationManager notificationManager = getSystemService(NotificationManager.class);
                    notificationManager.createNotificationChannel(channel);
                }


                Intent intent = new Intent(MainActivity.this,Register.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                PendingIntent pendingIntent= PendingIntent.getActivity(MainActivity.this,0,intent,0);

                NotificationCompat.Builder builder= new NotificationCompat.Builder(MainActivity.this,CHANNEL_ID).setSmallIcon(R.drawable.ic_launcher_background).setContentTitle("My notification").setContentText("Hello world!").setPriority(NotificationCompat.PRIORITY_DEFAULT).setContentIntent(pendingIntent).setAutoCancel(true);

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity.this);

                notificationManager.notify(0,builder.build());


            }
        });



    }
}
