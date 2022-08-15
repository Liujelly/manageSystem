package lsd.qiye.system.service.impl;

import lsd.qiye.system.dao.InWarehouseDAO;
import lsd.qiye.system.dataobject.InWarehouseDO;
import lsd.qiye.system.service.InWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service
public class InWarehouseServiceImpl implements InWarehouseService {
    @Autowired
    private InWarehouseDAO inWarehouseDAO;
    @Override
    public List<InWarehouseDO> findAll() {
        List<InWarehouseDO> inWarehouseDOS=inWarehouseDAO.findAll();
        return inWarehouseDOS;
    }

    @Override
    public void insert(String productId, String productName, String vendor, long operand, String operatorId, String reviewerId, Date now) {

        InWarehouseDO inWarehouseDO=new InWarehouseDO();
        inWarehouseDO.setProductId(productId);
        inWarehouseDO.setProductName(productName);
        inWarehouseDO.setVendor(vendor);
        inWarehouseDO.setOperand(operand);
        inWarehouseDO.setOperatorId(operatorId);
        inWarehouseDO.setReviewerId(reviewerId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String gmtCreated = sdf.format(now);
        inWarehouseDO.setGmtCreated(gmtCreated);
        inWarehouseDAO.insert(inWarehouseDO);
    }

    @Override
    public List<InWarehouseDO> findComplex(String keyWord, String time) {
        List<InWarehouseDO> inWarehouseDOS=inWarehouseDAO.findComplex(keyWord,time);
        return inWarehouseDOS;
    }
}
