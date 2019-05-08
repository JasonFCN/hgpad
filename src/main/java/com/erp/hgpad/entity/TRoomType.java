package com.erp.hgpad.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
/**
 * 空间类型
 * @author 10352
 *T_ProductBrand
 */
@Entity
public class TRoomType {	
	private String  id ; //由Public.getGuid()产生
	private Integer  status ; //1表示有效
	private Integer  state ; //是否有效
	private Integer  no ; //序号
	@Column(length=32)
	private String  name ; //空间类型名称	
	@Column(length=32)
	private String orgId;//组织ID
	@Column(length=255)
	private String  picture ;
	
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
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	} //空间类型图片	
}
