package cerebrik.com.calendarexample.alarm;

import android.content.Context;

public interface AlarmContract {
    interface Views {
        void onSetAlarm(Context context, long alarmTime, long currentTime, String id);
    }

    interface Presenters {
        void setAlarm(Context context, long alarmTime, String id);
    }
}
