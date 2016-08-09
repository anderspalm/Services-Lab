package com.example.ander.serviceslab.service;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.InterruptedIOException;

/**
 * Created by ander on 8/8/2016.
 */
public class MusicService extends Service {

    private final static String TAG = "CustomService";
    static MediaPlayer mMediaPlayer = new MediaPlayer();

    HandlerThread mHandlerThread;
    Handler mHandler;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mHandlerThread = new HandlerThread("some filler");
        mHandlerThread.start();
        Looper looper = mHandlerThread.getLooper();

            mHandler = new Handler(looper) {
                @Override
                public void handleMessage(Message message) {
                    super.handleMessage(message);
                    String word = (String) message.obj;
                    Log.i(TAG, "handleMessage: " + word);
                    try {
                        String myUri = "http://www.stephaniequinn.com/Music/The%20Irish%20Washerwoman.mp3"; // initialize Uri here
                        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);


                        if (!mMediaPlayer.isPlaying()) {
                            mMediaPlayer.setDataSource(myUri);
                            mMediaPlayer.prepare();
                            mMediaPlayer.start();
//                            Toast.makeText(MusicService.this,"play button clicked", Toast.LENGTH_LONG).show();
                        }

                        Toast.makeText(MusicService.this, "Reached handleMessage method", Toast.LENGTH_LONG).show();

                        Log.i(TAG, "handleMessage: If this shows we have reached the final stage named " + word);
                    } catch (InterruptedIOException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(!mMediaPlayer.isPlaying()){
            mMediaPlayer.start();
            Toast.makeText(MusicService.this,"play button clicked", Toast.LENGTH_LONG).show();
        }
        else {
            mMediaPlayer.pause();
            Toast.makeText(MusicService.this,"play button clicked", Toast.LENGTH_LONG).show();
        }

        Message message = mHandler.obtainMessage();
        mHandler.sendMessage(message);
        return START_NOT_STICKY;
    }

    public static void pausePlayer(){
       mMediaPlayer.pause();
    }

    public static void stopPlayer(){
        mMediaPlayer.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
