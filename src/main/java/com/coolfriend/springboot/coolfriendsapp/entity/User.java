package com.coolfriend.springboot.coolfriendsapp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="type")
	private String type;
	
	@Column(name="name")
	@NotNull(message=" User name cannot be null")
	@Size(min=1, message=" is required")
	private String name;
	
	@Column(name="email")
	@NotNull(message=" Email cannot be null")
	@Pattern(regexp="^[\\w-]+@([\\w-]+\\.)+[\\w-]+", message="Must be a correct Email format.")
	private String email;
	
	@Column(name="gender")
	@NotNull(message=" Gender cannot be null")
	private String gender;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	@Column(name="dob")
	private Date dob;

	@Column(name="country")
	private String country;
	
	@Column(name="city")
	private String city;
	
	@Column(name="postalcode")
	private String postalcode;
	
	@Column(name="contact_no")
	@NotNull(message=" contact cannot be null")
	@Pattern(regexp="^[0-9]{5,10}$", message="Must be a correct contact number format.")
	private String contact;
	
	@Column(name="password")
	@NotNull(message="Password cannot be null")
	@Pattern(regexp="^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$", 
		message="Must be at least 8 characters and contain alphanumeric (upper, lower and digit).")
	private String password;
	
	public User() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", type=" + type + ", name=" + name + ", email=" + email + ", gender=" + gender
				+ ", dob=" + dob + ", country=" + country + ", city=" + city + ", postalcode=" + postalcode
				+ ", contact=" + contact + ", password=" + password + "]";
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	

	


}
