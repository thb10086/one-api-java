package com.tang.core.config;

import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

@Configuration
public class OkHttpClientConfig {

    @Value("${okhttp.connectTimeout:1000}")
    private int connectTimeout; // 连接超时时间，默认为10秒

    @Value("${okhttp.readTimeout:10}")
    private int readTimeout; // 读取超时时间，默认为10秒

    @Value("${okhttp.writeTimeout:10}")
    private int writeTimeout; // 写入超时时间，默认为10秒

    @Value("${okhttp.followRedirects:true}")
    private boolean followRedirects; // 是否自动重定向，默认为true

    @Bean
    public OkHttpClient okHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                //本地测试开启代理
                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 15732)))
                .writeTimeout(writeTimeout, TimeUnit.SECONDS)
                .followRedirects(followRedirects);

        // 可以进行其他的自定义配置，例如添加拦截器、设置连接池等

        return builder.build();
    }
}
