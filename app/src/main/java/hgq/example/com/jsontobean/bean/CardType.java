package hgq.example.com.jsontobean.bean;


import java.io.Serializable;

public class CardType implements Serializable{

	private int id;
	private String name;
	
	private String rule;
	private String ruleComment;
	
	private int type;
	
	private int barberShopId;
	private int creatorId;
	
	private String createDate;
	
	private int status = 0;

	
	
	public String getRuleComment() {
		return ruleComment;
	}

	public void setRuleComment(String ruleComment) {
		this.ruleComment = ruleComment;
	}

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

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
