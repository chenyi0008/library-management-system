package com.library.controller;

import com.library.domain.Code;
import com.library.domain.Result;
import com.library.domain.User;
import com.library.domain.UserToken;
import com.library.service.UserService;
import com.library.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public Result login(@RequestBody User user){

        String username = user.getUsername();
        String password = user.getPassword();
        User temp = userService.login(username,password);
        if(temp == null)return new Result(Code.ERR,"账号或密码输入有误");
        else{
            String token = JwtUtil.createToken(username,password);
            UserToken userToken = new UserToken(username,token);
            return new Result(Code.OK,userToken,"登录成功");
        }
    }

    @PutMapping
    public Result Registor(@RequestBody User user,@RequestParam String code,HttpServletRequest request){
        HttpSession session = request.getSession();
        Object checkCode = session.getAttribute("checkCode");
        if(!code.equals(checkCode))return new Result(Code.ERR,"验证码输入有误");

        String username = user.getUsername();
        String password = user.getPassword();

        User judge = userService.judge(username);
        if(judge != null)return new Result(Code.ERR,"该账号已存在");

        int num = userService.registor(username,password);
        if(num > 0)return new Result(Code.OK,"注册成功");
        else return new Result(Code.DEFAULT,"系统繁忙");
    }

    @GetMapping()
    public Result test(HttpServletRequest request){
        HttpSession session = request.getSession();
        Object checkCode = session.getAttribute("checkCode");
        System.out.println(checkCode);
        return new Result();
    }

    @GetMapping("/checkToken")
    public Boolean checkToken(HttpServletRequest request){
        String token = request.getHeader("token");
        try{
            JwtUtil.parse(token);
        }catch (Exception e){
            return false;
        }
        return JwtUtil.checkToken(token);
    }











}
