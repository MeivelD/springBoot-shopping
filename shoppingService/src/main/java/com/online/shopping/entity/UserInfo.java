package com.online.shopping.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="user_info")
public class UserInfo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private int userId;

	@Column(name="user_name")
	private String userName;

	@Column(name="user_email")
	private String userEmail;

	@Column(name="user_phone")
	private String userPhone;

	@Column(name="user_address")
	private String userAddress;

	@Column(name="city")
	private String city;

	@Column(name="Country")
	private String Country;

	@Column(name="postcode")
	private String postcode;
	
	
}
