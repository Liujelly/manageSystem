package lsd.qiye.system.controller;

import lsd.qiye.system.dao.UserDAO;
import lsd.qiye.system.dataobject.UserDO;
import lsd.qiye.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class loginController {
    @Autowired
    private UserService userService;

    //用户登录，根据id，密码验证
    @GetMapping("/login")
    public String login(@RequestParam("userId") String userId,
                        @RequestParam("userPwd") String userPwd,
                        @RequestParam("code") String code, Model model,
                        HttpSession session){
        UserDO userDO=userService.userFind(userId);
        if(userDO!=null&&userId.equals(userDO.getUserId())&&userPwd.equals(userDO.getUserPwd())&&"6982".equals(code)){
            session.setAttribute("loginUser",userId);
            return "redirect:/main.html";
        }else{
            //登录失败
            model.addAttribute("msg","登录信息有误，请重新输入");
            return "index";
        }
    }




}
