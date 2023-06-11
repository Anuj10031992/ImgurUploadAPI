package com.example.synchrony.assignment.demo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;



@Entity
@Table(name="USER_PROFILE")
public class userProfile {

	@Id
	@NotNull
	private String loginId;
	@Column
	@NotNull
	private String name;
	@Column
	@NotNull
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	@Column
	@NotNull
	private String country;
	@Column
	@NotNull
	@Email(message="please enter a valid email")
	private String email;
	@Column
	@Size(min=10,max=10, message="enter 20 digit mobile number")
	@Pattern(regexp="^[0-9]*$" , message="only numbers are allowed in mobile number")
	
	private String mobileNo;
	 @JsonManagedReference
	 @JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@OneToMany(mappedBy="user")
	private List<userImages> images;
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<userImages> getImages() {
		return images;
	}
	public void setImages(List<userImages> images) {
		this.images = images;
	}
	public userProfile() {
		super();
	}
	@Override
	public String toString() {
		return "userProfile [loginId=" + loginId + ", name=" + name + ", password=" + password + ", country=" + country
				+ ", email=" + email + ", mobileNo=" + mobileNo + ", images=" + images + "]";
	}
	
	
	
}
