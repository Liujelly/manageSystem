package lsd.qiye.system.dao;

import lsd.qiye.system.dataobject.WarehouseApplicationDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface WarehouseApplicationDAO {
    List<WarehouseApplicationDO> findAll();
    int deleteById(@Param("id") long id);
    int insert(WarehouseApplicationDO warehouseApplicationDO);
    WarehouseApplicationDO findById(long id);
}
