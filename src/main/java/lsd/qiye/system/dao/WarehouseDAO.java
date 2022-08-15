package lsd.qiye.system.dao;

import lsd.qiye.system.dataobject.WarehouseDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface WarehouseDAO {
    List<WarehouseDO> findAll();
    int insert(WarehouseDO warehouseDO);
    List<WarehouseDO> findComplex(@Param("category")String category, @Param("productName") String productName,
                                  @Param("vendor") String vendor,@Param("productId") String productId);
    int update(WarehouseDO warehouseDO);
    WarehouseDO findById(@Param("productId") String productId);

}
