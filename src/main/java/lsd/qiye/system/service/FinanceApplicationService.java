package lsd.qiye.system.service;

public interface FinanceApplicationService {
    //增加申请单
    void insert(String financeId,String applicantId,String applicationEmail);
}
