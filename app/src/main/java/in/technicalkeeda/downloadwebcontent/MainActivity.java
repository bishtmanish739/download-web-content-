package in.technicalkeeda.downloadwebcontent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    protected class DownloadTask extends AsyncTask<String,Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            String result="";
            URL url;
            HttpsURLConnection urlConnection =null;
            try {
                url=new URL(strings[0]);
                urlConnection=(HttpsURLConnection)url.openConnection();
                InputStream inputStream= urlConnection.getInputStream();
                InputStreamReader reader=new InputStreamReader(inputStream);
                int data=reader.read();
                while (data!=-1){
                    char character=(char)data;
                    result+=character;
                    data=reader.read();

                }
                return  result;
            }
            catch (Exception e){

            }

          //   Log.i("url", strings[0]);

            return "DONE";
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DownloadTask Task=new DownloadTask();
        String result=null;
        try {
            result = Task.execute("https://www.technicalkeeda.in").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i("url",result);
        TextView textView;
        textView =findViewById(R.id.textview);
        textView.setText(result);



    }
}
