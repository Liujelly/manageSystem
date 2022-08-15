package lsd.qiye.system.dao;

import lsd.qiye.system.dataobject.UserDO;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDAO {
    //单人通过id和密码查询
    UserDO findById_Pwd(@Param("userId") String userId,@Param("userPwd") String userPwd);
    //插入数据
    int insert(UserDO userDO);
    //修改数据
    int update(UserDO userDO);
    //修改密码
    int updatePwd(@Param("userId") String userId,@Param("newPwd") String newPwd);
    //根据id删除用户
    int delete(@Param("userId") String userId);
    //复合查询(部门，名字，id）
    List<UserDO> findComplex(@Param("department") String department,
                             @Param("userName") String userName, @Param("userId") String userId);
    //查找全
    List<UserDO> findAll();


}
