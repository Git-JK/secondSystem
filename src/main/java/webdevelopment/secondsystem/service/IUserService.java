package webdevelopment.secondsystem.service;

import webdevelopment.secondsystem.domain.entity.User;

import javax.servlet.http.HttpSession;

public interface IUserService {
    User login(Long studentId, String password);
    User getUserInfo(Long studentId);
    User changePassword(User user, String newPassword);

}
