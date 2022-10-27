package webdevelopment.secondsystem.service.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webdevelopment.secondsystem.domain.entity.User;
import webdevelopment.secondsystem.dao.BuildingMapper;
import webdevelopment.secondsystem.dao.UserMapper;
import webdevelopment.secondsystem.service.IUserService;
import webdevelopment.secondsystem.utils.exception.PasswordNotMatchException;
import webdevelopment.secondsystem.utils.exception.UserNotFoundException;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Service
public class IUserServiceImpl implements IUserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User login(Long studentId, String password) {
        User result = userMapper.findByUserId(studentId);
        if (result == null) {
            throw new UserNotFoundException("用户数据不存在");
        }
        String userPassword = result.getPassword();
        if(!userPassword.equals(password)) {
            throw new PasswordNotMatchException("用户账号或密码错误");
        }
        User user = new User();
        user.setId(result.getId());
        user.setStudentId(result.getStudentId());
        user.setName(result.getName());
        user.setGender(result.getGender());
        user.setPhoneNumber(result.getPhoneNumber());
        user.setStatus(result.getStatus());
        user.setLabel(result.getLabel());
        user.setPassword(result.getPassword());
        user.setHasBedroom(result.getHasBedroom());
        user.setBedroomId(result.getBedroomId());
        user.setVerificationCode(result.getVerificationCode());
        user.setUserType(result.getUserType());
        return user;
    }

    @Override
    public User getUserInfo(Long studentId) {
        return userMapper.findByUserId(studentId);
    }

    @Override
    public User changePassword(User user, String newPassword) {
        user.setPassword(newPassword);
        userMapper.update(user);
        return user;
    }
}
