package com.erp.hgpad.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * 配置表实体类
 * 
 * @author 武杰
 *
 */
@Entity
public class TConfiguration {
	private String id;// 配置字段id
	@Column(length = 20)
	private String keyName;// 字段名
	@Column(length = 20)
	private String alias;// 别名
	@Column(length = 20)
	private String value;// 字段值
	@Column(length = 20)
	private String typeOfKey;// 字段所属类型
	@Column(length = 10)
	private String status;
	
	@Id
	@Column(length = 32)
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getTypeOfKey() {
		return typeOfKey;
	}
	public void setTypeOfKey(String typeOfKey) {
		this.typeOfKey = typeOfKey;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}// 是否可用

}
