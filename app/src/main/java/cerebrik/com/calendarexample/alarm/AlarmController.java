package cerebrik.com.calendarexample.alarm;

import android.content.Context;
import android.util.Log;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.Calendar;

public class AlarmController extends BaseController<AlarmContract.Views> implements AlarmContract.Presenters {
    public static final String TAG = "AlarmController";

    protected AlarmController(AlarmContract.Views views) {
        super(views);
    }

    @Override
    public void setAlarm(Context context, long alarmTimeInMillis, String id) {
        int year, month, dayOfMonth, hourOfDay, minute, second;

        /*#####################################################################*/

        // Converting Asia/Kolkata server time to human readable time

        DateTime dateTimeFromServer = new DateTime(alarmTimeInMillis, DateTimeZone.forID("Asia/Kolkata"));
        year = dateTimeFromServer.getYear();
        month = dateTimeFromServer.getMonthOfYear() - 1;
        dayOfMonth = dateTimeFromServer.getDayOfMonth();
        hourOfDay = dateTimeFromServer.getHourOfDay();
        minute = dateTimeFromServer.getMinuteOfHour();
        second = dateTimeFromServer.getSecondOfMinute();
        String humanReadableServerTime = dayOfMonth + "/" + dateTimeFromServer.getMonthOfYear() + "/" + year + ", " + hourOfDay + ":" + minute + ":" + second;
        Log.d(TAG, "Server time => " + humanReadableServerTime);

        // Setting the calendar with the server alarm time
        Calendar calendarServer = Calendar.getInstance();
        calendarServer.set(year, month, dayOfMonth, hourOfDay, minute, second);
        long alarmTime = calendarServer.getTimeInMillis();

        /*#####################################################################*/

        // Getting current time in UTC and converting to Asia/Kolkata time to human readable time

        DateTime dateTime = DateTime.now(DateTimeZone.UTC).toDateTime(DateTimeZone.forID("Asia/Kolkata"));
        year = dateTime.getYear();
        month = dateTime.getMonthOfYear() - 1;
        dayOfMonth = dateTime.getDayOfMonth();
        hourOfDay = dateTime.getHourOfDay();
        minute = dateTime.getMinuteOfHour();
        second = dateTime.getSecondOfMinute();
        String humanReadableCurrentTime = dayOfMonth + "/" + dateTime.getMonthOfYear() + "/" + year + ", " + hourOfDay + ":" + minute + ":" + second;

        Log.d(TAG, "Current time => " + humanReadableCurrentTime);

        // Setting the calendar with the current time
        Calendar calendarCurrent = Calendar.getInstance();
        calendarCurrent.set(year, month, dayOfMonth, hourOfDay, minute, second);
        long currentTime = calendarCurrent.getTimeInMillis();

        view.onSetAlarm(context, alarmTime, currentTime, id);
    }
}
