package com.kcs.portal.service.impl;

import com.kcs.portal.service.RoleService;
import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.pojo.Role;
import com.kcs.rest.pojo.RolePresent;
import com.kcs.rest.utils.HttpClientUtil;
import com.kcs.rest.utils.JsonUtils;
import com.kcs.rest.utils.Rest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Override
    public List<Role> findAllRole(int begin, int end) {
        HashMap<String, String> param = new HashMap<>();
        param.put("begin",begin+ "");
        param.put("end",end+ "");
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"role/findAllRole",param);
            KcsResult result = KcsResult.formatToList(s, Role.class);
            if (result.getStatus() == 200) {
                List<Role> roleList = (List<Role>) result.getData();
                return roleList;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer addRole(String roleName) {
        try {
            String s = HttpClientUtil.doPost(Rest.rest+"role/addRole"+roleName);
            KcsResult result = KcsResult.format(s);
            if (result.getStatus() == 200) {
                return (Integer) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer getRoleCount(String roleName) {
        HashMap<String, String> param = new HashMap<>();
        param.put("roleName",roleName);
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"role/getRoleCount",param);
            KcsResult result = KcsResult.format(s);
            if (result.getStatus() == 200) {
                Integer count = (Integer) result.getData();
                return count;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Role> findRoleByRoleName(int begin, int end, String roleName) {
        return null;
    }

    @Override
    public Integer updateRole(Role role) {
        Integer i = 0;
        try {
            String s = HttpClientUtil.doPostJson(Rest.rest+"role/updateRole", JsonUtils.objectToJson(role));
            KcsResult result = KcsResult.format(s);
            if (result.getStatus() == 200) {
                i = (Integer) result.getData();

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public Integer delRole(int roleID) {
        Integer i = 0;
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"role/delRole"+roleID);
            KcsResult result = KcsResult.format(s);
            if (result.getStatus() == 200) {
                i = (Integer) result.getData();

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public List<Role> findTheOthersRoleByUserID(int userID) {
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"role/findTheOthersRoleByUserID"+userID);
            KcsResult result = KcsResult.formatToList(s,Role.class);
            if (result.getStatus() == 200) {
                List<Role> roleList = (List<Role>) result.getData();
                return roleList;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer addUserRole(int userID, int roleID) {
        HashMap<String, String> param = new HashMap<>();
        param.put("userID",userID+"");
        param.put("roleID",roleID+"");
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"role/addUserRole",param);
            KcsResult result = KcsResult.format(s);
            if (result.getStatus() == 200) {
                Integer count = (Integer) result.getData();
                return count;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer delUserRoleByUserID_RoleID(int userID, int roleID) {
        HashMap<String, String> param = new HashMap<>();
        param.put("userID",userID+"");
        param.put("roleID",roleID+"");
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"role/delUserRoleByUserID_RoleID",param);
            KcsResult result = KcsResult.format(s);
            if (result.getStatus() == 200) {
                Integer count = (Integer) result.getData();
                return count;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<RolePresent> findAllRolePresent(int front, int back, int roleID) {
        HashMap<String, String> param = new HashMap<>();
        param.put("front",front+"");
        param.put("back",back+"");
        param.put("roleID",roleID+"");
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"role/findAllRolePresent",param);
            KcsResult result = KcsResult.formatToList(s,RolePresent.class);
            if (result.getStatus() == 200) {
                return (List<RolePresent>) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int findRolePresentCount(int roleID) {
        HashMap<String, String> param = new HashMap<>();
        param.put("roleID",roleID+"");
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"role/findRolePresentCount",param);
            KcsResult result = KcsResult.format(s);
            if (result.getStatus() == 200) {
                Integer count = (Integer) result.getData();
                return count;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Role findRoleByID(int roleID) {
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"role/findRoleByID"+roleID);
            KcsResult result = KcsResult.formatToPojo(s,Role.class);
            if (result.getStatus() == 200) {
                Role role = (Role) result.getData();
                return role;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int delRolePermission(int roleID, int permissionID) {
        HashMap<String, String> param = new HashMap<>();
        param.put("roleID",roleID+"");
        param.put("permissionID",permissionID+"");
        try {
            String s = HttpClientUtil.doGet(Rest.rest+"role/delRolePermission",param);
            KcsResult result = KcsResult.format(s);
            if (result.getStatus() == 200) {
                Integer count = (Integer) result.getData();
                return count;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
