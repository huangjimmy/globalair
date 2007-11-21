
import java.util.*
class FlightServiceService {
	
	boolean transactional = true
	
	static expose=['xfire']
	
	def serviceMethod() {
		// TODO
	}
	
	Strategy chooseBestStrategy(params)
	{
	  def strategy

	  println "chooseBestStrategy"
	  println params
	  
	  Strategy.list().each
	  {
		  
		  if(strategy == null)
		  {
			  strategy = it
		  }
		  else if(strategy.discount < it.discount)
		  {
			  if(it.isApplicable(params))strategy = it
		  }
	  }
	
	  return strategy
	}
	
	public String hello()
	{
		def b = new HashMap()
		b.memberLevel = 5
		println new Strategy().executeStrategy(b)
		return "Hello"
	}
	
	public String hello2(String m)
	{
		return "Hello "+m+"!"
	}
	
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
	 private static int cc = 0
	public int regUser( String username, String password, String email )
	 {
		 def m = new Member()
		 m.nick = username
		 m.name = username
		 m.password = password
		 if(email == null || email == "")email = username+"@localhost"
		 m.email = email
		 ++cc
		 switch(1+(cc%5))
		 {
		 case 1:
			m.ffpLevel = "Normal"
			break;
		 case 2:
			 m.ffpLevel = "VIP"
			 break;
		 case 3:
			 m.ffpLevel = "Gold"
			 break;
		 case 4:
			 m.ffpLevel = "Platium"
			break;
		 case 5:
			 m.ffpLevel = "Premier"
			 break;
		 }
		 
	     m.address = ""
	     m.zipCode = ""
    	 m.landPhone = ""
	     m.mobilePhone = ""
		 m.description = ""
	 
		 println m
		 if(m.save())
		 {
			 return 1+(cc%5)
		 }
		 return -1;
	 }

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
	public int delUser( String username, String password )
	 {
		 println username+"\t"+password
		 
		 def m = Member.findWhere(nick:username)
		 println m
		 if(m == null)return 0
		 int mid = m.id
		 m = Member.findWhere(nick:username, password:password)
		 println m
		 if(m == null)return -1
		try{
			m.delete()
		
		
			 println mid + " deleted"
			 return mid
		}catch(Exception ex)
		{
			println ex
			return -1;
		}
		
	 }

	/**
	 * 查询航班/机票情况
	 * 
	 * <br/><br/>
	 * 根据若干条件查询航班/机票情况, 并按指定的排序方式返回航班详情列表.<br/>
	 * 如果指定了"出发日期"条件, 则只是查询当天(即到晚上12:00为止)的航班/机票信息.<br/>
	 * 调用该方法时, 必须指定有效的用户名称.
	 * <br/>
	 * 
	 * @param username   用户名称, 不应为空
	 * @param fromCity   出发城市, 为空时不匹配该字段
	 * @param toCity     到达城市, 为空时不匹配该字段
	 * @param startDate  出发日期, 格式: yyyy-MM-dd hh:mm, 为空时不匹配该字段
	 * @param cabin      指定舱段(1: 头等舱; 2: 商务舱; 3: 经济舱), 其他数值指任意舱段
	 * @param numbers    准备预订的座位数( >0指预订的座位数; <=0是指不考虑航班是否还有剩余座位 )
	 * @param orderBy    结果排序方式, 1: 按优惠折扣从大到小的顺序(缺省方式); 2: 按起飞时间从早到晚的顺序; 其他: 按缺省方式处理 
	 * 
	 * @return 符合条件的航班/机票信息; 如果没有任何符合条件的信息,则返回null(空值).
	 * 
	 * @see net.seproject.ws.flight.Flight
	 * @see #reserve(String, String, String, int, int)
	 * @see #listStrategies()
	 * 
	 */
	public net.seproject.ws.flight.Flight[] search( String username,
			String fromCity,
			String toCity,
			String startDate,
			int cabin,
			int numbers,
			int orderBy
			)
	 {
		 def member = Member.findByNick(username)
		 if(member == null)
		 {
			 println "Invalid user"
			 return null
		 }
		 
		 if(startDate == null)
		 {
			 //no open ticket avaliable
			 return null
		 }
		 
		 def froms = Airport.findAllByCityLike("%"+fromCity+"%")
		 def tos = Airport.findAllByCityLike("%"+toCity+"%")
		 
		 println froms
		 println tos
		 def flightList = []
		 froms.each
		 {
			 def from = it
			 tos.each
			 {
				 def to = it
			 	 flightList += Flight.findAllWhere(from:from, to:to)
			 	 
			 }
		 }
		 println flightList
		 
		 def flist = []
		 flightList.each
		 {
			 def fit = it
			 println it.id +"*****************************"
			 
			 net.seproject.ws.flight.Flight f = new net.seproject.ws.flight.Flight()
			 f.id = fit.number+""
			 f.company = fit.company
			 f.STD = "2000-01-01 "+fit.STD
			 f.STA = "2000-01-01 "+fit.STA
			 f.fromCity = fit.from.city
			 f.toCity = fit.to.city
			 
			 def d

			 if(startDate != "" && startDate != null)
			 {
				 startDate = startDate.replace('-', '/')
				 d = new Date(startDate)
				 
			 }
			 else
			 {
				 //d1 = new Date()
			 }
			
			 def strategy = chooseBestStrategy(["flight":fit, "member":member, "date":d])
			 
			 fit.seatClasses.each
			 {
				 if(it.type == 'Economy' && it.code == 'E')
				 {
					 f.economyClassPrice = (100-it.discount)*fit.basePrice/100
					 f.economyClassTotalSeats = it.capacity
					 
						 
						 if(strategy.discount/100.0 > 1-f.maxDiscount)
						 {
							 f.maxDiscount = 1-strategy.discount/100.0
						 }
		
						 f.economyClassAvailableSeats = it.getAvailable(["date":d, "seatClass":it])
						 //println d
						 //println f.economyClassAvailableSeats
					 
				 }
				 else if(it.type == 'First Class')
				 {
					 if(strategy.discount/100.0 > 1-f.maxDiscount)
					 {
						 f.maxDiscount = 1-strategy.discount/100.0
					 }
					 f.firstClassPrice = (100-it.discount)*fit.basePrice/100
					 f.firstClassTotalSeats = it.capacity
					 

						 f.firstClassAvailableSeats = it.getAvailable(["date":d, "seatClass":it])
						 //println d
					 
				 }
				 else if(it.type == 'Business Class')
				 {
					 if(strategy.discount/100.0 > 1-f.maxDiscount)
					 {
						 f.maxDiscount = 1-strategy.discount/100.0
					 }
					 f.businessClassPrice = (100-it.discount)*fit.basePrice/100
					 f.businessClassTotalSeats = it.capacity
					 
					 f.businessClassAvailableSeats = it.getAvailable(["date":d, "seatClass":it])
					 //println d

				 }
				 
			 }
			 
			 def maxdis = 1.0
			 boolean bestStrategy = false
			 def stra
			 def i
			 
			 f.detailedDiscount = ""
			 
			 for(i = 1 ;i<=3;++i)
			 {
				 maxdis = 1.0
				 stra = null
				 println "Policy:t"+i+" -********************"
				 Strategy.findAllWhere(type:"t"+i).each
				 {
					 if(it.isApplicable(["flight":fit, "member":member, "date":d]))
					 {
						 if( (it.discount/100.0) >= (1-maxdis))
						 {
							 stra = it
							 maxdis = 1- it.discount/100.0
							 println "New discount selected - "+maxdis
						 }
					 }
					 
				 }
			 
				 
				 if(stra == strategy)
				 {
					 f.detailedDiscount += i+" "+maxdis+"*<BR/>\r\n"
				 }
				 else if(maxdis <= 1.0)
				 {
					 f.detailedDiscount += i+" "+maxdis+"<BR/>\r\n"
				 }
				 else
				 {
					 f.detailedDiscount += i+" -<BR/>\r\n"
				 }
			 }
			
			//println f.detailedDiscount
		
			 f.detailedDiscount += "4 -<BR/>\r\n"
			 f.detailedDiscount += "5 -<BR/>\r\n"
			 flist += f
		 }
		 
		 def fl = ["a", "b"]
		 println fl
		 
		 return flist;
	 }

	/**
	 * 预订某个航班的机票
	 * 
	 * <br/><br/>
	 * 从查询结果里面选择合适的航班,预订机票.<br/>
	 * 调用该方法时, 必须指定有效的用户名称/密码.
	 * <br/>
	 * 
	 * @param username 用户名称, 不应为空
	 * @param password 用户密码, 不应为空
	 * @param flightId 航班号, 即Flight.id字段
	 * @param cabin    指定舱段(1: 头等舱; 2: 商务舱; 3: 经济舱), 其他数值无效
	 * @param numbers  准备预订的座位数(>=1张), 如果没有足够座位, 则不能预订成功.
	 * 
	 * @return 如果预订成功, 返回这个新的订单号(字串格式), 否则, 返回null(空值).
	 * 
	 * @see #search(String, String, String, String, int, int, int)
	 * @see #cancel(String, String, String)
	 * 
	 */
	public String reserve( String username, String password, String flightId, int cabin, int numbers )
	 {
		 def member = Member.findByNick(username)
		 
		 if(member == null)
		 {
			 println "no such user"
			 return null
		 }
		 
		 if(member.password != password){
			 println "auth failed"
			 return null
		 }
		 
		 def f = Flight.findByNumber(flightId)
		 if(f == null)
		 {
			 println "no such flight as "+flightId
			 return null;
		 }
		 def seatClass
		 switch(cabin)
		 {
		 case 1:
			 seatClass = SeatClass.findWhere(flight:f, type:"First Class")
			 break
		 case 2:
			 seatClass = SeatClass.findWhere(flight:f, type:"Business Class")
			 break
		 case 3:
			 seatClass = SeatClass.findWhere(flight:f, type:"Economy")
			 break
		 }
		 
		 println "tickets left:"+seatClass.getAvailable(["date":null])
		 if(numbers > seatClass.getAvailable(["date":null]))
		 {
			 println "not enough tickets"
			 return null
		 }
		 
		println "Ready to create a new booking..."
		 def booking = new Booking()
		 booking.member = member
		 booking.total = numbers
		 booking.status = "Submitted"
		 booking.seatClass = seatClass
		
		 booking.name = ""
		 booking.photoId = ""
		 booking.email = ""
		 booking.phone = ""
		 booking.mobilephone = ""
		 booking.address = ""
		 booking.zipCode = ""
		 booking.remark = "Created by Web Service"
		 booking.strategy = chooseBestStrategy(["flight":f, "member":member, "date":null])
		 booking.date = new Date()
		
		 println booking.strategy
			 
		 if(booking.save())
		 {
			 println booking
			 return booking.id
		 }
		 else
		 {
			 booking.errors.each { error ->
			          println error
			  }
			 println "Booking not created."
		 }
		 
		 return null
	 }

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
	public int cancel( String username, String password, String orderId )
	 {
		def member = Member.findByNick(username)
		 
		 if(member == null)return -1
		 
		 if(member.password != password)return -1
		 
		 try
		 {
			 def order = Booking.get(Integer.parseInt(orderId))
			 			 		 
			 if(order.member.nick != username || order.member.password != password)return -1
			 if(order == null)return 0
			 
			 order.status = "Cancelled"
			 if(order.save())
			 {
				 return order.Id
			 }
			 else
			 {
				 order.errors.each { error ->
		          println error
				 }
			 }
		 }
		catch(Exception ex)
		{
			return -1;
		}
		 
		 return -1
	 }

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
	public String listOrderInfo( String username, boolean isOrderIdOnly )
	 {
		 if(username == null)username = ""
		 
		 def member  = Member.findByNick(username)
		 if(member == null && isOrderIdOnly)
		 {
			 String orders = ""
			 Booking.list().each
			 {
				 orders += it.id+";"
			 }
			 
			 return orders
		 }
		 if(member != null && isOrderIdOnly)
		 {
			 String orders = ""
			 Booking.findAllWhere(member:member).each
			 {
				 orders += it.id+";"
			 }
			 
			 return orders
		 }
		 
		 def bookings
		 if(member != null)
		 {
			 bookings = Booking.findAllWhere(member:member)
		 }
		 else
		 {
			 bookings = Booking.list()
		 }
		 def orders = "[序号 Index]\t[订单号Order]\t[出发日期Date]\t[出发S]\t[到达D]\t[航班F]\t[座位Seat]\t[订单状态Status]\t[数量Total]\t[总价Amount Due]\r\n"
		 def i = 0
		 bookings.each
		 {
			 ++i
			 orders += i+"\t"+it.id+"\t"+it.date+"\t"+it.seatClass.flight.from+"\t"+
			 it.seatClass.flight.to+"\t"+it.seatClass.flight.number+"\t"+it.seatClass.type+"\t"+
			 it.status+"\t"+it.total+"\t"+it.getFinalTotalPrice()+"\r\n"
		 }
		 return orders;
	 }

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
	public String listStrategies()
	 {
		 def stra = Strategy.findByType("wsRule")
		 if(stra == null || stra.rule == "")
		 {
			 
		 }
		 else
		 {
			 return stra.rule
		 }
		 return "";
	 }

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
	public int updateStrategies( String username, String password, String n )
	 {
		 if(username != "partner" || password != "12345678")return -1
		 
		 return -1
	 }

	/**
	 * 添加一个新的, 或更新已有的航班/机票信息
	 * 
	 * <br/><br/>
	 * 用来添加一个新的航班/机票信息(如果Flight.id字段的值无效/不存在), 或者<br/>
	 * 更新已有的航班/机票信息(如果Flight.id字段的值存在).<br/>
	 * <br/>
	 * 只有服务提供商才有权维护航班/机票定义信息, 应禁止其他用户执行该操作.<br/>
	 * 调用该方法时, 必须指定有效的用户名称/密码.
	 * <br/>
	 * 
	 * @param username 用户名称, 不应为空
	 * @param password 用户密码, 不应为空
	 * @param flight   一个新的, 或已有的航班/机票定义信息
	 * 
	 * @return 是否添加/更新成功. >0: 成功; <=0: 失败. 
	 * 
	 */
	public int updateData( String username, String password, net.seproject.ws.flight.Flight f )
	 {
		 try{
			 if(username != "partner" || password != "12345678")return -1
			 def flight = Flight.findByNumber(f.id)
			 if(flight == null)
			 {
				 flight = new Flight()
			 }
		 
		 
			 flight.STD = f.STD.substring(f.STD.indexOf(' '))
			 flight.STA = f.STA.substring(f.STA.indexOf(' '))
			 flight.number = f.id
			 flight.company = f.company
			 
			 def froms = []
			 froms += Airport.findAllByCityLike("%"+f.fromCity+"%")
			 def tos = []
			 tos += Airport.findAllByCityLike("%"+f.toCity+"%")
			 
			 println f.fromCity+"\t"+f.toCity
			 
			 def from
			 def to
			 if(froms != null && froms.size()>0)from = froms[0]
			 if(tos != null && tos.size()>0)to = tos[0]
			 
			 if(from == null)
			 {
				 from = new Airport()
				 from.code = f.fromCity
				 from.city = f.fromCity
				 from.save()
			 }
			 if(to == null)
			 {
				 to = new Airport()
				 to.code = f.toCity
				 to.city = f.toCity
				 to.save()
			 }
			 
			 flight.from = from
			 flight.to = to
			 flight.schedule = "1234567"
			 flight.basePrice = f.economyClassPrice
			 flight.description = ""
			 
			 println "Save flight first"
			 if(flight.save())
			 {
				 
			 }
			 else
			 {
				 flight.errors.each { error ->
		          println error
				 }
			 }
			 println "try to install Seats"
			 
			 println flight.id+" \t is the new flight id in system"
			 if(f.economyClassTotalSeats >=0)
			 {
				 def seat
				 try{
				 seat = SeatClass.findWhere(flight:flight, type:"Economy")
				 }catch(Exception){}
				 
				 if(seat == null)seat = new SeatClass()
				 flight.save()
				 seat.flight = flight
				 seat.type = "Economy"
				 seat.code = "E"
				 seat.discount = 0
				 seat.capacity = f.economyClassTotalSeats
				 seat.description = "Created by Web Service"
				println "Save economy"
				 seat.save()
				 
			 }
			 
			 if(f.businessClassTotalSeats >=0)
			 {
				 def seat = SeatClass.findWhere(flight:flight, type:"Business Class")
				 if(seat == null)seat = new SeatClass()
				 flight.save()
				 seat.flight = flight
				 seat.type = "Business Class"
				 seat.code = "B"
				 seat.discount = (1-f.businessClassPrice*1.0/flight.basePrice)*100
				 seat.capacity = f.businessClassTotalSeats
				 seat.description = "Created by Web Service"
					 println "Save business"
				 seat.save()
			 }
			 
			 if(f.firstClassTotalSeats >=0)
			 {
				 def seat = SeatClass.findWhere(flight:flight, type:"First Class")
				 if(seat == null)seat = new SeatClass()
				 flight.save()
				 seat.flight = flight
				 seat.type = "First Class"
				 seat.code = "F"
				 seat.discount = (1-f.firstClassPrice*1.0/flight.basePrice)*100
				 seat.capacity = f.firstClassTotalSeats
				 seat.description = "Created by Web Service"
					 println "Save first class"
			     seat.save()
			 }
			 
			 println "Save last objects"
			 if(flight.save())
			 {
				 return 1
			 }
			 else
			 {
				 flight.errors.each { error ->
		          println error
		  }
			 }
		 
		 }
		 catch(Exception ex)
		 {
			 println ex
			 return -1;
		 }
		 return -1;
	 }

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
	public int getServiceState(int flag)
	 {
		 if(flag == 0)internalCounter = 0;
		 
		 return ++internalCounter
	 }
	 
	private static int internalCounter = 0
}

