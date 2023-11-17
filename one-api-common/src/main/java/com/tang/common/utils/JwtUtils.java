package com.tang.common.utils;
import com.tang.common.constant.TokenConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import com.tang.common.text.Convert;

/**
 * @projectName: Lzx-CloudPlatform
 * @package: com.lzx.cloudplatform.common.core.com.tang.common.utils
 * @className: JwtUtils
 * @author: HuangHui
 * @description: Jwt工具类
 * @date: 2023/5/29 10:56
 * @version: 1.0
 */
public class JwtUtils {
    public static String secret = TokenConstants.SECRET;

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
//    public static String createToken(Map<String, Object> claims)
//    {
//        String token = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).compact();
//        return token;
//    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    public static Claims parseToken(String token)
    {
        return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
    }

    /**
     * 根据令牌获取用户标识
     *
     * @param token 令牌
     * @return 用户ID
     */
//    public static String getUserKey(String token)
//    {
//        Claims claims = parseToken(token);
//        return getValue(claims, SecurityConstants.USER_KEY);
//    }

    /**
     * 根据令牌获取用户标识
     *
     * @param claims 身份信息
     * @return 用户ID
     */
//    public static String getUserKey(Claims claims)
//    {
//        return getValue(claims, SecurityConstants.USER_KEY);
//    }

    /**
     * 根据令牌获取用户ID
     *
     * @param token 令牌
     * @return 用户ID
     */
//    public static String getUserId(String token)
//    {
//        Claims claims = parseToken(token);
//        return getValue(claims, SecurityConstants.DETAILS_USER_ID);
//    }

    /**
     * 根据身份信息获取用户ID
     *
     * @param claims 身份信息
     * @return 用户ID
     */
//    public static String getUserId(Claims claims)
//    {
//        return getValue(claims, SecurityConstants.DETAILS_USER_ID);
//    }

    /**
     * 根据令牌获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
//    public static String getUserName(String token)
//    {
//        Claims claims = parseToken(token);
//        return getValue(claims, SecurityConstants.DETAILS_USERNAME);
//    }

    /**
     * 根据身份信息获取用户名
     *
     * @param claims 身份信息
     * @return 用户名
     */
//    public static String getUserName(Claims claims)
//    {
//        return getValue(claims, SecurityConstants.DETAILS_USERNAME);
//    }

    /**
     * 根据身份信息获取键值
     *
     * @param claims 身份信息
     * @param key 键
     * @return 值
     */
    public static String getValue(Claims claims, String key)
    {
        return Convert.toStr(claims.get(key), "");
    }
}
