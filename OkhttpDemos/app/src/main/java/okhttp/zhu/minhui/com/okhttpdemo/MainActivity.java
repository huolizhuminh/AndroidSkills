package okhttp.zhu.minhui.com.okhttpdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Movie;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.ByteString;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView result;
    private static final String OKHTTP_RESOURCE_DIR = "okhttp" + File.separator + "src"
            + File.separator + "main" + File.separator + "resources" + File.separator
            + "okhttp3" + File.separator + "internal" + File.separator + "publicsuffix";

    private static final ByteString EXCEPTION_RULE_MARKER = ByteString.encodeUtf8("!");
    private static final String WILDCARD_CHAR = "*";
    private Handler handler;
    private ConnectivityManager mConnectivityManager;
    private GifView gif1;
    private String url1 = "https://upload-images.jianshu.io/upload_images/3790386-2b34ed487398b0c1.gif?imageMogr2/auto-orient/strip%7CimageView2/2/w/630";
    private String url2 = "https://upload-images.jianshu.io/upload_images/3790386-621860129e020351?imageMogr2/auto-orient/strip%7CimageView2/2/w/480";
    private String url3 = "https://upload-images.jianshu.io/upload_images/3790386-621860129e020351?imageMogr2/auto-orient/strip%7CimageView2/2/w/480";
    private String url4 = "https://upload-images.jianshu.io/upload_images/3790386-bd1b53f4101feb26.gif?imageMogr2/auto-orient/strip%7CimageView2/2/w/700";
    private String url5 = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2813434517,83142011&fm=200&gp=0.jpg";
    private String url6 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1526965751109&di=113508857e0782a856d0a3e1acc2437d&imgtype=0&src=http%3A%2F%2Fold.bz55.com%2Fuploads%2Fallimg%2F150210%2F139-150210134411-50.jpg";
    private String url7 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1526965751109&di=f58f422d13eb72cfdf5124ed3a1be15c&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Ff2deb48f8c5494ee4f79f13a27f5e0fe99257ebe.jpg";
    private String url8 = "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3008776979,632905106&fm=27&gp=0.jpg";
    private String url9 = "https://img10.360buyimg.com/n1/s450x450_jfs/t11770/18/1814837705/153966/6c19aa0d/5a094722N89aec14c.jpg";
    private String url10 = "https://img30.360buyimg.com/sku/jfs/t4438/156/441403021/212754/d4303294/58cf8dedN95cf2fe2.jpg";
    private String url11 = "https://d2211byn0pk9fi.cloudfront.net/spree/activities/66706/image/large/首页banner.jpg?1521106413";
    private String url12 = "https://d2211byn0pk9fi.cloudfront.net/spree/activities/38470/image/large/Anker-PowerHouse-0930.jpg?1506762039";

    private String url13 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1526968646740&di=e41c207adbb3fb4659647ac77412c556&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fe7cd7b899e510fb3f14e9c88d333c895d1430c5e.jpg";
    private String url14 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1526968723548&di=558e84557323cc56ddde39d6893d8d95&imgtype=jpg&src=http%3A%2F%2Fimg3.imgtn.bdimg.com%2Fit%2Fu%3D3853789478%2C2794472228%26fm%3D214%26gp%3D0.jpg";
    private String url15 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1526968646740&di=b2b1d29d69e3df19487edf41af11a54a&imgtype=0&src=http%3A%2F%2Fpic30.photophoto.cn%2F20140218%2F0020033044690071_b.jpg";
    private String url16 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1526968646740&di=3d8eb6f8f830a6ee5bc21843536bd525&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dshijue1%252C0%252C0%252C294%252C40%2Fsign%3Dc70427ba06f431ada8df4b7a235fc6da%2Fcaef76094b36acaf5b6e54d476d98d1001e99c08.jpg";
    private String url17 = "https://upload-images.jianshu.io/upload_images/4834364-9d1ff6f2339dfc7e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/700";
    private String url18 = "https://upload-images.jianshu.io/upload_images/4834364-c540fac04e3a3f1d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/700";
    private String url19 = "https://upload-images.jianshu.io/upload_images/1727605-3d8934309d4d09fd?imageMogr2/auto-orient/strip%7CimageView2/2/w/600";
    private String url20 = "https://upload-images.jianshu.io/upload_images/1727605-ad4a5eeb9241f37f?imageMogr2/auto-orient/strip%7CimageView2/2/w/700";
    private String url21 = "https://upload-images.jianshu.io/upload_images/1727605-ba8b14aace061cbe?imageMogr2/auto-orient/strip%7CimageView2/2/w/640";
    private String url22 = "https://upload-images.jianshu.io/upload_images/1727605-18eb64e53510a222?imageMogr2/auto-orient/strip%7CimageView2/2/w/640";
    private String url23 = "https://upload-images.jianshu.io/upload_images/1727605-a3cb232577079511?imageMogr2/auto-orient/";
    private String url24 = "https://upload-images.jianshu.io/upload_images/4834364-3a967eea94aff5df.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/700";
    /*private String url16 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1526968646740&di=3d8eb6f8f830a6ee5bc21843536bd525&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dshijue1%252C0%252C0%252C294%252C40%2Fsign%3Dc70427ba06f431ada8df4b7a235fc6da%2Fcaef76094b36acaf5b6e54d476d98d1001e99c08.jpg";
    private String url5 = "http://zxpic.imtt.qq.com/zxpic_imtt/abstractimage/2018/05/22/1830/omg_183039_3474570161_0_192_144.jpg";
    private String url6 = "http://zxpic.imtt.qq.com/zxpic_imtt/abstractimage/2018/05/21/1840/omg_184240_3353921556_0_192_144.jpg";
    private String url7 = "http://zxpic.imtt.qq.com/zxpic_imtt/abstractimage/2018/05/22/2310/omg_231255_2306417380_0_192_144.jpg";
    private String url8 = "http://zxpic.imtt.qq.com/zxpic_imtt/abstractimage/2018/05/23/0930/omg_093831_2796701086_1_192_144.jpg";
    private String url9 = "http://wa.gtimg.com/website/201805/Sy_AKS_20180521185333874.jpg?md5=b7218bcd338a643449131a0735ed20e7";
    private String url10 = "http://zxpic.imtt.qq.com/zxpic_imtt/abstractimage/2018/05/23/0720/omg_072828_2491179680_0_192_144.jpg";
    private String url11 = "http://cdn.read.html5.qq.com/image?src=video_hot&q=5&h=384&w=688&r=0&imageUrl=http%3A%2F%2Fpuui.qpic.cn%2Fqqvideo_ori%2F0%2Fr0652rshw2j_1280_720%2F0";
    private String url12 = "http://cdn.read.html5.qq.com/image?src=video_hot&q=5&h=384&w=688&r=0&imageUrl=http%3A%2F%2Fpuui.qpic.cn%2Fqqvideo_ori%2F0%2Fk0659n69woh_1280_720%2F0";
    private String url13 = "http://cdn.read.html5.qq.com/image?src=video_hot&q=5&h=384&w=688&r=0&imageUrl=http%3A%2F%2Fpuui.qpic.cn%2Fqqvideo_ori%2F0%2Fk0659n69woh_1280_720%2F0";
    private String url14 = "http://cdn.read.html5.qq.com/image?src=video_hot&q=5&h=384&w=688&r=0&imageUrl=http%3A%2F%2Fpuui.qpic.cn%2Fqqvideo_ori%2F0%2Fk0659n69woh_1280_720%2F0";
    private String url15 = "http://car3.m.autoimg.cn/cardfs/series/g25/M09/27/A9/160x120_0_f40_autohomecar__wKgHIlqnPXuAOGPHAAWfOSHP0EE835.png";
    //private String url16 = "http://car2.m.autoimg.cn/cardfs/series/g24/M07/9A/FC/160x120_0_f40_autohomecar__wKgHIVqvagaAYsSSAAY10_cPFkc310.png";
    private String url17 = "http://zxpic.imtt.qq.com/zxpic_imtt/abstractimage/2018/05/23/0810/omg_081656_887992858_1_192_144.jpg";
    private String url18 = "http://zxpic.imtt.qq.com/zxpic_imtt/abstractimage/2018/05/23/0810/omg_081656_887992858_0_192_144.jpg";
    private String url19 = "http://zxpic.imtt.qq.com/zxpic_imtt/abstractimage/2018/05/23/0930/omg_093830_2796701086_0_192_144.jpg";
    private String url20 = "http://zxpic.imtt.qq.com/zxpic_imtt/abstractimage/2018/05/23/0930/omg_093832_2796701086_2_192_144.jpg";
    private String url21 = "http://zxpic.imtt.qq.com/zxpic_imtt/abstractimage/2018/05/20/1620/omg_162945_1091145829_0_192_144.jpg";
    private String url22 = "http://zxpic.imtt.qq.com/zxpic_imtt/abstractimage/2018/05/20/1620/omg_162945_1091145829_1_192_144.jpg";
    private String url23 = "http://zxpic.imtt.qq.com/zxpic_imtt/abstractimage/2018/05/20/1620/omg_162946_1091145829_2_192_144.jpg";
    private String url24 = "http://zxpic.imtt.qq.com/zxpic_imtt/abstractimage/2018/05/23/0810/omg_081657_887992858_2_192_144.jpg";
   */ private String textUrl = "https://www.baidu.com";

    private GifView gif2;
    private GifView gif3;
    private GifView gif4;
    private OkHttpClient client;
    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;
    private ImageView iv4;
    private ImageView iv5;
    private ImageView iv6;
    private ImageView iv7;
    private ImageView iv8;
    private ThreadPoolExecutor threadPoolExecutor;
    private ImageView iv9;
    private ImageView iv10;
    private ImageView iv11;
    private ImageView iv12;
    private ImageView iv13;
    private ImageView iv14;
    private ImageView iv15;
    private ImageView iv16;
    private ImageView iv17;
    private ImageView iv18;
    private ImageView iv19;
    private ImageView iv20;
    private static DateFormat HHMMSSSFormat = new SimpleDateFormat("HH:mm:ss:s", Locale.getDefault());
    private static DateFormat formatYYMMDDHHMMSSFormat = new SimpleDateFormat("yyyy:MM:dd_HH:mm:ss:s", Locale.getDefault());
    private boolean isRequest;
    private long last = System.currentTimeMillis();

    public static String formatHHMMSSMM(long time) {
        Date date = new Date(time);
        return HHMMSSSFormat.format(date);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.request).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long current = System.currentTimeMillis();
                if (current - last < 2000) {
                    return;
                }
                last = current;
                isRequest = true;
                gif1.setMovie(null);
                gif2.setMovie(null);
                gif3.setMovie(null);
                gif4.setMovie(null);
                iv1.setImageBitmap(null);
                iv2.setImageBitmap(null);
                iv3.setImageBitmap(null);
                iv4.setImageBitmap(null);
                iv5.setImageBitmap(null);
                iv6.setImageBitmap(null);
                iv7.setImageBitmap(null);
                iv8.setImageBitmap(null);
                iv9.setImageBitmap(null);
                iv10.setImageBitmap(null);
                iv11.setImageBitmap(null);
                iv12.setImageBitmap(null);
                iv13.setImageBitmap(null);
                iv14.setImageBitmap(null);
                iv15.setImageBitmap(null);
                iv16.setImageBitmap(null);
                iv17.setImageBitmap(null);
                iv18.setImageBitmap(null);
                iv19.setImageBitmap(null);
                iv20.setImageBitmap(null);
                Log.d(TAG, "onclick");
                result.setText(null);
                threadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        requestText(textUrl,result);
                    }
                });
              /*  threadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        requestImage(url16, iv12);
                    }
                });*/
              /*  threadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        requestImage(url7, iv3);
                    }
                });*/
              /*  threadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        requestGif(url1, gif1);
                    }
                });
                threadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        requestGif(url2, gif2);
                    }
                });
                threadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        requestGif(url3, gif3);
                    }
                });
                threadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        requestGif(url4, gif4);
                    }
                });
                threadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        requestImage(url5, iv1);
                    }
                });
                threadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        requestImage(url6, iv2);
                    }
                });
                threadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        requestImage(url7, iv3);
                    }
                });
                threadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        requestImage(url8, iv4);
                    }
                });
                threadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        requestImage(url9, iv5);
                    }
                });
                threadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        requestImage(url10, iv6);
                    }
                });
                threadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        requestImage(url11, iv7);
                    }
                });
                threadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        requestImage(url12, iv8);
                    }
                });
                threadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        requestImage(url13, iv9);
                    }
                });
                threadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        requestImage(url14, iv10);
                    }
                });
                threadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        requestImage(url15, iv11);
                    }
                });
                threadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        requestImage(url16, iv12);
                    }
                });
                threadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        requestImage(url17, iv13);
                    }
                });
                threadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        requestImage(url18, iv14);
                    }
                });
                threadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        requestImage(url19, iv15);
                    }
                });
                threadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        requestImage(url20, iv16);
                    }
                });
                threadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        requestImage(url21, iv17);
                    }
                });
                threadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        requestImage(url22, iv18);
                    }
                });
                threadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        requestImage(url23, iv19);
                    }
                });
                threadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        requestImage(url24, iv20);
                    }
                });*/
             //   result.setText(formatHHMMSSMM(current));
            }
        });
        result = findViewById(R.id.result);
        gif1 = findViewById(R.id.result_im1);
        gif2 = findViewById(R.id.result_im2);
        gif3 = findViewById(R.id.result_im3);
        gif4 = findViewById(R.id.result_im4);
        iv1 = findViewById(R.id.iv_1);
        iv2 = findViewById(R.id.iv_2);
        iv3 = findViewById(R.id.iv_3);
        iv4 = findViewById(R.id.iv_4);
        iv5 = findViewById(R.id.iv_5);
        iv6 = findViewById(R.id.iv_6);
        iv7 = findViewById(R.id.iv_7);
        iv8 = findViewById(R.id.iv_8);
        iv9 = findViewById(R.id.iv_9);
        iv10 = findViewById(R.id.iv_10);
        iv11 = findViewById(R.id.iv_11);
        iv12 = findViewById(R.id.iv_12);
        iv13 = findViewById(R.id.iv_13);
        iv14 = findViewById(R.id.iv_14);
        iv15 = findViewById(R.id.iv_15);
        iv16 = findViewById(R.id.iv_16);
        iv17 = findViewById(R.id.iv_17);
        iv18 = findViewById(R.id.iv_18);
        iv19 = findViewById(R.id.iv_19);
        iv20 = findViewById(R.id.iv_20);
        handler = new Handler();
        threadPoolExecutor = new ThreadPoolExecutor(8, 8, 15, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1000));
    }


    private void requestText(String url, final TextView resultView) {
       initClient();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            // final String string = response.body().string();
            final byte[] bytes = response.body().bytes();
            long tag = 0;
            for (int i = 0; i < bytes.length; i++) {
                tag = tag + bytes[i] * bytes[i];
            }

            Log.d(TAG, "receive byte tag " + tag + " length " + bytes.length + " port" + request.port);
            //   final Movie movie = Movie.decodeByteArray(bytes, 0, bytes.length);
            final String resultStr = new String(bytes, 0, bytes.length, "utf-8");
            //   Log.d(TAG,"result is "+string);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    // Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    resultView.setText(resultStr);

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG + request.port, e.getMessage());
        }

    }


    private void requestGif(String url, final GifView view) {
        initClient();

        final Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            // final String string = response.body().string();
            final byte[] bytes = response.body().bytes();
            long tag = 0;
            for (int i = 0; i < bytes.length; i++) {
                tag = tag + bytes[i] * bytes[i];
            }
            Log.d(TAG, "receive byte tag " + tag + " length " + bytes.length + " port" + request.port);
            final Movie movie = Movie.decodeByteArray(bytes, 0, bytes.length);
            //   Log.d(TAG,"result is "+string);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    // Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    view.setMovie(movie);

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG + request.port, e.getMessage());
        }

    }

    private void initClient() {
        if (client == null) {
            InetSocketAddress inetSocketAddress=null;
            try {
                inetSocketAddress = new InetSocketAddress(InetAddress.getByName("192.168.31.240"), 8889);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            client = new OkHttpClient
                    .Builder()
                //   .proxy(new Proxy(Proxy.Type.SOCKS, inetSocketAddress))
              //      .proxy(new Proxy(Proxy.Type.HTTP, inetSocketAddress))
                    .build();
        }
    }

    private void requestImage(String url, final ImageView view) {


        final Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            // final String string = response.body().string();
            final byte[] bytes = response.body().bytes();
            long tag = 0;
            for (int i = 0; i < bytes.length; i++) {
                tag = tag + bytes[i] * bytes[i];
            }
            Log.d(TAG, "receive byte tag " + tag + " length " + bytes.length + "port :" + response.request().port);
            Log.d(TAG, request.port + "  url:  " + url);
            final Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
           /* final Movie movie = Movie.decodeByteArray(bytes, 0, bytes.length);
            //   Log.d(TAG,"result is "+string);
            */
            handler.post(new Runnable() {
                @Override
                public void run() {
                    // Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    view.setImageBitmap(bitmap);
                    isRequest = false;

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, request.port + "  error is " + e.getMessage());
        }

    }

}
