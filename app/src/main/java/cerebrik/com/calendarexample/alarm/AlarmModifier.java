package cerebrik.com.calendarexample.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

public class AlarmModifier implements AlarmContract.Views {
    private static final String TAG = "AlarmModifier";
    private PendingIntent pendingIntent;
    AlarmManager alarmManager;
    private Context context;
    private long alarmTime;

    private  AlarmContract.Presenters presenters = new AlarmController(this);

    public void setAlarmOnTime(Context context,long alarmOnTime, String id) {
        this.context = context;
        Log.d(TAG, "SetAlarmOnTime");
        presenters.setAlarm(context, alarmOnTime, id);
    }

    @Override
    public void onSetAlarm(long alarmTime, long currentTime, String id) {
        Log.d(TAG, "onSetAlarm");
        if (currentTime <= alarmTime){
            Log.d(TAG, "Display notification will trigger....");

            // Set broadCast for notification
            Intent intent = new Intent(context, AlarmReceiver.class);
            //final long idValue = Integer.parseInt(id);
            pendingIntent = PendingIntent.getBroadcast(context,0,intent,PendingIntent.FLAG_ONE_SHOT);

            // Set alarm on exact time
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, alarmTime, pendingIntent);
            } else {
                alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, alarmTime, pendingIntent);
            }

        } else {
            Log.d(TAG, "current time " + currentTime + " > alarm time " + alarmTime);
        }

    }
}
