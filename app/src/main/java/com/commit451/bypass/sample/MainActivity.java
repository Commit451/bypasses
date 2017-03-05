package com.commit451.bypass.sample;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

import in.uncod.android.bypass.Bypass;
import in.uncod.android.bypass.ImageSpanClickListener;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity implements ImageSpanClickListener {

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

        service.getMarkdown().enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Bypass bypass = new Bypass(MainActivity.this, MainActivity.this);
                    try {
                        textView.setText(bypass.markdownToSpannable(response.body().string()));
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "Failed to load Markdown", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to load Markdown", Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    @Override
    public void onImageClicked(Drawable drawable) {
        //Respond to clicks
    }
}
