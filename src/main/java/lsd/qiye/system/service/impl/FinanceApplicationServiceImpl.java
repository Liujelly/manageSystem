package lsd.qiye.system.service.impl;

import lsd.qiye.system.dao.FinanceApplicationDAO;
import lsd.qiye.system.dataobject.FinanceApplicationDO;
import lsd.qiye.system.service.FinanceApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinanceApplicationServiceImpl implements FinanceApplicationService {
    @Autowired
    FinanceApplicationDAO financeApplicationDAO;
    @Override
    public void insert(String financeId, String applicantId, String applicationEmail) {
        FinanceApplicationDO financeApplicationDO=new FinanceApplicationDO();
        financeApplicationDO.setApplicantId(applicantId);
        financeApplicationDO.setFinanceId(financeId);
        financeApplicationDO.setApplicantEmail(applicationEmail);
        financeApplicationDAO.insert(financeApplicationDO);
    }
}
