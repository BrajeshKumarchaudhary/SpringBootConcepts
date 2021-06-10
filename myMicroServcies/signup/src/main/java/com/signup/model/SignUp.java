package com.signup.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

@Entity
@Table(name = "userSignup")
@NamedStoredProcedureQueries({
		@NamedStoredProcedureQuery(name = "registerUser", procedureName = "MyDb.LoginInsert", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "user_name", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "user_passwd", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "user_email", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "dob", type = Date.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "user_role", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "is_active", type = Integer.class) }) })
public class SignUp {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int id;
	@Column(name = "login_id")
	public int loginid;

	@Column(name = "name")
	public String name;
	@Column(name = "email")
	public String email;
	@Column(name = "dob")
	public Date dob;
	@Column(name = "user_role")
	public int userrole;
	@Column(name = "is_active")
	public int isActive;

	public int getLoginid() {
		return loginid;
	}

	public void setLoginid(int loginid) {
		this.loginid = loginid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public int getUserrole() {
		return userrole;
	}

	public void setUserrole(int userrole) {
		this.userrole = userrole;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "SignUp [loginid=" + loginid + ", name=" + name + ", email=" + email + ", dob=" + dob + ", userrole="
				+ userrole + ", isActive=" + isActive + "]";
	}

}
