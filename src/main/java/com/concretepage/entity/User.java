package com.concretepage.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="user")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String email;

	private String addressline1;

	private String addressline2;

	private String channeltoken;

	private String city;

	private String fbuserid;

	private byte isconfirmed;

	private byte isdelete;

	private String isloginvia;

	private byte isloginviasocialmedia;

	private String name;

	private String password;

	private String phonenumber;

	@Lob
	private byte[] profimage;

	private String pushtoken;

	private String state;

	private String zipcode;

	public User() {
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddressline1() {
		return this.addressline1;
	}

	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}

	public String getAddressline2() {
		return this.addressline2;
	}

	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}

	public String getChanneltoken() {
		return this.channeltoken;
	}

	public void setChanneltoken(String channeltoken) {
		this.channeltoken = channeltoken;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFbuserid() {
		return this.fbuserid;
	}

	public void setFbuserid(String fbuserid) {
		this.fbuserid = fbuserid;
	}

	public byte getIsconfirmed() {
		return this.isconfirmed;
	}

	public void setIsconfirmed(byte isconfirmed) {
		this.isconfirmed = isconfirmed;
	}

	public byte getIsdelete() {
		return this.isdelete;
	}

	public void setIsdelete(byte isdelete) {
		this.isdelete = isdelete;
	}

	public String getIsloginvia() {
		return this.isloginvia;
	}

	public void setIsloginvia(String isloginvia) {
		this.isloginvia = isloginvia;
	}

	public byte getIsloginviasocialmedia() {
		return this.isloginviasocialmedia;
	}

	public void setIsloginviasocialmedia(byte isloginviasocialmedia) {
		this.isloginviasocialmedia = isloginviasocialmedia;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhonenumber() {
		return this.phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public byte[] getProfimage() {
		return this.profimage;
	}

	public void setProfimage(byte[] profimage) {
		this.profimage = profimage;
	}

	public String getPushtoken() {
		return this.pushtoken;
	}

	public void setPushtoken(String pushtoken) {
		this.pushtoken = pushtoken;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

}