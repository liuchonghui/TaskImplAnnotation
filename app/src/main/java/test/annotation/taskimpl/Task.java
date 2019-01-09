package test.annotation.taskimpl;

import android.compact.impl.TaskCallback;
import android.compact.impl.TaskImpl;
import android.compact.impl.TaskPayload;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

public class Task implements TaskImpl {

    public Task(Context context) {

    }

    @Override
    public void run(Context context, TaskPayload payload, TaskCallback callback) {
        Log.d("PPP", "json|" + new Gson().toJson(payload));
    }
}
