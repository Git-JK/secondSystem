package webdevelopment.secondsystem.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Request相关工具类
 */
public class RequestUtils {

    public static Object getAttributeFromCookies(HttpServletRequest request, String Attribute) {
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(Attribute)) {
                    Object attributeValue = cookie.getValue();
                    return attributeValue;
                }
            }
        }
        return null;
    }
}
