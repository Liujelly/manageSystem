package lsd.qiye.system.service;

import lsd.qiye.system.dataobject.UserDO;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    //查询个人信息
    UserDO userFind(String userId);
    //更新个人信息
    void updateInfo(String userId,String userName,String userSex,String userPhone,String userEmail,
                      String department,String posts);
    //修改密码
    void updatePwd(String userId,String userPwd);
    //复合查询员工
    List<UserDO> findComplex(String department, String userName,  String userId);
    //查找所有员工
    List<UserDO> findAll();
    //删除员工
    void delete(String userId);
    //增加员工
    void insert(String userId, String userPwd, String userName, String userSex, String userPhone, String userEmail, String department, String posts,String role);
    //角色判断
    Boolean roleJudge(String useId);
}
