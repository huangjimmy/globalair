/**
 * Created on Nov 04, 2007
 * Copyright: SEGroup.cs.tsinghua.edu.cn
 * All rights reserved.
 * 
 */
package net.seproject.ws.flight;

/**
 * ��ƱԤ������(�ӿ�)
 * 
 * @author shufang
 * @version 1.0
 *
 */
public interface IFlightService {

	/**
	 * ע��һ���û��ʺ�
	 * 
	 * <br/><br/>
	 * �κ��˶�����������ϵͳ��ע���ʺ�, ������ʺ��Ѵ���,����ע��ɹ�.<br/>
	 * ������û�������Ϊ�ʺ�����, ͬһ���û�����ֻ��ע��һ��ͬ���ʺ�.<br/>
	 * ���ע��ɹ�, ϵͳӦ���ָ������û��Ļ�Ա������(��Ϊ1-5�Ǽ�).
	 * <br/>
	 * 
	 * @param username �û�����, ��ӦΪ��
	 * @param password �û�����, ��ӦΪ��
	 * @param email    �û�Email��ַ, ��Ϊ��
	 * 
	 * @return �Ƿ�ע��ɹ�. >0: ע��ɹ������ػ�Ա������(ȡֵ1~5); =0: �û��Ѵ���; <0: ����
	 * 
	 * @see #delUser(String, String)
	 * 
	 */
	public int regUser( String username, String password, String email );

	/**
	 * ɾ��һ���û��ʺ�
	 * 
	 * <br/><br/>
	 * �û�����ɾ���Լ����ʺ�, ֻҪָ������ȷ���û�����/����.<br/>
	 * ɾ���û�ʱ, �û����еĸ�����Ϣ�Ͷ�����Ϣ��һ��ɾ��.
	 * <br/>
	 * 
	 * @param username �û�����, ��ӦΪ��
	 * @param password �û�����, ��ӦΪ��
	 * 
	 * @return �Ƿ�ɾ���ɹ�. >0: ɾ���ɹ�; =0: �û�������; <0: ����
	 * 
	 * @see #regUser(String, String, String)
	 * 
	 */
	public int delUser( String username, String password );

	/**
	 * ��ѯ����/��Ʊ���
	 * 
	 * <br/><br/>
	 * ��������������ѯ����/��Ʊ���, ����ָ��������ʽ���غ��������б�.<br/>
	 * ���ָ����"��������"����, ��ֻ�ǲ�ѯ����(��������12:00Ϊֹ)�ĺ���/��Ʊ��Ϣ.<br/>
	 * ���ø÷���ʱ, ����ָ����Ч���û�����.
	 * <br/>
	 * 
	 * @param username   �û�����, ��ӦΪ��
	 * @param fromCity   ��������, Ϊ��ʱ��ƥ����ֶ�
	 * @param toCity     �������, Ϊ��ʱ��ƥ����ֶ�
	 * @param startDate  ��������, ��ʽ: yyyy-MM-dd hh:mm, Ϊ��ʱ��ƥ����ֶ�
	 * @param cabin      ָ���ն�(1: ͷ�Ȳ�; 2: �����; 3: ���ò�), ������ֵָ����ն�
	 * @param numbers    ׼��Ԥ������λ��( >0ָԤ������λ��; <=0��ָ�����Ǻ����Ƿ���ʣ����λ )
	 * @param orderBy    �������ʽ, 1: ���Ż��ۿ۴Ӵ�С��˳��(ȱʡ��ʽ); 2: �����ʱ����絽���˳��; ����: ��ȱʡ��ʽ���� 
	 * 
	 * @return ���������ĺ���/��Ʊ��Ϣ; ���û���κη�����������Ϣ,�򷵻�null(��ֵ).
	 * 
	 * @see net.seproject.ws.flight.Flight
	 * @see #reserve(String, String, String, int, int)
	 * @see #listStrategies()
	 * 
	 */
	public Flight[] search( String username,
			String fromCity,
			String toCity,
			String startDate,
			int cabin,
			int numbers,
			int orderBy
			);

	/**
	 * Ԥ��ĳ������Ļ�Ʊ
	 * 
	 * <br/><br/>
	 * �Ӳ�ѯ�������ѡ����ʵĺ���,Ԥ����Ʊ.<br/>
	 * ���ø÷���ʱ, ����ָ����Ч���û�����/����.
	 * <br/>
	 * 
	 * @param username �û�����, ��ӦΪ��
	 * @param password �û�����, ��ӦΪ��
	 * @param flightId �����, ��Flight.id�ֶ�
	 * @param cabin    ָ���ն�(1: ͷ�Ȳ�; 2: �����; 3: ���ò�), ������ֵ��Ч
	 * @param numbers  ׼��Ԥ������λ��(>=1��), ���û���㹻��λ, ����Ԥ���ɹ�.
	 * 
	 * @return ���Ԥ���ɹ�, ��������µĶ�����(�ִ���ʽ), ����, ����null(��ֵ).
	 * 
	 * @see #search(String, String, String, String, int, int, int)
	 * @see #cancel(String, String, String)
	 * 
	 */
	public String reserve( String username, String password, String flightId, int cabin, int numbers );

	/**
	 * ȡ��һ�Ŷ���
	 * 
	 * <br/><br/>
	 * ����ָ���Ķ����ţ�ȡ�����Ŷ���.<br/>
	 * �û�ֻ��ȡ���Լ��Ķ���.<br/>
	 * ���ø÷���ʱ, ����ָ����Ч���û�����/����.
	 * <br/>
	 * 
	 * @param username �û�����, ��ӦΪ��
	 * @param password �û�����, ��ӦΪ��
	 * @param orderId  ������, ��ӦΪ��
	 * 
	 * @return �����Ƿ�ȡ���ɹ�. >0: ȡ���ɹ�; =0: ����������; <0: ����
	 * 
	 * @see #reserve(String, String, String, int, int)
	 * 
	 */
	public int cancel( String username, String password, String orderId ); 

	/**
	 * ��ȡ������Ϣ
	 * 
	 * <br/><br/>
	 * ���isOrderIdOnlyΪtrue, �����÷ֺ�';'�ָ��Ķ������б�.<br/>
	 * ���isOrderIdOnlyΪfalse, ������ϸ�Ķ�����Ϣ, ������Ϊ�ɶ��ĸ�ʽ���б�.<br/>
	 * <br/>
	 * ��ϸ�Ķ�����Ϣ��Ҫ�Ƿ����û��鿴��������, Ӧ�����и�ʽ�����Ű�,<br/>
	 * ÿ�ж�Ӧһ������,ÿ���ֶζ����ж���,����:<br/>
	 * <br/>
	 * 
	 * �û�����: xxxxxx ��������:<br/>
	 * [���]    [������]    [��������]     [...]     [...]    ... <br/>
	 *    1       xxxx      xxxxxxx      xxxx       xx          <br/>
	 *    2       xxxx      xxxxxxx      xxxx       xx          <br/>
	 *    3       xxxx      xxxxxxx      xxxx       xx          <br/>
	 * <br/><br/>
	 * 
	 * ����û�����username��Ч��Ϊ��ʱ,ָ�г������û��Ķ���.
	 * 
	 * @param username �û�����, ��Ϊ�� (��Ч��Ϊ��ʱ,ָ�г������û��Ķ���)
	 * @param isOrderIdOnly �Ƿ�����ض����ŵ��б�(Ϊtrueʱ), ΪfalseʱӦ������ϸ�Ķ�����Ϣ. 
	 * 
	 * @return �����÷ֺ�';'�ָ��Ķ������б�, ����ϸ�Ķ�����Ϣ, ����ִ�(û���κζ�����Ϣ).
	 * 
	 * @see #regUser(String, String, String)
	 * 
	 */
	public String listOrderInfo( String username, boolean isOrderIdOnly );

	/**
	 * ��ȡ�ۿ۲��Զ�����Ϣ
	 * 
	 * <br/><br/>
	 * ��ϸ�ۿ۲�����Ϣ. ����XML��ʽ����, ��StrategySchema.xsdԼ����StrategyDemo.xmlʾ��.
	 * <br/>
	 * 
	 * @return ��ϸ�ۿ۲�����Ϣ. ����XML��ʽ����, ��StrategySchema.xsdԼ����StrategyDemo.xmlʾ��.
	 * 
	 * @see #updateStrategies(String, String, String)
	 * 
	 */
	public String listStrategies();

	/**
	 * �������е��ۿ۲��Զ�����Ϣ
	 * 
	 * <br/><br/>
	 * ����֮��, ����������������,�µĲ��Զ����Ӧ�ñ�Ӧ��.<br/>
	 * <br/>
	 * ֻ�з����ṩ�̲���Ȩά���ۿ۲��Զ�����Ϣ, Ӧ��ֹ�����û�ִ�иò���.<br/>
	 * ���ø÷���ʱ, ����ָ����Ч���û�����/����.
	 * <br/>
	 * 
	 * @param username �û�����, ��ӦΪ��
	 * @param password �û�����, ��ӦΪ��
	 * @param newStrategies �µĲ��Զ���, ����XML��ʽ����, ��StrategySchema.xsdԼ����StrategyDemo.xmlʾ��.
	 * 
	 * @return �Ƿ���³ɹ�. >0: �ɹ�; <=0: ʧ��. 
	 * 
	 * @see #listStrategies()
	 * 
	 */
	public int updateStrategies( String username, String password, String newStrategies );

	/**
	 * ���һ���µ�, ��������еĺ���/��Ʊ��Ϣ
	 * 
	 * <br/><br/>
	 * �������һ���µĺ���/��Ʊ��Ϣ(���Flight.id�ֶε�ֵ��Ч/������), ����<br/>
	 * �������еĺ���/��Ʊ��Ϣ(���Flight.id�ֶε�ֵ����).<br/>
	 * <br/>
	 * ֻ�з����ṩ�̲���Ȩά������/��Ʊ������Ϣ, Ӧ��ֹ�����û�ִ�иò���.<br/>
	 * ���ø÷���ʱ, ����ָ����Ч���û�����/����.
	 * <br/>
	 * 
	 * @param username �û�����, ��ӦΪ��
	 * @param password �û�����, ��ӦΪ��
	 * @param flight   һ���µ�, �����еĺ���/��Ʊ������Ϣ
	 * 
	 * @return �Ƿ����/���³ɹ�. >0: �ɹ�; <=0: ʧ��. 
	 * 
	 */
	public int updateData( String username, String password, Flight flight );

	/**
	 * ע�����ļ��ӷ�����ʵ�stub����
	 * 
	 * <br/><br/>
	 * ��������ʵ�ָýӿڼ���:<br/>
	 * <br/>
	 * private static int internalCounter = 0;<br/>
	 * <br/>
	 * {<br/>
	 *   if (flag == 0)<br/>
	 *   {<br/>
	 *     internalCounter = 0;<br/>
	 *   }<br/>
	 * <br/>
	 *   return ++internalCounter;<br/>
	 * }<br/>
	 * <br/>
	 * 
	 * @param flag
	 * @return �ڲ�����ֵ(internalCounter)
	 */
	public int getServiceState(int flag);
}
