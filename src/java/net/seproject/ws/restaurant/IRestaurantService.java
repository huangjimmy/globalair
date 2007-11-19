/**
 * Created on Nov 04, 2007
 * Copyright: SEGroup.cs.tsinghua.edu.cn
 * All rights reserved.
 * 
 */
package net.seproject.ws.restaurant;

/**
 * ����Ԥ������(�ӿ�)
 * 
 * @author shufang
 * @version 1.0
 *
 */
public interface IRestaurantService {

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
	 * ��ѯ����/��λ���
	 * 
	 * <br/><br/>
	 * ��������������ѯ����/��λ���, ����ָ��������ʽ���ز�λ�����б�.<br/>
	 * ���ָ����"��������"����, ��ֻ�ǲ�ѯ����(��������12:00Ϊֹ)�Ĳ���/��λ��Ϣ.<br/>
	 * ���ø÷���ʱ, ����ָ����Ч���û�����.
	 * <br/>
	 * 
	 * @param username   �û�����, ��ӦΪ��
	 * @param restName   ��������, Ϊ��ʱ��ƥ����ֶ�
	 * @param inCity     ���ڳ���, Ϊ��ʱ��ƥ����ֶ�
	 * @param startDate  �Ͳ�����, ��ʽ: yyyy-MM-dd hh:mm, Ϊ��ʱ��ƥ����ֶ�
	 * @param category   ָ����λλ��/���(1~3��Ч, ��С�����ж�����庬��), ������ֵָ�������
	 * @param numbers    ׼��Ԥ���Ĳ�λ����( >0ָԤ��������; <=0��ָ�����ǲ����Ƿ���ʣ���λ )
	 * @param orderBy    �������ʽ, 1: ���Ż��ۿ۴Ӵ�С��˳��(ȱʡ��ʽ); 2: ���Ͳ�ʱ����絽���˳��; ����: ��ȱʡ��ʽ���� 
	 * 
	 * @return ���������Ĳ���/��λ��Ϣ; ���û���κη�����������Ϣ,�򷵻�null(��ֵ).
	 * 
	 * @see net.seproject.ws.restaurant.RestaTable
	 * @see #reserve(String, String, String, int, int)
	 * @see #listStrategies()
	 * 
	 */
	public RestaTable[] search( String username,
			String restName,
			String inCity,
			String startDate,
			int category,
			int numbers,
			int orderBy
			);

	/**
	 * Ԥ��ĳ�������Ĳ�λ
	 * 
	 * <br/><br/>
	 * �Ӳ�ѯ�������ѡ����ʵĲ���,Ԥ����λ.<br/>
	 * ���ø÷���ʱ, ����ָ����Ч���û�����/����.
	 * <br/>
	 * 
	 * @param username �û�����, ��ӦΪ��
	 * @param password �û�����, ��ӦΪ��
	 * @param restaId  ��������, ��RestaTable.id�ֶ�
	 * @param category ָ����λλ��/���(1~3��Ч, ��С�����ж�����庬��), ������ֵ��Ч
	 * @param numbers  ׼��Ԥ���Ĳ�λ����( >=1λ ), ���û���㹻��λ, ����Ԥ���ɹ�.
	 * 
	 * @return ���Ԥ���ɹ�, ��������µĶ�����(�ִ���ʽ), ����, ����null(��ֵ).
	 * 
	 * @see #search(String, String, String, String, int, int, int)
	 * 
	 */
	public String reserve( String username, String password, String restaId, int category, int numbers );

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
	 * ���һ���µ�, ��������еĲ���/��λ��Ϣ
	 * 
	 * <br/><br/>
	 * �������һ���µĲ���/��λ��Ϣ(���RestaTable.id�ֶε�ֵ��Ч/������), ����<br/>
	 * �������еĲ���/��λ��Ϣ(���RestaTable.id�ֶε�ֵ����).<br/>
	 * <br/>
	 * ֻ�з����ṩ�̲���Ȩά������/��λ������Ϣ, Ӧ��ֹ�����û�ִ�иò���.<br/>
	 * ���ø÷���ʱ, ����ָ����Ч���û�����/����.
	 * <br/>
	 * 
	 * @param username �û�����, ��ӦΪ��
	 * @param password �û�����, ��ӦΪ��
	 * @param restatable һ���µ�, �����еĲ���/��λ������Ϣ
	 * 
	 * @return �Ƿ����/���³ɹ�. >0: �ɹ�; <=0: ʧ��. 
	 * 
	 */
	public int updateData( String username, String password, RestaTable restatable );

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
