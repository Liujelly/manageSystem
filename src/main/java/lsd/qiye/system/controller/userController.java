package lsd.qiye.system.controller;

import lsd.qiye.system.dao.UserDAO;
import lsd.qiye.system.dataobject.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class userController {
    @Autowired
    private UserDAO userDAO;

    @GetMapping("/info")
    public String frame1(){
        return "info";
    }

    @GetMapping("/changePwd")
    public String iframePwd(@RequestParam("oldPwd") String oldPwd,
                            @RequestParam("newPwd") String newPwd,
                            @RequestParam("renewPwd") String renewPwd,
                            Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session=request.getSession();
        response.setContentType("text/html;charset=utf8");
        PrintWriter out = response.getWriter();
        String userId=(String) session.getAttribute("loginUser");
        UserDO userDO=userDAO.findById_Pwd(userId,null);
        if(oldPwd!=null&&userDO.getUserPwd().equals(oldPwd)){
            if(renewPwd.equals(newPwd)) {
                out.print("<script language=\"javascript\">alert('恭喜你密码修改成功！');window.location.href='/pwd.html'</script>");
                userDAO.updatePwd(userId,renewPwd);
                return "pwd";
            }else {
                model.addAttribute("msg1","两次输入密码不一致！");
                return "pwd";
            }
        }else{
            model.addAttribute("msg2","原始密码错误！");
            return "pwd";
        }
    }
}
