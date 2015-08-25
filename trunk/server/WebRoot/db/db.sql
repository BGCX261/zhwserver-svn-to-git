/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.1.55-community : Database - user
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`user` /*!40100 DEFAULT CHARACTER SET gbk */;

USE `user`;

/*Table structure for table `menu` */

DROP TABLE IF EXISTS `menu`;

CREATE TABLE `menu` (
  `id` int(11) NOT NULL COMMENT 'id',
  `pid` int(11) NOT NULL COMMENT '父id',
  `name` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `link` varchar(255) DEFAULT NULL COMMENT '菜单链接',
  `status` int(5) NOT NULL COMMENT '菜单状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='菜单表';

/*Data for the table `menu` */

insert  into `menu`(`id`,`pid`,`name`,`link`,`status`) values (1,0,'用户管理','',1),(2,1,'新增用户','/user/input.jhtml',1),(3,1,'用户列表','/user/list.jhtml',1);

/*Table structure for table `t_admin` */

DROP TABLE IF EXISTS `t_admin`;

CREATE TABLE `t_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=gbk COMMENT='管理员表';

/*Data for the table `t_admin` */

insert  into `t_admin`(`id`,`username`,`password`) values (1,'admin','21232f297a57a5a743894a0e4a801fc3');

/*Table structure for table `t_rest` */

DROP TABLE IF EXISTS `t_rest`;

CREATE TABLE `t_rest` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rest_key` varchar(255) NOT NULL,
  `rest_value` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=gbk;

/*Data for the table `t_rest` */

insert  into `t_rest`(`id`,`rest_key`,`rest_value`,`create_time`) values (3,'firsttest','firstvalueeeeeeeeeee','2012-03-21 16:11:26'),(4,'firsttest','firstvalueeeeeeeeeee','2012-03-21 16:12:46'),(5,'firsttest','firstvalueeeeeeeeeee','2012-03-21 16:24:53'),(6,'11111','2222','2012-03-21 16:25:53'),(7,'11111','2222','2012-03-21 16:33:53'),(8,'11111','2222','2012-03-21 16:34:36'),(9,'11111','2222','2012-03-21 16:35:57'),(10,'123456789','123123123','2012-03-22 16:28:59'),(11,'测试key','测试value','2012-03-22 16:30:05'),(12,'测试key','测试value','2012-03-22 16:31:39'),(13,'29540654',NULL,'2012-03-23 17:24:14'),(14,'keytest','valueTest','2012-03-23 17:41:52');

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userNo` varchar(20) DEFAULT NULL,
  `userName` varchar(50) DEFAULT NULL,
  `userStatus` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=gbk;

/*Data for the table `t_user` */

insert  into `t_user`(`userId`,`userNo`,`userName`,`userStatus`) values (3,'222','什么','1'),(9,'88','sadsa倒萨十大','0'),(13,'测试','测试','1'),(17,'倒萨倒萨','敖德萨2220','1'),(19,'22','22','1'),(20,'33','33','0'),(21,'44','44','1'),(22,'55','556','0'),(24,'1321321','321321321','1'),(25,'33','3333在','1'),(26,'','',NULL),(27,'11',NULL,NULL),(28,NULL,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
