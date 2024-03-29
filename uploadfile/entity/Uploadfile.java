package com.salesupload.uploadfile.entity;
// Generated Feb 26, 2019 12:41:48 PM by Hibernate Tools 5.2.11.Final

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Uploadfile generated by hbm2java
 */
@Entity
@Table(name = "uploadfile", catalog = "dalmia")
public class Uploadfile implements java.io.Serializable {

	private short uploadfileid;
	private String login;
	private Integer reportid;
	private String reportname;
	private String year;
	private String month;
	private String filename;
	private Date fileuploaddate;
	private Integer active;
	private Integer createdby;
	private Date createdon;
	private Integer modifiedby;
	private Date modifiedon;
	private String ipaddress;
	private String macaddress;

	public Uploadfile() {
	}

	public Uploadfile(short uploadfileid) {
		this.uploadfileid = uploadfileid;
	}

	public Uploadfile(short uploadfileid, String login, Integer reportid, String reportname, String year, String month,
			String filename, Date fileuploaddate, Integer active, Integer createdby, Date createdon, Integer modifiedby,
			Date modifiedon, String ipaddress, String macaddress) {
		this.uploadfileid = uploadfileid;
		this.login = login;
		this.reportid = reportid;
		this.reportname = reportname;
		this.year = year;
		this.month = month;
		this.filename = filename;
		this.fileuploaddate = fileuploaddate;
		this.active = active;
		this.createdby = createdby;
		this.createdon = createdon;
		this.modifiedby = modifiedby;
		this.modifiedon = modifiedon;
		this.ipaddress = ipaddress;
		this.macaddress = macaddress;
	}

	@Id

	@Column(name = "uploadfileid", unique = true, nullable = false, precision = 4, scale = 0)
	public short getUploadfileid() {
		return this.uploadfileid;
	}

	public void setUploadfileid(short uploadfileid) {
		this.uploadfileid = uploadfileid;
	}

	@Column(name = "login", length = 32)
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Column(name = "reportid", precision = 8, scale = 0)
	public Integer getReportid() {
		return this.reportid;
	}

	public void setReportid(Integer reportid) {
		this.reportid = reportid;
	}

	@Column(name = "reportname", length = 32)
	public String getReportname() {
		return this.reportname;
	}

	public void setReportname(String reportname) {
		this.reportname = reportname;
	}

	@Column(name = "year", length = 4)
	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Column(name = "month", length = 16)
	public String getMonth() {
		return this.month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	@Column(name = "filename", length = 128)
	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fileuploaddate", length = 26)
	public Date getFileuploaddate() {
		return this.fileuploaddate;
	}

	public void setFileuploaddate(Date fileuploaddate) {
		this.fileuploaddate = fileuploaddate;
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdon", length = 26)
	public Date getCreatedon() {
		return this.createdon;
	}

	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
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

	@Column(name = "ipaddress", length = 32)
	public String getIpaddress() {
		return this.ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	@Column(name = "macaddress", length = 32)
	public String getMacaddress() {
		return this.macaddress;
	}

	public void setMacaddress(String macaddress) {
		this.macaddress = macaddress;
	}

}
