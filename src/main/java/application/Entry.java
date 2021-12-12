package main.java.application;

import java.time.LocalDate;

public class Entry {

	private String action;
	private String catagory;
	private String size;
	private String type;
	private String contactDate;
	private String needByDate;
	private String source;
	private String name;
	private String phone;
	private String email;
	private String notes;
	
	public Entry(String action, String catagory, String size, String type, String contactDate, String needByDate,
			String source, String name, String phone, String email, String notes) {
		super();
		this.action = action;
		this.catagory = catagory;
		this.size = size;
		this.type = type;
		this.contactDate = contactDate;
		this.needByDate = needByDate;
		this.source = source;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.notes = notes;
	}
	

	public String getAction() {
		
		return action;
	}

	public void setAction(String action) {
		
		this.action = action;
	}
	public String getCatagory() {
		
		return catagory;
	}
	
	public void setCatagory(String catagory) {
		
		this.catagory = catagory;
	}
	
	public String getSize() {
		
		return size;
	}
	
	public void setSize(String size) {
		
		this.size = size;
	}
	
	public String getType() {
		
		return type;
	}
	
	public void setType(String type) {
		
		this.type = type;
	}
	
	public String getContactDate() {
		
		return contactDate;
	}
	
	public void setContactDate(String contactDate) {
		
		this.contactDate = contactDate;
	}
	
	public String getNeedByDate() {
		
		return needByDate;
	}
	
	public void setNeedByDate(String needByDate) {
		
		this.needByDate = needByDate;
	}
	
	public String getSource() {
		
		return source;
	}
	
	public void setSource(String source) {
		
		this.source = source;
	}
	
	public String getName() {
		
		return name;
	}
	
	public void setName(String name) {
		
		this.name = name;
	}
	
	public String getPhone() {
		
		return phone;
	}
	
	public void setPhone(String phone) {
		
		this.phone = phone;
	}
	
	public String getEmail() {
		
		return email;
	}
	
	public void setEmail(String email) {
		
		this.email = email;
	}
	
	public String getNotes() {
		
		return notes;
	}
	
	public void setNotes(String notes) {
		
		this.notes = notes;
	}

}
