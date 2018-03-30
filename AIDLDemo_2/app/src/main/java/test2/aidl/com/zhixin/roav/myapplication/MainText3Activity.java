package test2.aidl.com.zhixin.roav.myapplication;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainText3Activity extends AppCompatActivity {
    private static final String TAG = "MainText3Activity";
    private Button button1;
    static Handler messageHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.d(TAG,"handleMessage");
            switch (msg.what) {
                case 0:
                    Log.i(TAG, "receive msg from server:" + msg.getData().getString("msg"));
                    break;
                default:
                    super.handleMessage(msg);
            }

        }
    };
    private final Messenger mMessenger = new Messenger(messageHandler);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Intent intent = new Intent();
        intent.setAction("com.zhixin.show2");
        Intent finalIntent = createExplicitFromImplicitIntent(getApplicationContext(), intent);
        MyServiceConnection myServiceConnection = new MyServiceConnection();

        bindService(finalIntent, myServiceConnection, BIND_AUTO_CREATE);
        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = Message.obtain(null, 1);
                Bundle data = new Bundle();
                data.putString("msg", "this is client");
                message.setData(data);
                message.replyTo = mMessenger;
                try {
                    serverMessenger.send(message);
                } catch (Exception e) {
                }
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    public static Intent createExplicitFromImplicitIntent(Context context, Intent implicitIntent) {
        // Retrieve all services that can match the given intent
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> resolveInfo = pm.queryIntentServices(implicitIntent, 0);

        // Make sure only one match was found
        if (resolveInfo == null || resolveInfo.size() != 1) {
            return null;
        }

        // Get component info and create ComponentName
        ResolveInfo serviceInfo = resolveInfo.get(0);
        String packageName = serviceInfo.serviceInfo.packageName;
        String className = serviceInfo.serviceInfo.name;
        ComponentName component = new ComponentName(packageName, className);

        // Create a new intent. Use the old one for extras and such reuse
        Intent explicitIntent = new Intent(implicitIntent);

        // Set the component to be explicit
        explicitIntent.setComponent(component);

        return explicitIntent;
    }

    private Messenger serverMessenger;

    class MyServiceConnection implements ServiceConnection {


        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            serverMessenger = new Messenger(service);
            Message message = Message.obtain(null, 0);
            Bundle data = new Bundle();
            data.putString("msgKey", "very good");
            message.setData(data);
            try {
                serverMessenger.send(message);
            } catch (Exception e) {

            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

}
