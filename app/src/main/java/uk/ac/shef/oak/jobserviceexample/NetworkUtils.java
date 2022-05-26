package uk.ac.shef.oak.jobserviceexample;


import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class NetworkUtils {

    private static final String BASE_URL = "http://192.168.100.6:5000/getjobs/hardware";
    private static final String POST_PING = "http://192.168.100.6:5000/postResults";


    static String getInfo() {
        Log.d("PERO", "se povika getInfo");
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String JSONString = null;

        try {
            Uri builtURI = Uri.parse(BASE_URL).buildUpon().build();
            URL requestURL = new URL(builtURI.toString());
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }

            if (builder.length() == 0) {
                return null;
            }

            JSONString = builder.toString();

        } catch (IOException e) {
            Log.i("PERO", "nesto bidna ahahha");
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //Log.d("PERO", JSONString);
        //Log.i("PERO", "nesto bidna po vtor pat");
        return JSONString;
    }

    static Void postInfo(String bodyString) {
        Log.i(Globals.LOG_TAG, "postInfo()");
        HttpURLConnection urlConnection = null;

        try {
            Uri builtURI = Uri.parse(POST_PING).buildUpon().build();
            URL requestURL = new URL(builtURI.toString());
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json; utf-8");
            urlConnection.setDoOutput(true);

            // Write to connection
            try (OutputStream os = urlConnection.getOutputStream()) {
                byte[] input = bodyString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = urlConnection.getResponseCode();

            Log.i(Globals.LOG_TAG, "RESPONSE CODE:");
            Log.i(Globals.LOG_TAG, String.valueOf(responseCode));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
