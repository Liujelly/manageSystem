package lsd.qiye.system.service.impl;

import lsd.qiye.system.dao.WarehouseApplicationDAO;
import lsd.qiye.system.dataobject.WarehouseApplicationDO;
import lsd.qiye.system.service.WarehouseApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WarehouseApplicationServiceImpl implements WarehouseApplicationService {
    @Autowired
    WarehouseApplicationDAO warehouseApplicationDAO;
    @Override
    public List<WarehouseApplicationDO> findAll() {
        List<WarehouseApplicationDO> warehouseApplicationDOS=warehouseApplicationDAO.findAll();
        return  warehouseApplicationDOS;
    }

    @Override
    public WarehouseApplicationDO findById(long id) {
        WarehouseApplicationDO warehouseApplicationDO=warehouseApplicationDAO.findById(id);
        return warehouseApplicationDO;
    }

    @Override
    public void deleteById(long id) {
        warehouseApplicationDAO.deleteById(id);
    }

    @Override
    public void insert(String productId, String productName, String upDown, String operation, long operand, String operatorId) {
        WarehouseApplicationDO warehouseApplicationDO=new WarehouseApplicationDO();
        warehouseApplicationDO.setProductId(productId);
        warehouseApplicationDO.setProductName(productName);
        warehouseApplicationDO.setUpDown(upDown);
        warehouseApplicationDO.setOperation(operation);
        warehouseApplicationDO.setOperand(operand);
        warehouseApplicationDO.setOperatorId(operatorId);
        warehouseApplicationDAO.insert(warehouseApplicationDO);
    }

}
