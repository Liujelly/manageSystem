package lsd.qiye.system.controller;

import lsd.qiye.system.dao.AdminDAO;
import lsd.qiye.system.dao.UserDAO;
import lsd.qiye.system.entity.AdminDO;
import lsd.qiye.system.entity.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {


    @GetMapping("/login")
    public String loginPage(){return "adminLogin";}



}
