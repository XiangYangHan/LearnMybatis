package cn.hans.mapper;

import cn.hans.model.SysPrivilege;
import cn.hans.provider.PrivilegeProvider;
import org.apache.ibatis.annotations.SelectProvider;

public interface PrivilegeMapper {

    @SelectProvider(type = PrivilegeProvider.class, method = "selectById")
    SysPrivilege selectById(Long id);
}
