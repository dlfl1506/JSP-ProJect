# JSP로 오늘의집 사이트 사진 게시판 구현 


## 환경

- windows10
- jdk1.8
- tomcat9.0
- sts tool
- mysql8.0
- postman
- lombok
- gson (json파싱)
- 인코딩 utf-8
- git

## MySQL 데이터베이스 생성 및 사용자 생성

```sql
create user 'jsp_project'@'%' identified by 'bitc5600';
GRANT ALL privileges ON *.* TO 'jspproject'@'%';
create database jspprojectdb;
```

## MySQL 테이블 생성

- jspproject 사용자로 접속
- use jspprojectdb; 데이터 베이스 선택

```sql
create table user(
	id int auto_increment primary key,
    email varchar(50) not null unique,
    password varchar(50) not null,
    nickname varchar(50) not null unique,
    profile_img mediumblob,
    createDate timestamp
);

create table photo_board(
id int auto_increment primary key,
userId int,
photoImage varchar(50),
content longtext,
readCount int default 0,
favorite int default 0,
createDate timestamp,
foreign key (userId) references user (id)
);

CREATE TABLE reply(
    id int primary key auto_increment,
    userId int,
    boardId int,
    content varchar(300) not null,
    createDate timestamp,
    foreign key (userId) references user (id) on delete set null,
    foreign key (boardId) references photo_board (id) on delete cascade
) engine=InnoDB default charset=utf8;
```


