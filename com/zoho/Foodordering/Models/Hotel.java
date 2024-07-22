package com.zoho.Foodordering.Models;

public class Hotel{
	public enum hotelType{
		veg , nonveg , vegandnonveg
	}
	private int hotelId;
	private String hotelName;
	private String location;
	private int adminId;
	private hotelType type;
	public Hotel(){
		
	}
	public Hotel(String hotelName,String location,int adminId,hotelType type){
		this.hotelName = hotelName;
		this.location = location;
		this.adminId = adminId;
		this.type = type;
	}
	public Hotel(String hotelName,String location,hotelType type){
		this.hotelName = hotelName;
		this.location = location;
		
		this.type = type;
	}
	public Hotel(int hotelId,String hotelName,String location,hotelType type){
		this(hotelName,location,type);
		this.hotelId = hotelId;
	}
	public int getHotelId(){
		return hotelId;
	}
	public void setHotelId(int hotelId){
		this.hotelId = hotelId;
	}
	public String getHotelName(){
		return hotelName;
	}
	public void setHotelName(String hotelName){
		this.hotelName = hotelName;
	}
	public void setLocation(String location){
		this.location = location;
	}
	public String getLocation(){
		return location;
	}
	public hotelType getType(){
		return type;
	}
	public void setType(hotelType type){
		this.type = type;
	}
	public int getAdminId() {
		return adminId;
	}
    public String toString() {
        return "Hotel{" +
                "HotelId=" + hotelId +
                ", Hotel name='" + hotelName + '\'' +
                ", Type=" + type +
                ", Location=" + location +
                '}';
    }
}