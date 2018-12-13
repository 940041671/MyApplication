package com.umeng.soexample.myapplication.m;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Okhttp联网工具类
 */
public class NetWorkUtil{

    /**
     * 单例模式
     */
    private NetWorkUtil() {
    }

    private static NetWorkUtil netWorkUtil;
    public  static  NetWorkUtil getInstance(){
        if (netWorkUtil==null) {
            synchronized (NetWorkUtil.class) {
                if (netWorkUtil == null) {
                    netWorkUtil = new NetWorkUtil();
                }
            }
        }
        return netWorkUtil;
    }
    private Callbacks callbacks;
    public void Result( Callbacks callbacks){
        this.callbacks=callbacks;
    }
    /**
     * get
     */
    public NetWorkUtil get(String url){
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request=chain.request();
                String method = request.method();
                String host = request.url().host();
                return chain.proceed(request);
            }
        }).build();

        final Request request=new Request.Builder().get().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callbacks.success(e.getStackTrace().toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callbacks.failure(response.body().string());
            }
        });
        return this;
    }
    public void post(String url, RequestBody body){
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                String method = request.method();
                String host = request.url().host();
                return chain.proceed(request);
            }
        }).build();
        Request request = new Request.Builder().url(url).post(body).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callbacks.success(e.getStackTrace().toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callbacks.failure(response.body().string());
            }
        });
    }

}
