package lsd.qiye.system.service.impl;

import lsd.qiye.system.dao.FinanceApplicationDAO;
import lsd.qiye.system.dataobject.FinanceApplicationDO;
import lsd.qiye.system.service.FinanceApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FinanceApplicationServiceImpl implements FinanceApplicationService {
    @Autowired
    FinanceApplicationDAO financeApplicationDAO;
    @Override
    public void insert(String financeId, String applicantId, String applicationEmail, String reason, Date now) {
        FinanceApplicationDO financeApplicationDO=new FinanceApplicationDO();
        financeApplicationDO.setApplicantId(applicantId);
        financeApplicationDO.setFinanceId(financeId);
        financeApplicationDO.setApplicantEmail(applicationEmail);
        financeApplicationDO.setReason(reason);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String gmtCreated = sdf.format(now);
        financeApplicationDO.setGmtCreated(gmtCreated);
        financeApplicationDAO.insert(financeApplicationDO);
    }

    @Override
    public List<FinanceApplicationDO> findAll() {
        List<FinanceApplicationDO> financeApplicationDOS=financeApplicationDAO.findAll();
        return financeApplicationDOS;
    }

    @Override
    public void deleteBy_fId(String financeId) {
        financeApplicationDAO.deleteBy_fId(financeId);
    }

    @Override
    public List<String> findEmails(String financeId) {
        List<String> emails=new ArrayList<>();
        List<FinanceApplicationDO> financeApplicationDOS=financeApplicationDAO.selectBy_fId(financeId);
        for(FinanceApplicationDO  financeApplicationDO:financeApplicationDOS){
            emails.add(financeApplicationDO.getApplicantEmail());
        }
        return emails;
    }
}
