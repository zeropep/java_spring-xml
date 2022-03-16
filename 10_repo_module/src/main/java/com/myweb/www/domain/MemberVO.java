package com.myweb.www.domain;

public class MemberVO {
	private String email;
	private String pwd;
	private String nickName;
	private String regAt;
	private String lastLogin;
	private int grade;
	
	public MemberVO() {}
	// insert : email, pwd, nickName
	// login : email, pwd
	// list : email, nickName, regAt, lastLogin, grade
	// detail : all
	// modify : email, pwd, nickName, grade
	// delete : email
	
	public final String getEmail() {
		return email;
	}

	public final void setEmail(String email) {
		this.email = email;
	}

	public final String getPwd() {
		return pwd;
	}

	public final void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public final String getNickName() {
		return nickName;
	}

	public final void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public final String getRegAt() {
		return regAt;
	}

	public final void setRegAt(String regAt) {
		this.regAt = regAt;
	}

	public final String getLastLogin() {
		return lastLogin;
	}

	public final void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

	public final int getGrade() {
		return grade;
	}

	public final void setGrade(int grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "MemberVO [email=" + email + ", pwd=" + pwd + ", nickName=" + nickName + ", regAt=" + regAt
				+ ", lastLogin=" + lastLogin + ", grade=" + grade + "]";
	}
}
