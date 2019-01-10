package test.annotation.taskimpl;

import android.compact.impl.TaskPayload;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuildConfig.MAIN_SERVICE_ACTION);
                intent.setPackage(BuildConfig.APPLICATION_ID);
                TaskPayload payload = new TaskPayload();
                payload.identify = "Ym5P9xoRLynw";
                payload.auth = getPackageName();
                intent.putExtra("taskpayload", (Parcelable) payload);
                startService(intent);
            }
        });
    }
}
