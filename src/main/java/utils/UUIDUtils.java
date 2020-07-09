package utils;

import java.util.UUID;

/**
 * 生成唯一标识ID
 *
 * @author Hu.Wang 2020/07/07 13:50
 */
public class UUIDUtils {

    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
