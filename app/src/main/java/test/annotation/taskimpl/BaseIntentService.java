package test.annotation.taskimpl;

import android.app.IntentService;
import android.compact.impl.TaskCallback;
import android.compact.impl.TaskImpl;
import android.compact.impl.TaskPayload;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.gson.Gson;

public class BaseIntentService extends IntentService {

    public static String TAG = "";

    public BaseIntentService() {
        this("");
    }

    public BaseIntentService(String name) {
        super(name);
    }

    protected TaskImpl createTask() {
        return new test.annotation.taskimpl.Task(getApplicationContext());
    }

    @Override
    protected final void onHandleIntent(Intent intent) {
        Bundle extra = intent.getExtras();
        if (extra == null) {
            return;
        }
        TaskPayload payload = null;
        Object obj = extra.get("taskpayload");
        if (obj instanceof TaskPayload) {
            payload = (TaskPayload) obj;
        }
        if (payload == null) {
            return;
        }
        if (payload.identify == null || payload.auth == null) {
            return;
        }

        createTask().run(getApplicationContext(), payload, new TaskCallback() {
            @Override
            public void onResult(TaskPayload taskPayload) {
                Bundle extras = new Bundle();
                extras.putString("action", "setTaskPayload");
                extras.putString("extra_json", new Gson().toJson(taskPayload));
                Uri uri = Uri.parse("content://" + taskPayload.auth);
                try {
                    getApplicationContext().getContentResolver().call(uri, "sendMessagesToHost", null, extras);
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }
        });
    }
}
