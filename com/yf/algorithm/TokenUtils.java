package com.yf.algorithm;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Map;

/**
 * TOKEN获取类
 * @author ouyangyufeng
 * @date 2019/1/31
 */
public class TokenUtils {

    /**
     * 获取token值
     * @param map
     * @return
     */
    public static String getToken(Map map) {
        String token= JWT.create().withAudience(map.get("userid").toString())
                .sign(Algorithm.HMAC256(map.get("password").toString()));
        return token;
    }

}
