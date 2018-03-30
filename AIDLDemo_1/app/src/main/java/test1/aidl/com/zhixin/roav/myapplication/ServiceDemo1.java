package test1.aidl.com.zhixin.roav.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * @author minhui.zhu
 *         Created by minhui.zhu on 2018/3/24.
 *         Copyright © 2017年 Oceanwing. All rights reserved.
 */

public class ServiceDemo1 extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyShow();
    }

    class MyShow extends ShowInterfaces.Stub {

        @Override
        public void showString(String stringShow) throws RemoteException {
            if (MainActivity.instance == null) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.instance, stringShow, Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public String getBookString() throws RemoteException {
            return "wo shi xx";
        }
    }
}
