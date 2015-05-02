package com.mitchelllustig.spinmorepoi;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Mitch on 4/16/15.
 */
public class RandomizerActivity extends Activity {

    //We inject button because injection is cool, but we never actually use it, because we have the OnClick injection anyways.
    @InjectView(R.id.button)TextView button;

    Posts posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.randomizer_layout);
        ButterKnife.inject(this);

        posts = new Gson().fromJson(getIntent().getStringExtra("posts"), Posts.class);
    }

    //Recursive rehashing? Not all posts have a video, if we get a post that doesn't, retry!
    @OnClick(R.id.button)
    public void playRandomVideo(){
        int random = (int)(Math.random() * posts.count);
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(posts.posts.get(random).custom_fields.dp_video_url.get(0))));
        }catch(Exception e){
            playRandomVideo();
        }
    }

    //Here's my shitty attempt to simply make a button open up a url in a browser
    @OnClick(R.id.button2)
    public void openWebURL() {
        Intent browse = new Intent( Intent.ACTION_VIEW , Uri.parse( "http://www.spinmorepoi.com/" ) );
        startActivity( browse );
    }
}
