package lsd.qiye.system.service;

import lsd.qiye.system.dataobject.FinanceDO;
import org.apache.ibatis.annotations.Param;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface FinanceService {

    void insertFinance(Date gmtCreated, String id, double companyDep, double onlinePay, double manualDep,
                       double arppu, double manualDed, double withdrawals, double fundFlow, double income,
                       double netProfit, String fillPerson);
    void deleteFinance(String id);
    List<FinanceDO> findComplex(String time,String fillPerson,String id);
    List<FinanceDO> findAll();
}
