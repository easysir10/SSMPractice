package utils;

import info.UserInfo;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * 生成随机用户（id和昵称）
 *
 * @author Hu.Wang 2020/07/08 17:23
 */
public class UserUtils {

    /**
     * 生成随机用户
     *
     * @author Hu.Wang 2020-07-08 17:29
     * @return info.UserInfo
     */
    public static UserInfo getRandomUser(){
        return new UserInfo(UUIDUtils.getUUID(),UserUtils.getNickname());
    }

    /**
     * 生成随机昵称
     *
     * @author Hu.Wang 2020-07-08 17:27
     * @return java.lang.String
     */
    private static String getNickname(){
        StringBuilder randomName = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            String str = null;
            int highPos, lowPos; // 定义高低位
            Random random = new Random();
            highPos = (176 + Math.abs(random.nextInt(39))); // 获取高位值
            lowPos = (161 + Math.abs(random.nextInt(93))); // 获取低位值
            byte[] b = new byte[2];
            b[0] = (new Integer(highPos).byteValue());
            b[1] = (new Integer(lowPos).byteValue());
            try {
                str = new String(b, "GBK"); // 转成中文
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
            randomName.append(str);
        }
        return randomName.toString();
    }
}
