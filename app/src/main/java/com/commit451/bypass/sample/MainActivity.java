package com.commit451.bypass.sample;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.commit451.bypasspicassoimagegetter.BypassPicassoImageGetter;
import com.squareup.picasso.Picasso;

import in.uncod.android.bypass.Bypass;
import in.uncod.android.bypass.ImageSpanClickListener;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity {

    public interface GitHubService {
        @GET("Commit451/LabCoat/raw/master/README.md")
        Call<ResponseBody> getMarkdown();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ViewGroup root = (ViewGroup) findViewById(R.id.root);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gitlab.com/")
                .build();

        GitHubService service = retrofit.create(GitHubService.class);

        final TextView textView = (TextView) findViewById(R.id.text);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        service.getMarkdown().enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Bypass bypass = new Bypass(MainActivity.this);
                    bypass.setImageSpanClickListener(new ImageSpanClickListener() {
                        @Override
                        public void onImageClicked(View view, ImageSpan imageSpan, String url) {
                            Snackbar.make(root, "Image clicked with url: " + url, Snackbar.LENGTH_LONG)
                                    .show();
                        }
                    });
                    try {
                        String content = response.body().string();
                        textView.setText(bypass.markdownToSpannable(content,
                                new BypassPicassoImageGetter(textView, Picasso.with(MainActivity.this))));
                    } catch (Exception e) {
                        Snackbar.make(root, "Failed to load Markdown", Snackbar.LENGTH_INDEFINITE)
                                .show();
                    } finally {
                        response.body().close();
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
}
