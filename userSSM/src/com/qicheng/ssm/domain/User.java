package com.qicheng.ssm.domain;

import java.io.Serializable;

public class User implements Serializable {
	private int id;
	private String password;
	private String username;
	private transient String file;//
	private static final long serialVersionUID = 1L;
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @param password
	 * @param username
	 */
	public User(String password, String username) {
		super();
		this.password = password;
		this.username = username;
	}
	/**
	 * 
	 */
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param id
	 * @param password
	 * @param username
	 */
	public User(int id, String password, String username) {
		super();
		this.id = id;
		this.password = password;
		this.username = username;
	}
	/**
	 * @param id
	 * @param password
	 * @param username
	 * @param file
	 */
	public User(int id, String password, String username, String file) {
		super();
		this.id = id;
		this.password = password;
		this.username = username;
		this.file = file;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", password=").append(password);
        sb.append(", username=").append(username);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
	}
	
	
}
