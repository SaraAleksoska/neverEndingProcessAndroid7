package uk.ac.shef.oak.jobserviceexample;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class RESTget extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... voids) {
        Log.d("PERO", "se povika doInBackground");
        String resultString = NetworkUtils.getInfo();
        try {
            // Parse the json string into an object
            JSONArray jobs = new JSONArray(resultString);
            JSONObject job = jobs.getJSONObject(0);
            String jobType = job.getString("jobType");
            Log.i("PERO", "The job type is: " + jobType);
        } catch (Exception e) {
            Log.i("PERO", "Something bad happened...");
        }
        return null;
    }
}
