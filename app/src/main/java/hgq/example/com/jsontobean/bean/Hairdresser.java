package hgq.example.com.jsontobean.bean;


import java.io.Serializable;

public class Hairdresser implements Serializable{

	private int id;
	private String name;
	private String mobile;
	private int barberShopId;
	private int creatorId;
	private String createTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getBarberShopId() {
		return barberShopId;
	}
	public void setBarberShopId(int barberShopId) {
		this.barberShopId = barberShopId;
	}
	public int getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}


}
