package org.skytech.kefu.customer.controller;

import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;
import org.skytech.kefu.common.entity.User;
import org.skytech.kefu.common.model.ResponseResult;
import org.skytech.kefu.common.service.JWTProvideSerivce;
import org.skytech.kefu.common.service.UserService;
import org.skytech.kefu.common.utils.EncryptUtils;
import org.skytech.kefu.customer.model.LoginModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("")
public class LoginController extends BaseApiController {

    @Autowired
    private JWTProvideSerivce jwtProvideSerivce;

    @Autowired
    private UserService userService;

    @PostMapping(value = "login")
    public ResponseResult login(HttpServletRequest request, @RequestBody LoginModel loginModel) {
        User user = userService.findTopByUsernameAndUsertype(loginModel.getUsername(),"0");
        if(user == null) return ResponseResult.error(40003,"用户名错误");
        String pass =  getEncryptPassword(loginModel.getPassword(),user.getSalt());
        if(! user.getPassword().equals(pass)) return ResponseResult.error(40001,"密码错误");

        String token = jwtProvideSerivce.createJWTToken(user.getId(),loginModel.getUsername());
        return ResponseResult.success(token);
    }

    private  String getEncryptPassword(String password, String salt) {
       return StringUtils.isNotBlank(salt)?EncryptUtils.sha256(password, salt):EncryptUtils.md5(password);
    }
}