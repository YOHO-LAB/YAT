/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.19 : Database - yat
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`yat` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `yat`;

/*Table structure for table `bsh_java_code` */

DROP TABLE IF EXISTS `bsh_java_code`;

CREATE TABLE `bsh_java_code` (
  `ops_id` int(11) NOT NULL,
  `bsh_args` varchar(1000) DEFAULT '',
  `code` text,
  PRIMARY KEY (`ops_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `data_source_loop` */

DROP TABLE IF EXISTS `data_source_loop`;

CREATE TABLE `data_source_loop` (
  `case_id` int(11) NOT NULL DEFAULT '0',
  `th_list` varchar(10000) DEFAULT '[]',
  `tr_list` text,
  PRIMARY KEY (`case_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `db` */

DROP TABLE IF EXISTS `db`;

CREATE TABLE `db` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `env_id` int(11) DEFAULT '0',
  `name` varchar(500) DEFAULT '',
  `note` varchar(1000) DEFAULT '',
  `add_time` datetime DEFAULT NULL,
  `add_user_id` int(11) DEFAULT '0',
  `update_time` datetime DEFAULT NULL,
  `update_user_id` int(11) DEFAULT '0',
  `ip` varchar(50) DEFAULT '',
  `port` int(5) DEFAULT '3306',
  `db_name` varchar(100) DEFAULT '',
  `user_name` varchar(100) DEFAULT '',
  `pass_word` varchar(100) DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_envId_name` (`env_id`,`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Table structure for table `environment` */

DROP TABLE IF EXISTS `environment`;

CREATE TABLE `environment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(500) DEFAULT '',
  `note` varchar(1000) DEFAULT '',
  `add_time` datetime DEFAULT NULL,
  `add_user_id` int(11) DEFAULT '0',
  `update_time` datetime DEFAULT NULL,
  `update_user_id` int(11) DEFAULT '0',
  `host_url` varchar(100) DEFAULT '',
  `project_id` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_prjId_name` (`project_id`,`name`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Table structure for table `feedback` */

DROP TABLE IF EXISTS `feedback`;

CREATE TABLE `feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT '0',
  `feedback_type` int(11) DEFAULT '0',
  `feedback_data` varchar(1000) DEFAULT '',
  `add_time` datetime DEFAULT NULL,
  `is_resolved` tinyint(1) DEFAULT '0',
  `resolve_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Table structure for table `operation` */

DROP TABLE IF EXISTS `operation`;

CREATE TABLE `operation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `env_id` int(11) DEFAULT '0',
  `name` varchar(500) DEFAULT '',
  `note` varchar(1000) DEFAULT '',
  `ops_type` int(1) DEFAULT '0',
  `add_user_id` int(11) DEFAULT '0',
  `add_time` datetime DEFAULT NULL,
  `update_user_id` int(11) DEFAULT '0',
  `update_time` datetime DEFAULT NULL,
  `db_id` int(11) DEFAULT '0',
  `db_sql` varchar(1000) DEFAULT '',
  `http_is_post` tinyint(1) DEFAULT '0',
  `http_url` varchar(500) DEFAULT '',
  `http_param` varchar(1000) DEFAULT '',
  `tc_id` int(11) DEFAULT '0',
  `tc_val_list` varchar(5000) DEFAULT '[]',
  `java_code` varchar(1000) DEFAULT '',
  `wait_time` int(10) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_envId_name` (`env_id`,`name`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8;

/*Table structure for table `parameter` */

DROP TABLE IF EXISTS `parameter`;

CREATE TABLE `parameter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `env_id` int(11) DEFAULT '0',
  `name` varchar(500) DEFAULT '',
  `note` varchar(1000) DEFAULT '',
  `param_type` int(1) DEFAULT '1' COMMENT '1:kV;2:SQL;3:TESTCASE',
  `add_user_id` int(11) DEFAULT '0',
  `add_time` datetime DEFAULT NULL,
  `update_user_id` int(11) DEFAULT '0',
  `update_time` datetime DEFAULT NULL,
  `kv_val` varchar(500) DEFAULT '',
  `db_id` int(11) DEFAULT '0',
  `db_sql` varchar(1000) DEFAULT '',
  `db_column` varchar(100) DEFAULT '',
  `db_get_val_type` int(1) DEFAULT '0',
  `tc_id` int(11) DEFAULT '0',
  `tc_get_val_type` int(1) DEFAULT '0',
  `tc_json_path` varchar(500) DEFAULT '',
  `tc_json_match_num` int(10) DEFAULT '0',
  `tc_left` varchar(500) DEFAULT '',
  `tc_right` varchar(500) DEFAULT '',
  `tc_lr_match_num` int(10) DEFAULT '0',
  `tc_cookie` varchar(100) DEFAULT '',
  `tc_header` varchar(100) DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_envId_name` (`env_id`,`name`)
) ENGINE=InnoDB AUTO_INCREMENT=149 DEFAULT CHARSET=utf8;

/*Table structure for table `project` */

DROP TABLE IF EXISTS `project`;

CREATE TABLE `project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT '',
  `note` varchar(1000) DEFAULT '',
  `add_user_id` int(11) DEFAULT '0',
  `add_time` datetime DEFAULT NULL,
  `update_user_id` int(11) DEFAULT '0',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Table structure for table `record` */

DROP TABLE IF EXISTS `record`;

CREATE TABLE `record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT '0',
  `operation` varchar(100) DEFAULT '',
  `operate_data` varchar(1000) DEFAULT '',
  `operate_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3806 DEFAULT CHARSET=utf8;

/*Table structure for table `run_summary` */

DROP TABLE IF EXISTS `run_summary`;

CREATE TABLE `run_summary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(1000) DEFAULT '',
  `user_id` int(11) DEFAULT '0',
  `is_ci` tinyint(1) DEFAULT '0',
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `log` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2779 DEFAULT CHARSET=utf8;

/*Table structure for table `service` */

DROP TABLE IF EXISTS `service`;

CREATE TABLE `service` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(500) DEFAULT '',
  `note` varchar(1000) DEFAULT '',
  `add_time` datetime DEFAULT NULL,
  `add_user_id` int(11) DEFAULT '0',
  `update_time` datetime DEFAULT NULL,
  `update_user_id` int(11) DEFAULT '0',
  `project_id` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_prjId_name` (`project_id`,`name`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

/*Table structure for table `team` */

DROP TABLE IF EXISTS `team`;

CREATE TABLE `team` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(500) DEFAULT '',
  `note` varchar(1000) DEFAULT '',
  `add_time` datetime DEFAULT NULL,
  `add_user_id` int(11) DEFAULT '0',
  `update_time` datetime DEFAULT NULL,
  `update_user_id` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Table structure for table `testcase` */

DROP TABLE IF EXISTS `testcase`;

CREATE TABLE `testcase` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `team_id` int(11) DEFAULT '0',
  `status` int(1) DEFAULT '0' COMMENT '0删除 1未ci 2ci',
  `service_id` int(11) DEFAULT '0',
  `test_env_id` int(11) DEFAULT '0',
  `method` varchar(100) DEFAULT '',
  `is_post` tinyint(1) DEFAULT '0',
  `url` varchar(2000) DEFAULT '',
  `parameters` varchar(2000) DEFAULT '',
  `note` varchar(500) DEFAULT '',
  `pre_ops_ids` varchar(100) DEFAULT '',
  `after_test_ops_ids` varchar(100) DEFAULT '',
  `post_ops_ids` varchar(100) DEFAULT '',
  `http_code_check` varchar(100) DEFAULT '200',
  `contain_check` varchar(500) DEFAULT '',
  `not_contain_check` varchar(500) DEFAULT '',
  `json_check` varchar(10000) DEFAULT '[]',
  `db_check` varchar(1000) DEFAULT '[]',
  `add_time` datetime DEFAULT NULL,
  `add_user_id` int(11) DEFAULT '0',
  `update_time` datetime DEFAULT NULL,
  `update_user_id` int(11) DEFAULT '0',
  `cookie_list` varchar(1000) DEFAULT '[]',
  `header_list` varchar(1000) DEFAULT '[]',
  `get_http_res_list` varchar(1000) DEFAULT '[]',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_serviceId_envId_note` (`service_id`,`test_env_id`,`note`)
) ENGINE=InnoDB AUTO_INCREMENT=309 DEFAULT CHARSET=utf8;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT '',
  `password` varchar(50) DEFAULT '',
  `username_cn` varchar(50) DEFAULT '',
  `email` varchar(50) DEFAULT '',
  `department` int(11) DEFAULT '0',
  `group_name` int(11) DEFAULT '0',
  `add_time` datetime DEFAULT NULL,
  `login_time` datetime DEFAULT NULL,
  `role_id` varchar(1000) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
