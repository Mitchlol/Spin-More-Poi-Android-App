package com.mitchelllustig.spinmorepoi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;
import retrofit.http.GET;

/**
 * Created by Mitch on 4/16/15.
 */
public class SplashActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://www.spinmorepoi.com/")
                .setClient(new OkClient(new OkHttpClient()))//Poi-ntless?
                .build();

        SpinMorePoiService service = restAdapter.create(SpinMorePoiService.class);
        service.getPosts(new Callback<Posts>() {
            @Override
            public void success(Posts posts, Response response) {
                if(posts.count < 1){
                    failure(null);
                }
                Intent intent = new Intent(getApplicationContext(), RandomizerActivity.class);
                intent.putExtra("posts", new Gson().toJson(posts));
                startActivity(intent);
                finish();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(), "Error loading posts. Please eat shit :-P", Toast.LENGTH_LONG).show();
                finish();
            }


        });
    }

    public interface SpinMorePoiService {
        @GET("/?json=1&count=-1")//-1 seems to get me all available posts, this is not in the docs.
        void getPosts(Callback<Posts> cb);
    }


}
