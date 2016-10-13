package com.example.user.yuncsie;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.MediaController;

import com.example.user.yuncsie.ViewPager.godeye;

/**
 * Created by user on 2015/12/18.
 */
public class playmovie extends AppCompatActivity {
    private int[] moviedata = new int[]{R.raw.movie1_1, R.raw.movie1_2, R.raw.movie1_3, R.raw.movie2_1, R.raw.movie2_2, R.raw.movie2_3, R.raw.movie3_1, R.raw.movie3_2, R.raw.movie3_3, R.raw.movie4_1,
            R.raw.movie4_2, R.raw.movie4_3, R.raw.movie5_1, R.raw.movie5_2, R.raw.movie5_3 };
    private MyVideoView videoView;
    private Intent intent;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playmovie);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        bundle = getIntent().getExtras();
        intent = new Intent();
        videoView = (MyVideoView) this.findViewById(R.id.videoView);
        MediaController mc = new MediaController(this);
        videoView.setMediaController(mc);

        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + moviedata[bundle.getInt("ags")]));
        videoView.requestFocus();
        videoView.start();
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                intent.setClass(playmovie.this, godeye.class);
                playmovie.this.finish();
            }
        });

    }
}
