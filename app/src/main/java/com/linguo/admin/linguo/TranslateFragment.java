package com.linguo.admin.linguo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class TranslateFragment extends AppCompatActivity implements View.OnClickListener {

private Button btn;
private Spinner spin;
private Spinner spin2;
private TextView badoo;
private EditText baduu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btn =  findViewById(R.id.buttonLogout);
        btn.setOnClickListener(this);
        btn.setEnabled(true);

         spin =  findViewById(R.id.networkSp);


        spin2 =  findViewById(R.id.networkSp1);


        baduu =  findViewById(R.id.editTextPhone);
        badoo =  findViewById(R.id.editTextPhone1);




    }

    @Override
    public void onClick(View v) {

      //  if (isOnline()) {
            sendAndRequestResponse();
       // Toast.makeText(getActivity(), "OnClickListener:" + "\ntextview:" + String.valueOf(textView.getSelectedItem()) + "\nspinner:" + String.valueOf(spinner.getSelectedItem()), Toast.LENGTH_SHORT).show();
           // badoo.setText(baduu.getText().toString());
          //  badoo.setText(String.valueOf(textView.getSelectedItem().toString()));
        //  }

       // }
     //   else {
          //  Toast.makeText(getActivity(), "OnClickListener:" + "\ntextview:" + String.valueOf(textView.getSelectedItem()) + "\nspinner:" + String.valueOf(spinner.getSelectedItem()), Toast.LENGTH_SHORT).show();
        //    badoo.setText(baduu.getText().toString());
        //    badoo.setText(String.valueOf(textView.getSelectedItem().toString()));
      //  }
    }





    private void sendAndRequestResponse() {

            spin.setOnItemSelectedListener(new CustomItemSelectedListener());


            spin2.setOnItemSelectedListener(new CustomItemSelectedListener());

            String texte = String.valueOf(spin.getSelectedItem().toString());
            String texty = String.valueOf(spin2.getSelectedItem().toString());
            String textu = baduu.getText().toString();

            String GET_URL = "https://www.linguoapis.com/api/v2/translate?text="+textu+"&src="+texty+"&dest="+texte;

            RequestQueue mRequestQueue = Volley.newRequestQueue(getApplicationContext());
            StringRequest mStringRequest = new StringRequest(Request.Method.GET, GET_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject ob = new JSONObject(response);
                        String thor = ob.getString("res");
                        badoo.setText(thor);
                      //  Intent intent = new Intent(getActivity(), result.class);
                     //   intent.putExtra("SCORE", score);
                     //   intent.putExtra("LIFE", life);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            mRequestQueue.add(mStringRequest);

        }


    }




