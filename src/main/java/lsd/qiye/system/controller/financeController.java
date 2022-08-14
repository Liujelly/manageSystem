package lsd.qiye.system.controller;

import lsd.qiye.system.dataobject.FinanceDO;
import lsd.qiye.system.dataobject.UserDO;
import lsd.qiye.system.service.FinanceApplicationService;
import lsd.qiye.system.service.FinanceService;
import lsd.qiye.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
public class financeController {
    @Autowired
    private FinanceService financeService;
    @Autowired
    private FinanceApplicationService financeApplicationService;
    @Autowired
    private UserService userService;
    //初始化财务界面，并返回所有财务信息
    @GetMapping("/financePage")
    public String financeListPage(Model model){
        List<FinanceDO> financeDOS=financeService.findAll();
        model.addAttribute("financeDOs",financeDOS);
        return "financeList";
    }
    //复合查询财务信息
    @PostMapping("financeFind")
    public String financeComplexFind(@RequestParam("time")String time,
                                     @RequestParam("fillPerson") String fillPerson, @RequestParam("id") String id, Model model){
        List<FinanceDO> financeDOS=financeService.findComplex(time,fillPerson,id);
        model.addAttribute("financeDOs",financeDOS);
        return "financeList";
    }
    //增加财务表
    @PostMapping("/addFinance")
    public String addFinance(@RequestParam("id") String id, @RequestParam("companyDep") double companyDep,
                             @RequestParam("onlinePay") double onlinePay, @RequestParam("manualDep") double manualDep,
                             @RequestParam("arppu") double arppu, @RequestParam("manualDed")  double manualDed,
                             @RequestParam("withdrawals") double withdrawals, @RequestParam("fundFlow") double fundFlow,
                             @RequestParam("income") double income, @RequestParam("netProfit") double netProfit,
                             HttpServletRequest request){
        Date now=new Date();
        HttpSession session= request.getSession();
        String userId=(String)session.getAttribute("loginUser");
        financeService.insertFinance(now, id,companyDep,onlinePay,  manualDep,  arppu, manualDed, withdrawals, fundFlow, income,  netProfit,userId );
        return"redirect:/financePage";
    }
    //申请删除
    @GetMapping("/deleteAlcPage")
    public String deleteAlc(@RequestParam("financeId") String financeId,Model model, HttpServletRequest request){
        HttpSession session=request.getSession();
        String applicantId=(String) session.getAttribute("loginUser");
        UserDO userDO=userService.userFind(applicantId);
        model.addAttribute("financeId",financeId);
        model.addAttribute("applicant",userDO);
        return "deleteAlc";
    }
    //申请提交（添加到申请表中）及跳转提示界面
    @PostMapping("/AlcPush")
    public String AlcPush(@RequestParam("financeId") String financeId,@RequestParam("applicantId") String applicantId,
                          @RequestParam("applicantEmail") String applicantEmail,@RequestParam("reason") String reason){
        Date now=new Date();
        financeApplicationService.insert(financeId,applicantId,applicantEmail,reason,now);
        return "tips";
    }
}
