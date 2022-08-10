package lsd.qiye.system.dao;

import lsd.qiye.system.entity.AdminDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminDAO {

    AdminDO findById(@Param("adminId") String adminId);
}
