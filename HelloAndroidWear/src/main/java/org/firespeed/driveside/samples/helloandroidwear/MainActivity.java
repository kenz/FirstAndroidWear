package org.firespeed.driveside.samples.helloandroidwear;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.preview.support.wearable.notifications.*;
import android.preview.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity implements View.OnClickListener{
    private Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button)findViewById(R.id.button);
        mButton.setOnClickListener(this);
    }

    @Override
    protected void onNewIntent(Intent i){
        if(i!=null){
            i.getStringExtra("hoge");
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(View v) {
        if(v.equals(mButton)) {
// NotificationをStackするときなどに使用するID
            int notificationId = 1;
// Notificationに表示するタイトルと文字
            String eventTitle = "Hello Wear";
            String eventText = "This is a first application.";
// タップされた時にActivityに渡すIntentをPendingIntentとして作成
            String extraName = "extraName";
            String extraString = "Tap AndroidWear";
            Intent viewIntent = new Intent(this, MainActivity.class);
            viewIntent.putExtra(extraName, extraString);
            PendingIntent viewPendingIntent = PendingIntent.getActivity(this, 0, viewIntent, 0);
// NotificationCompat.Builderを使ってNotificationを作る
            NotificationCompat.Builder notificationBuilder =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(R.drawable.ic_stat_name)
                            .setContentTitle(eventTitle)
                            .setContentText(eventText)
                            .setContentIntent(viewPendingIntent);
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(notificationId, notificationBuilder.build());
        }
    }
}
