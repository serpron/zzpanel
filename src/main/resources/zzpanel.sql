/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50528
 Source Host           : localhost:3306
 Source Schema         : zzpanel

 Target Server Type    : MySQL
 Target Server Version : 50528
 File Encoding         : 65001

 Date: 16/11/2018 11:40:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_department
-- ----------------------------
DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '部门名称',
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  `parent_id` int(20) DEFAULT NULL COMMENT '上级部门编码',
  `create_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建时间',
  `parent_ids` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '祖先所有编号',
  `remark_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '部门备注编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `parent_id`(`parent_id`) USING BTREE,
  CONSTRAINT `sys_department_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `sys_department` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_department
-- ----------------------------
INSERT INTO `sys_department` VALUES (1, '河南省', '', NULL, NULL, '1', '');
INSERT INTO `sys_department` VALUES (2, '河北省', NULL, NULL, NULL, '2', NULL);
INSERT INTO `sys_department` VALUES (3, '湖南省', '', NULL, NULL, '3', NULL);
INSERT INTO `sys_department` VALUES (4, '湖北省', NULL, NULL, NULL, '4', NULL);
INSERT INTO `sys_department` VALUES (5, '郑州市', NULL, 1, NULL, '1/5', '0371');
INSERT INTO `sys_department` VALUES (6, '洛阳市', NULL, 1, NULL, '1/6', '0379');
INSERT INTO `sys_department` VALUES (7, '开封市', NULL, 1, NULL, '1/7', '0378');
INSERT INTO `sys_department` VALUES (8, '平顶山市', NULL, 1, NULL, '1/8', '0375');
INSERT INTO `sys_department` VALUES (9, '鹤壁市', NULL, 1, NULL, '1/9', '0392');
INSERT INTO `sys_department` VALUES (10, '新乡市', NULL, 1, NULL, '1/10', '0373');
INSERT INTO `sys_department` VALUES (11, '焦作市', NULL, 1, NULL, '1/11', '0391');
INSERT INTO `sys_department` VALUES (12, '济源市', NULL, 1, NULL, '1/12', '0391');
INSERT INTO `sys_department` VALUES (13, '濮阳市', NULL, 1, NULL, '1/13', '0393');
INSERT INTO `sys_department` VALUES (14, '许昌市', NULL, 1, NULL, '1/14', '0374');
INSERT INTO `sys_department` VALUES (15, '三门峡市', NULL, 1, NULL, '1/15', '0398');
INSERT INTO `sys_department` VALUES (16, '南阳市', NULL, 1, NULL, '1/16', '0377');
INSERT INTO `sys_department` VALUES (17, '商丘市', NULL, 1, NULL, '1/17', '0370');
INSERT INTO `sys_department` VALUES (18, '信阳市', NULL, 1, NULL, '1/18', '0376');
INSERT INTO `sys_department` VALUES (19, '周口市', NULL, 1, NULL, '1/19', '0394');
INSERT INTO `sys_department` VALUES (20, '驻马店市', NULL, 1, NULL, '1/20', '0396');
INSERT INTO `sys_department` VALUES (21, '偃师市', NULL, 6, NULL, '1/6/21', NULL);
INSERT INTO `sys_department` VALUES (22, '伊川县', NULL, 6, NULL, '1/6/22', NULL);
INSERT INTO `sys_department` VALUES (23, '汝阳县', NULL, 6, NULL, '1/6/23', NULL);
INSERT INTO `sys_department` VALUES (24, '嵩县', NULL, 6, NULL, '1/6/24', NULL);
INSERT INTO `sys_department` VALUES (25, '栾川', NULL, 6, NULL, '1/6/25', NULL);
INSERT INTO `sys_department` VALUES (26, '宜阳县', NULL, 6, NULL, '1/6/26', NULL);
INSERT INTO `sys_department` VALUES (27, '洛宁县', NULL, 6, NULL, '1/6/27', NULL);
INSERT INTO `sys_department` VALUES (28, '新安县', NULL, 6, NULL, '1/6/28', NULL);
INSERT INTO `sys_department` VALUES (29, '孟津县', NULL, 6, NULL, '1/6/29', NULL);
INSERT INTO `sys_department` VALUES (30, '洛龙区', NULL, 6, NULL, '1/6/30', NULL);
INSERT INTO `sys_department` VALUES (31, '西工区', NULL, 6, NULL, '1/6/31', NULL);
INSERT INTO `sys_department` VALUES (32, '老城区', NULL, 6, NULL, '1/6/32', NULL);
INSERT INTO `sys_department` VALUES (33, '涧西区', NULL, 6, NULL, '1/6/33', NULL);
INSERT INTO `sys_department` VALUES (34, '吉利区', NULL, 6, NULL, '1/6/34', NULL);
INSERT INTO `sys_department` VALUES (35, '廛河区', NULL, 6, NULL, '1/6/35', NULL);
INSERT INTO `sys_department` VALUES (36, '伊滨区', NULL, 6, NULL, '1/6/36', NULL);
INSERT INTO `sys_department` VALUES (37, '安阳市', NULL, 1, NULL, '1/37', NULL);
INSERT INTO `sys_department` VALUES (38, '漯河', NULL, 1, NULL, '1/38', NULL);

-- ----------------------------
-- Table structure for sys_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionary`;
CREATE TABLE `sys_dictionary`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typename` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_dictionary
-- ----------------------------
INSERT INTO `sys_dictionary` VALUES (1, 'menu_type', '1', '菜单');
INSERT INTO `sys_dictionary` VALUES (2, 'menu_type', '2', '按钮');
INSERT INTO `sys_dictionary` VALUES (3, 'user_sex', '1', '男');
INSERT INTO `sys_dictionary` VALUES (4, 'user_sex', '2', '女');

-- ----------------------------
-- Table structure for sys_front_route
-- ----------------------------
DROP TABLE IF EXISTS `sys_front_route`;
CREATE TABLE `sys_front_route`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '路由路径',
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '路由页面',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '路由名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_front_route
-- ----------------------------
INSERT INTO `sys_front_route` VALUES (1, '/users', '/views/users/list.html', '用户列表');
INSERT INTO `sys_front_route` VALUES (2, '/users/add', '/views/users/add.html', '添加用户');
INSERT INTO `sys_front_route` VALUES (3, '/users/edit', '/views/users/edit.html', '修改用户');
INSERT INTO `sys_front_route` VALUES (4, '/users/find', '/views/users/find.html', '查找用户');
INSERT INTO `sys_front_route` VALUES (5, '/roles', '/views/roles/list.html', '角色列表');
INSERT INTO `sys_front_route` VALUES (6, '/roles/add', '/views/roles/add.html', '添加用户');
INSERT INTO `sys_front_route` VALUES (7, '/roles/edit', '/views/roles/edit.html', '修改角色');
INSERT INTO `sys_front_route` VALUES (8, '/roles/find', '/views/roles/find.html', '查找角色');
INSERT INTO `sys_front_route` VALUES (9, '/departments', '/views/departments/list.html', '列表部门');
INSERT INTO `sys_front_route` VALUES (10, '/departments/add', '/views/departments/add.html', '添加部门');
INSERT INTO `sys_front_route` VALUES (11, '/departments/edit', '/views/departments/edit.html', '修改部门');
INSERT INTO `sys_front_route` VALUES (12, '/departments/find', '/views/departments/find.html', '查找部门');
INSERT INTO `sys_front_route` VALUES (13, '/resources', '/views/resources/list.html', '资源列表');
INSERT INTO `sys_front_route` VALUES (14, '/resources/add', '/views/resources/add.html', '添加资源');
INSERT INTO `sys_front_route` VALUES (15, '/resources/edit', '/views/resources/edit.html', '修改资源');
INSERT INTO `sys_front_route` VALUES (16, '/resources/find', '/views/resources/find.html', '查找资源');
INSERT INTO `sys_front_route` VALUES (17, '/', '/views/panel.html', '首页');

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '连接路径或方法',
  `menu_class` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'menu_dir' COMMENT '菜单样式',
  `menu_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` int(50) DEFAULT NULL COMMENT '上级菜单编号',
  `sequence` bigint(20) DEFAULT 0 COMMENT '排序',
  `menu_type` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '1' COMMENT '菜单类型(1是左导航菜单 2是按钮权限)',
  `create_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建时间',
  `permissions` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限',
  `parent_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '祖先菜单编号',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图标',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES (1, '/', 'menu_dir', '系统管理', NULL, 1, '1', NULL, 'system:list', '1', '&#xe62d;');
INSERT INTO `sys_resource` VALUES (2, '/users', 'menu_dir', '用户列表', 1, 21, '1', NULL, 'users:list', '1/2', '&#xe62d;');
INSERT INTO `sys_resource` VALUES (3, '/roles', 'menu_dir', '角色列表', 1, 31, '1', NULL, 'roles:list', '1/3', '&#xe63c;');
INSERT INTO `sys_resource` VALUES (9, '/departments', 'menu_dir', '部门列表', 1, 91, '1', NULL, 'departments:list', '1/9', '&#xe63c;');
INSERT INTO `sys_resource` VALUES (10, '/resources', 'menu_dir', '资源列表', 1, 101, '1', NULL, 'resources:list', '1/10', '&#xe63c;');
INSERT INTO `sys_resource` VALUES (11, NULL, 'menu_btn', '添加用户', 2, 22, '2', NULL, 'users:add', '1/2/11', NULL);
INSERT INTO `sys_resource` VALUES (12, NULL, 'menu_btn', '修改用户', 2, 23, '2', NULL, 'users:update', '1/2/12', NULL);
INSERT INTO `sys_resource` VALUES (13, NULL, 'menu_btn', '删除用户', 2, 24, '2', NULL, 'users:delete', '1/2/13', NULL);
INSERT INTO `sys_resource` VALUES (14, NULL, 'menu_btn', '添加角色', 3, 32, '2', NULL, 'roles:add', '1/3/14', NULL);
INSERT INTO `sys_resource` VALUES (15, NULL, 'menu_btn', '修改角色', 3, 33, '2', NULL, 'roles:update', '1/3/15', NULL);
INSERT INTO `sys_resource` VALUES (16, NULL, 'menu_btn', '删除角色', 3, 34, '2', NULL, 'roles:delete', '1/3/16', NULL);
INSERT INTO `sys_resource` VALUES (17, NULL, 'menu_btn', '添加部门', 9, 92, '2', NULL, 'departments:add', '1/9/17', NULL);
INSERT INTO `sys_resource` VALUES (18, NULL, 'menu_btn', '修改部门', 9, 93, '2', NULL, 'departments:update', '1/9/18', NULL);
INSERT INTO `sys_resource` VALUES (19, NULL, 'menu_btn', '删除部门', 9, 94, '2', NULL, 'departments:delete', '1/9/19', NULL);
INSERT INTO `sys_resource` VALUES (20, NULL, 'menu_btn', '添加资源', 10, 102, '2', NULL, 'resource:add', '1/10/20', NULL);
INSERT INTO `sys_resource` VALUES (21, NULL, 'menu_btn', '修改资源', 10, 103, '2', NULL, 'resource:update', '1/10/21', NULL);
INSERT INTO `sys_resource` VALUES (22, NULL, 'menu_btn', '删除资源', 10, 104, '2', NULL, 'resource:delete', '1/10/22', NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建时间',
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  `name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色中文名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, NULL, '', 'admin', '管理员');
INSERT INTO `sys_role` VALUES (2, NULL, '', 'operator', '操作员');
INSERT INTO `sys_role` VALUES (3, NULL, '', 'analyzer', '分析员');
INSERT INTO `sys_role` VALUES (32, '2018-11-04 21:06:51', 'test', 'test', '测试员');

-- ----------------------------
-- Table structure for sys_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键编号自增长',
  `resource_id` int(50) NOT NULL COMMENT '菜单编码',
  `role_id` int(40) NOT NULL COMMENT '角色编码',
  `menu_type` int(11) DEFAULT NULL COMMENT '菜单类型 1 导航 2 按钮',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE,
  INDEX `menu_id`(`resource_id`) USING BTREE,
  CONSTRAINT `sys_role_resource_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sys_role_resource_ibfk_2` FOREIGN KEY (`resource_id`) REFERENCES `sys_resource` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色菜单表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role_resource
-- ----------------------------
INSERT INTO `sys_role_resource` VALUES (8, 1, 1, NULL);
INSERT INTO `sys_role_resource` VALUES (9, 2, 1, NULL);
INSERT INTO `sys_role_resource` VALUES (10, 3, 1, NULL);
INSERT INTO `sys_role_resource` VALUES (11, 9, 1, NULL);
INSERT INTO `sys_role_resource` VALUES (12, 10, 1, NULL);
INSERT INTO `sys_role_resource` VALUES (13, 10, 2, NULL);
INSERT INTO `sys_role_resource` VALUES (14, 9, 3, NULL);
INSERT INTO `sys_role_resource` VALUES (16, 9, 32, NULL);
INSERT INTO `sys_role_resource` VALUES (18, 3, 32, NULL);
INSERT INTO `sys_role_resource` VALUES (19, 13, 1, NULL);
INSERT INTO `sys_role_resource` VALUES (20, 14, 1, NULL);
INSERT INTO `sys_role_resource` VALUES (21, 15, 1, NULL);
INSERT INTO `sys_role_resource` VALUES (22, 16, 1, NULL);
INSERT INTO `sys_role_resource` VALUES (23, 17, 1, NULL);
INSERT INTO `sys_role_resource` VALUES (24, 18, 1, NULL);
INSERT INTO `sys_role_resource` VALUES (25, 10, 1, NULL);
INSERT INTO `sys_role_resource` VALUES (26, 20, 1, NULL);
INSERT INTO `sys_role_resource` VALUES (27, 21, 1, NULL);
INSERT INTO `sys_role_resource` VALUES (28, 22, 1, NULL);
INSERT INTO `sys_role_resource` VALUES (29, 10, 32, NULL);
INSERT INTO `sys_role_resource` VALUES (30, 2, 32, NULL);
INSERT INTO `sys_role_resource` VALUES (31, 12, 1, NULL);
INSERT INTO `sys_role_resource` VALUES (32, 11, 1, NULL);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录账号',
  `pass` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录密码',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '昵称',
  `head` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机',
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `sex` int(11) DEFAULT 1 COMMENT '性别',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `register_time` date NOT NULL COMMENT '注册时间',
  `department_id` int(20) DEFAULT NULL COMMENT '部门编码',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '加密用的盐',
  `state` int(255) DEFAULT 1 COMMENT '1=正常，2=锁定',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_sys_user_login_account`(`account`) USING BTREE,
  INDEX `department_id`(`department_id`) USING BTREE,
  CONSTRAINT `sys_user_ibfk_1` FOREIGN KEY (`department_id`) REFERENCES `sys_department` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (5, 'admin', 'b445cccc88edb3b77d9c8f4410ae77100b4f5984d0339a1239526721e7144c4ef334b0321fa3937179cd407da6a2fb1f974695cf78517a59554fa6f48f59131d', '管理员', NULL, NULL, NULL, NULL, NULL, '2018-10-01', 1, '1517a7db33c8b1c648d6999ea5e48ff5', 1);
INSERT INTO `sys_user` VALUES (9, 'wukong', '8b30a4f88725c8fa60c8152e1019577bf41e9b56348e6895ca015a51d1aaf332c3b82f9de1497ca5106d84d1c765408de562aad4294e59236e587aec44a64915', '悟空', NULL, NULL, NULL, NULL, NULL, '2018-10-01', 9, '1aa3b61fffcf5cf2392f26635e730bd6', 1);
INSERT INTO `sys_user` VALUES (11, 'linghuchong', '225e7b4534b6ef981900ad3c852807de0994924c998eba9eee6c82e599248cbf0a317410f8b25eb38780d013c136b69b9e8cc5c93c2f19e75f1a4f0fa7c07c66', '令狐冲', NULL, NULL, NULL, NULL, NULL, '2018-10-01', 3, '8724aacfee97f32ed51c18e1502d70bb', 1);
INSERT INTO `sys_user` VALUES (12, 'shipotian', '9dd07feb362193aca9ecdc03fb7bc199efb491f7e55f24524e0eb346c5a3371f02e9bcddb13affb51cd712c0b4f9f94780aaefddd70e4c0b26849bc4415a14bf', '石破天', NULL, NULL, NULL, NULL, NULL, '2018-10-01', 4, '60df6b08cd02e679b6e9af6e9d2c0c7b', 1);
INSERT INTO `sys_user` VALUES (13, 'zhangwuji', '09152898838fc83ac59e904c60da2580063cd37fd5ea28846ce32247e8e0bf2c8a1c650270119bacadd47f50555766e73b218b7b808b88bfb1d1a63612f6000c', '张无忌', NULL, NULL, NULL, NULL, NULL, '2018-10-01', 5, '0cf94227ad941ab3d8f64d2732cde39a', 1);
INSERT INTO `sys_user` VALUES (14, 'qiaofeng', '2c72abdf55a5da75581867ea586c785cd75c207ad6af2d8881b1ea5e6db2ab1a1355e09827ebeae94ff522705bac8025dde2d3054d8aef5b81de5fd240c84276', '乔峰', NULL, NULL, NULL, NULL, NULL, '2018-10-01', 10, 'fadd5a04e0b4f6a4f00ae116b6efe1f8', 1);
INSERT INTO `sys_user` VALUES (15, 'xuzhu', '22473e7617b92d5113aafa784fb7535c5d6b5f9b64163ac12a0198890e2f741920c7fa6fbeae1b6e6959e909da55b058a65c3a0dbcfb193d2722d905a34a67fb', '虚竹', NULL, NULL, NULL, NULL, NULL, '2018-10-01', 18, 'd9e7359396c8b4a48ec0ceacf17994bf', 1);
INSERT INTO `sys_user` VALUES (16, 'duanyu', 'e7deaf09f26912f92c8b3a37e9a786a75003e5989abc9c61b1d12558d86f30e17b31f0a8f05c0b35fd30830f2c99c8e06224083dbcd085aaa82ee8460d3febf0', '段誉', NULL, NULL, NULL, NULL, NULL, '2018-10-01', 25, 'fde7b6c8f4b4a2535f5f38c029ed682d', 1);
INSERT INTO `sys_user` VALUES (45, 'luxiaofeng', '2e664293a71bc8b4bc6adab74ddbebd85c1741ef090f4fd1a7371053f17733880f62700f81170a0939c9d199a4159dfa16217631c6018da94030398a5bc0a7db', '陆小凤', '201810161003042270.jpg', '18613791111', 'luxiaofeng@qx.com', 1, '2018-10-15', '2018-10-15', 1, '492dc623760b3df8d66a3aad46dbe085', 1);
INSERT INTO `sys_user` VALUES (46, 'ddd', '2af6426cbbce657dc291ccd1667834db41baa3503a04d5868fe9cfd5c5e5ea2514bb9d09a048bc1d0a02bca7517a921a05b845ea7a80d92a85d8909a37cb076a', 'ddd', '201811160931451874.jpg', '18613791111', 'ddd@163.com', 1, '2018-11-15', '2018-11-16', 23, '30bd9942dfca9449eed9d65051a6b451', 1);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` int(20) NOT NULL COMMENT '用户编号',
  `role_id` int(20) NOT NULL COMMENT '角色编号',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_id`(`user_id`, `role_id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE,
  CONSTRAINT `sys_user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sys_user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色映射表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (5, 1, 22);
INSERT INTO `sys_user_role` VALUES (9, 32, 25);
INSERT INTO `sys_user_role` VALUES (11, 2, 23);
INSERT INTO `sys_user_role` VALUES (11, 32, 26);
INSERT INTO `sys_user_role` VALUES (12, 32, 27);
INSERT INTO `sys_user_role` VALUES (14, 3, 24);
INSERT INTO `sys_user_role` VALUES (45, 32, 28);

SET FOREIGN_KEY_CHECKS = 1;
