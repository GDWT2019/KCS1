package com.kcs.portal.service.impl;

import com.kcs.portal.service.PermissionService;
import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.pojo.Permission;
import com.kcs.rest.utils.HttpClientUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

    @Override
    public List<Permission> findAllPermission(int before, int after) {
        HashMap<String, String> param = new HashMap<>();
        param.put("before",before+ "");
        param.put("after",after+ "");
        try {
            String s = HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/permission/findAllPermission",param);
            KcsResult result = KcsResult.formatToList(s,Permission.class);
            if (result.getStatus() == 200) {
                return (List<Permission>) result.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer getPermissionCount() {
        try {
            String s = HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/permission/permissionCount");
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
    public List<Permission> findTheOthersPermissionByRoleID(int roleID) {
        try {
            String s = HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/permission/findTheOthersPermissionByRoleID"+roleID);
            KcsResult result = KcsResult.formatToList(s,Permission.class);
            if (result.getStatus() == 200) {
                List<Permission> permissionList = (List<Permission>) result.getData();
                return permissionList;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer addRolePermission(int roleID, int permissionID) {
        HashMap<String, String> param = new HashMap<>();
        param.put("roleID",roleID+"");
        param.put("permissionID",permissionID+"");
        try {
            String s = HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/permission/addRolePermission",param);
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
}
