package com.commit451.bypass.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.ResponseBody;

import in.uncod.android.bypass.Bypass;
import retrofit.Call;
import retrofit.Retrofit;
import retrofit.http.GET;

public class MainActivity extends AppCompatActivity {


    public interface GitHubService {
        @GET("/Uncodin/bypass/master/README.md")
        Call<ResponseBody> getMarkdown();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com")
                .build();

        GitHubService service = retrofit.create(GitHubService.class);

        final TextView textView = (TextView) findViewById(R.id.text);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        service.getMarkdown().enqueue(new retrofit.Callback<ResponseBody>() {
            @Override
            public void onResponse(retrofit.Response<ResponseBody> response) {
                if (response.isSuccess()) {
                    Bypass bypass = new Bypass(MainActivity.this);
                    try {
                        textView.setText(bypass.markdownToSpannable(response.body().string()));
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "Failed to load Markdown", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to load Markdown", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
