-- powerjob server控制台 update by 2023-7-11
delete from sys_menu where menu_id = 120;
insert into sys_menu values('120',  '任务调度中心',  '2',   '5',  'powerjob',           'monitor/powerjob/index',        '', 0, 1, 'C', '1', '1', 'monitor:powerjob:list',          'video',           103, 1, sysdate(), null, null, 'PowerJob控制台菜单');

-- ----------------------------
-- 第三方平台授权表
-- ----------------------------
drop table if exists sys_social;
create table sys_social
(
    id                 bigint           not null        comment '主键',
    user_id            bigint           not null        comment '用户ID',
    tenant_id          varchar(20)      default null    comment '租户id',
    auth_id            varchar(255)     not null        comment '平台+平台唯一id',
    source             varchar(255)     not null        comment '用户来源',
    open_id            varchar(255)     default null    comment '平台编号唯一id',
    user_name          varchar(30)      not null        comment '登录账号',
    nick_name          varchar(30)      default ''      comment '用户昵称',
    email              varchar(255)     default ''      comment '用户邮箱',
    avatar             varchar(500)     default ''      comment '头像地址',
    access_token       varchar(255)     not null        comment '用户的授权令牌',
    expire_in          int              default null    comment '用户的授权令牌的有效期，部分平台可能没有',
    refresh_token      varchar(255)     default null    comment '刷新令牌，部分平台可能没有',
    access_code        varchar(255)     default null    comment '平台的授权信息，部分平台可能没有',
    union_id           varchar(255)     default null    comment '用户的 unionid',
    scope              varchar(255)     default null    comment '授予的权限，部分平台可能没有',
    token_type         varchar(255)     default null    comment '个别平台的授权信息，部分平台可能没有',
    id_token           varchar(255)     default null    comment 'id token，部分平台可能没有',
    mac_algorithm      varchar(255)     default null    comment '小米平台用户的附带属性，部分平台可能没有',
    mac_key            varchar(255)     default null    comment '小米平台用户的附带属性，部分平台可能没有',
    code               varchar(255)     default null    comment '用户的授权code，部分平台可能没有',
    oauth_token        varchar(255)     default null    comment 'Twitter平台用户的附带属性，部分平台可能没有',
    oauth_token_secret varchar(255)     default null    comment 'Twitter平台用户的附带属性，部分平台可能没有',
    create_dept        bigint(20)                       comment '创建部门',
    create_by          bigint(20)                       comment '创建者',
    create_time        datetime                         comment '创建时间',
    update_by          bigint(20)                       comment '更新者',
    update_time        datetime                         comment '更新时间',
    del_flag           char(1)          default '0'     comment '删除标志（0代表存在 1代表删除）',
    PRIMARY KEY (id)
) engine=innodb comment = '社会化关系表';

-- ----------------------------
-- 系统授权表
-- ----------------------------
drop table if exists sys_client;
create table sys_client (
    id                  bigint(20)    not null            comment 'id',
    client_id           varchar(64)   default null        comment '客户端id',
    client_key          varchar(32)   default null        comment '客户端key',
    client_secret       varchar(255)  default null        comment '客户端秘钥',
    grant_type          varchar(255)  default null        comment '授权类型',
    device_type         varchar(32)   default null        comment '设备类型',
    active_timeout      int(11)       default 1800        comment 'token活跃超时时间',
    timeout             int(11)       default 604800      comment 'token固定超时',
    status              char(1)       default '1'         comment '状态（1正常 0停用）',
    del_flag            char(1)       default '0'         comment '删除标志（0代表存在 1代表删除）',
    create_dept         bigint(20)    default null        comment '创建部门',
    create_by           bigint(20)    default null        comment '创建者',
    create_time         datetime      default null        comment '创建时间',
    update_by           bigint(20)    default null        comment '更新者',
    update_time         datetime      default null        comment '更新时间',
    primary key (id)
) engine=innodb comment='系统授权表';

insert into sys_client values (1, 'e5cd7e4891bf95d1d19206ce24a7b32e', 'pc', 'pc123', 'password,social', 'pc', 1800, 604800, 1, 0, 103, 1, sysdate(), 1, sysdate());
insert into sys_client values (2, '428a8310cd442757ae699df5d894f051', 'app', 'app123', 'password,sms,social', 'app', 1800, 604800, 1, 0, 103, 1, sysdate(), 1, sysdate());

insert into sys_dict_type values(15, '000000', '授权类型', 'sys_grant_type',     '1', 103, 1, sysdate(), null, null, '认证授权类型');
insert into sys_dict_type values(16, '000000', '设备类型', 'sys_device_type',    '1', 103, 1, sysdate(), null, null, '客户端设备类型');

insert into sys_dict_data values(49, '000000', 0,  '密码认证', 'password',   'sys_grant_type',   '',   'default', 'N', '1', 103, 1, sysdate(), null, null, '密码认证');
insert into sys_dict_data values(50, '000000', 0,  '短信认证', 'sms',        'sys_grant_type',   '',   'default', 'N', '1', 103, 1, sysdate(), null, null, '短信认证');
insert into sys_dict_data values(51, '000000', 0,  '邮件认证', 'email',      'sys_grant_type',   '',   'default', 'N', '1', 103, 1, sysdate(), null, null, '邮件认证');
insert into sys_dict_data values(52, '000000', 0,  '小程序认证', 'xcx',      'sys_grant_type',   '',   'default', 'N', '1', 103, 1, sysdate(), null, null, '小程序认证');
insert into sys_dict_data values(53, '000000', 0,  '三方登录认证', 'social', 'sys_grant_type',   '',   'default', 'N', '1', 103, 1, sysdate(), null, null, '三方登录认证');
insert into sys_dict_data values(54, '000000', 0,  'PC端', 'pc',          'sys_device_type',     '',   'default', 'N', '1', 103, 1, sysdate(), null, null, 'PC端');
insert into sys_dict_data values(55, '000000', 0,  'APP端', 'app',        'sys_device_type',     '',   'default', 'N', '1', 103, 1, sysdate(), null, null, 'APP端');

-- 二级菜单
insert into sys_menu values('123',  '客户端管理',   '1',   '12', 'client',           'system/client/index',          '', 0, 1, 'C', '1', '1', 'system:client:list',          'internet', 103, 1, sysdate(), null, null, '客户端管理菜单');
-- 客户端管理按钮
insert into sys_menu values('1061', '客户端管理查询', '123', '1',  '#', '', '', 0, 1, 'F', '1', '1', 'system:client:query',        '#', 103, 1, sysdate(), null, null, '');
insert into sys_menu values('1062', '客户端管理新增', '123', '2',  '#', '', '', 0, 1, 'F', '1', '1', 'system:client:add',          '#', 103, 1, sysdate(), null, null, '');
insert into sys_menu values('1063', '客户端管理修改', '123', '3',  '#', '', '', 0, 1, 'F', '1', '1', 'system:client:edit',         '#', 103, 1, sysdate(), null, null, '');
insert into sys_menu values('1064', '客户端管理删除', '123', '4',  '#', '', '', 0, 1, 'F', '1', '1', 'system:client:remove',       '#', 103, 1, sysdate(), null, null, '');
insert into sys_menu values('1065', '客户端管理导出', '123', '5',  '#', '', '', 0, 1, 'F', '1', '1', 'system:client:export',       '#', 103, 1, sysdate(), null, null, '');

-- 角色菜单权限
insert into sys_role_menu values ('2', '1061');
insert into sys_role_menu values ('2', '1062');
insert into sys_role_menu values ('2', '1063');
insert into sys_role_menu values ('2', '1064');
insert into sys_role_menu values ('2', '1065');
