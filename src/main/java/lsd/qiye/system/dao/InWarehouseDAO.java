package lsd.qiye.system.dao;

import lsd.qiye.system.dataobject.InWarehouseDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface InWarehouseDAO {
    List<InWarehouseDO> findAll();
    int insert(InWarehouseDO inWarehouseDO);
    List<InWarehouseDO> findComplex(@Param("keyWord") String keyWord,@Param("time") String time);
}
