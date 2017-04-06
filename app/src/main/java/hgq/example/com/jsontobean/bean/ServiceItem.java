package hgq.example.com.jsontobean.bean;


import java.io.Serializable;
import java.util.Date;

public class ServiceItem  implements Serializable {

	private int id;
	private String name;
	private int price;
	private Date createTime;
	private int barberShopId;
	private int creatorId;
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

}
