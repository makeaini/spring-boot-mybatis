-- --------------------------------------------------------
-- 主机:                           localhost
-- 服务器版本:                        5.6.25 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  8.1.0.4649
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 test 的数据库结构
CREATE DATABASE IF NOT EXISTS `test` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `test`;


-- 导出  表 test.teacher 结构
CREATE TABLE IF NOT EXISTS `teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `teacher_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- 正在导出表  test.teacher 的数据：~2 rows (大约)
DELETE FROM `teacher`;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` (`id`, `teacher_name`) VALUES
	(1, 'tearcher1'),
	(2, 'tearcher2'),
	(3, 'tearcher3');
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;


-- 导出  表 test.user 结构
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `user_password` varchar(50) NOT NULL,
  `user_age` int(11) NOT NULL,
  `user_sex` varchar(50) NOT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- 正在导出表  test.user 的数据：~13 rows (大约)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `user_name`, `user_password`, `user_age`, `user_sex`, `teacher_id`) VALUES
	(1, 'liuwenzhong', '22', 1, 'MAN', 3),
	(2, 'liuwenzhong', '22', 1, 'MAN', 3),
	(3, 'ldjfldjf', '22', 2, 'MAN', 3),
	(4, '4444', '22', 44, 'MAN', 3),
	(5, 'dfdfdf', '22', 33, 'MAN', 2),
	(6, '222', 'pass', 344, 'MAN', 2),
	(7, '332', 'pass', 2, 'MAN', 1),
	(11, 'liuwenzhong1', 'pass', 1, 'MAN', 1),
	(12, 'liuwenzhong2', 'pass', 222, 'MAN', 1),
	(13, 'liuwenzhong', 'password', 1, 'MAN', 1),
	(14, 'liuwenzhong', 'password', 1, 'WOMAN', 1),
	(16, 'adduser1', '122', 10, 'MAN', NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
