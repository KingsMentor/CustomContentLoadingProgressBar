package xyz.belvi.contentprogressbardemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ContentLoadingProgressBar loadingProgressBar = (ContentLoadingProgressBar) findViewById(R.id.loading_bar);
        final CustomContentLoadingProgressBar customProgressBar = (CustomContentLoadingProgressBar) findViewById(R.id.custom_loading_bar);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.normal_bar);


        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // house keeping - collect input from editText
                String duration_text = ((EditText) findViewById(R.id.duration)).getText().toString();
                String minTime_text = ((EditText) findViewById(R.id.mindelayTime)).getText().toString();
                String min_shown_time_text = ((EditText) findViewById(R.id.minShowTime)).getText().toString();
                int duration = Integer.parseInt(duration_text.trim().isEmpty() ? "0" : duration_text);
                int min_delay = Integer.parseInt(minTime_text.trim().isEmpty() ? "0" : minTime_text);
                int min_shown = Integer.parseInt(min_shown_time_text.trim().isEmpty() ? "0" : min_shown_time_text);


                // change values of MIN_SHOW_TIME and MIN_DELAY for CustomContentLoadingProgressBar
                customProgressBar.setMinDelay(min_delay);
                customProgressBar.setMinShowTime(min_shown);

                progressBar.setVisibility(View.VISIBLE);
                loadingProgressBar.show();
                customProgressBar.show();
                // delay for specified duration before hiding the progress bar again
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                        loadingProgressBar.hide();
                        customProgressBar.hide();
                    }
                }, duration);


            }
        });
    }
}
