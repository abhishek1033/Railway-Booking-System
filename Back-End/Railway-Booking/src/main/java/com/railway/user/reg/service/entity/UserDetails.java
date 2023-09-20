package com.railway.user.reg.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.railway.user.reg.service.pojo.CommonTableFields;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "userDetails")
public class UserDetails extends CommonTableFields 
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "userId", nullable = false)
	private String userId;

	@Column(name = "userName")
	private String userName;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "preferredLanguage")
	private String preferredLanguage;
	
	@Column(name = "securityQuestion")
	private String securityQuestion;
	
	@Column(name = "securityAnswer")
	private String securityAnswer;
	
	@Column(name = "firstName")
	private String firstName;
	
	@Column(name = "middleName")
	private String middleName;
	
	@Column(name = "lastName")
	private String lastName;
	
	@Column(name = "occupation")
	private String occupation;
	
	@Column(name = "dob")
	private String dob;
	
	@Column(name = "marritalStatus")
	private String marritalStatus;
	
	@Column(name = "country")
	private String country;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "emailId")
	private String emailId;
	
	@Column(name = "mobileNo")
	private String mobileNo;
	
	@Column(name = "nationality")
	private String nationality;
	
	@Column(name = "addressNo")
	private String addressNo;
	
	@Column(name = "street")
	private String street;
	
	@Column(name = "area")
	private String area;
	
	@Column(name = "pincode")
	private String pincode;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "city")
	private String city;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPreferredLanguage() {
		return preferredLanguage;
	}

	public void setPreferredLanguage(String preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}

	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getSecurityAnswer() {
		return securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getMarritalStatus() {
		return marritalStatus;
	}

	public void setMarritalStatus(String marritalStatus) {
		this.marritalStatus = marritalStatus;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getAddressNo() {
		return addressNo;
	}

	public void setAddressNo(String addressNo) {
		this.addressNo = addressNo;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public UserDetails(String userId, String userName, String password, String preferredLanguage,
			String securityQuestion, String securityAnswer, String firstName, String middleName, String lastName,
			String occupation, String dob, String marritalStatus, String country, String gender, String emailId,
			String mobileNo, String nationality, String addressNo, String street, String area, String pincode,
			String state, String city) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.preferredLanguage = preferredLanguage;
		this.securityQuestion = securityQuestion;
		this.securityAnswer = securityAnswer;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.occupation = occupation;
		this.dob = dob;
		this.marritalStatus = marritalStatus;
		this.country = country;
		this.gender = gender;
		this.emailId = emailId;
		this.mobileNo = mobileNo;
		this.nationality = nationality;
		this.addressNo = addressNo;
		this.street = street;
		this.area = area;
		this.pincode = pincode;
		this.state = state;
		this.city = city;
	}

	public UserDetails() {
		super();
	}
	
	
	
}
