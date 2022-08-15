package lsd.qiye.system.service;

import lsd.qiye.system.dataobject.InWarehouseDO;

import java.util.Date;
import java.util.List;

public interface InWarehouseService {
    List<InWarehouseDO> findAll();
    void insert(String productId, String productName, String vendor, long operand, String operatorId, String reviewerId, Date gmtCreated);
    List<InWarehouseDO> findComplex( String keyWord, String time);
}
