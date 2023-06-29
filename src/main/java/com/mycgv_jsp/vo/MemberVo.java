package com.mycgv_jsp.vo;

/*
 * �� --> email1, email2, addr1, addr2,phone1, phone2, phone3,hobby 
 * ���̺� --> rno, email, addr, pnumber, hobbyList, mdate 
 * ��&���̺� -->id, pass, name, gender, tel, intro
 */
public class MemberVo {
	//������ �Ѿ���� ��� name/value ����	 
	String id, pass, name, gender, email1, email2, addr1, addr2, tel,
		phone1, phone2, phone3, intro;
	String[] hobby;
	
	//member ���̺� �÷� ����
	int rno;
	String email, addr, pnumber, hobbyList, mdate, grade;
	/*
	 * email --> ������  set X, ���̺� set O
	 * email --> ������  get O(email1+"@"+ email2), ���̺� get O
	 * */
		
	
	public String getId() {
		return id;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail1() {
		return email1;
	}
	public void setEmail1(String email1) {
		this.email1 = email1;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public String getPhone3() {
		return phone3;
	}
	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String[] getHobby() {
		return hobby;
	}
	public void setHobby(String[] hobby) {
		this.hobby = hobby;
	}
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public String getEmail() {
		if(email1 != null) {  //������ email �ּҰ� �Ѿ�� ���
			email = email1 + "@" + email2;
		}
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddr() {
		if(addr1 != null) {
			addr = addr1 + addr2;
		}
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getPnumber() {
		if(phone1 != null) {
			pnumber = phone1 +"-"+ phone2 +"-"+ phone3;
		}
		return pnumber;
	}
	public void setPnumber(String pnumber) {
		this.pnumber = pnumber;
	}
	public String getHobbyList() {
		if(hobby != null) {
			hobbyList = String.join(",", hobby);
		}
		return hobbyList;
	}
	public void setHobbyList(String hobbyList) {
		this.hobbyList = hobbyList;
	}
	public String getMdate() {
		return mdate;
	}
	public void setMdate(String mdate) {
		this.mdate = mdate;
	}	
	
}
  