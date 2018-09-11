package com.example.android.basicsyncadapter;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

/**
 * Activity for holding EntryListFragment.
 */
public class EntryListActivity extends FragmentActivity {
    private static final String TAG = EntryListActivity.class.getSimpleName();
    public static final int SYNC_FREQUENCY = 60;

    public void createAlarm(Context context) {
        Log.d(TAG,"createAlarm");
        AlarmManager alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        alarmMgr.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() +
                        SYNC_FREQUENCY * 1000,SYNC_FREQUENCY * 1000,alarmIntent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_list);
        createAlarm(this);
    }
}
