package lsd.qiye.system.dao;

import lsd.qiye.system.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDAO {

    UserDO loginFind(@Param("userId") String userId);

}
