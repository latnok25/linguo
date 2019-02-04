package com.linguo.admin.linguo;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Locale;

public class ImportFragment extends AppCompatActivity implements TextToSpeech.OnInitListener {
    private TextToSpeech tts;
    private static final String TAG = "TextToSpeechDemo";
    private static final int MY_DATA_CHECK_CODE = 1234;
    private MediaPlayer media;
    private Button btn;
    private EditText txt;
    private Spinner spin, spin2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone);



        btn = findViewById(R.id.buttonLogout);
        btn.setEnabled(false);

        txt =  findViewById(R.id.editTextPhone);


         spin =  findViewById(R.id.networkSp1);


         spin2 =  findViewById(R.id.networkSp);


        // Fire off an intent to check if a TTS engine is installed
        Intent checkIntent = new Intent();
        checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == MY_DATA_CHECK_CODE)
        {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS)
            {
                // success, create the TTS instance
                tts = new TextToSpeech(this, this);
                tts.setLanguage(Locale.US);
                btn.setEnabled(true);

            }
            else
            {
                // missing data, install it
                Intent installIntent = new Intent();
                installIntent.setAction(
                        TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installIntent);
            }
        }
    }

    private void speakText(String text){
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }


    @Override
    public void onDestroy(){

        if (tts != null){
            tts.stop();
            tts.shutdown();
        }

        super.onDestroy();
    }
    public void onClick(View v) {
        spin.setOnItemSelectedListener(new CustomItemSelectedListener());

        spin2.setOnItemSelectedListener(new CustomItemSelectedListener());


        String texte = String.valueOf(spin.getSelectedItem().toString());
        String texty = String.valueOf(spin2.getSelectedItem().toString());
        String textu = txt.getText().toString();

        String GET_URL = "https://old.linguoapis.com/api/v1/data?text="+textu+"&lang="+texty+"&gender="+texte;

        try {
            playAudio(GET_URL);
        } catch (Exception e) {
            e.printStackTrace();
        }


       // String text = txt.getText().toString();
       // tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }



    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA ||
                    result == TextToSpeech.LANG_NOT_SUPPORTED) {

            } else {
                btn.setEnabled(true);

            }
        } else {

            Log.e(TAG, "Could not initialize TextToSpeech.");
        }

    }

    private void playAudio(String url) throws Exception {



        String texte = String.valueOf(spin.getSelectedItem().toString());
        String texty = String.valueOf(spin2.getSelectedItem().toString());
        String textu = txt.getText().toString();

        String GET_URL = "https://old.linguoapis.com/api/v1/data?text="+textu+"&lang="+texty+"&gender="+texte;
        media = new MediaPlayer();
        media.setDataSource(GET_URL);
        media.prepare();
        media.start();
    }


}


