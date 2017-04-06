package hgq.example.com.jsontobean.bean;


import java.io.Serializable;
import java.util.Date;

public class CostItem   implements Serializable {

	private int id;
	private String name;
	private String unit;
	private int barberShopId;
	private Date createTime;
	private int creatorId;
	private int status;
	
	private int sort;
	
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
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public int getBarberShopId() {
		return barberShopId;
	}
	public void setBarberShopId(int barberShopId) {
		this.barberShopId = barberShopId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}

}
