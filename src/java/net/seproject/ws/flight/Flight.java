/**
 * Created on Nov 04, 2007
 * Copyright: SEGroup.cs.tsinghua.edu.cn
 * All rights reserved.
 * 
 */
package net.seproject.ws.flight;

/**
 * 航班及机票情况
 * 
 * @author shufang
 * @version 1.0
 *
 */
public class Flight {

	/**
	 * 航班号
	 * 
	 * <br/><br/>
	 * 唯一标识某个航班
	 */
	private String id;

	/**
	 * 航空公司名称
	 */
	private String company;

	/**
	 * 出发城市名称
	 */
	private String fromCity;

	/**
	 * 到达城市名称
	 */
	private String toCity;

	/**
	 * 起飞时间
	 * 
	 * <br/><br/>
	 * Scheduled Time Departure
	 * <br/>
	 * 格式: yyyy-MM-dd hh:mm
	 */
	private String STD;

	/**
	 * 到达时间
	 * 
	 * <br/><br/>
	 * Scheduled Time of Arrival
	 * <br/>
	 * 格式: yyyy-MM-dd hh:mm
	 */
	private String STA;

	/**
	 * 最优惠折扣
	 * 
	 * <br/><br/>
	 * 各种可用折扣策略中的最优惠折扣( 0 < x <= 1.0 )
	 * <br/>
	 * 这里的"折扣"是指打几折,例如折扣=0.85, 则指打85折.
	 */
	private float maxDiscount;

	/**
	 * 显示应用各种折扣策略的结果
	 * 
	 * <br/><br/>
	 * 提供人为可读的格式化列表,显示应用各种可用折扣策略的结果. 例如,<br/>
	 * <br/>
	 * 
	 * [策略序号] [折扣] (如果不可用,则显示"-", *标出最优惠的策略)<br/>
	 *    1       85%   <br/>
	 *    2       ---   <br/>
	 *    3       70% * <br/>
	 *    4       95%   <br/>
	 *    5       ---   <br/>
	 * <br/><br/>
	 * 
	 */
	private String detailedDiscount;

	/**
	 * 头等舱全价(单位:RMB元)
	 */
	private int firstClassPrice;

	/**
	 * 商务舱全价(单位:RMB元)
	 */
	private int businessClassPrice;

	/**
	 * 经济舱全价(单位:RMB元)
	 */
	private int economyClassPrice;

	/**
	 * 头等舱剩余票数(单位:张)
	 */
	private int firstClassAvailableSeats;

	/**
	 * 商务舱剩余票数(单位:张)
	 */
	private int businessClassAvailableSeats;

	/**
	 * 经济舱剩余票数(单位:张)
	 */
	private int economyClassAvailableSeats;

	/**
	 * 头等舱总的票数(单位:张)
	 */
	private int firstClassTotalSeats;

	/**
	 * 商务舱总的票数(单位:张)
	 */
	private int businessClassTotalSeats;

	/**
	 * 经济舱总的票数(单位:张)
	 */
	private int economyClassTotalSeats;

	//
	// 以下是自动生成的get/set方法对。。。
	//

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getFromCity() {
		return fromCity;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	public String getToCity() {
		return toCity;
	}

	public void setToCity(String toCity) {
		this.toCity = toCity;
	}

	public String getSTD() {
		return STD;
	}

	public void setSTD(String std) {
		STD = std;
	}

	public String getSTA() {
		return STA;
	}

	public void setSTA(String sta) {
		STA = sta;
	}

	public float getMaxDiscount() {
		return maxDiscount;
	}

	public void setMaxDiscount(float maxDiscount) {
		this.maxDiscount = maxDiscount;
	}

	public String getDetailedDiscount() {
		return detailedDiscount;
	}

	public void setDetailedDiscount(String detailedDiscount) {
		this.detailedDiscount = detailedDiscount;
	}

	public int getFirstClassPrice() {
		return firstClassPrice;
	}

	public void setFirstClassPrice(int firstClassPrice) {
		this.firstClassPrice = firstClassPrice;
	}

	public int getBusinessClassPrice() {
		return businessClassPrice;
	}

	public void setBusinessClassPrice(int businessClassPrice) {
		this.businessClassPrice = businessClassPrice;
	}

	public int getEconomyClassPrice() {
		return economyClassPrice;
	}

	public void setEconomyClassPrice(int economyClassPrice) {
		this.economyClassPrice = economyClassPrice;
	}

	public int getFirstClassAvailableSeats() {
		return firstClassAvailableSeats;
	}

	public void setFirstClassAvailableSeats(int firstClassAvailableSeats) {
		this.firstClassAvailableSeats = firstClassAvailableSeats;
	}

	public int getBusinessClassAvailableSeats() {
		return businessClassAvailableSeats;
	}

	public void setBusinessClassAvailableSeats(int businessClassAvailableSeats) {
		this.businessClassAvailableSeats = businessClassAvailableSeats;
	}

	public int getEconomyClassAvailableSeats() {
		return economyClassAvailableSeats;
	}

	public void setEconomyClassAvailableSeats(int economyClassAvailableSeats) {
		this.economyClassAvailableSeats = economyClassAvailableSeats;
	}

	public int getFirstClassTotalSeats() {
		return firstClassTotalSeats;
	}

	public void setFirstClassTotalSeats(int firstClassTotalSeats) {
		this.firstClassTotalSeats = firstClassTotalSeats;
	}

	public int getBusinessClassTotalSeats() {
		return businessClassTotalSeats;
	}

	public void setBusinessClassTotalSeats(int businessClassTotalSeats) {
		this.businessClassTotalSeats = businessClassTotalSeats;
	}

	public int getEconomyClassTotalSeats() {
		return economyClassTotalSeats;
	}

	public void setEconomyClassTotalSeats(int economyClassTotalSeats) {
		this.economyClassTotalSeats = economyClassTotalSeats;
	}

}
