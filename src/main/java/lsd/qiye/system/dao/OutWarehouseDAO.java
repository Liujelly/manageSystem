package lsd.qiye.system.dao;

import lsd.qiye.system.dataobject.OutWarehouseDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OutWarehouseDAO {
    List<OutWarehouseDO> findAll();
    int insert(OutWarehouseDO outWarehouseDO);
    List<OutWarehouseDO> findComplex(@Param("keyWord") String keyWord,@Param("time") String time);
}
