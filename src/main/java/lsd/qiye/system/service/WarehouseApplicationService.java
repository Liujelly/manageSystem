package lsd.qiye.system.service;

import lsd.qiye.system.dataobject.WarehouseApplicationDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WarehouseApplicationService {
    List<WarehouseApplicationDO> findAll();
    WarehouseApplicationDO findById(long id);
    void deleteById(long id);
    void insert(String productId, String productName, String upDown, String operation, long operand, String operatorId);
}
