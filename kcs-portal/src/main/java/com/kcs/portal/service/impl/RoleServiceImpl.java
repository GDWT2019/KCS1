package com.kcs.portal.service.impl;

import com.kcs.portal.service.RoleService;
import com.kcs.rest.pojo.KcsResult;
import com.kcs.rest.pojo.Role;
import com.kcs.rest.utils.HttpClientUtil;
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
            String s = HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/role/findAllRole",param);
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
    public Integer addRole(Role role) {
        return null;
    }

    @Override
    public Integer getRoleCount(String roleName) {
        HashMap<String, String> param = new HashMap<>();
        param.put("roleName",roleName);
        try {
            String s = HttpClientUtil.doGet("http://localhost:8081/kcs_rest_war/role/getRoleCount",param);
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
        return null;
    }

    @Override
    public Integer delRole(int roleID) {
        return null;
    }
}
