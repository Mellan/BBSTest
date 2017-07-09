package com.example.i_leidian.bbstest;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private Button send;
    private Button sendbbs;
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
        sendbbs=(Button)findViewById(R.id.sendbbs);
        send=(Button)findViewById(R.id.send_broad);
        textView=(TextView)findViewById(R.id.json_text);
        what="This is what";
        ack=true;
        msgid="10086";
        msg_bbs="";
        msg="{\"isdirect\": true,\"entity\": {\"pushtype\": 0,\"pushDisplayType\": 0,\"isshow\": true,\"event_start\": \"10010\",\"event_end\": \"10011\",\"times\": \"100\",\"title\": \"您的银行账户存在风险\",\"content\": \"请迅速拨打电话95511联系客服！\",\"isorder\": true,\"isgoing\": true,\"islarge\": false,\"id\": 1111111111,\"rscurl\": \"www.baidu.com\",\"rscMd5\": \"This is rscMd5\",\"iconUrl\": \"www.baidu.com\"}}";
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
                textView.setText("发送成功");

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
                intent.putExtra("msg",msg);
                sendBroadcast(intent);
                textView.setText("发送成功");
            }
        });
    }
}
