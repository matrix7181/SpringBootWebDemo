package com.cc.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by xt on 7/5/17.
 */
public class HttpUtil {

    public static HttpHeaders generateAllowHeader(HttpMethod... methods) {
        HttpHeaders headers = new HttpHeaders();
        Set<HttpMethod> allow = new HashSet<>();
        allow.addAll(Arrays.asList(methods));
        headers.setAllow(allow);
        return headers;
    }
}
