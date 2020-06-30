package com.hzhang.utils.tokenUtils;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

/**
 * @author ：Hzhang
 * @date ：Created in 2020/6/1 20:22
 * @description：使用MD5算法根据入参即时间戳生成Token
 * @modified By：
 * @version: $
 */
@Component
public class MD5TokenGenerator implements TokenGenerator {
    /**
     * 生成Token
     * @param strings
     * @return
     */
    @Override
    public String generate(String... strings) {
        long timestamp = System.currentTimeMillis();
        String tokenMeta = "";
        for(String s: strings){
            tokenMeta += s;
        }
        tokenMeta += timestamp;
        String token = DigestUtils.md5DigestAsHex(tokenMeta.getBytes());
        return token;
    }

    public static void main(String[] args) {
        System.out.println(DigestUtils.md5DigestAsHex("".getBytes()));
    }
}
