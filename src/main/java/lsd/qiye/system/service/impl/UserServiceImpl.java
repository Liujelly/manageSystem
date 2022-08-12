package lsd.qiye.system.service.impl;

import lsd.qiye.system.dao.UserDAO;
import lsd.qiye.system.dataobject.UserDO;
import lsd.qiye.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDO userFind(String userId) {
        UserDO userDO=userDAO.findById_Pwd(userId,null);
        return userDO;
    }

    @Override
    public void updateInfo(String userId, String userName, String userSex, String userPhone, String userEmail, String department, String posts) {
        UserDO userDO=new UserDO();
        userDO.setUserId(userId);
        userDO.setUserName(userName);
        userDO.setUserSex(userSex);
        userDO.setUserPhone(userPhone);
        userDO.setUserEmail(userEmail);
        userDO.setDepartment(department);
        userDO.setPosts(posts);
        userDAO.update(userDO);
    }

    @Override
    public void updatePwd(String userId, String userPwd) {
        userDAO.updatePwd(userId,userPwd);
    }

    @Override
    public List<UserDO> findComplex(String department, String userName, String userId) {
        List<UserDO> userDOs=userDAO.findComplex(department, userName, userId);
        return userDOs;
    }

    @Override
    public List<UserDO> findAll() {
        List<UserDO> userDOS=userDAO.findAll();
        return userDOS;
    }

    @Override
    public void delete(String userId) {
        userDAO.delete(userId);
    }

    @Override
    public void insert(String userId, String userPwd, String userName, String userSex, String userPhone, String userEmail, String department, String posts,String role) {
        UserDO userDO = new UserDO();
        userDO.setUserId(userId);
        userDO.setUserPwd(userPwd);
        userDO.setUserName(userName);
        userDO.setUserSex(userSex);
        userDO.setUserPhone(userPhone);
        userDO.setUserEmail(userEmail);
        userDO.setDepartment(department);
        userDO.setPosts(posts);
        if ("admin".equals(role)) {
            userDO.setRole(1);
        } else {
            userDO.setRole(0);
        }
        userDAO.insert(userDO);

    }

    @Override
    public Boolean roleJudge(String useId) {
        UserDO userDO=userDAO.findById_Pwd(useId,null);
        if(userDO.getRole()==1){
            return true;
        }
        return false;
    }

}
