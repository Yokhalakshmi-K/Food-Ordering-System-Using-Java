package com.zoho.Foodordering.Models;

public class User{
	public enum Role{
		ADMIN,
		HOTEL_ADMIN,
		CUSTOMER
	}
	private int userId;
	private String name;
	private String userName;
	private String password;
	private String contact;
	private String email;
	private Role role;
	
	public User(int userId,String name,String userName,String password,String contact,String email,Role role){
		this(name,userName,password,contact,email,role);
		this.userId = userId;
	}
	public User(String name,String userName,String password,String contact,String email,Role role){
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.contact = contact;
		this.email = email;
		this.role = role;
	}
	public User(){
		
	}
	public int getUserId(){
		return userId;
	}
	public void setUserId(int userId){
		this.userId = userId;
	}
	public String getName(){
		return name;
	}
	public void setName(String name) {
        this.name = name;
    }
	 public String getUsername() {
        return userName;
    }

    public void setUsername(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", username='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", emailId='" + email + '\'' +
                ", mobileNumber='" +contact + '\'' +
                ", role=" + role +
                '}';
    }
}