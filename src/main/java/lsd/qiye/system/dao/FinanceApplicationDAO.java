package lsd.qiye.system.dao;

import lsd.qiye.system.dataobject.FinanceApplicationDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FinanceApplicationDAO {
    //添加一条申请记录
    int insert(FinanceApplicationDO financeApplicationDO);
}
