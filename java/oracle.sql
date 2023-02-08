SELECT * FROM PRODUCT;

CREATE SEQUENCE WJH.emp_seq
INCREMENT BY 1
START WITH 1
MINVALUE 1
MAXVALUE 9999
NOCYCLE
NOCACHE
NOORDER
;

SELECT * FROM MEMBER;
SELECT * FROM PRODUCT;

CREATE TABLE PRODUCT(
   P_NUM INT NOT NULL PRIMARY KEY,         -- PK --
   P_STATUS VARCHAR(20) DEFAULT '판매중',
   P_CATEGORY VARCHAR(30) NOT NULL,
   P_NAME VARCHAR(200) NOT NULL,
   P_PRICE INT NOT NULL,
   P_CNT INT NULL,
   P_INFO VARCHAR(1000) NULL,
   P_INFOIMG VARCHAR(1000) NULL,
   P_IMG VARCHAR(1000) NULL,
   P_SALE INT DEFAULT 0               -- 할인율 --
);

DROP TABLE PRODUCT;

CREATE TABLE ADDRESS(
	A_NUM INT NOT NULL PRIMARY KEY,		-- PK --
	MEMBERID VARCHAR(30) NOT NULL,		-- FK --
	A_ADDRESS VARCHAR(300) NOT NULL,
	A_POST INT NULL
);
SELECT * FROM ADDRESS;
DROP TABLE ADDRESS;

CREATE TABLE ORDERS(
	O_NUM INT NOT NULL PRIMARY KEY, 	-- PK --
	P_NUM INT NOT NULL,					-- FK --
	M_ID VARCHAR(50) NOT NULL,
	M_NAME VARCHAR(50) NOT NULL,
	M_PHONE VARCHAR(50) NOT NULL,
	O_DATE DATE NOT NULL,
	O_CNT INT NOT NULL,					-- 주문수량 --
	O_MEMO VARCHAR(300) NULL,
	O_PAYMENT INT DEFAULT 0,		-- 결제상태 --
	O_PAYMENT_METHOD VARCHAR(100) NOT NULL,	-- 결제방식 --
	O_STATE INT DEFAULT 0				-- 배송상태 --
);
SELECT * FROM ORDERS;
SELECT * FROM ORDERS WHERE O_NUM = 1339;
DROP TABLE ORDERS;
SELECT * FROM ORDERS ORDER BY O_NUM DESC;
SELECT * FROM ORDERS WHERE M_ID='admin1' ORDER BY O_NUM DESC;

UPDATE PRODUCT 
SET P_CATEGORY = ?,
P_NAME = ?, 
P_PRICE = ?, 
P_CNT = ?, 
P_IMG = ?, 
P_INFO = ?, 
P_INFOIMG = ?, 
P_SALE = ? 
P_STATUS = ? 
WHERE P_NUM = ?

SELECT * FROM PRODUCT;
SELECT * FROM ORDERS;
SELECT * FROM ADDRESS;

--ORDERS 샘플데이터 업데이트 필요(안 됨, 모델은 상호협의가 필요합니다)
INSERT INTO PRODUCT VALUES((SELECT NVL(MAX(P_NUM),0)+1 FROM PRODUCT),'판매중', '수조', '이름어항', 24000, 12, '깨짐', '','', 10);
INSERT INTO ORDERS VALUES((SELECT NVL(MAX(O_NUM),0)+1 FROM ORDERS),700, 'aaa111', SYSDATE, 1, '',1, '현금결제', 0);
INSERT INTO ORDERS VALUES((SELECT NVL(MAX(O_NUM),0)+1 FROM ORDERS),701, 'adimin1', SYSDATE, 2, '',1, '카드결제', 0);
INSERT INTO ORDERS VALUES((SELECT NVL(MAX(O_NUM),0)+1 FROM ORDERS),589, 'nam123', SYSDATE, 1, '',1, '카카오뱅크', 0);
INSERT INTO ADDRESS VALUES((SELECT NVL(MAX(A_NUM),0)+1 FROM ADDRESS), 'kim','신림동 1234-1', '58922');

--카테고리별 상품출력
SELECT * FROM PRODUCT WHERE P_CATEGORY = ?
--할인
UPDATE PRODUCT SET P_SALE = 1 WHERE P_NUM = 1048;
UPDATE PRODUCT SET P_SALE = 1 WHERE P_NUM = 1049;

------------------------------------------------------------------
CREATE TABLE MEMBER (
	MEMBERID	VARCHAR(40)	NOT NULL PRIMARY KEY,
	MEMBERPW	VARCHAR(20)	NOT NULL,
	MEMBERNAME	VARCHAR(20)	NOT NULL,
	MEMBERBIRTH	DATE NOT NULL,
	MEMBERPHONE	VARCHAR(40)	NOT NULL,
	MEMBEREMAIL	VARCHAR(50)	NULL,
	MEMBERATHU	VARCHAR(16)	DEFAULT 'MEMBER'
);

DROP TABLE MEMBER;
--MEMBERBIRTH	DATE NOT NULL,

CREATE TABLE BOARD (
	BOARDNUM INT NOT NULL PRIMARY KEY,
	BOARDTITLE	VARCHAR(100) NOT NULL,
	BOARDCONTENT VARCHAR(500) NOT NULL,
    MEMBERID VARCHAR(40) NOT NULL,
    BOARDIMG VARCHAR(300) NULL,
    BOARDDATE DATE NOT NULL
);

DROP TABLE BOARD;
SELECT * FROM BOARD;

CREATE TABLE MSG (
	MSGNUM	INT	NOT NULL PRIMARY KEY,
	MSGEMAIL	VARCHAR(100)  NOT NULL,
	MSGTITLE    VARCHAR(100)   NOT NULL,
	MSGCONTENT VARCHAR(1000) NOT NULL,
	MSGDATE	DATE	NOT NULL	
);

DROP TABLE MSG;


CREATE TABLE MSGREPLY (
	MSGREPLYNUM	INT	NOT NULL PRIMARY KEY,
    MSGNUM  INT	NOT NULL,
    MEMBERID VARCHAR(40) NOT NULL,
    MSGREPLYTITLE VARCHAR(100) NOT NULL,
	MSGREPLYCONTENT VARCHAR(1000)	NOT NULL,
	MSGREPLYDATE	DATE	NOT NULL
);

DROP TABLE MSGREPLY;

INSERT INTO MEMBER VALUES('mmm1','!admin000','관리자00',SYSDATE,'01099991111','c@naver.com','ADMIN');
INSERT INTO MEMBER VALUES('admin1','!admin000','관리자1',SYSDATE,'01099991111','c@naver.com','admin');

INSERT INTO MSG VALUES(44,'mmm@naver.com','문의합니다','문의합니다AAAAAAAAAAAAAAAAAAAA',SYSDATE);

INSERT INTO MSGREPLY VALUES(13,1,'admin1','반갑습니다~','문의답장입니다',SYSDATE);

INSERT INTO BOARD VALUES((SELECT NVL(MAX(BOARDNUM),0)+1 FROM BOARD),'제목입니다','내용입니다','admin1','src8',SYSDATE);

SELECT * FROM MEMBER;
SELECT * FROM MSG;
SELECT * FROM MSGREPLY;
SELECT * FROM BOARD;

--BOARD--
INSERT INTO BOARD VALUES((SELECT NVL(MAX(BOARDNUM),0)+1 FROM BOARD),'제목31','아아아','admin1','src8',SYSDATE);
INSERT INTO BOARD VALUES((SELECT NVL(MAX(BOARDNUM),0)+1 FROM BOARD),'제목32','아아아아','admin2','src8',SYSDATE);
INSERT INTO BOARD VALUES((SELECT NVL(MAX(BOARDNUM),0)+1 FROM BOARD),'제목33','아아아아','admin3','src8',SYSDATE);
INSERT INTO BOARD VALUES((SELECT NVL(MAX(BOARDNUM),0)+1 FROM BOARD),'제목34','아아','admin1','src8',SYSDATE);
INSERT INTO BOARD VALUES((SELECT NVL(MAX(BOARDNUM),0)+1 FROM BOARD),'제목35','아아아','admin1','src8',SYSDATE);
SELECT COUNT(*) AS COUNT FROM BOARD;
DELETE FROM BOARD WHERE BOARDNUM=0;
UPDATE BOARD SET BOARDTITLE='바꾼제목1',BOARDCONTENT='바꾼내용1', MEMBERID='admin1', BOARDIMG='sc3' WHERE BOARDNUM=1;
SELECT*FROM BOARD WHERE BOARDNUM=2;
--전체출력
SELECT BOARDNUM, a.* FROM (SELECT * FROM BOARD ORDER BY BOARDNUM DESC)a WHERE ROWNUM<=2;
--검색출력(내용검색)
SELECT BOARDNUM, b.* FROM (SELECT*FROM BOARD WHERE BOARDCONTENT LIKE '%내%' ORDER BY BOARDNUM DESC)b WHERE ROWNUM<=2; 
--검색 수
SELECT COUNT(*) AS COUNT FROM BOARD WHERE BOARDCONTENT LIKE '%아아아%';
--검색 페이징


SELECT * FROM ("
			+ "SELECT * FROM ("
		    + "SELECT ROWNUM row_num, Z.* FROM ("
			+ "SELECT*FROM BOARD WHERE BOARDCONTENT LIKE '%'||?||'%' "
		    + ")Z WHERE row_num >= 2"
		    + ") WHERE row_num <= 5;

		    
--전체글 페이징
SELECT * FROM ("
			+ "SELECT * FROM ("
			+ "SELECT ROWNUM row_num, BOARD.* FROM BOARD"
			+ ") WHERE row_num >= 2"
			+ ") WHERE row_num <= 5;
		    
UPDATE MEMBER SET MEMBERATHU='admin' WHERE MEMBERID='admin1';



--MSG--
--INSERT INTO MSG VALUES((SELECT NVL(MAX(MSGNUM),0)+1 FROM MSG),'bbb','타이틀4','qwerqrerqr');
--선택출력
SELECT*FROM MSG WHERE MSGNUM=1;
--문의삭제시에 -> 문의답글도 같이 삭제되게.
--문의답글 삭제됨.
DELETE FROM (SELECT MSG.MSGNUM FROM MSG,MSGREPLY WHERE MSG.MSGNUM = MSGREPLY.MSGNUM)a WHERE a.MSGNUM=5;
--문의글삭제
DELETE FROM MSG WHERE MSGNUM=3;

SELECT * FROM MSG ORDER BY MSGNUM DESC LIMIT 0,?
SELECT a.* FROM (SELECT * FROM MSG ORDER BY MSGNUM DESC)a WHERE ROWNUM<=1;
--전체출력
SELECT*FROM MSG WHERE MSGNUM=1;

--MSGREPLY--
--INSERT INTO MSGREPLY VALUES((SELECT NVL(MAX(MSGREPLYNUM),0)+1 FROM MSGREPLY),7,'admin1',SYSDATE);
DELETE FROM MSGREPLY WHERE MSGREPLYNUM=0;
SELECT*FROM MSGREPLY WHERE MSGREPLYNUM=2;

--MEMBER--
--INSERT INTO MEMBER VALUES('ccc','cc11','씨씨','01012341234','c@naver.com','MEMBER');
UPDATE MEMBER SET MEMBERPW='ccc', MEMBERNAME='씨씨', MEMBERPHONE='01023452435', MEMBEREMAIL='c@naver.com' WHERE MEMBERID='ccc';
--회원탈퇴시 -> 문의글 삭제됨. 
DELETE FROM (SELECT MEMBER.MEMBERID FROM MEMBER,MSG WHERE MEMBER.MEMBERID = MSG.MEMBERID)a WHERE a.MEMBERID='bbb';
--회원탈퇴
DELETE FROM MEMBER WHERE MEMBERID='bbb';

UPDATE MSG SET MEMBERID='(알수없음)' WHERE MEMBERID='aaa';
UPDATE MSGREPLY SET MEMBERID='(알수없음)' WHERE MEMBERID='aaa';
UPDATE BOARD SET MEMBERID='(알수없음)' WHERE MEMBERID='aaa';
--null일 경우 로직 쓰기

--회원탈퇴시(MEMBERID=? ) -> 해당 회원의 문의글들에 대한 문의 답변 삭제
SELECT*FROM MSG WHERE MEMBERID='aaa'; --테이블 a. //a와 msgreply 의 msgnum 비교해서 삭제. 
DELETE FROM (SELECT MSG.MSGNUM FROM MSG,MSGREPLY WHERE MSG.MSGNUM = MSGREPLY.MSGNUM)a WHERE a.MSGNUM=5;

DELETE FROM (SELECT a.MSGNUM FROM (SELECT*FROM MSG WHERE MEMBERID='aaa')a , MSGREPLY WHERE a.MSGNUM = MSGREPLY.MSGNUM);

--DATE타입 문자열로 바꾸기
SELECT TO_CHAR(MEMBERBIRTH,'YYYY-MM-DD') AS BIRTH FROM MEMBER WHERE MEMBERID='nam123';                




--페이징--
--총 공지글 개수: 29개 / 한 페이지에 보여줄 글 개수:6개로 정함
SELECT COUNT(*) AS TOTALCOUNT FROM BOARD; 

SELECT BOARDNUM,BOARDTITLE,BOARDCONTENT,MEMBERID, BOARDDATE, BOARDIMG
     FROM ( SELECT BOARDNUM,BOARDTITLE,BOARDCONTENT,MEMBERID, BOARDDATE, BOARDIMG, ROW_NUMBER() OVER(ORDER BY BOARDNUM DESC) AS RNUM
        FROM BOARD ) WHERE RNUM BETWEEN 시작열 AND 마지막열 ORDER BY BOARDNUM DESC ;
--ROWNUM 표시
SELECT ROWNUM as row_num, BOARD.* FROM BOARD;

--최신순 부터 PRODUCT 출력
SELECT * FROM PRODUCT WHERE ROWNUM <=9 ORDER BY P_NUM DESC;





