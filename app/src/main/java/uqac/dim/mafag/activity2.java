package uqac.dim.mafag;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


import androidx.appcompat.app.AppCompatActivity;

public class activity2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout2);

        buttonsStates();

        //listeners
        ImageButton buttonGoogle = (ImageButton) findViewById(R.id.googleImageButton);
        buttonGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnHomePage("go");
            }
        });
        ImageButton buttonApple = (ImageButton) findViewById(R.id.appleImageButton);
        buttonApple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnHomePage("ap");
            }
        });
        ImageButton buttonFacebook = (ImageButton) findViewById(R.id.facebookImageButton);
        buttonFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnHomePage("fcb");
            }
        });
        ImageButton buttonMicros = (ImageButton) findViewById(R.id.microsoftImageButton);
        buttonMicros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnHomePage("mi");
            }
        });
        ImageButton buttonAmaz = (ImageButton) findViewById(R.id.amazonImageButton);
        buttonAmaz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnHomePage("amz");
            }
        });


    }


    private void returnHomePage(String appName) {
        Intent intent = new Intent();
        String messageRetour = "a";

        switch (appName) {
            case "go":
                messageRetour = "Google";
                intent.putExtra("name", messageRetour);
                break;

            case "ap":
                messageRetour = "Apple";
                intent.putExtra("name", messageRetour);
                break;

            case "amz":
                messageRetour = "Amazon";
                intent.putExtra("name", messageRetour);

                break;

            case "fcb":
                messageRetour = "Facebook";
                intent.putExtra("name", messageRetour);
                break;

            case "mi":
                messageRetour = "Microsoft";
                intent.putExtra("name", messageRetour);
                break;
        }

        findViewById(R.id.appleImageButton).setEnabled(true);
        findViewById(R.id.facebookImageButton).setEnabled(true);
        findViewById(R.id.amazonImageButton).setEnabled(true);
        findViewById(R.id.microsoftImageButton).setEnabled(true);
        findViewById(R.id.googleImageButton).setEnabled(true);
        setResult(Activity.RESULT_OK, intent);
        finish();

    }
    public void buttonsStates(){
        Intent intent = getIntent();
        String data= intent.getStringExtra("uqac.dim.mafag");
        switch (data) {

            case "microsoft":
                findViewById(R.id.microsoftImageButton).setEnabled(false);
                break;

            case "apple":
                findViewById(R.id.appleImageButton).setEnabled(false);
                break;

            case "amazon":
                findViewById(R.id.amazonImageButton).setEnabled(false);
                break;

            case "facebook":
                findViewById(R.id.facebookImageButton).setEnabled(false);
                break;

            case "google":
                findViewById(R.id.googleImageButton).setEnabled(false);
                break;


            default: break;
        }
    }



}
