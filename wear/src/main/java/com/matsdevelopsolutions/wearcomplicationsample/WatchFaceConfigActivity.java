package com.matsdevelopsolutions.wearcomplicationsample;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.wearable.complications.ComplicationData;
import android.support.wearable.complications.ProviderChooserIntent;

public class WatchFaceConfigActivity extends Activity {

    public static final String ACTION_SELECT_PROVIDER = "com.matsdevelopsolutions.watchmode.ACTION_SELECT_SAMPLE_PROVIDER";
    public static final String ARG_COMPLICATION_ID = "ARG_COMPLICATION_ID";
    public static final String ARG_COMPONENT_NAME = "ARG_COMPONENT_NAME";
    public static final int SELECT_PROVIDE_REQUEST_CODE = 2343;

    public static Intent createIntent(int complicationId, @NonNull ComponentName watchfaceComponentName) {
        Intent intent = new Intent(ACTION_SELECT_PROVIDER);
        intent.putExtra(ARG_COMPLICATION_ID, complicationId);
        intent.putExtra(ARG_COMPONENT_NAME, watchfaceComponentName);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_face_config);

        int complicationId = getIntent().getIntExtra(ARG_COMPLICATION_ID, -1);
        ComponentName watchFaceComponentName = getIntent().getParcelableExtra(ARG_COMPONENT_NAME);
        if (complicationId < 0 || watchFaceComponentName == null) {
            // log bad intent argument
            // todo validate component name
            finish();
        } else {

            Intent intent = ProviderChooserIntent.createProviderChooserIntent(watchFaceComponentName, complicationId,
                    ComplicationData.TYPE_RANGED_VALUE, ComplicationData.TYPE_LONG_TEXT, ComplicationData.TYPE_SHORT_TEXT, ComplicationData.TYPE_ICON);
            startActivityForResult(intent, SELECT_PROVIDE_REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SELECT_PROVIDE_REQUEST_CODE) {
            finish();
        }
    }
}
