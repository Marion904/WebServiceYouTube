package com.example.wilder.webserviceyoutube;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class MainActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private YouTubePlayerView youTubeView;
    private static final int RECOVERY_REQUEST = 1;
    private MyPlayBackEventListener myPlayBackEventListener;
    private MyPlayerStateChangeListener myPlayerStateChangeListener;
    private YouTubePlayer player;
    Button jumpTo;
    EditText secondsRequest;
    int skipToSec;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        jumpTo = (Button) findViewById(R.id.seek_to_button);
        secondsRequest =(EditText) findViewById(R.id.seek_to_text);
        jumpTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(secondsRequest.getText().toString() ==null){
                    showMessage(getString(R.string.where_to_sec));
                } else{
                    skipToSec = Integer.valueOf(secondsRequest.getText().toString());
                    player.seekToMillis(skipToSec*1000);
                }
            }
        });

        youTubeView.initialize(Config.YOUTUBE_API_KEY, this);
        myPlayBackEventListener = new MyPlayBackEventListener();
        myPlayerStateChangeListener = new MyPlayerStateChangeListener();

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        youTubePlayer.setPlaybackEventListener(myPlayBackEventListener);
        youTubePlayer.setPlayerStateChangeListener(myPlayerStateChangeListener);
        this.player = youTubePlayer;

        if(!wasRestored){
            // Plays https://www.youtube.com/watch?v=fhWaJi1Hsfo
            youTubePlayer.cueVideo("fhWaJi1Hsfo");
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_REQUEST).show();
        } else {
            String error = String.format(getString(R.string.player_error), errorReason.toString());
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==RECOVERY_REQUEST){
            getYouTubePlayerProvider().initialize(Config.YOUTUBE_API_KEY,this);
        }
    }

    protected YouTubePlayer.Provider getYouTubePlayerProvider(){
        return youTubeView;
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private final class MyPlayBackEventListener implements YouTubePlayer.PlaybackEventListener{

        @Override
        public void onPlaying() {
            showMessage(getString(R.string.playing));
        }

        @Override
        public void onPaused() {
            showMessage(getString(R.string.pause));
        }

        @Override
        public void onStopped() {
            // Called when playback stops for a reason other than being paused.
            showMessage(getString(R.string.stop));
        }

        @Override
        public void onBuffering(boolean b) {
            // Called when buffering starts or ends.
        }

        @Override
        public void onSeekTo(int i) {
            // Called when a jump in playback position occurs, either
            // due to user scrubbing or call to seekRelativeMillis() or seekToMillis()
        }
    }

    private final class MyPlayerStateChangeListener implements YouTubePlayer.PlayerStateChangeListener{

        @Override
        public void onLoading() {
            showMessage(getString(R.string.loading));
        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        public void onAdStarted() {

        }

        @Override
        public void onVideoStarted() {

        }

        @Override
        public void onVideoEnded() {

        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {

        }
    }
}
