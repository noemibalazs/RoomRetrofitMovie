package com.example.android.roomretrofitmovie.room;

import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import android.os.Handler;


public class MovieExecutor {

    private final Executor diskIo;
    private final Executor networkIo;
    private final Executor mainThread;
    private static final Object LOCK = new Object();
    private static MovieExecutor sInstance;

    private MovieExecutor(Executor diskIo, Executor networkIo, Executor mainThread) {
        this.diskIo = diskIo;
        this.networkIo = networkIo;
        this.mainThread = mainThread;
    }

    public static MovieExecutor getsInstance(){
        if (sInstance == null){
            synchronized (LOCK){
                sInstance = new MovieExecutor(Executors.newSingleThreadExecutor(),
                        Executors.newFixedThreadPool(3),
                        new MovieThread());
            }
        }
        return sInstance;
    }

    public Executor getDiskIo(){
        return diskIo;
    }

    public Executor getNetworkIo() {
        return networkIo;
    }

    public Executor getMainThread() {
        return mainThread;
    }

    private static class MovieThread implements Executor{

        private Handler mainHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainHandler.post(command);

        }
    }
}
