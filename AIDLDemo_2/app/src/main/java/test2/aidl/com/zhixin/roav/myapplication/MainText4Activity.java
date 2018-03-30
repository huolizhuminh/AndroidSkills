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
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import test1.aidl.com.zhixin.roav.myapplication.Book;
import test1.aidl.com.zhixin.roav.myapplication.BookInterfaces;
import test1.aidl.com.zhixin.roav.myapplication.OnBookChangeListener;

public class MainText4Activity extends AppCompatActivity {
    private static final String TAG = "MainText4Activity";

    private BookInterfaces bookInterfaces;
    private int index;
    private MyBookListener myBookListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Intent intent = new Intent();
        intent.setAction("com.zhixin.show3");
        Intent finalIntent = createExplicitFromImplicitIntent(getApplicationContext(), intent);
        MyServiceConnection myServiceConnection = new MyServiceConnection();

        bindService(finalIntent, myServiceConnection, BIND_AUTO_CREATE);
        index = 0;
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            final Book book = new Book();
                            book.ID = "client" + index;
                            book.name = index;
                            index++;
                            bookInterfaces.addBookList(book);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }

                }).start();


            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printBook();

            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myBookListener != null) {
                    return;
                }
                myBookListener = new MyBookListener();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            bookInterfaces.addBookChangeListener(myBookListener);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myBookListener == null) {
                    return;
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            bookInterfaces.deleteBookChangeListener(myBookListener);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        myBookListener = null;
                    }
                }).start();
            }
        });
    }

    private void printBook() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<Book> bookList = bookInterfaces.getBookList();
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int k = 0; k < bookList.size(); k++) {
                        Book book = bookList.get(k);
                        stringBuilder.append("book:")
                                .append("ID=")
                                .append(book.ID)
                                .append("name=")
                                .append(book.name)
                                .append("\n");

                    }
                    Log.d(TAG, stringBuilder.toString());

                } catch (Exception e) {

                }
            }
        }).start();
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


    class MyServiceConnection implements ServiceConnection {


        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            bookInterfaces = BookInterfaces.Stub.asInterface(service);


        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bookInterfaces = null;
        }
    }

    class MyBookListener extends OnBookChangeListener.Stub {

        @Override
        public void onBookAdd(Book book) throws RemoteException {
            Log.d(TAG, "new Book has added,name is:" + book.name + "id is:" + book.ID);
        }

        @Override
        public void onBookDelete(Book book) throws RemoteException {
            Log.d(TAG, "new Book has delete,name is:" + book.name + "id is:" + book.ID);
        }
    }

}
