# XBangumi
一个用来记录自己动画漫画观看进度的小项目
使用到的技术：springboot\mybatis\shiro\thymeleaf\layui\mysql

## 数据库
### ER图
![image](https://user-images.githubusercontent.com/53892437/128622505-295d8d30-194b-4bf4-9639-9bd0a3eeb9ca.png)
### 建表SQL语句
```sql
create database xbgm;
use xbgm;

create table `user`(
	`id` int(7) unsigned not null auto_increment comment '用户id',
    `username` varchar(20) not null comment '用户名',
    `email` varchar(50) comment '用户邮箱',
    `password` varchar(50) not null comment '用户密码',
    `tele` varchar(20) default '' comment '电话号码',
    `birthday` datetime default null comment '生日',
    `introduction` varchar(100) default '' comment '签名',
    primary key(`id`)
)engine=InnoDB default charset=utf8mb4;

create table `anime`(
	`animeId` int(11) unsigned not null auto_increment comment '动画条目id',
    `animeName` varchar(50) not null comment '动画名',
    `episode` int unsigned not null comment '动画总话数',
    `playDate` datetime default null comment '首次播放日期',
    `director` varchar(20) default '' comment '导演',
    `company` varchar(20) default '' comment '制作公司',
    `poster` varchar(100) default '' comment '动画海报URL',
    `recordNumber` varchar(30) default '' comment '动画备案号',
    primary key(`animeId`)
)engine=InnoDB default charset=utf8mb4;

create table `watch`(
	`watchId` int (11) unsigned not null auto_increment comment '观看记录id',
    `animeId` int(11) unsigned not null comment '动画条目id' ,
    `userId` int(7) unsigned not null comment '用户id',
    `currentEpisode` int unsigned not null comment '当前记录话数',
    `currentState` int unsigned not null comment '当前观看状态',
    -- 状态，1表示想看，2表示在看，3表示看过
    `score` int unsigned default 0 comment '评分',
    `comment` varchar(200) default '' comment '评论',
    primary key(`watchId`),
    foreign key(`userId`) references `user`(`id`),
    foreign key(`animeId`) references `anime`(`animeId`)
)engine=InnoDB default charset=utf8mb4;

create table `comic`(
	`comicId` int(11) unsigned not null auto_increment comment '漫画条目id',
    `comicName` varchar(50) not null comment '漫画名',
    `page` int unsigned not null comment '漫画总页数',
    `publicationDate` datetime default null comment '出版日期',
    `author` varchar(20) default '' comment '作者',
    `press` varchar(50) default '' comment '出版社',
    `cover` varchar(100) default '' comment '漫画封面URL',
    `isbn` varchar(30) default '' comment '漫画书籍ISBN',
    primary key(`comicId`)
)engine=InnoDB default charset=utf8mb4;

create table `read`(
	`readId` int (11) unsigned not null auto_increment comment '阅读记录id',
    `comicId` int(11) unsigned not null comment '漫画条目id' ,
    `userId` int(7) unsigned not null comment '用户id',
    `currentPage` int unsigned not null comment '当前记录页数',
    `currentState` int unsigned not null comment '当前观看状态',
    -- 状态，1表示在看，2表示看过
    `score` int unsigned default 0 comment '评分',
    `comment` varchar(200) default '' comment '评论',
    primary key(`readId`),
    foreign key(`userId`) references `user`(`id`),
    foreign key(`comicId`) references `comic`(`comicId`)
)engine=InnoDB default charset=utf8mb4;
```
