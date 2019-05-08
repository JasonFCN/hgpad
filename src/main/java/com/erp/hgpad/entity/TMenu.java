package com.erp.hgpad.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;
@Entity
public class TMenu {
	
	private String id;//ID
	private Integer status;//是否有效
	private Integer no;//顺序
	private Integer state;//是否使用
	@Column(length=32)
	private String code;//编号
	@Column(length=32)
	private String name;//名称
	@Column(length=250)
	private String icon1;//icon1
	@Column(length=250)
	private String icon2;//icon2
	@Column(length=250)
	private String note;//备注	
	
	@Id
    @Column(length=32)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon1() {
		return icon1;
	}
	public void setIcon1(String icon1) {
		this.icon1 = icon1;
	}
	public String getIcon2() {
		return icon2;
	}
	public void setIcon2(String icon2) {
		this.icon2 = icon2;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
}
