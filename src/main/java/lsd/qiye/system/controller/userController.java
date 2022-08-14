package lsd.qiye.system.controller;

import lsd.qiye.system.dao.UserDAO;
import lsd.qiye.system.dataobject.UserDO;

import lsd.qiye.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class userController {

    @Autowired
    private UserService userService;

    //显示个人信息
    @GetMapping("/ownInfo")
    public String iframeInfo(Model model,HttpServletRequest request){
        HttpSession session=request.getSession();
        String userId=(String) session.getAttribute("loginUser");
        UserDO userDO=userService.userFind(userId);
        model.addAttribute("user",userDO);
        return "info";
    }
    //form表单提交信息后，修改信息
    @PostMapping("/changeOwnInfo")
    public String changeInfoPage(@RequestParam("userName") String userName,@RequestParam("userSex") String userSex,
                             @RequestParam("userPhone") String userPhone,@RequestParam("userEmail") String userEmail,
                             @RequestParam("department") String department,@RequestParam("posts") String posts,
                             HttpServletRequest request) {
        HttpSession session=request.getSession();
        String userId=(String) session.getAttribute("loginUser");

        userService.updateInfo(userId,userName,userSex,userPhone,userEmail,department,posts);
        return "redirect:/ownInfo";
    }

    //从session得到登录id，查询的密码进行比对和更新
    @PostMapping("/changePwd")
    public String iframePwd(@RequestParam("oldPwd") String oldPwd,
                            @RequestParam("newPwd") String newPwd,
                            @RequestParam("renewPwd") String renewPwd,
                            Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session=request.getSession();
        String userId=(String) session.getAttribute("loginUser");
        response.setContentType("text/html;charset=utf8");
        PrintWriter out = response.getWriter();
        UserDO userDO=userService.userFind(userId);
        if(oldPwd!=null&&userDO.getUserPwd().equals(oldPwd)){
            if(renewPwd.equals(newPwd)) {
                out.print("<script language=\"javascript\">alert('恭喜你密码修改成功！');window.location.href='/pwd.html'</script>");
                userService.updatePwd(userId,renewPwd);
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

    //初始界面显示所有员工
    @GetMapping("/empPage")
    public String empListPage(Model model){
        List<UserDO> userDOS=userService.findAll();
        model.addAttribute("emps",userDOS);
        return "empList";
    }
    //复合查询员工
    @PostMapping("/empFind")
    public String iframeAllEmp(@RequestParam("department") String department,@RequestParam("userName") String userName,
                               @RequestParam("userId") String userId,Model model){
        List<UserDO> userDOS=userService.findComplex(department,userName,userId);
        model.addAttribute("emps",userDOS);
        return "empList";
    }
    //删除员工
    @GetMapping("/userDelete")
    public String deleteUser(@RequestParam("userId") String userId){
        userService.delete(userId);
        return "redirect:/empPage";
    }
    //跳转至员工修改信息界面
    @GetMapping("/empInfo")
    public String empInfo(Model model,@RequestParam("userId") String userId){
        UserDO userDO=userService.userFind(userId);
        model.addAttribute("emp",userDO);
        return "empInfo";
    }
    //修改员工信息,修改完返回用户列表
    @PostMapping("/changeEmpInfo")
    public String changeEmpInfo(@RequestParam("userId") String userId,@RequestParam("userName") String userName,
                                @RequestParam("userSex") String userSex, @RequestParam("userPhone") String userPhone,
                                @RequestParam("userEmail") String userEmail, @RequestParam("department") String department,
                                @RequestParam("posts") String posts) {
        userService.updateInfo(userId,userName,userSex,userPhone,userEmail,department,posts);
        return "redirect:/empPage";
    }
    //增加员工,添加到数据库
    @PostMapping("/addEmp")
    public  String addEmp(@RequestParam("userId") String userId,@RequestParam("userPwd") String userPwd,
                          @RequestParam("userName") String userName, @RequestParam("userSex") String userSex,
                          @RequestParam("userPhone") String userPhone, @RequestParam("userEmail") String userEmail,
                          @RequestParam("department") String department, @RequestParam("posts") String posts,
                          @RequestParam("role") String role,Model model){
        userService.insert(userId,userPwd,userName,userSex,userPhone,userEmail,department,posts,role);
        return "redirect:/empPage";

    }
    //角色判断
    @GetMapping("/EMProleJudge")
    public String roleJudge(HttpServletRequest request,HttpServletResponse response) throws IOException {
        HttpSession session= request.getSession();
        String userId=(String) session.getAttribute("loginUser");
        response.setContentType("text/html;charset=utf8");
        PrintWriter out = response.getWriter();
        Boolean role=userService.roleJudge(userId);
        if(role){
            return "redirect:/empPage";
        }else{
            out.print("<script language=\"javascript\">alert('您不是管理员，没有权限进入该页面，将返回欢迎界面！');window.location.href='/welcome.html'</script>");
            return "welcome";
        }
    }

}
