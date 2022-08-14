package lsd.qiye.system.service;

import lsd.qiye.system.dataobject.FinanceApplicationDO;

import java.util.Date;
import java.util.List;

public interface FinanceApplicationService {
    //增加申请单
    void insert(String financeId, String applicantId, String applicationEmail, String reason, Date now);
    //查询所有申请
    List<FinanceApplicationDO> findAll();
    //根据财务单id删除
    void deleteBy_fId(String financeId);
    //根据财务单id查询对应邮件
    List<String> findEmails(String financeId);
}
