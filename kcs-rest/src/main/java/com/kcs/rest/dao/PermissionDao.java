package com.kcs.rest.dao;

import com.kcs.rest.pojo.Permission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionDao {
    @Select("select * from Permission where PermissionID in (select PermissionID from RolePermission where RoleID = #{id})")
    List<Permission> findPermissionByRoleId(String id);

    List<Permission> findAllPermission(@Param("front")int front, @Param("back")int back);

    Integer permissionCount();

    @Select("select * from Permission where PermissionID not in (select PermissionID from RolePermission where RoleID = #{id})")
    List<Permission> findTheOthersPermissionByRoleID(int roleID);

    Integer addRolePermission(@Param("roleID") int rid, @Param("permissionID") int pid);
}
