package lsd.qiye.system.service.impl;

import lsd.qiye.system.dao.WarehouseDAO;
import lsd.qiye.system.dataobject.WarehouseDO;
import lsd.qiye.system.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WarehouseServiceImpl implements WarehouseService {
    @Autowired
    private WarehouseDAO warehouseDAO;
    @Override
    public List<WarehouseDO> findAll() {
        List<WarehouseDO> warehouseDOS=warehouseDAO.findAll();
        return warehouseDOS;
    }

    @Override
    public void insert(String productId, String productName, String vendor, String category, long number) {
        WarehouseDO warehouseDO=new WarehouseDO();
        warehouseDO.setProductId(productId);
        warehouseDO.setProductName(productName);
        warehouseDO.setVendor(vendor);
        warehouseDO.setCategory(category);
        warehouseDO.setNumber(number);
        warehouseDAO.insert(warehouseDO);
    }

    @Override
    public List<WarehouseDO> findComplex(String category, String productName, String vendor,String productId) {
        List<WarehouseDO> warehouseDOS=warehouseDAO.findComplex(category,productName,vendor,productId);
        return warehouseDOS;
    }

    @Override
    public void update(String productId, String productName, String vendor, String category, long number) {
        WarehouseDO warehouseDO=new WarehouseDO();
        warehouseDO.setProductId(productId);
        warehouseDO.setProductName(productName);
        warehouseDO.setVendor(vendor);
        warehouseDO.setCategory(category);
        warehouseDO.setNumber(number);
        warehouseDAO.update(warehouseDO);
    }

    @Override
    public WarehouseDO findById(String productId) {
        WarehouseDO warehouseDO=warehouseDAO.findById(productId);
        return warehouseDO;
    }
}
