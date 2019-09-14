package com.kacha.wapproj.util;

import org.apache.commons.lang.StringUtils;
import javax.servlet.http.Cookie;

/**
 * Created by Hayden on 2019/9/11.
 */
public class CookieUtil {

    public static String resolveINfoFromCookie(Cookie[] cookies, String key){

        if(cookies != null && StringUtils.isNotBlank(key)){
            for(Cookie cookie : cookies){
                if(key.equals(cookie.getName())){
                    return cookie.getValue();
                }
            }
        }

        return null;

    }


}
