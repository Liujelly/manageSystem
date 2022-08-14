package lsd.qiye.system.dao;

import lsd.qiye.system.dataobject.FinanceApplicationDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FinanceApplicationDAO {
    //添加一条申请记录
    int insert(FinanceApplicationDO financeApplicationDO);
    //查询所有申请
    List<FinanceApplicationDO> findAll();
    //根据财务单id查询
    List<FinanceApplicationDO> selectBy_fId(@Param("financeId") String financeId);
    // 审核后根据财务单id删除
    int deleteBy_fId(@Param("financeId") String financeId);
}
