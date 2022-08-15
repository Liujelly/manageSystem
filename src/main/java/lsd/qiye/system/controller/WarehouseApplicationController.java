package lsd.qiye.system.controller;

import lsd.qiye.system.dataobject.WarehouseApplicationDO;
import lsd.qiye.system.dataobject.WarehouseDO;
import lsd.qiye.system.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

@Controller
public class WarehouseApplicationController {
    @Autowired
    WarehouseApplicationService warehouseApplicationService;
    @Autowired
    WarehouseService warehouseService;
    @Autowired
    InWarehouseService inWarehouseService;
    @Autowired
    OutWarehouseService outWarehouseService;
    @Autowired
    UserService userService;
    @Autowired
    SendMailService sendMailService;
    //初始化界面
    @GetMapping("/warehouseApplicationPage")
    public String warehouseApplicationPage(Model model){
        List<WarehouseApplicationDO> warehouseApplicationDOS=warehouseApplicationService.findAll();
        model.addAttribute("warehouseApplicationDOS",warehouseApplicationDOS);
        return "warehouseApplication";
    }
    @GetMapping("/agreeApplication")
    public String agreeApplication(@RequestParam("id") long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session=request.getSession();
        String loginUser=(String)session.getAttribute("loginUser");
        response.setContentType("text/html;charset=utf8");
        PrintWriter out = response.getWriter();
        Date now=new Date();
        //根据id找到该申请表
        WarehouseApplicationDO warehouseApplicationDO=warehouseApplicationService.findById(id);
        //从申请表中得到需要操作的数量
        long operand=warehouseApplicationDO.getOperand();
        //从申请表中得到申请人id，用来发送邮件
        String operatorId=warehouseApplicationDO.getOperatorId();
        //从申请表中得到需要操作的产品的id
        String productId=warehouseApplicationDO.getProductId();
        //查询库存数量
        long number=warehouseService.findById(productId).getNumber();
        //如果是入库不用判断数量，直接加到库存
        if(warehouseApplicationDO.getOperation().equals("入库")){
            //更新库存
            warehouseService.update(productId,null,null,null,number+operand);
            //插入到入库表中
            inWarehouseService.insert(productId,warehouseApplicationDO.getProductName(),warehouseApplicationDO.getUpDown(),
                    operand,warehouseApplicationDO.getOperatorId(),loginUser,now);
            String email=userService.userFind(operatorId).getUserEmail();
            String text="您的入库申请已被批准，请入库";
            sendMailService.sendMail(email,text);
            //删除审核申请表
            warehouseApplicationService.deleteById(id);
            return "redirect:/warehouseApplicationPage";
        }else{
            //出库需要判断数量是否足够
            if(number>=operand){
                warehouseService.update(productId,null,null,null,number-operand);
                outWarehouseService.insert(productId,warehouseApplicationDO.getProductName(),warehouseApplicationDO.getUpDown(),
                        operand,warehouseApplicationDO.getOperatorId(),loginUser,now);
                String email=userService.userFind(operatorId).getUserEmail();
                String text="您的出库申请已被批准，请出库";
                sendMailService.sendMail(email,text);
                warehouseApplicationService.deleteById(id);
                return "redirect:/warehouseApplicationPage";
            }else{
                out.print("<script language=\"javascript\">alert('库存数量不足，无法出库！');window.location.href='/warehouseApplicationPage'</script>");
                return "warehouseApplication";
            }
        }
    }
    @GetMapping("/refuseApplication")
    public String refuseApplication(@RequestParam("id") long id){
        WarehouseApplicationDO warehouseApplicationDO=warehouseApplicationService.findById(id);
        String operatorId=warehouseApplicationDO.getOperatorId();
        String operation=warehouseApplicationDO.getOperation();
        String email=userService.userFind(operatorId).getUserEmail();
        String text="您的"+operation+"申请被否决，请检查信息是否正确，库存是否足够或是联系您的上级领导！";
        sendMailService.sendMail(email,text);
        warehouseApplicationService.deleteById(id);
        return "redirect:/warehouseApplicationPage";
    }
    //角色判断
    @GetMapping("/WarehouseAlcRoleJudge")
    public String roleJudge(HttpServletRequest request,HttpServletResponse response) throws IOException {
        HttpSession session= request.getSession();
        String userId=(String) session.getAttribute("loginUser");
        response.setContentType("text/html;charset=utf8");
        PrintWriter out = response.getWriter();
        Boolean role=userService.roleJudge(userId);
        if(role){
            return "redirect:/warehouseApplicationPage";
        }else{
            out.print("<script language=\"javascript\">alert('您不是管理员，没有权限进入该页面，将返回欢迎界面！');window.location.href='/welcome.html'</script>");
            return "welcome";
        }
    }
}
