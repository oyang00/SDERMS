# 数据库（sderms）

```mysql
CREATE DATABASE `sderms` CHARSET utf8;
```



## 学生表（student）

|  字段名   |      类型       |          约束           |   描述   |
| :-------: | :-------------: | :---------------------: | :------: |
|  account  |   varchar(20)   |          主键           | 学生学号 |
| password  |   varchar(50)   |                         |   密码   |
|   name    |   varchar(20)   |          非空           | 学生姓名 |
| dormitory | bigint unsigned | 外键，`dormitory`(`id`) |   宿舍   |

```mysql
CREATE TABLE `student`(
	`account` VARCHAR(20),
    `password` VARCHAR(50) NOT NULL,
    `name` VARCHAR(20) NOT NULL,
    `dormitory` BIGINT UNSIGNED,
    PRIMARY KEY(`account`),
    CONSTRAINT fk_student_dormitory FOREIGN KEY(`dormitory`) REFERENCES `dormitory`(`id`)
)CHARSET utf8 ENGINE INNODB;
```

## 管理员表（admin）

|  字段名  |    类型     | 约束 |    描述    |
| :------: | :---------: | :--: | :--------: |
| account  | varchar(20) | 主键 | 管理员账号 |
| password | varchar(50) |      |   密码`    |

```mysql
CREATE TABLE `admin`(
	`account` VARCHAR(20),
    `password` VARCHAR(50) NOT NULL,
    PRIMARY KEY(`account`)
)CHARSET utf8 ENGINE INNODB;
```

## 维修人员表（repair）

|  字段名  |    类型     | 约束 |    描述    |
| :------: | :---------: | :--: | :--------: |
| account  | varchar(20) | 主键 | 维修员账号 |
| password | varchar(50) |      |    密码    |

```mysql
CREATE TABLE `repair`(
	`account` VARCHAR(20),
    `password` VARCHAR(50) NOT NULL,
    PRIMARY KEY(`account`)
)CHARSET utf8 ENGINE INNODB;
```

## 宿舍表（dormitory）

| 字段名 |      类型       | 约束 |       描述       |
| :----: | :-------------: | :--: | :--------------: |
|   id   | bigint unsigned | 主键 | 宿舍 id，自增 id |
|  name  |   varchar(30)   |      |      宿舍名      |

```mysql
CREATE TABLE `dormitory`(
	`id` BIGINT UNSIGNED AUTO_INCREMENT,
    `name` VARCHAR(30) NOT NULL,
    PRIMARY KEY(`id`)
)CHARSET utf8 ENGINE INNODB;
```

## 报修表单表（form）

|    字段名     |       类型       |          约束          |                    描述                    |
| :-----------: | :--------------: | :--------------------: | :----------------------------------------: |
|      id       | bigint unsigned  |          主键          |            报修表单 id，自增 id            |
|    propose    |   varchar(20)    | 外键，student(account) |                   提出人                   |
| propose_title |       text       |          非空          |                  问题标题                  |
| propose_desc  |       text       |          非空          |                  问题描述                  |
| propose_time  |     datetime     |          非空          |                  提出时间                  |
|    repair     |   varchar(20)    | 外键，repair(account)  |                   维修人                   |
|  repair_desc  |       text       |                        |                  维修描述                  |
|  repair_time  |     datetime     |                        |                  维修时间                  |
|   is_handle   | tinyint unsigned |          非空          | 维修标记，0 为未处理，1 为已处理，默认为 0 |
|   feedback    |       text       |                        |                  学生反馈                  |

```mysql
CREATE TABLE `form`(
	`id` BIGINT UNSIGNED AUTO_INCREMENT,
    `propose` VARCHAR(20),
    `propose_title` TEXT NOT NULL,
    `propose_desc` TEXT NOT NULL,
    `propose_time` DATETIME NOT NULL,
    `repair` VARCHAR(20),
    `repair_desc` TEXT,
    `repair_time` DATETIME,
    `is_handle` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '0 为未处理，1 为已处理',
    `feedback` TEXT,
    PRIMARY KEY(`id`),
    CONSTRAINT fk_form_student FOREIGN KEY(`propose`) REFERENCES `student`(`account`),
    CONSTRAINT fk_form_repair FOREIGN KEY(`repair`) REFERENCES `repair`(`account`)
)CHARSET utf8 ENGINE INNODB;
```

## 执行 sql

```mysql
DROP DATABASE IF EXISTS `sderms`;
CREATE DATABASE `sderms` CHARSET utf8;
USE `sderms`;

CREATE TABLE `dormitory`(
	`id` BIGINT UNSIGNED AUTO_INCREMENT,
    `name` VARCHAR(30) NOT NULL,
    PRIMARY KEY(`id`)
)CHARSET utf8 ENGINE INNODB;

CREATE TABLE `student`(
	`account` VARCHAR(20),
    `password` VARCHAR(50) NOT NULL,
    `name` VARCHAR(20) NOT NULL,
    `dormitory` BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY(`account`),
    CONSTRAINT fk_student_dormitory FOREIGN KEY(`dormitory`) REFERENCES `dormitory`(`id`)
)CHARSET utf8 ENGINE INNODB;

CREATE TABLE `admin`(
	`account` VARCHAR(20),
    `password` VARCHAR(50) NOT NULL,
    PRIMARY KEY(`account`)
)CHARSET utf8 ENGINE INNODB;

CREATE TABLE `repair`(
	`account` VARCHAR(20),
    `password` VARCHAR(50) NOT NULL,
    PRIMARY KEY(`account`)
)CHARSET utf8 ENGINE INNODB;

CREATE TABLE `form`(
	`id` BIGINT UNSIGNED AUTO_INCREMENT,
    `propose` VARCHAR(20),
    `propose_title` TEXT NOT NULL,
    `propose_desc` TEXT NOT NULL,
    `propose_time` DATETIME NOT NULL,
    `repair` VARCHAR(20),
    `repair_desc` TEXT,
    `repair_time` DATETIME,
    `is_handle` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '0 为未处理，1 为已处理',
    `feedback` TEXT,
    PRIMARY KEY(`id`),
    CONSTRAINT fk_form_student FOREIGN KEY(`propose`) REFERENCES `student`(`account`),
    CONSTRAINT fk_form_repair FOREIGN KEY(`repair`) REFERENCES `repair`(`account`)
)CHARSET utf8 ENGINE INNODB;

INSERT INTO `admin` (`account`, `password`) VALUES ('admin', '14e1b600b1fd579f47433b88e8d85291');
```

