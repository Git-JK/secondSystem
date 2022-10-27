package webdevelopment.secondsystem.interceptor;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import webdevelopment.secondsystem.domain.entity.JsonResult;
import webdevelopment.secondsystem.service.RedisService;
import webdevelopment.secondsystem.utils.TokenUtils;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Resource
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //跨域请求会首先发一个option请求，直接返回正常状态并通过拦截器
        if(request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        response.setCharacterEncoding("utf-8");
        Cookie[] cookies = request.getCookies();
        String token = null;
        if(cookies != null && cookies.length > 0) {
            for(Cookie cookie : cookies) {
                if(cookie.getName().equals("token")) {
                    token = cookie.getValue();
                    break;
                }
            }
        }
        if(token != null) {
            Boolean result = TokenUtils.checkToken(token);
            Long studentId = redisService.get(token);
            System.out.println(studentId);
            Long tokenStudentId = Long.valueOf(TokenUtils.getTokenData(token, "studentId"));
            if(result && studentId != null && studentId.equals(tokenStudentId)) {
                System.out.println("通过拦截器");
                return true;
            }
        }
        response.setContentType("application/json; charset=utf-8");
        try {
            JSONObject json = new JSONObject();
            json.put("msg", "token check failed");
            json.put("code", "500");
            response.getWriter().append(json.toString());
            System.out.println("认证失败，未通过拦截器");
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
