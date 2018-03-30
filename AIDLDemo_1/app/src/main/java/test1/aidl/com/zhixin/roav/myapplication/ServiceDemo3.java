package test1.aidl.com.zhixin.roav.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * @author minhui.zhu
 *         Created by minhui.zhu on 2018/3/24.
 *         Copyright © 2017年 Oceanwing. All rights reserved.
 */

public class ServiceDemo3 extends Service {

    private static final String TAG = "ServiceDemo3";
    private MyBookImplement myBookImplement;
    ArrayList<Book> bookList = new ArrayList<>();
    RemoteCallbackList<OnBookChangeListener> listeners = new RemoteCallbackList<>();


    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(new Runnable() {

            @Override
            public void run() {
                for (int k = 0; k < 20; k++) {
                    Book book = new Book();
                    book.ID = "ID" + String.valueOf(k);
                    book.name = k;
                    bookList.add(book);
                    Log.d(TAG,"has addbook");
                    final int size = listeners.beginBroadcast();
                    Log.d(TAG,"size is "+size);
                    for(int z=0;z<size;z++){

                        OnBookChangeListener broadcastItem = listeners.getBroadcastItem(z);
                        if(broadcastItem!=null){
                            try {
                                broadcastItem.onBookAdd(book);
                            }catch (Exception e){

                            }
                            Log.d(TAG,"has notify one");
                        }

                    }
                    listeners.finishBroadcast();
                    Log.d(TAG,"start to sleep");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }
        }).start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        if (myBookImplement == null) {
            myBookImplement = new MyBookImplement();
        }
        return myBookImplement.asBinder();
    }

    class MyBookImplement extends BookInterfaces.Stub {

        @Override
        public void addBookList(Book book) throws RemoteException {
            bookList.add(book);
        }

        @Override
        public List<Book> getBookList() throws RemoteException {
            return bookList;
        }

        @Override
        public void addBookChangeListener(OnBookChangeListener listener) throws RemoteException {
            listeners.register(listener);
        }

        @Override
        public void deleteBookChangeListener(OnBookChangeListener listener) throws RemoteException {
            listeners.unregister(listener);
        }
    }


}
