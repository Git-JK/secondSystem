package webdevelopment.secondsystem.controller;

import org.springframework.web.bind.annotation.*;
import webdevelopment.secondsystem.domain.entity.Building;
import webdevelopment.secondsystem.domain.entity.JsonResult;
import webdevelopment.secondsystem.domain.entity.User;
import webdevelopment.secondsystem.domain.vo.ChangePasswordVo;
import webdevelopment.secondsystem.domain.vo.LoginVo;
import webdevelopment.secondsystem.service.BuildingService;
import webdevelopment.secondsystem.service.IUserService;
import webdevelopment.secondsystem.service.RedisService;
import webdevelopment.secondsystem.utils.RequestUtils;
import webdevelopment.secondsystem.utils.TokenUtils;
import webdevelopment.secondsystem.utils.exception.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private RedisService redisService;

    @Resource(name = "IUserServiceImpl")
    private IUserService userService;

    @Resource
    private BuildingService buildingService;

    private final Integer timeout = 60 * 60 * 1;

    @ExceptionHandler(ServiceException.class)
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<Void>(e);
        if (e instanceof UserIdDuplicatedException) {
            result.setState(4000);
        } else if (e instanceof UserNotFoundException) {
            result.setState(4001);
        } else if (e instanceof PasswordNotMatchException) {
            result.setState(4002);
        } else if (e instanceof SessionTimeOutException) {
            result.setState(4003);
        } else if (e instanceof InsertException) {
            result.setState(5000);
        }
        return result;
    }

    @PostMapping("login")
    public JsonResult<User> login(@RequestBody LoginVo loginVo, HttpServletResponse response) {
        User user = userService.login(loginVo.getStudentId(), loginVo.getPassword());
        String token = TokenUtils.getToken(user);
        Cookie cookie = new Cookie("token", token);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * 30);
        response.addCookie(cookie);
        redisService.set(token, user.getStudentId(), timeout, TimeUnit.SECONDS);
        return new JsonResult<User>(200, "登录成功", user);
    }

    @PostMapping("changePassword")
    public JsonResult<User> changePassword(HttpServletRequest request, @RequestBody ChangePasswordVo changePasswordVo) {
        String token = (String) RequestUtils.getAttributeFromCookies(request, "token");
        Long studentId = Long.valueOf(TokenUtils.getTokenData(token, "studentId"));
        User user = userService.getUserInfo(studentId);
        if(user == null) {
            throw new UserNotFoundException("用户不存在");
        }
        user = userService.changePassword(user, changePasswordVo.getNewPassword());
        return new JsonResult<User>(200, "修改密码成功",  user);
    }

    @PostMapping("getUserInfo")
    public JsonResult<User> getUserInfo(HttpServletRequest request) {
        String token = (String) RequestUtils.getAttributeFromCookies(request, "token");
        Long studentId = Long.valueOf(TokenUtils.getTokenData(token, "studentId"));
        User user = userService.getUserInfo(studentId);
        if(user == null) {
            throw new UserNotFoundException("用户不存在");
        }
        return new JsonResult<User>(200, "个人信息如下：", user);
    }

    @PostMapping("getBuilding")
    public JsonResult<List<Building>> getBuilding() {
        List<Building> buildingList = buildingService.getAvailableBuilding();
        return new JsonResult<List<Building>>(200, "可选宿舍楼信息以及宿舍楼剩余床位量如下", buildingList);
    }
}
