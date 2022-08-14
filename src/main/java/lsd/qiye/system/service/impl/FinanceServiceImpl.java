package lsd.qiye.system.service.impl;

import lsd.qiye.system.dao.FinanceDAO;
import lsd.qiye.system.dao.UserDAO;
import lsd.qiye.system.dataobject.FinanceDO;
import lsd.qiye.system.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class FinanceServiceImpl implements FinanceService {
    @Autowired
    private FinanceDAO financeDAO;

    @Override
    public FinanceDO findById(String id) {
        return null;
    }

    @Override
    public void insertFinance(Date now, String id, double companyDep, double onlinePay, double manualDep, double arppu,
                              double manualDed, double withdrawals, double fundFlow, double income, double netProfit, String fillPerson) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String gmtCreated = sdf.format(now);
        FinanceDO financeDO=new FinanceDO();
        financeDO.setGmtCreated(gmtCreated);
        financeDO.setId(id);
        financeDO.setCompanyDep(companyDep);
        financeDO.setOnlinePay(onlinePay);
        financeDO.setManualDep(manualDep);
        financeDO.setArppu(arppu);
        financeDO.setManualDed(manualDed);
        financeDO.setWithdrawals(withdrawals);
        financeDO.setFundFlow(fundFlow);
        financeDO.setIncome(income);
        financeDO.setNetProfit(netProfit);
        financeDO.setFillPerson(fillPerson);
        financeDAO.insert(financeDO);
    }

    @Override
    public void updateFinance(String id, double companyDep, double onlinePay, double manualDep, double arppu, double manualDed, double withdrawals, double fundFlow, double income, double netProfit, String fillPerson) {

    }

    @Override
    public void deleteFinance(String id) {
        financeDAO.delete(id);
    }

    @Override
    public List<FinanceDO> findComplex(String time, String fillPerson, String id) {
        List<FinanceDO> financeDOS=financeDAO.findComplex(time,fillPerson,id);
        return financeDOS;
    }

    @Override
    public List<FinanceDO> findAll() {
        List<FinanceDO> financeDOS= financeDAO.findAll();
        return financeDOS;
    }
}
