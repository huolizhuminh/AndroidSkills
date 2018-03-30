package test1.aidl.com.zhixin.roav.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * @author minhui.zhu
 *         Created by minhui.zhu on 2018/3/24.
 *         Copyright © 2017年 Oceanwing. All rights reserved.
 */

public class ServiceDemo2 extends Service {

    private static final String TAG = "ServiceDemo2";
    static Handler messageHandler = new Handler() {

        private Messenger clientMessager;

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Log.i(TAG, "receive msg from Client:" + msg.getData().getString("msgKey"));
                    break;
                case 1:
                    Log.i(TAG,"receive messenger for Client "+msg.getData().getString("msg"));
                    clientMessager = msg.replyTo;
                    Message replyMessage = Message.obtain(null, 0);
                    Bundle bundle = new Bundle();
                    bundle.putString("msg","server has receive client message");
                    replyMessage.setData(bundle);
                    try {
                        clientMessager.send(replyMessage);
                    }catch (Exception e){
                        Log.d(TAG,e.getMessage());
                    }
                    break;
                default:
                    super.handleMessage(msg);
            }

        }
    };
    private final Messenger mMessenger = new Messenger(messageHandler);

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }


}
