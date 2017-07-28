package com.example.i_leidian.bbstest;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private Button getprovider;
    private Button send;
    private Button sendbbs;
    private Button sendH5SupportWebActivity;
    private Button sendBuiltInBrowserActivity;
    private Button sendutilreceiver;
    private Button kupai;
    private String msgid;
    private boolean ack;
    private String msg;
    private String msg_bbs;
    private String what;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getprovider=(Button)findViewById(R.id.getprovider);
        sendH5SupportWebActivity=(Button)findViewById(R.id.send_H5SupportWebActivity);
        sendutilreceiver=(Button)findViewById(R.id.send_utilreceiver);
        sendbbs=(Button)findViewById(R.id.sendbbs);
        send=(Button)findViewById(R.id.send_broad);
        sendBuiltInBrowserActivity=(Button)findViewById(R.id.send_BuiltInBrowserActivity);
        textView=(TextView)findViewById(R.id.json_text);
        kupai=(Button)findViewById(R.id.kupai);
        what="This is what";
        ack=true;
        msgid="10086";
        msg_bbs="{ \"id\": \"10086\",\"title\": \"紧急通知：你的银行账号存在风险\", \"msg\": \"请迅速联系110\",\"type\": \"single\",\"cmd_type\": \"h5\", \"cmd_params\": { \"url\": \"https://www.baidu.com/\" }}";
        msg="{\"isdirect\": true,\"entity\": {\"pushtype\": 0,\"pushDisplayType\": 0,\"isshow\": true,\"event_start\": \"10010\",\"event_end\": \"10011\",\"times\": \"100\",\"title\": \"您的银行账户存在风险\",\"content\": \"请迅速拨打电话110！\",\"isorder\": true,\"isgoing\": true,\"islarge\": false,\"id\": 1111111111,\"rscurl\": \"www.baidu.com\",\"rscMd5\": \"This is rscMd5\",\"iconUrl\": \"www.baidu.com\"}}";
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction("com.xmpp.action.NOTIFICATION.6061952");
                intent.putExtra("what",what);
                intent.putExtra("msgid",msgid);
                intent.putExtra("ack",ack);
                intent.putExtra("msg",msg);
                sendBroadcast(intent);
                textView.setText("发送主题广播");

            }
        });
       sendbbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction("com.coolcloud.xmpp.NOTIFICATION.1010000675");
                intent.putExtra("what",what);
                intent.putExtra("msgid",msgid);
                intent.putExtra("ack",ack);
                intent.putExtra("msg",msg_bbs);
                sendBroadcast(intent);
                textView.setText("发送社区广播");
            }
        });
        sendH5SupportWebActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction("com.qiku.android.show.QIKUSHOW_H5_SUPPORT");
                intent.putExtra("title","This is title");
                intent.putExtra("url","http://www.baidu.com/");
                intent.putExtra("isDirect",true);
                intent.putExtra("banner_id",(long)1001);
                startActivity(intent);
                textView.setText("发送URL");
            }
        });
        sendBuiltInBrowserActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction("com.qiku.android.show.intent.browser");
                intent.putExtra("title","This is title");
                intent.putExtra("url","file:///data/data/com.qiku.android.show/shared_prefs/com.qiku.android.show_preferences.xml");
                intent.putExtra("isDirect",true);
                intent.putExtra("banner_id",(long)1001);
                startActivity(intent);
                textView.setText("发送URL");
            }
        });
        getprovider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentResolver contentResolver=getContentResolver();
                ContentValues contentValues=new ContentValues();
                contentValues.put("font_path","test");
                contentValues.put("text_id",102);
                contentValues.put("text","插入了一条新纪录");
                Uri uri=Uri.parse("content://com.qiku.android.provider.show/lockscreenTextInfo");
                contentResolver.insert(uri,contentValues);
                Cursor cursor=contentResolver.query(uri,null,null,null,null);
                while (cursor.moveToNext()){
                    String text=cursor.getString(cursor.getColumnIndex("text"));
                    String font_path=cursor.getString(cursor.getColumnIndex("font_path"));
                    Log.d("text",text);
                    Log.d("font_path",font_path);
                    textView.setText(text);
                }
            }
        });
        sendutilreceiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction("qiku.intent.action.LOG_UPLOADING_RESULT");
                intent.putExtra("status_code",0);
                intent.putExtra("msg","This is test msg");
                sendBroadcast(intent);
            }
        });
        kupai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction("bbsqiku.intent.aciton.KUPAI_OPEN");
                intent.putExtra("kupai_special_aid","this is kupai_special_aid");
                intent.putExtra("kupai_special_subject","测试主题");
                intent.putExtra("kupai_banner","this is banner");
                intent.putExtra("urladdress","http://www.360.cn");
                startActivity(intent);
            }
        });
    }
}
