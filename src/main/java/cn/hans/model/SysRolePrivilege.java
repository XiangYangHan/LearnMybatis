package cn.hans.model;

/**
 * @author : hans
 * @date : 2018/7/13 13:24
 */
public class SysRolePrivilege {

    private Long roleId;

    private Long privilegeId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Long privilegeId) {
        this.privilegeId = privilegeId;
    }
}
