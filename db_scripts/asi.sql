DROP DATABASE IF EXISTS asi;
CREATE DATABASE asi;
USE asi;

DROP TABLE IF EXISTS `asi`.`project`;
CREATE TABLE  `asi`.`project` (
  `id` bigint(20) NOT NULL auto_increment,
  `code` varchar(255) NOT NULL,
  `manager` varchar(255) default NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `asi`.`office`;
CREATE TABLE  `asi`.`office` (
  `id` bigint(20) NOT NULL auto_increment,
  `address` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `asi`.`employee`;
CREATE TABLE  `asi`.`employee` (
  `id` bigint(20) NOT NULL auto_increment,
  `contact_number` bigint(20) NOT NULL,
  `emp_code` varchar(255) NOT NULL default '',
  `first_name` varchar(255) NOT NULL,
  `last_expected_arrival_time` bigint(20) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `office_id` bigint(20) default NULL,
  `project_id` bigint(20) default NULL,
  `email_id` varchar(100) NOT NULL default '',
  `password` varchar(1000) NOT NULL default '',
  PRIMARY KEY  (`id`),
  KEY `FK4722E6AE7775355D` (`office_id`),
  KEY `FK4722E6AE87B78657` (`project_id`),
  CONSTRAINT `FK4722E6AE7775355D` FOREIGN KEY (`office_id`) REFERENCES `office` (`id`),
  CONSTRAINT `FK4722E6AE87B78657` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `asi`.`declaration_form`;
CREATE TABLE  `asi`.`declaration_form` (
  `id` bigint(20) NOT NULL auto_increment,
  `contact_number` bigint(20) NOT NULL,
  `expected_arrival_date_time` datetime NOT NULL,
  `leaving_date_time` datetime NOT NULL,
  `emp_id` bigint(20) default NULL,
  `office_id` bigint(20) default NULL,
  `project_id` bigint(20) default NULL,
  `status` varchar(2) default NULL,
  `remarks` varchar(1000) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKCBE3AC697775355D` (`office_id`),
  KEY `FKCBE3AC6987B78657` (`project_id`),
  KEY `FK_declaration_form_3` (`emp_id`),
  CONSTRAINT `FKCBE3AC697775355D` FOREIGN KEY (`office_id`) REFERENCES `office` (`id`),
  CONSTRAINT `FKCBE3AC6987B78657` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`),
  CONSTRAINT `FK_declaration_form_3` FOREIGN KEY (`emp_id`) REFERENCES `office` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;