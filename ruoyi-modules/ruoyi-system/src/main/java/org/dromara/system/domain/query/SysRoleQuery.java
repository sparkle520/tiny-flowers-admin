package org.dromara.system.domain.query;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.dromara.common.mybatis.core.domain.BaseEntity;

/**
 * 角色信息查询对象 sys_role
 *
 * @author hexm
 * @date 2023-04-12
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SysRoleQuery extends BaseEntity {

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色权限字符串
     */
    private String roleKey;

    /**
     * 角色状态（1正常 0停用）
     */
    private String status;

    public SysRoleQuery(Long roleId) {
        this.roleId = roleId;
    }
}
