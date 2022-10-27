package webdevelopment.secondsystem.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import webdevelopment.secondsystem.domain.entity.User;

import java.util.Date;

/**
 * Token工具类，用于生成Token
 */
public class TokenUtils {
    //token到期时间1小时
    public static final Long EXPIRE_TIME = Long.valueOf(1*60*60*1000);
    //密钥salt
    public static final String TOKEN_SECRET = "ljdyaishijin**3nkjnj??";

    /**
     * 生成Token
     * @param user
     * @return
     */
    public static String getToken(User user) {
        String token = null;
        System.out.println(user.getStudentId());
        try {
            Date expireAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            token = JWT.create()
                    //发行人
                    .withIssuer("auth0")
                    //存放数据
                    .withClaim("studentId", String.valueOf(user.getStudentId()))
                    .withClaim("name", user.getName())
                    //过期时间
                    .withExpiresAt(expireAt)
                    //加密
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (IllegalArgumentException | JWTCreationException je) {
            System.out.println(je);
        }
        return token;
    }
    public static Boolean checkToken(String token) {
        try {
            //建立token验证器
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            System.out.println("token认证通过：");
            System.out.println("student_id: " + decodedJWT.getClaim("studentId").asString() + " name: " + decodedJWT.getClaim("name").asString());
            System.out.println("token过期时间：" + decodedJWT.getExpiresAt());
        } catch (IllegalArgumentException | JWTVerificationException e) {
            //抛出错误即为验证不通过
            return false;
        }
        return true;
    }

    public static String getTokenData(String token, String key) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            return decodedJWT.getClaim(key).asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
}
