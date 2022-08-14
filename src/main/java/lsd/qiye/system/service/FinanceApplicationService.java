package lsd.qiye.system.service;

import java.util.Date;

public interface FinanceApplicationService {
    //增加申请单
    void insert(String financeId, String applicantId, String applicationEmail, String reason, Date now);
}
