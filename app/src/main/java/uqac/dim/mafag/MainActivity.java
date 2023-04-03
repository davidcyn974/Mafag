package uqac.dim.mafag;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Icon;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //mafag cst
    static final String MICROSOFT_CODE = "Microsoft";
    static final String GOOGLE_CODE= "Google";
    static final String APPLE_CODE = "Apple";
    static final String AMAZON_CODE = "Amazon";
    static final String FACEBOOK_CODE = "Facebook";
    //to send to Activ2
    public final static String EXTRA_MESSAGE = "uqac.dim.mafag";

    //notif
    private static final int ID_NOTIFICATION = 1234;
    private static final String CHANNEL_ID = "id_257";
    private static final String CHANNEL_NAME = "channel_257";
    private static final String CHANNEL_DESCRIPTION = "description_257";

    private NotificationManager nm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        createNotificationChannel();

        //merci android studio pour les suggestions de lambda :)
        Button butonURL = (Button) findViewById(R.id.buttonURL);
        butonURL.setOnClickListener(view -> openURL());


        Button butonChoix= (Button) findViewById(R.id.choix);
        butonChoix.setOnClickListener(view -> ouvrirActivite());

        Button boutonNotif = (Button) findViewById(R.id.buttonNotif);
        boutonNotif.setOnClickListener(this::creerNotification);

        if (savedInstanceState != null) {
            String message = savedInstanceState.getString("message");
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode==RESULT_OK){
            TextView mafag = (TextView) findViewById(R.id.mafagUnit);
            TextView url = (TextView)findViewById(R.id.textURL);

            String varaiableRetour = data.getStringExtra("name");

            switch (varaiableRetour){
                case MICROSOFT_CODE:
                    mafag.setText("microsoft");
                    url.setText("https://www.microsoft.com/fr-fr/");

                    break;
                case GOOGLE_CODE:
                    mafag.setText("google");
                    url.setText("https://www.google.fr/");

                    break;

                case AMAZON_CODE:
                    mafag.setText("amazon");
                    url.setText("https://www.amazon.com/");
                    break;

                case APPLE_CODE:
                    mafag.setText("apple");
                    url.setText("https://www.apple.com/fr/");

                    break;

                case FACEBOOK_CODE:
                    mafag.setText("facebook");

                    url.setText("https://www.facebook.com/");
                    break;
            }

        }

    }

    public void ouvrirActivite(){

        Intent intent = new Intent(MainActivity.this, activity2.class);
        TextView textView = (TextView)findViewById(R.id.mafagUnit);
        String currentAppName = textView.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, currentAppName);
        startActivityForResult(intent, 12);

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        TextView textView1 = (TextView)findViewById(R.id.textURL);
        String abc = textView1.getText().toString();
        outState.putString("message1",abc);

        TextView textView2 = (TextView)findViewById(R.id.mafagUnit);
        String abcd = textView2.getText().toString();
        outState.putString("message2",abcd);


    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //Toast.makeText(getApplicationContext(), "onRestoreInstanceState", Toast.LENGTH_SHORT).show();

        TextView textView1 = (TextView)findViewById(R.id.textURL);
        String abc = savedInstanceState.getString("message1");
        textView1.setText(abc);

        TextView textView2 = (TextView)findViewById(R.id.mafagUnit);
        String abcd = savedInstanceState.getString("message2");
        textView2.setText(abcd);
    }


    public void openURL(){
        TextView textView = (TextView) findViewById(R.id.textURL);
        String urll = textView.getText().toString();
        switch (urll) {
            case "https://www.apple.com/fr/":
                Uri apple = Uri.parse("https://www.apple.com/fr/");
                Intent webIntentAp = new Intent(Intent.ACTION_VIEW, apple);
                startActivity(webIntentAp);
                break;
            case "https://www.google.fr/":
                Uri google = Uri.parse("https://www.google.fr/");
                Intent webIntentGo = new Intent(Intent.ACTION_VIEW, google);
                startActivity(webIntentGo);
                break;
            case "https://www.facebook.com/":
                Uri facebook = Uri.parse("https://www.facebook.com/");
                Intent webIntentFa = new Intent(Intent.ACTION_VIEW, facebook);
                startActivity(webIntentFa);
                break;
            case "https://www.amazon.com/":
                Uri amazon = Uri.parse("https://www.amazon.com/");
                Intent webIntentAz = new Intent(Intent.ACTION_VIEW, amazon);
                startActivity(webIntentAz);
                break;
            case "https://www.microsoft.com/fr-fr/":
                Uri microsoft = Uri.parse("https://www.microsoft.com/fr-fr/");
                Intent webIntentMi = new Intent(Intent.ACTION_VIEW, microsoft);
                startActivity(webIntentMi);

        }
    }
    private void createNotificationChannel() {

        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);
        channel.setDescription(CHANNEL_DESCRIPTION);

        nm = getSystemService(NotificationManager.class);
        nm.createNotificationChannel(channel);
    }

    public void creerNotification(View view) {

        TextView textUrl = (TextView) findViewById(R.id.textURL);
        Uri realUrl = Uri.parse(textUrl.getText().toString());

        Intent webIntent = new Intent(Intent.ACTION_VIEW, realUrl);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, webIntent, 0);


        android.app.Notification.Action action1 = new android.app.Notification.Action.Builder(
                Icon.createWithResource(this, R.mipmap.ic_launcher_round),
                "open",
                pIntent).build();

        TextView textView2 = (TextView) findViewById(R.id.mafagUnit);
        String name = textView2.getText().toString();

        android.app.Notification n  = new android.app.Notification.Builder(this, CHANNEL_ID)
                .setContentTitle(name)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pIntent)
                .setAutoCancel(true)
                .setFullScreenIntent(pIntent, true)
                .addAction(action1)

                .build();
        try{
            nm.notify(ID_NOTIFICATION, n);
        }
        catch(Exception e){
            Log.i("DICJ", e.getMessage(), e);
        }
    }

}