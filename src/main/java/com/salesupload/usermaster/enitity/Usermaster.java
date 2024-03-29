package com.salesupload.usermaster.enitity;
// Generated 8 Mar, 2019 11:02:03 AM by Hibernate Tools 5.2.11.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Usermaster generated by hbm2java
 */
@Entity
@Table(name = "usermaster", catalog = "dalmia")
public class Usermaster implements java.io.Serializable {

	private int userid;
	private String login;
	private String password;
	private String emailid;
	private String reportname;
	@JsonFormat(pattern="dd/MM/yyyy")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date fromdate;
	@JsonFormat(pattern="dd/MM/yyyy")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date todate;
	private Integer active;
	private Integer createdby;
	private Integer modifiedby;
	private Date modifiedon;
	private Date createdon;
	private String macaddress;
	private String ipaddress;

	public Usermaster() {
	}

	public Usermaster(int userid) {
		this.userid = userid;
	}

	public Usermaster(int userid, String login, String password, String emailid, String reportname, Date fromdate,
			Date todate, Integer active, Integer createdby, Integer modifiedby, Date modifiedon, Date createdon,
			String macaddress, String ipaddress) {
		this.userid = userid;
		this.login = login;
		this.password = password;
		this.emailid = emailid;
		this.reportname = reportname;
		this.fromdate = fromdate;
		this.todate = todate;
		this.active = active;
		this.createdby = createdby;
		this.modifiedby = modifiedby;
		this.modifiedon = modifiedon;
		this.createdon = createdon;
		this.macaddress = macaddress;
		this.ipaddress = ipaddress;
	}

	@Id

	@Column(name = "userid", unique = true, nullable = false, precision = 8, scale = 0)
	public int getUserid() {
		return this.userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	@Column(name = "login", length = 32)
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Column(name = "password", length = 32)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "emailid", length = 64)
	public String getEmailid() {
		return this.emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	@Column(name = "reportname", length = 32)
	public String getReportname() {
		return this.reportname;
	}

	public void setReportname(String reportname) {
		this.reportname = reportname;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fromdate", length = 26)
	public Date getFromdate() {
		return this.fromdate;
	}

	public void setFromdate(Date fromdate) {
		this.fromdate = fromdate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "todate", length = 26)
	public Date getTodate() {
		return this.todate;
	}

	public void setTodate(Date todate) {
		this.todate = todate;
	}

	@Column(name = "active")
	public Integer getActive() {
		return this.active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	@Column(name = "createdby", precision = 8, scale = 0)
	public Integer getCreatedby() {
		return this.createdby;
	}

	public void setCreatedby(Integer createdby) {
		this.createdby = createdby;
	}

	@Column(name = "modifiedby", precision = 8, scale = 0)
	public Integer getModifiedby() {
		return this.modifiedby;
	}

	public void setModifiedby(Integer modifiedby) {
		this.modifiedby = modifiedby;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modifiedon", length = 26)
	public Date getModifiedon() {
		return this.modifiedon;
	}

	public void setModifiedon(Date modifiedon) {
		this.modifiedon = modifiedon;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdon", length = 26)
	public Date getCreatedon() {
		return this.createdon;
	}

	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}

	@Column(name = "macaddress", length = 32)
	public String getMacaddress() {
		return this.macaddress;
	}

	public void setMacaddress(String macaddress) {
		this.macaddress = macaddress;
	}

	@Column(name = "ipaddress", length = 32)
	public String getIpaddress() {
		return this.ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

}
