/**
 * Created on Nov 04, 2007
 * Copyright: SEGroup.cs.tsinghua.edu.cn
 * All rights reserved.
 * 
 */
package net.seproject.ws.flight;

/**
 * ���༰��Ʊ���
 * 
 * @author shufang
 * @version 1.0
 *
 */
public class Flight {

	/**
	 * �����
	 * 
	 * <br/><br/>
	 * Ψһ��ʶĳ������
	 */
	private String id;

	/**
	 * ���չ�˾����
	 */
	private String company;

	/**
	 * ������������
	 */
	private String fromCity;

	/**
	 * �����������
	 */
	private String toCity;

	/**
	 * ���ʱ��
	 * 
	 * <br/><br/>
	 * Scheduled Time Departure
	 * <br/>
	 * ��ʽ: yyyy-MM-dd hh:mm
	 */
	private String STD;

	/**
	 * ����ʱ��
	 * 
	 * <br/><br/>
	 * Scheduled Time of Arrival
	 * <br/>
	 * ��ʽ: yyyy-MM-dd hh:mm
	 */
	private String STA;

	/**
	 * ���Ż��ۿ�
	 * 
	 * <br/><br/>
	 * ���ֿ����ۿ۲����е����Ż��ۿ�( 0 < x <= 1.0 )
	 * <br/>
	 * �����"�ۿ�"��ָ����,�����ۿ�=0.85, ��ָ��85��.
	 */
	private float maxDiscount;

	/**
	 * ��ʾӦ�ø����ۿ۲��ԵĽ��
	 * 
	 * <br/><br/>
	 * �ṩ��Ϊ�ɶ��ĸ�ʽ���б�,��ʾӦ�ø��ֿ����ۿ۲��ԵĽ��. ����,<br/>
	 * <br/>
	 * 
	 * [�������] [�ۿ�] (���������,����ʾ"-", *������ŻݵĲ���)<br/>
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
	 * ͷ�Ȳ�ȫ��(��λ:RMBԪ)
	 */
	private int firstClassPrice;

	/**
	 * �����ȫ��(��λ:RMBԪ)
	 */
	private int businessClassPrice;

	/**
	 * ���ò�ȫ��(��λ:RMBԪ)
	 */
	private int economyClassPrice;

	/**
	 * ͷ�Ȳ�ʣ��Ʊ��(��λ:��)
	 */
	private int firstClassAvailableSeats;

	/**
	 * �����ʣ��Ʊ��(��λ:��)
	 */
	private int businessClassAvailableSeats;

	/**
	 * ���ò�ʣ��Ʊ��(��λ:��)
	 */
	private int economyClassAvailableSeats;

	/**
	 * ͷ�Ȳ��ܵ�Ʊ��(��λ:��)
	 */
	private int firstClassTotalSeats;

	/**
	 * ������ܵ�Ʊ��(��λ:��)
	 */
	private int businessClassTotalSeats;

	/**
	 * ���ò��ܵ�Ʊ��(��λ:��)
	 */
	private int economyClassTotalSeats;

	//
	// �������Զ����ɵ�get/set�����ԡ�����
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
