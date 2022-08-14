package lsd.qiye.system.controller;

import lsd.qiye.system.dataobject.FinanceApplicationDO;
import lsd.qiye.system.service.FinanceApplicationService;
import lsd.qiye.system.service.FinanceService;
import lsd.qiye.system.service.SendMailService;
import lsd.qiye.system.service.UserService;
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
import java.util.List;

@Controller
public class FinanceAlcController {
    @Autowired
    FinanceApplicationService financeApplicationService;
    @Autowired
    FinanceService financeService;
    @Autowired
    UserService userService;
    @Autowired
    SendMailService sendMailService;
    //初始化申请界面
    @GetMapping("/financeAlcPage")
    public String financeAlcPage(Model model){
        List<FinanceApplicationDO> financeApplicationDOS=financeApplicationService.findAll();
        model.addAttribute("financeAlcs",financeApplicationDOS);
        return "financeApplication";
    }
    //同时删除申请表和财务表中信息
    @GetMapping("/releDelete")
    public String releDelete(@RequestParam("financeId") String financeId){
        String text="审核通过，您申请的id为"+financeId+"的改财务单信息已删除。";
        List<String> emails=financeApplicationService.findEmails(financeId);
        for(String email:emails){
            sendMailService.sendMail(email,text);
        }
        financeService.deleteFinance(financeId);
        financeApplicationService.deleteBy_fId(financeId);
        return "redirect:/financeAlcPage";
    }
    //拒绝申请，删除申请表信息
    @GetMapping("/refuseDelete")
    public String refuseDelete(@RequestParam("financeId") String financeId){
        String text="您申请删除的id为"+financeId+"的财务单信息，审核未通过，请重新选择或是联系您的上级领导。";
        List<String> emails=financeApplicationService.findEmails(financeId);
        for(String email:emails){
            sendMailService.sendMail(email,text);
        }
        financeApplicationService.deleteBy_fId(financeId);
        return "redirect:/financeAlcPage";
    }
    //角色判断
    @GetMapping("/FAroleJudge")
    public String roleJudge(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session= request.getSession();
        String userId=(String) session.getAttribute("loginUser");
        response.setContentType("text/html;charset=utf8");
        PrintWriter out = response.getWriter();
        Boolean bl=userService.roleJudge(userId);
        if(bl){
            return "redirect:/financeAlcPage";
        }else{
            out.print("<script language=\"javascript\">alert('您不是管理员，没有权限进入该页面，将返回欢迎界面！');window.location.href='/welcome.html'</script>");
            return "welcome";
        }
    }
}
