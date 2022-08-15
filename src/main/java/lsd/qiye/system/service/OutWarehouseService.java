package lsd.qiye.system.service;

import lsd.qiye.system.dataobject.OutWarehouseDO;

import java.util.Date;
import java.util.List;

public interface OutWarehouseService {
    List<OutWarehouseDO> findAll();
    void insert(String productId, String productName, String whereabouts, long operand, String operatorId, String reviewerId, Date now);
    List<OutWarehouseDO> findComplex(String keyWord,String time);
}
