package test.apts.lroute;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

//import tools.android.taskimpl.annotation.CreateService;

//@CreateService(path = "Main")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                tools.android.taskimpl.
            }
        });
    }
}
