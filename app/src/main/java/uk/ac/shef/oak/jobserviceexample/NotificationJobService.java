package uk.ac.shef.oak.jobserviceexample;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class NotificationJobService extends JobService {

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.d("PERO", "se povika onStartJob");
        new RESTget().execute();
        return true;
    }


    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return true;
    }
}
