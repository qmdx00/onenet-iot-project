package com.qmdx00.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * @author yuanweimin
 * @date 19/06/10 10:42
 * @description token 工具类
 */
@SuppressWarnings("ALL")
@Component
@PropertySource("classpath:config/jwt-config.properties")
public class TokenUtil {

    @Autowired
    private Environment env;

    /**
     * 创建jwt串
     *
     * @param map     自定义的 K, V
     * @param expires 过期时间(ms)
     * @return token串
     * @throws JWTCreationException
     */
    public String createJwt(Map<String, String> map, long expires) throws JWTCreationException {

        Algorithm algorithm = Algorithm.HMAC256(env.getProperty("jwt.secret-key"));

        JWTCreator.Builder builder = JWT.create()
                .withIssuer(env.getProperty("jwt.issuer"))
                .withSubject(env.getProperty("jwt.subject"))
                .withExpiresAt(new Date(System.currentTimeMillis() + expires));

        for (Map.Entry<String, String> entry : map.entrySet()) {
            builder.withClaim(entry.getKey(), entry.getValue());
        }
        return builder.sign(algorithm);
    }

    /**
     * 通过token获取其中的key对应的值
     *
     * @param token 认证的token
     * @param key   对应的键
     * @return claim对象
     * @throws JWTVerificationException
     */
    public Claim getClaim(String token, String key) throws JWTVerificationException {
        Algorithm algorithm = Algorithm.HMAC256(env.getProperty("jwt.secret-key"));
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim(key);
    }
}
