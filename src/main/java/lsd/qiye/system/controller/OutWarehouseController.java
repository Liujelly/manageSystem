package lsd.qiye.system.controller;


import lsd.qiye.system.dataobject.OutWarehouseDO;
import lsd.qiye.system.service.OutWarehouseService;
import lsd.qiye.system.service.WarehouseApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OutWarehouseController {
    @Autowired
    OutWarehouseService outWarehouseService;
    @Autowired
    WarehouseApplicationService warehouseApplicationService;
    //初始化出库单界面
    @GetMapping("/OutWarehousePage")
    public String OutWarehousePage(Model model){
        List<OutWarehouseDO> outWarehouseDOS= outWarehouseService.findAll();
        model.addAttribute("outWarehouseDOS",outWarehouseDOS);
        return "OutWarehouse";
    }
    //复合查询
    @PostMapping("/OutWFindComplex")
    public String OutWFindComplex(@RequestParam("keyWord") String keyWord, @RequestParam("time") String time, Model model){
        List<OutWarehouseDO> outWarehouseDOS= outWarehouseService.findComplex(keyWord,time);
        model.addAttribute("outWarehouseDOS",outWarehouseDOS);
        return "OutWarehouse";
    }
    //提交申请到审核表
    @PostMapping("/OutWarehouseAlc")
    public  String OutWarehouseAlc(@RequestParam("productId") String productId, @RequestParam("productName") String productName,
                                  @RequestParam("whereabouts") String whereabouts, @RequestParam("operand") long operand,
                                  HttpServletRequest request, Model model){
        HttpSession session=request.getSession();
        String operatorId=(String) session.getAttribute("loginUser");
        String operation="出库";
        warehouseApplicationService.insert(productId,productName,whereabouts,operation,operand,operatorId);
        return "redirect:/OutWarehousePage";
    }
}
