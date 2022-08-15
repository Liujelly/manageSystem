package lsd.qiye.system.controller;

import lsd.qiye.system.dataobject.InWarehouseDO;
import lsd.qiye.system.dataobject.WarehouseDO;
import lsd.qiye.system.service.InWarehouseService;
import lsd.qiye.system.service.WarehouseApplicationService;
import lsd.qiye.system.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class InWarehouseController {
    @Autowired
    InWarehouseService inWarehouseService;
    @Autowired
    WarehouseApplicationService warehouseApplicationService;
    //初始化入库单界面
    @GetMapping("/InWarehousePage")
    public String InWarehousePage(Model model){
       List<InWarehouseDO> inWarehouseDOS= inWarehouseService.findAll();
       model.addAttribute("inWarehouseDOS",inWarehouseDOS);
       return "InWarehouse";
    }
    //复合查询
    @PostMapping("/InWFindComplex")
    public String InWFindComplex(@RequestParam("keyWord") String keyWord,@RequestParam("time") String time,Model model){
        List<InWarehouseDO> inWarehouseDOS=inWarehouseService.findComplex(keyWord,time);
        model.addAttribute("inWarehouseDOS",inWarehouseDOS);
        return "InWarehouse";
    }
    //提交申请到审核表
    @PostMapping("/InWarehouseAlc")
    public  String InWarehouseAlc(@RequestParam("productId") String productId,@RequestParam("productName") String productName,
                                  @RequestParam("vendor") String vendor, @RequestParam("operand") long operand,
                                  HttpServletRequest request,Model model){
        HttpSession session=request.getSession();
        String operatorId=(String) session.getAttribute("loginUser");
        String operation="入库";
        warehouseApplicationService.insert(productId,productName,vendor,operation,operand,operatorId);
        return "redirect:/InWarehousePage";
    }
}
