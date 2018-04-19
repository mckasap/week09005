package org.kasapbasi.week09005;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
     TextView tv;
     Button btn;

    private class myAsycTask  extends AsyncTask<TextView,Void,TextView>{


        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this,
                    " Data is downloading",Toast.LENGTH_LONG).show();

        }

        @Override
        protected TextView doInBackground(TextView... textViews) {
            HttpHandler myHandler= new HttpHandler();
            String url = "http://192.168.224.127:8888/AndroidTest/myecho.php";
            String jsonStr = myHandler.makeServiceCall(url);
            textViews[0].setText(jsonStr);
            return null;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("MyJSon", WriteJson("Mustafa Cem","Kasapbaşı","BizeHeryerTrabzon"));




        tv=(TextView)findViewById(R.id.textView);

        new myAsycTask().execute(tv);
        btn=(Button)findViewById(R.id.btnTest);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }




public String WriteJson(String name, String Lastname, String Pass){

    JSONObject obje= new JSONObject();
    try{
        obje.put("name",name);
        obje.put("lastname",Lastname);
        obje.put(  "pass",Pass);

    }
    catch (JSONException e){

        return e.getMessage().toString();
    }
return  obje.toString();
}

}

