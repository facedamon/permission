drop table if exists `sys_dept`;
create table `sys_dept`(
  `id` int(11) not null auto_increment comment '部门ID',
  `name` varchar(20) not null default '' comment '部门名称',
  `parent_id` int(11) not null default '0' comment '上级部门id',
  `level` varchar(200) not null default '' comment '部门层级',
  `seq` int(11) not null default '0' comment '部门在当前层级下的顺序，由小到大',
  `remark` varchar(200) default '' comment '备注',
  `operator` varchar(20) not null default '' comment '操作者',
  `operator_time` datetime not null default CURRENT_TIMESTAMP comment '最后一次操作时间',
  `operator_ip` varchar(20) not null default '' comment '最后一次更新操作的ip地址',
  primary key (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT character set=utf8;

begin ;
insert into `sys_dept` values('1','技术部','0','0','1','技术部','system','2018-05-13 10:42:40','127.0.0.1');
commit;

drop table if exists `sys_user`;
create table `sys_user`(
  `id` int(11) not null auto_increment comment '用户ID',
  `username` varchar(20) not null default '' comment '用户名称',
  `telephone` varchar(13) not null default '' comment '手机号',
  `mail` varchar(20) not null default '' comment '邮箱',
  `password` varchar(40) not null default '' comment '加密后的密码',
  `dept_id` int(11) not null default '0' comment '用户所在部门ID',
  `status` int(11) not null default '1' comment '状态，1：正常，0：冻结状态，2：删除',
  `remark` varchar(200) default '' comment '备注',
  `operator` varchar(20) not null default '' comment '操作者',
  `operator_time` datetime not null default CURRENT_TIMESTAMP comment '最后一次操作时间',
  `operator_ip` varchar(20) not null default '' comment '最后一次更新操作的ip地址',
  primary key (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT character set=utf8;

drop table if exists `sys_role`;
create table `sys_role`(
  `id` int(11) not null auto_increment comment '角色ID',
  `name` varchar(20) not null,
  `type` int(11) not null default '1' comment '角色的类型，1：管理员角色，2：其它',
  `remark` varchar(200) default '' comment '备注',
  `operator` varchar(20) not null default '' comment '操作者',
  `operator_time` datetime not null default CURRENT_TIMESTAMP comment '最后一次操作时间',
  `operator_ip` varchar(20) not null default '' comment '最后一次更新操作的ip地址',
  primary key (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT character set=utf8;

drop table if exists `sys_acl_module`;
create table `sys_acl_module`(
  `id` int(11) not null auto_increment comment '权限模块ID',
  `name` varchar(20) not null default '' comment '权限模块名称',
  `parent_id` int(11) not null default '0' comment '上级权限模块ID',
  `level` varchar(200) not null default '' comment '权限模块层级',
  `seq` int(11) not null default '0' comment '权限模块在当前层级下的顺序，由小到大',
  `status` int(11) not null default '1' comment '状态，1：正常，0：冻结',
  `remark` varchar(200) default '' comment '备注',
  `operator` varchar(20) not null default '' comment '操作者',
  `operator_time` datetime not null default CURRENT_TIMESTAMP comment '最后一次操作时间',
  `operator_ip` varchar(20) not null default '' comment '最后一次更新操作的ip地址',
  primary key (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT character set=utf8;

DROP TABLE IF EXISTS `sys_acl`;
CREATE TABLE `sys_acl` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `code` varchar(20) NOT NULL DEFAULT '' COMMENT '权限码',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '权限名称',
  `acl_module_id` int(11) NOT NULL DEFAULT '0' COMMENT '权限所在的权限模块id',
  `url` varchar(100) NOT NULL DEFAULT '' COMMENT '请求的url, 可以填正则表达式',
  `type` int(11) NOT NULL DEFAULT '3' COMMENT '类型，1：菜单，2：按钮，3：其他',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态，1：正常，0：冻结',
  `seq` int(11) NOT NULL DEFAULT '0' COMMENT '权限在当前模块下的顺序，由小到大',
  `remark` varchar(200) DEFAULT '' COMMENT '备注',
  `operator` varchar(20) NOT NULL DEFAULT '' COMMENT '操作者',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
  `operate_ip` varchar(20) NOT NULL DEFAULT '' COMMENT '最后一个更新者的ip地址',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT character set=utf8;

DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '权限更新的类型，1：部门，2：用户，3：权限模块，4：权限，5：角色，6：角色用户关系，7：角色权限关系',
  `target_id` int(11) NOT NULL COMMENT '基于type后指定的对象id，比如用户、权限、角色等表的主键',
  `old_value` text COMMENT '旧值',
  `new_value` text COMMENT '新值',
  `operator` varchar(20) NOT NULL DEFAULT '' COMMENT '操作者',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新的时间',
  `operate_ip` varchar(20) NOT NULL DEFAULT '' COMMENT '最后一次更新者的ip地址',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '当前是否复原过，0：没有，1：复原过',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT character set=utf8;

DROP TABLE IF EXISTS `sys_role_acl`;
CREATE TABLE `sys_role_acl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `acl_id` int(11) NOT NULL COMMENT '权限id',
  `operator` varchar(20) NOT NULL DEFAULT '' COMMENT '操作者',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新的时间',
  `operate_ip` varchar(200) NOT NULL DEFAULT '' COMMENT '最后一次更新者的ip',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT character set=utf8;

DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `operator` varchar(20) NOT NULL DEFAULT '' COMMENT '操作者',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新的时间',
  `operate_ip` varchar(20) NOT NULL DEFAULT '' COMMENT '最后一次更新者的ip地址',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT character set=utf8;

