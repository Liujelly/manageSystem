package lsd.qiye.system.controller;

import lsd.qiye.system.dataobject.WarehouseDO;
import lsd.qiye.system.service.UserService;
import lsd.qiye.system.service.WarehouseService;
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
public class warehouseController{
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private UserService userService;
    //初始化库存界面，找出所有产品
    @GetMapping("/warehousePage")
    public String warehousePage(Model model){
        List<WarehouseDO> warehouseDOS=warehouseService.findAll();
        model.addAttribute("warehouseDOS",warehouseDOS);
        return "warehouse";
    }
    //复合模糊查询产品
    @PostMapping("/warehouseFind")
    public String warehouseFind(@RequestParam("category") String category, @RequestParam("productName") String productName,
                                @RequestParam("vendor") String vendor,@RequestParam("productId") String productId,Model model){
        List<WarehouseDO> warehouseDOS=warehouseService.findComplex(category,productName,vendor,productId);
        model.addAttribute("warehouseDOS",warehouseDOS);
        return "warehouse";
    }
    //角色判断，跳转到重定向的增加产品页面。
    @GetMapping("/addProductJudge")
    public String addProductJudge(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session=request.getSession();
        String loginUser=(String)session.getAttribute("loginUser");
        response.setContentType("text/html;charset=utf8");
        PrintWriter out = response.getWriter();
        boolean role=userService.roleJudge(loginUser);
        if(role){
            return "redirect:/addProductPage.html";
        }else{
            out.print("<script language=\"javascript\">alert('您不是管理员，没有权限使用此功能！');window.location.href='/warehousePage'</script>");
            return "warehouse";
        }
    }
    //实现增加产品，提交到数据库，并返回到初始界面
    @PostMapping("/addProduct")
    public String addProduct(@RequestParam("productId") String productId, @RequestParam("productName") String productName,
                             @RequestParam("vendor") String vendor, @RequestParam("category") String category, @RequestParam("number") long number){
        warehouseService.insert(productId,productName,vendor,category,number);
        return "redirect:/warehousePage";
    }
    //进行角色判断，跳转到修改页面并传递id信息
    @GetMapping("/updateProductPage")
    public String updateProductPage(@RequestParam("productId") String productId,Model model,HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session=request.getSession();
        String loginUser=(String)session.getAttribute("loginUser");
        response.setContentType("text/html;charset=utf8");
        PrintWriter out = response.getWriter();
        boolean role=userService.roleJudge(loginUser);
        if(role){
            WarehouseDO warehouseDO=warehouseService.findById(productId);
            model.addAttribute("product",warehouseDO);
            return "updateProduct";
        }else{
            out.print("<script language=\"javascript\">alert('您不是管理员，没有权限使用此功能！');window.location.href='/warehousePage'</script>");
            return "warehouse";
        }
    }
    //进行修改，传递数据到数据库
    @PostMapping("/updateProduct")
    public String updateProduct(@RequestParam("productId") String productId,@RequestParam("productName") String productName,
                             @RequestParam("vendor") String vendor,@RequestParam("category") String category,@RequestParam("number") long number){
        warehouseService.update(productId,productName,vendor,category,number);
        return "redirect:/warehousePage";
    }
}
