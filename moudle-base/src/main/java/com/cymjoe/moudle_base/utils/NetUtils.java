package com.cymjoe.moudle_base.utils;

import com.cymjoe.lib_http.NetManager;

import java.util.HashMap;
import java.util.Map;

public class NetUtils {

    public static <T> T getService(String url, Class<T> service) {
        NetManager instance = NetManager.INSTANCE;
        Map<String, String> map = new HashMap<>();
        return instance.Builder()
                .baseUrl(url)
                .setHeader(map)
                .client()
                .build()
                .getService(service);
    }
}
