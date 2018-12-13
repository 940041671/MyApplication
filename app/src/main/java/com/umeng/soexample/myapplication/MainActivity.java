package com.umeng.soexample.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.umeng.soexample.myapplication.m.Callbacks;
import com.umeng.soexample.myapplication.m.NetWorkUtil;

public class MainActivity extends AppCompatActivity {

    private Toolbar tool;
    private ImageView img;
    private XRecyclerView xrecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        NetWorkUtil.getInstance().get("http://v.juhe.cn/toutiao/index?type=&key=83a69c67e9272f816e42450ef0eb50ee").Result(new Callbacks() {
            @Override
            public void success(String data) {
                Log.i("aa",data);
            }

            @Override
            public void failure(String error) {
                Log.i("aa",error);
            }
        });
    }

    private void initView() {
        tool = (Toolbar) findViewById(R.id.tool);
        img = (ImageView) findViewById(R.id.img);
        xrecyclerview = (XRecyclerView) findViewById(R.id.xrecyclerview);
    }
}
