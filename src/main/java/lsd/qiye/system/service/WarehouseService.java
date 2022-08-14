package lsd.qiye.system.service;

import lsd.qiye.system.dataobject.WarehouseDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WarehouseService {

    List<WarehouseDO> findAll();
    void insert(String productId,String productName,String vendor,String category,long number);
    List<WarehouseDO> findComplex(String category,  String productName, String vendor,String productId);
    void update(String productId,String productName,String vendor,String category,long number);
    WarehouseDO findById(String productId);
}
