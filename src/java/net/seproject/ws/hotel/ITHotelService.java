/**
 * Created on Nov 04, 2007
 * Copyright: SEGroup.cs.tsinghua.edu.cn
 * All rights reserved.
 * 
 */
package net.seproject.ws.hotel;

/**
 * 酒店预订服务(接口)
 * 
 * @author shufang
 * @version 1.0
 *
 */
public interface ITHotelService {

	/**
	 * 注册一个用户帐号
	 * 
	 * <br/><br/>
	 * 任何人都可自助的在系统上注册帐号, 如果该帐号已存在,则不能注册成功.<br/>
	 * 输入的用户名称作为帐号名称, 同一个用户名称只能注册一个同名帐号.<br/>
	 * 如果注册成功, 系统应随机指定这个用户的会员卡级别(分为1-5星级).
	 * <br/>
	 * 
	 * @param username 用户名称, 不应为空
	 * @param password 用户密码, 不应为空
	 * @param email    用户Email地址, 可为空
	 * 
	 * @return 是否注册成功. >0: 注册成功并返回会员卡级别(取值1~5); =0: 用户已存在; <0: 出错
	 * 
	 * @see #delUser(String, String)
	 * 
	 */
	public int regUser( String username, String password, String email );

	/**
	 * 删除一个用户帐号
	 * 
	 * <br/><br/>
	 * 用户可以删除自己的帐号, 只要指定了正确的用户名称/密码.<br/>
	 * 删除用户时, 用户所有的个人信息和订单信息等一起被删除.
	 * <br/>
	 * 
	 * @param username 用户名称, 不应为空
	 * @param password 用户密码, 不应为空
	 * 
	 * @return 是否删除成功. >0: 删除成功; =0: 用户不存在; <0: 出错
	 * 
	 * @see #regUser(String, String, String)
	 * 
	 */
	public int delUser( String username, String password );

	/**
	 * 查询酒店/客房情况
	 * 
	 * <br/><br/>
	 * 根据若干条件查询酒店/客房情况, 并按指定的排序方式返回客房详情列表.<br/>
	 * 如果指定了"入住日期"条件, 则只是查询当天(即到晚上12:00为止)的酒店/客房信息.<br/>
	 * 调用该方法时, 必须指定有效的用户名称.
	 * <br/>
	 * 
	 * @param username   用户名称, 不应为空
	 * @param hotelName  酒店名称, 为空时不匹配该字段
	 * @param inCity     所在城市, 为空时不匹配该字段
	 * @param startDate  入住日期, 格式: yyyy-MM-dd hh:mm, 为空时不匹配该字段
	 * @param category   指定客房类别(1: 高级套间; 2: 商务间; 3: 标准间), 其他数值指任意类别
	 * @param numbers    准备预订的客房数量( >0指预订的数量; <=0是指不考虑酒店是否还有剩余客房 )
	 * @param orderBy    结果排序方式, 1: 按优惠折扣从大到小的顺序(缺省方式); 2: 按入住时间从早到晚的顺序; 其他: 按缺省方式处理 
	 * 
	 * @return 符合条件的酒店/客房信息; 如果没有任何符合条件的信息,则返回null(空值).
	 * 
	 * @see net.seproject.ws.hotel.HotelRoom
	 * @see #reserve(String, String, String, int, int)
	 * @see #listStrategies()
	 * 
	 */
	public HotelRoom[] search( String username,
			String hotelName,
			String inCity,
			String startDate,
			int category,
			int numbers,
			int orderBy
			);

	/**
	 * 预订某个酒店的客房
	 * 
	 * <br/><br/>
	 * 从查询结果里面选择合适的酒店,预订客房.<br/>
	 * 调用该方法时, 必须指定有效的用户名称/密码.
	 * <br/>
	 * 
	 * @param username 用户名称, 不应为空
	 * @param password 用户密码, 不应为空
	 * @param hotelId  酒店代号, 即HotelRoom.id字段
	 * @param category 指定客房类别(1: 高级套间; 2: 商务间; 3: 标准间), 其他数值无效
	 * @param numbers  准备预订的客房数量( >=1间 ), 如果没有足够客房, 则不能预订成功.
	 * 
	 * @return 如果预订成功, 返回这个新的订单号(字串格式), 否则, 返回null(空值).
	 * 
	 * @see #search(String, String, String, String, int, int, int)
	 * @see #cancel(String, String, String)
	 * 
	 */
	public String reserve( String username, String password, String hotelId, int category, int numbers );

	/**
	 * 取消一张订单
	 * 
	 * <br/><br/>
	 * 根据指定的订单号，取消这张订单.<br/>
	 * 用户只能取消自己的订单.<br/>
	 * 调用该方法时, 必须指定有效的用户名称/密码.
	 * <br/>
	 * 
	 * @param username 用户名称, 不应为空
	 * @param password 用户密码, 不应为空
	 * @param orderId  订单号, 不应为空
	 * 
	 * @return 订单是否取消成功. >0: 取消成功; =0: 订单不存在; <0: 出错
	 * 
	 * @see #reserve(String, String, String, int, int)
	 * 
	 */
	public int cancel( String username, String password, String orderId ); 

	/**
	 * 获取订单信息
	 * 
	 * <br/><br/>
	 * 如果isOrderIdOnly为true, 返回用分号';'分隔的订单号列表.<br/>
	 * 如果isOrderIdOnly为false, 返回详细的订单信息, 采用人为可读的格式化列表.<br/>
	 * <br/>
	 * 详细的订单信息主要是方便用户查看订单详情, 应对行列格式进行排版,<br/>
	 * 每行对应一个订单,每个字段都按列对齐,例如:<br/>
	 * <br/>
	 * 
	 * 用户名称: xxxxxx 订单详情:<br/>
	 * [序号]    [订单号]    [出发日期]     [...]     [...]    ... <br/>
	 *    1       xxxx      xxxxxxx      xxxx       xx          <br/>
	 *    2       xxxx      xxxxxxx      xxxx       xx          <br/>
	 *    3       xxxx      xxxxxxx      xxxx       xx          <br/>
	 * <br/><br/>
	 * 
	 * 如果用户名称username无效或为空时,指列出所有用户的订单.
	 * 
	 * @param username 用户名称, 可为空 (无效或为空时,指列出所有用户的订单)
	 * @param isOrderIdOnly 是否仅返回订单号的列表(为true时), 为false时应返回详细的订单信息. 
	 * 
	 * @return 返回用分号';'分隔的订单号列表, 或详细的订单信息, 或空字串(没有任何订单信息).
	 * 
	 * @see #regUser(String, String, String)
	 * 
	 */
	public String listOrderInfo( String username, boolean isOrderIdOnly );

	/**
	 * 获取折扣策略定义信息
	 * 
	 * <br/><br/>
	 * 详细折扣策略信息. 采用XML形式描述, 见StrategySchema.xsd约定和StrategyDemo.xml示例.
	 * <br/>
	 * 
	 * @return 详细折扣策略信息. 采用XML形式描述, 见StrategySchema.xsd约定和StrategyDemo.xml示例.
	 * 
	 * @see #updateStrategies(String, String, String)
	 * 
	 */
	public String listStrategies();

	/**
	 * 更新已有的折扣策略定义信息
	 * 
	 * <br/><br/>
	 * 更新之后, 不必重新启动服务,新的策略定义就应该被应用.<br/>
	 * <br/>
	 * 只有服务提供商才有权维护折扣策略定义信息, 应禁止其他用户执行该操作.<br/>
	 * 调用该方法时, 必须指定有效的用户名称/密码.
	 * <br/>
	 * 
	 * @param username 用户名称, 不应为空
	 * @param password 用户密码, 不应为空
	 * @param newStrategies 新的策略定义, 采用XML形式描述, 见StrategySchema.xsd约定和StrategyDemo.xml示例.
	 * 
	 * @return 是否更新成功. >0: 成功; <=0: 失败. 
	 * 
	 * @see #listStrategies()
	 * 
	 */
	public int updateStrategies( String username, String password, String newStrategies );

	/**
	 * 添加一个新的, 或更新已有的酒店/客房信息
	 * 
	 * <br/><br/>
	 * 用来添加一个新的酒店/客房信息(如果HotelRoom.id字段的值无效/不存在), 或者<br/>
	 * 更新已有的酒店/客房信息(如果HotelRoom.id字段的值存在).<br/>
	 * <br/>
	 * 只有服务提供商才有权维护酒店/客房定义信息, 应禁止其他用户执行该操作.<br/>
	 * 调用该方法时, 必须指定有效的用户名称/密码.
	 * <br/>
	 * 
	 * @param username 用户名称, 不应为空
	 * @param password 用户密码, 不应为空
	 * @param hotelroom 一个新的, 或已有的酒店/客房定义信息
	 * 
	 * @return 是否添加/更新成功. >0: 成功; <=0: 失败. 
	 * 
	 */
	public int updateData( String username, String password, HotelRoom hotelroom );

	/**
	 * 注册中心监视服务访问的stub方法
	 * 
	 * <br/><br/>
	 * 按照下面实现该接口即可:<br/>
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
	 * @return 内部计数值(internalCounter)
	 */
	public int getServiceState(int flag);

}
