package uk.ac.shef.oak.jobserviceexample;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.BreakIterator;

public class RESTget extends AsyncTask<String , Void, String> {
    private static final String NETWORK_PREFS = "NetworkSharedPrefs";
    private static final String CACHED_RESULT_KEY = "result";
    private WeakReference<Context> context;


    @Override
    //ovde e void ako ne prakame nisto a nie bidejki prakame ne treba da e void zatoa ne ni raboti - slajd 18 -DONE
    protected String doInBackground(String... strings) {
        Log.d("PERO", "se povika doInBackground");
        String resultString = NetworkUtils.getInfo();
        try {
            // Parse the json string into an object
            JSONArray jobs = new JSONArray(resultString);
            JSONObject job = jobs.getJSONObject(0);
            String jobType = job.getString("jobType");
            String hostt = job.getString("host");

            Log.i("PERO", "The job type is: " + jobType);


            //isparsiraj gi site delovi od podatocite - DONE
            //tuka moze da se pinga so toa so ke ja vleceme ipto od backendot - DONE

            Ping.handleJob(job, context.get());
        } catch (Exception e) {
            Log.i("PERO", "Something bad happened...");
        }
        return null;
    }

}
