package com.kcs.rest.dao;

import com.kcs.rest.pojo.User;
import com.kcs.rest.pojo.UserPresent;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserDao {
    User findUserById(int id);

    @Select("select * from [User] where loginName = #{loginName}")
    @Results({
            @Result(id = true,property = "userID",column = "userID"),
            @Result(property = "PositionID",column = "PositionID"),
            @Result(property = "DepartmentID",column = "DepartmentID"),
            @Result(property = "LoginName",column = "LoginName"),
            @Result(property = "Sex",column = "Sex"),
            @Result(property = "UserName",column = "UserName"),
            @Result(property = "Password",column = "Password"),
            @Result(property = "Tel",column = "Tel"),
            @Result(property = "Email",column = "Email"),
            @Result(property = "Photo",column = "Photo"),
            @Result(property = "Note",column = "Note"),
            @Result(property = "WarehouseMark",column = "WarehouseMark"),
            @Result(property = "ListerMark",column = "ListerMark"),
            @Result(property = "Status",column = "Status"),
            @Result(property = "roles",column = "userID",javaType = java.util.List.class,many = @Many(select = "com.kcs.rest.dao.RoleDao.findRoleByUserId"))
    })
    User findByLoginName(String loginName);

    List<UserPresent> findAllUserPresent(@Param("front") int front, @Param("back") int back, @Param("name") String name);

    List<User> findAllUser();

    int count(@Param("name") String name);

    List<User> findoneUser(String loginName);

    void updateBase(User user);

    void updatePass(User user);

    List<User> findAlllister();

    List<User> findAllWarehouse();

    List<User> findByName(@Param("name") String name);

    Integer addUser(User user);

    Integer delUserByUserID(int userID);

    UserPresent findUserPresentById(int id);

    Integer updateUser(User user);

    Integer lockUser(@Param("userID") int userID, @Param("status") Boolean status);
}