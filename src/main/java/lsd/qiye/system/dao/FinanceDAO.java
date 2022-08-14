package lsd.qiye.system.dao;


import lsd.qiye.system.dataobject.FinanceDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Mapper
@Repository
public interface FinanceDAO {
    //通过id查找单个财务单
    FinanceDO findById(@Param("id") String id);
    //插入财务单数据
    int insert(FinanceDO financeDO);
    //修改财务单数据
    int update(FinanceDO financeDO);
    //根据id删除财务单
    int delete(@Param("id") String id);
    //复合查询，根据时间点（某个时间后的）和填写人
    List<FinanceDO> findComplex(@Param("time")String time,
                                @Param("fillPerson") String fillPerson,@Param("id") String id);
    //查找全部
    List<FinanceDO> findAll();
}
