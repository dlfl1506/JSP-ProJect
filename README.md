# JSP로 오늘의집 사이트 사진 게시판 구현 


## 환경

- windows10
- jdk1.8
- tomcat9.0
- sts tool
- mysql8.0
- lombok
- gson (json파싱)
- 인코딩 utf-8
- git
- JSTL
- cos
- gson

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

## ⭕ 홈페이지 기능
- 메인화면 
![Screenshot_12](https://user-images.githubusercontent.com/74044212/107005754-8447f200-67d3-11eb-925c-bb1f70c8827f.png)

##### 인덱스 페이지 입니다. 
##### JSTL 을 이용해서 날짜가 가장 최근순으로 4개만 디비에 있는 데이터를 SELECT 해서 뿌려주었습니다. 

- 회원가입 화면
<img src= "https://user-images.githubusercontent.com/74044212/107006154-13eda080-67d4-11eb-9a62-839e530b2ade.png" width="300px" height="300px" />

- 로그인 화면 
<img src="https://user-images.githubusercontent.com/74044212/107006143-105a1980-67d4-11eb-9817-0b5a50171491.png" width="300px" height="300px" />

- 사진업로드 화면 

<img src ="https://user-images.githubusercontent.com/74044212/107007168-81e69780-67d5-11eb-834a-d66fea22bf4a.png" width="300px" height="300px" />
<hr/>
<img src="https://user-images.githubusercontent.com/74044212/107007404-d68a1280-67d5-11eb-84be-607f6b764a70.png" />

##### 로그인 했을시 사진 업로드 페이지 입니다.
##### 올리기 버튼 누를시 동시에 WebContent/uploads 폴더안에 사진을 저장하고 사진 이름, 내용을 디비에 저장했습니다.
##### 파일 업로드는 cos.jar 라이브러리를 이용해서 MultipartRequest 를 이용했습니다.

###### 업로드 소스코드
``` 
if (cmd.equals("upload")) {
			String saveFolder = "C:/mylab/springwork/JspProject/WebContent/uploads";
			String encType = "UTF-8";
			int maxSize = 5 * 1024 * 1024;
			try {
				MultipartRequest multi = null;
				multi = new MultipartRequest(request, saveFolder, maxSize, encType, new DefaultFileRenamePolicy());
				Enumeration<E> params = multi.getParameterNames();
				Enumeration files = multi.getFileNames();

				String userId = (String) params.nextElement();
				int userId_value = Integer.parseInt(multi.getParameter(userId));

				String content = (String) params.nextElement();
				String content_value = multi.getParameter(content);

				String photo_name = (String) files.nextElement();
				String filename = multi.getFilesystemName(photo_name);
				
				UploadReqDto dto = new UploadReqDto();
				dto.setUserId(userId_value);
				dto.setPhotoImage(filename);
				dto.setContent(content_value);
		
				int result = service.사진업로드(dto);
				if (result == 1) { // 정상
					response.sendRedirect("index.jsp");
				} else {
					Script.back(response, "사진업로드실패");
				}

			} catch (IOException ioe) {
				System.out.println(ioe);
			}
		
		}

```


- 사진 게시판
<img src="https://user-images.githubusercontent.com/74044212/107008304-f5d56f80-67d6-11eb-8f59-3eb6c38d8488.png" width="300px" height="300px" /> 
<hr/>
<img src="https://user-images.githubusercontent.com/74044212/107008351-0685e580-67d7-11eb-9a04-fe6fd2bd3e39.png" />

##### 사진 게시판 페이지입니다.
##### 게시판 디비에 있는 데이터를 유저 닉네임,이미지,내용(20자까지),조회수를 JSTL을 이용하여 뿌려주었습니다.
##### 사진 클릭시 조회수가 1 올라갑니다.

- 사진 상세보기
<img src="https://user-images.githubusercontent.com/74044212/107009104-f0c4f000-67d7-11eb-9a6a-998326b4e5c6.png" />

##### 사진 상세보기 페이지입니다. 
##### 사진 클릭시 이미지,내용 , 그리고 게시판에 있는 댓글을 뿌려주었고 세션이 같으면 삭제기능을 구현하였습니다.
##### 동시에 댓글작성도 가능하고 세션이 똑같은 Id 면 삭제기능 을 구현하였습니다.
##### 댓글 등록,삭제시 디비에 insert,delete 되고 ajax 를 통해 save,delete 하였습니다. 
