/*********************************************
         MYCGV_MEMBMER ���̺� 
*********************************************/
-- ȸ������, �α���, ������(ȸ��������)
-- �÷�����Ʈ : ID, PASS, NAME, GENDER, EMAIL, ADDR, TEL, PNUMBER, HOBBYLIST, INTRO,MDATE;
-- ****  UI�� NAME = VO �ʵ�� = ���̺� �÷���
CREATE TABLE MYCGV_MEMBER(
    ID            VARCHAR2(30)    CONSTRAINT  PK_MYCGV_MEMBER_ID  PRIMARY KEY,
    PASS        VARCHAR2(30)  NOT NULL,
    NAME      VARCHAR2(30) NOT NULL,
    GENDER    VARCHAR2(1) NOT NULL,
    EMAIL       VARCHAR2(50)  NOT NULL,
    ADDR        VARCHAR2(150)  NOT NULL,
    TEL             VARCHAR2(10)   NOT NULL,
    PNUMBER   VARCHAR2(30)  NOT NULL,
    HOBBYLIST  VARCHAR2(50)  NOT NULL,
    INTRO        VARCHAR2(200) ,
    MDATE      DATE    
);

/*********************************************
         MYCGV_BOARD ���̺� 
*********************************************/
-- �Խ���
-- �÷�����Ʈ : BID(�Խñ۾��̵�), BTITLE(����), BCONTENT(����), BHITS(��ȸ��), ID(ȸ�����̵�), BDATE(�������)
-- ** BID�� SEQUENCE�� �����Ͽ� ����
CREATE TABLE MYCGV_BOARD(
  BID       VARCHAR2(30)    CONSTRAINT PK_MYCGV_BOARD_BID   PRIMARY KEY,
  BTITLE  VARCHAR2(100)   NOT NULL,
  BCONTENT  VARCHAR2(200),
  BHITS   NUMBER(4),
  ID       VARCHAR2(30)   NOT NULL,
  BDATE  DATE
);

CREATE SEQUENCE SEQU_MYCGV_BOARD
  START WITH 1
  INCREMENT BY 1;

SELECT * FROM USER_SEQUENCES;
SELECT * FROM USER_TABLES WHERE TABLE_NAME='MYCGV_BOARD';


SELECT * FROM USER_TABLES WHERE TABLE_NAME='MYCGV_MEMBER';
DESC MYCGV_MEMBER;
SELECT * FROM MYCGV_MEMBER;
DELETE  FROM MYCGV_MEMBER WHERE ID LIKE 'tet%';
commit;

SELECT COUNT(*) FROM MYCGV_MEMBER WHERE ID='test';
select count(*) from mycgv_member where id='hong' and pass='123dfdf4';

-- �������(��������) : ORDER BY BDATE DESC
-- ���ȣ(ROWNUM) : �÷�����Ʈ�� ROWNUM RNO
SELECT ROWNUM RNO, BID, BTITLE, BCONTENT, BHITS, ID, BDATE
FROM (SELECT BID, BTITLE,BCONTENT,BHITS, ID, BDATE FROM MYCGV_BOARD
          ORDER BY BDATE DESC);

-- MYCGV_MEMBER ���̺� ���(GRADE) �÷� �߰�
-- VARCHAR2(10) 
-- NULL ��� ���·� �߰��� --> ������ ���� �� ������� �����Ͽ�  NOT NULL
SELECT * FROM MYCGV_MEMBER;
ALTER TABLE MYCGV_MEMBER
ADD GRADE VARCHAR2(10);

-- ���� ������ ��� : GOLD
UPDATE MYCGV_MEMBER SET GRADE='GOLD';
SELECT * FROM MYCGV_MEMBER;
COMMIT;

-- �ֱٰ����� ȸ�� �������� ����ϸ�, ���ȣ�� �Բ� ���
select rownum rno, id, name, to_char(mdate,'yyyy-mm-dd') mdate, grade
from (select id, name, mdate, grade from mycgv_member
        order by mdate desc);

update mycgv_member set grade='GOLD' where id='smith';        
commit;


/*********************************************
         MYCGV_NOTICE ���̺� 
*********************************************/  
-- �������� : ������(CURD), �����(R)
-- �÷�����Ʈ : NID(�������׾��̵�), NTITLE(����), NCONTENT(����), NHITS(��ȸ��), NDATE(�������)
-- ** NID�� SEQUENCE�� �����Ͽ� ����
CREATE TABLE MYCGV_NOTICE(
  NID       VARCHAR2(30)    CONSTRAINT PK_MYCGV_NOTICE_NID   PRIMARY KEY,
  NTITLE  VARCHAR2(100)   NOT NULL,
  NCONTENT  VARCHAR2(200),
  NHITS   NUMBER(4),
  NDATE  DATE
);
SELECT * FROM USER_TABLES WHERE TABLE_NAME='MYCGV_NOTICE';
DESC MYCGV_NOTICE;
SELECT * FROM MYCGV_NOTICE;

CREATE SEQUENCE SEQU_MYCGV_NOTICE
  START WITH 1
  INCREMENT BY 1;

SELECT * FROM USER_SEQUENCES;  













