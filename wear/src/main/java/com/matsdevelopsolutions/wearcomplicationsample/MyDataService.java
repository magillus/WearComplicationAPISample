package com.matsdevelopsolutions.wearcomplicationsample;

import android.support.wearable.complications.ComplicationData;
import android.support.wearable.complications.ComplicationManager;
import android.support.wearable.complications.ComplicationProviderService;
import android.support.wearable.complications.ComplicationText;
import android.util.Log;

import java.util.Random;

public class MyDataService extends ComplicationProviderService {
    public static final String TAG = "ComplicationProvider";

    private Random random = new Random();

    @Override
    public void onComplicationUpdate(int i, int i1, ComplicationManager complicationManager) {
        Log.i(TAG, String.format("complication update: %d %d", i, i1));
        String testText = String.format("r: %d", random.nextInt(999));
        ComplicationData data = new ComplicationData.Builder(ComplicationData.TYPE_SHORT_TEXT)
                .setShortText(ComplicationText.plainText(testText)).build();
        complicationManager.updateComplicationData(i, data);
    }
}