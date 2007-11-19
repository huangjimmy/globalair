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

}
