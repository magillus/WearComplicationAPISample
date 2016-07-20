package com.matsdevelopsolutions.wearcomplicationsample;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.support.wearable.complications.ComplicationData;
import android.support.wearable.complications.ComplicationManager;
import android.support.wearable.complications.ComplicationProviderService;
import android.support.wearable.complications.ComplicationText;
import android.support.wearable.complications.ProviderUpdateRequester;
import android.util.Log;

import java.util.Random;

public class MyDataService extends ComplicationProviderService {
    public static final String TAG = "ComplicationProvider";
    public static final String TAP_ACTION = "ACTION_MY_DATA_SERVICE_TAP";

    private Random random = new Random();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getAction().equals(TAP_ACTION)) {
            new ProviderUpdateRequester(getApplicationContext(), new ComponentName(getApplicationContext(), MyDataService.class)).requestUpdateAll();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onComplicationUpdate(int i, int i1, ComplicationManager complicationManager) {
        Log.i(TAG, String.format("complication update: %d %d", i, i1));
        String testText = String.format("r: %d", random.nextInt(999));

        ComplicationData data = new ComplicationData.Builder(ComplicationData.TYPE_SHORT_TEXT)
                .setTapAction(PendingIntent.getService(getApplicationContext(), 0, new Intent(TAP_ACTION), PendingIntent.FLAG_ONE_SHOT))
                .setShortText(ComplicationText.plainText(testText)).build();

        complicationManager.updateComplicationData(i, data);
    }
}