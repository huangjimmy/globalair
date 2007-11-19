/**
 * Created on Nov 04, 2007
 * Copyright: SEGroup.cs.tsinghua.edu.cn
 * All rights reserved.
 * 
 */
package net.seproject.ws.hotel;

/**
 * �Ƶ꼰�ͷ����
 * 
 * @author shufang
 * @version 1.0
 *
 */
public class HotelRoom {

	/**
	 * �Ƶ����
	 * 
	 * <br/><br/>
	 * Ψһ��ʶĳ���Ƶ�
	 */
	private String id;

	/**
	 * �Ƶ�����
	 */
	private String hotel;

	/**
	 * ���ڳ���
	 */
	private String inCity;

	/**
	 * �ͷ����
	 * 
	 * <br/><br/>
	 * 1: �߼��׼�<br/>
	 * 2: �����<br/>
	 * 3: ��׼��<br/>
	 * <br/>
	 * Ŀǰ������ֵ��Ч.
	 */
	private int category;

	/**
	 * ��ʼʱ��
	 * 
	 * <br/><br/>
	 * ��ʽ: yyyy-MM-dd hh:mm
	 * <br/>
	 * ���㵱ǰmaxDiscount���ۿ۲�����صĿ�ʼʱ��
	 */
	private String startTime;

	/**
	 * ����ʱ��
	 * 
	 * <br/><br/>
	 * ��ʽ: yyyy-MM-dd hh:mm
	 * <br/>
	 * ���㵱ǰmaxDiscount���ۿ۲�����صĽ���ʱ��
	 */
	private String endTime;

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
	 * ����ͷ�ȫ��(��λ:RMBԪ)
	 */
	private int rPrice;

	/**
	 * ����ͷ�ʣ�����(��λ:��)
	 */
	private int rAvailableRooms;

	/**
	 * ����ͷ��ܵļ���(��λ:��)
	 */
	private int rTotalRooms;

}
