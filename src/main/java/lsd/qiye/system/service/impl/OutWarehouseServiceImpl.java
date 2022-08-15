package lsd.qiye.system.service.impl;

import lsd.qiye.system.dao.OutWarehouseDAO;
import lsd.qiye.system.dataobject.InWarehouseDO;
import lsd.qiye.system.dataobject.OutWarehouseDO;
import lsd.qiye.system.service.OutWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OutWarehouseServiceImpl implements OutWarehouseService {
    @Autowired
    OutWarehouseDAO outWarehouseDAO;
    @Override
    public List<OutWarehouseDO> findAll() {
        List<OutWarehouseDO> outWarehouseDOS=outWarehouseDAO.findAll();
        return outWarehouseDOS;
    }

    @Override
    public void insert(String productId, String productName, String whereabouts, long operand, String operatorId, String reviewerId, Date now) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String gmtCreated = sdf.format(now);
        OutWarehouseDO outWarehouseDO=new OutWarehouseDO();
        outWarehouseDO.setProductId(productId);
        outWarehouseDO.setProductName(productName);
        outWarehouseDO.setWhereabouts(whereabouts);
        outWarehouseDO.setOperand(operand);
        outWarehouseDO.setOperatorId(operatorId);
        outWarehouseDO.setReviewerId(reviewerId);
        outWarehouseDO.setGmtCreated(gmtCreated);
        outWarehouseDAO.insert(outWarehouseDO);
    }

    @Override
    public List<OutWarehouseDO> findComplex(String keyWord, String time) {
        List<OutWarehouseDO> outWarehouseDOS=outWarehouseDAO.findComplex(keyWord,time);
        return outWarehouseDOS;
    }
}
