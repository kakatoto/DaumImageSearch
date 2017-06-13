package com.kakatoto.imagesearch.util.retrofit;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.kakatoto.imagesearch.util.CommonUtil;
import com.kakatoto.imagesearch.util.IConstant;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RestfulAdapter {
    public static final int CONNECT_TIMEOUT = 15;
    public static final int WRITE_TIMEOUT = 15;
    public static final int READ_TIMEOUT = 15;
    private static OkHttpClient client;
    private static RestfulInterface Interface;

    public synchronized static RestfulInterface getInstance() {
        if (Interface == null) {

            //쿠키 메니저의 cookie policy를 변경 합니다.
            CookieManager cookieManager = new CookieManager();
            cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);

            //OkHttpClient를 생성합니다.
            client = new OkHttpClient().newBuilder() //인증서 무시
                    .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS) //연결 타임아웃 시간 설정
                    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS) //쓰기 타임아웃 시간 설정
                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS) //읽기 타임아웃 시간 설정
                    .addNetworkInterceptor(new StethoInterceptor()).build();


            //Retrofit 설정
            Interface = new Retrofit.Builder()
                    .baseUrl(IConstant.SERVER_URL +"/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create()) //Json Parser 추가
                    .build().create(RestfulInterface.class); //인터페이스 연결

        }
        return Interface;
    }

    public static void cancelCallWithTag(String tag) {
        // A call may transition from queue -> running. Remove queued Calls first.
        if(CommonUtil.isNull(client))
            return;
        for (Call call : client.dispatcher().queuedCalls()) {
            if (call.request().tag().equals(tag))
                call.cancel();
        }
        for (Call call : client.dispatcher().runningCalls()) {
            if (call.request().tag().equals(tag))
                call.cancel();
        }
        for (Call call : client.dispatcher().runningCalls()) {
            if (call.request().tag().equals(tag))
                call.cancel();
        }
    }

}