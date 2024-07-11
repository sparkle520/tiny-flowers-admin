-- powerjob server控制台 update by 2023-7-11
delete from sys_menu where menu_id = 120;
insert into sys_menu values('120',  '任务调度中心',  '2',    '5', 'powerjob',           'monitor/powerjob/index',        '', 0, 1, 'C', '1', '1', 'monitor:powerjob:list',         'video',           103, 1, sysdate, null, null, 'PowerJob控制台菜单');

-- ----------------------------
-- 第三方平台授权表
-- ----------------------------
create table sys_social
(
    id                 number(20)       not null,
    user_id            number(20)       not null,
    tenant_id          varchar2(20)      default null,
    auth_id            varchar2(255)     not null,
    source             varchar2(255)     not null,
    open_id            varchar2(255)     default null,
    user_name          varchar2(30)      not null,
    nick_name          varchar2(30)      default '',
    email              varchar2(255)     default '',
    avatar             varchar2(500)     default '',
    access_token       varchar2(255)     not null,
    expire_in          number(20)        default null,
    refresh_token      varchar2(255)     default null,
    access_code        varchar2(255)     default null,
    union_id           varchar2(255)     default null,
    scope              varchar2(255)     default null,
    token_type         varchar2(255)     default null,
    id_token           varchar2(255)     default null,
    mac_algorithm      varchar2(255)     default null,
    mac_key            varchar2(255)     default null,
    code               varchar2(255)     default null,
    oauth_token        varchar2(255)     default null,
    oauth_token_secret varchar2(255)     default null,
    create_dept        number(20),
    create_by          number(20),
    create_time        date,
    update_by          number(20),
    update_time        date,
    del_flag           char(1)          default '0'
);

alter table sys_social add constraint pk_sys_social primary key (id);

comment on table   sys_social                   is '社会化关系表';
comment on column  sys_social.id                is '主键';
comment on column  sys_social.user_id           is '用户ID';
comment on column  sys_social.tenant_id         is '租户id';
comment on column  sys_social.auth_id           is '平台+平台唯一id';
comment on column  sys_social.source            is '用户来源';
comment on column  sys_social.open_id           is '平台编号唯一id';
comment on column  sys_social.user_name         is '登录账号';
comment on column  sys_social.nick_name         is '用户昵称';
comment on column  sys_social.email             is '用户邮箱';
comment on column  sys_social.avatar            is '头像地址';
comment on column  sys_social.access_token      is '用户的授权令牌';
comment on column  sys_social.expire_in         is '用户的授权令牌的有效期，部分平台可能没有';
comment on column  sys_social.refresh_token     is '刷新令牌，部分平台可能没有';
comment on column  sys_social.access_code       is '平台的授权信息，部分平台可能没有';
comment on column  sys_social.union_id          is '用户的 unionid';
comment on column  sys_social.scope             is '授予的权限，部分平台可能没有';
comment on column  sys_social.token_type        is '个别平台的授权信息，部分平台可能没有';
comment on column  sys_social.id_token          is 'id token，部分平台可能没有';
comment on column  sys_social.mac_algorithm     is '小米平台用户的附带属性，部分平台可能没有';
comment on column  sys_social.mac_key           is '小米平台用户的附带属性，部分平台可能没有';
comment on column  sys_social.code              is '用户的授权code，部分平台可能没有';
comment on column  sys_social.oauth_token       is 'Twitter平台用户的附带属性，部分平台可能没有';
comment on column  sys_social.oauth_token_secret is 'Twitter平台用户的附带属性，部分平台可能没有';
comment on column  sys_social.create_dept       is '创建部门';
comment on column  sys_social.create_by         is '创建者';
comment on column  sys_social.create_time       is '创建时间';
comment on column  sys_social.update_by         is '更新者';
comment on column  sys_social.update_time       is '更新时间';
comment on column  sys_social.del_flag          is '删除标志（0代表存在 1代表删除）';

-- ----------------------------
-- 系统授权表
-- ----------------------------
create table sys_client (
    id                  number(20)    not null,
    client_id           varchar2(64)   default null,
    client_key          varchar2(32)   default null,
    client_secret       varchar2(255)  default null,
    grant_type          varchar2(255)  default null,
    device_type         varchar2(32)   default null,
    active_timeout      number(11)    default 1800,
    timeout             number(11)    default 604800,
    status              char(1)       default '1',
    del_flag            char(1)       default '0',
    create_dept         number(20)    default null,
    create_by           number(20)    default null,
    create_time         date,
    update_by           number(20)    default null,
    update_time         date
);

alter table sys_client add constraint pk_sys_client primary key (id);

comment on table sys_client                         is '系统授权表';
comment on column sys_client.id                     is '主建';
comment on column sys_client.client_id              is '客户端id';
comment on column sys_client.client_key             is '客户端key';
comment on column sys_client.client_secret          is '客户端秘钥';
comment on column sys_client.grant_type             is '授权类型';
comment on column sys_client.device_type            is '设备类型';
comment on column sys_client.active_timeout         is 'token活跃超时时间';
comment on column sys_client.timeout                is 'token固定超时';
comment on column sys_client.status                 is '状态（1正常 0停用）';
comment on column sys_client.del_flag               is '删除标志（0代表存在 1代表删除）';
comment on column sys_client.create_dept            is '创建部门';
comment on column sys_client.create_by              is '创建者';
comment on column sys_client.create_time            is '创建时间';
comment on column sys_client.update_by              is '更新者';
comment on column sys_client.update_time            is '更新时间';

insert into sys_client values (1, 'e5cd7e4891bf95d1d19206ce24a7b32e', 'pc', 'pc123', 'password,social', 'pc', 1800, 604800, 1, 0, 103, 1, sysdate, 1, sysdate);
insert into sys_client values (2, '428a8310cd442757ae699df5d894f051', 'app', 'app123', 'password,sms,social', 'app', 1800, 604800, 1, 0, 103, 1, sysdate, 1, sysdate);

insert into sys_dict_type values(15, '000000', '授权类型', 'sys_grant_type',     '1', 103, 1, sysdate, null, null, '认证授权类型');
insert into sys_dict_type values(16, '000000', '设备类型', 'sys_device_type',    '1', 103, 1, sysdate, null, null, '客户端设备类型');

insert into sys_dict_data values(49, '000000', 0,  '密码认证', 'password',   'sys_grant_type',   '',   'default', 'N', '1', 103, 1, sysdate, null, null, '密码认证');
insert into sys_dict_data values(50, '000000', 0,  '短信认证', 'sms',        'sys_grant_type',   '',   'default', 'N', '1', 103, 1, sysdate, null, null, '短信认证');
insert into sys_dict_data values(51, '000000', 0,  '邮件认证', 'email',      'sys_grant_type',   '',   'default', 'N', '1', 103, 1, sysdate, null, null, '邮件认证');
insert into sys_dict_data values(52, '000000', 0,  '小程序认证', 'xcx',      'sys_grant_type',   '',   'default', 'N', '1', 103, 1, sysdate, null, null, '小程序认证');
insert into sys_dict_data values(53, '000000', 0,  '三方登录认证', 'social', 'sys_grant_type',   '',   'default', 'N', '1', 103, 1, sysdate, null, null, '三方登录认证');
insert into sys_dict_data values(54, '000000', 0,  'PC端',      'pc',        'sys_device_type',  '',   'default', 'N', '1', 103, 1, sysdate, null, null, 'PC端');
insert into sys_dict_data values(55, '000000', 0,  'APP端',     'app',       'sys_device_type',  '',   'default', 'N', '1', 103, 1, sysdate, null, null, 'APP端');

-- 二级菜单
insert into sys_menu values('123',  '客户端管理',   '1',   '12', 'client',           'system/client/index',          '', 0, 1, 'C', '1', '1', 'system:client:list',          'internet', 103, 1, sysdate, null, null, '客户端管理菜单');
-- 客户端管理按钮
insert into sys_menu values('1061', '客户端管理查询', '123', '1',  '#', '', '', 0, 1, 'F', '1', '1', 'system:client:query',        '#', 103, 1, sysdate, null, null, '');
insert into sys_menu values('1062', '客户端管理新增', '123', '2',  '#', '', '', 0, 1, 'F', '1', '1', 'system:client:add',          '#', 103, 1, sysdate, null, null, '');
insert into sys_menu values('1063', '客户端管理修改', '123', '3',  '#', '', '', 0, 1, 'F', '1', '1', 'system:client:edit',         '#', 103, 1, sysdate, null, null, '');
insert into sys_menu values('1064', '客户端管理删除', '123', '4',  '#', '', '', 0, 1, 'F', '1', '1', 'system:client:remove',       '#', 103, 1, sysdate, null, null, '');
insert into sys_menu values('1065', '客户端管理导出', '123', '5',  '#', '', '', 0, 1, 'F', '1', '1', 'system:client:export',       '#', 103, 1, sysdate, null, null, '');
