
import java.util.*
import javax.xml.parsers.*

class FlightService {
	
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
	 * ע��һ���û��ʺ�
	 * 
	 * <br/><br/>
	 * �κ��˶����������ϵͳ��ע���ʺ�, �����ʺ��Ѵ���,����ע��ɹ�.<br/>
	 * ������û������Ϊ�ʺ����, ͬһ���û����ֻ��ע��һ��ͬ���ʺ�.<br/>
	 * ���ע��ɹ�, ϵͳӦ���ָ������û��Ļ�Ա������(��Ϊ1-5�Ǽ�).
	 * <br/>
	 * 
	 * @param username �û����, ��ӦΪ��
	 * @param password �û�����, ��ӦΪ��
	 * @param email    �û�Email��ַ, ��Ϊ��
	 * 
	 * @return �Ƿ�ע��ɹ�. >0: ע��ɹ������ػ�Ա������(ȡֵ1~5); =0: �û��Ѵ���; <0: ���
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
	 * ɾ��һ���û��ʺ�
	 * 
	 * <br/><br/>
	 * �û�����ɾ���Լ����ʺ�, ֻҪָ������ȷ���û����/����.<br/>
	 * ɾ���û�ʱ, �û����еĸ�����Ϣ�Ͷ�����Ϣ��һ��ɾ��.
	 * <br/>
	 * 
	 * @param username �û����, ��ӦΪ��
	 * @param password �û�����, ��ӦΪ��
	 * 
	 * @return �Ƿ�ɾ��ɹ�. >0: ɾ��ɹ�; =0: �û�������; <0: ���
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
		 
		 Booking.findAllWhere(member:m).each
		 {
			 it.delete()
		 }
		 
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
	 * ��ѯ����/��Ʊ���
	 * 
	 * <br/><br/>
	 * �����������ѯ����/��Ʊ���, ����ָ��������ʽ���غ��������б�.<br/>
	 * ���ָ����"������"���, ��ֻ�ǲ�ѯ����(��������12:00Ϊֹ)�ĺ���/��Ʊ��Ϣ.<br/>
	 * ���ø÷���ʱ, ����ָ����Ч���û����.
	 * <br/>
	 * 
	 * @param username   �û����, ��ӦΪ��
	 * @param fromCity   �����, Ϊ��ʱ��ƥ����ֶ�
	 * @param toCity     �������, Ϊ��ʱ��ƥ����ֶ�
	 * @param startDate  ������, ��ʽ: yyyy-MM-dd hh:mm, Ϊ��ʱ��ƥ����ֶ�
	 * @param cabin      ָ���ն�(1: ͷ�Ȳ�; 2: �����; 3: ���ò�), ������ֵָ����ն�
	 * @param numbers    ׼��Ԥ������λ��( >0ָԤ������λ��; <=0��ָ�����Ǻ����Ƿ���ʣ����λ )
	 * @param orderBy    �������ʽ, 1: ���Ż��ۿ۴Ӵ�С��˳��(ȱʡ��ʽ); 2: �����ʱ����絽���˳��; ����: ��ȱʡ��ʽ���� 
	 * 
	 * @return �������ĺ���/��Ʊ��Ϣ; ���û���κη���������Ϣ,�򷵻�null(��ֵ).
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
		 if(startDate == null)startDate = ""
		 println "search flights :"+username+" "+fromCity+
		 " "+toCity+" "+startDate
		 
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
				 d = new Date()
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
		 
		
		 switch(orderBy)
		 {
		 case 1:
			 return flist.sort{p1, p2-> p1.maxDiscount - p2.maxDiscount}
			 break
		 case 2:
			 return flist.sort{p1, p2 -> p1.STD < p2.STD?-1:1}
			 break
		 }
		 
		 
		 
		 return flist;
	 }

	/**
	 * Ԥ��ĳ���Ļ�Ʊ
	 * 
	 * <br/><br/>
	 * �Ӳ�ѯ�������ѡ����ʵĺ���,Ԥ����Ʊ.<br/>
	 * ���ø÷���ʱ, ����ָ����Ч���û����/����.
	 * <br/>
	 * 
	 * @param username �û����, ��ӦΪ��
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
	public String reserve( String username, String password, String flightId, int cabin, String startDate, int numbers )
	 {
		 def member = Member.findByNick(username)
		 
		 if(member == null)
		 {
			 println "no such user"
			 return "no such user"
		 }
		 
		 if(member.password != password){
			 println "auth failed"
			 return "auth failed"
		 }
		 
		 def f = Flight.findByNumber(flightId)
		 if(f == null)
		 {
			 println "no such flight as "+flightId
			 return "no such flight as "+flightId
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
		 try{
		 booking.date = new Date(startDate..replace('-','/'))
		 }catch(Exception ){booking.date = new Date()}
		
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
	 * ȡ��һ�Ŷ���
	 * 
	 * <br/><br/>
	 * ���ָ���Ķ����ţ�ȡ�����Ŷ���.<br/>
	 * �û�ֻ��ȡ���Լ��Ķ���.<br/>
	 * ���ø÷���ʱ, ����ָ����Ч���û����/����.
	 * <br/>
	 * 
	 * @param username �û����, ��ӦΪ��
	 * @param password �û�����, ��ӦΪ��
	 * @param orderId  ������, ��ӦΪ��
	 * 
	 * @return �����Ƿ�ȡ��ɹ�. >0: ȡ��ɹ�; =0: ����������; <0: ���
	 * 
	 * @see #reserve(String, String, String, int, int)
	 * 
	 */
	public int cancel( String username, String password, String orderId )
	 {
		def member = Member.findByNick(username)
		 
		 if(member == null)return -1
		 
		 if(member.password != password)return -1
		 
		 println "This user "+username+" want to cancel order "+orderId
		 
		 try
		 {
			 def order = Booking.get(Integer.parseInt(orderId))
			 
			 println "Order to cancel is " + order
			 			 		 
			 if(order.member.nick != username || order.member.password != password)
			{
				 println "This user "+username+" cannot cancel order "+order
				 return -1
			}
			 if(order == null)return 0
			 
			 order.status = "Cancelled"
			 if(order.save())
			 {
				 return 1
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
			println ex
			return -1;
		}
		 
		 return -1
	 }

	/**
	 * ��ȡ������Ϣ
	 * 
	 * <br/><br/>
	 * ���isOrderIdOnlyΪtrue, �����÷ֺ�';'�ָ�Ķ������б�.<br/>
	 * ���isOrderIdOnlyΪfalse, ������ϸ�Ķ�����Ϣ, ������Ϊ�ɶuĸ�ʽ���б�.<br/>
	 * <br/>
	 * ��ϸ�Ķ�����Ϣ��Ҫ�Ƿ����û��鿴��������, Ӧ�����и�ʽ�����Ű�,<br/>
	 * ÿ�ж�Ӧһ���,ÿ���ֶζ����ж���,����:<br/>
	 * <br/>
	 * 
	 * �û����: xxxxxx ��������:<br/>
	 * [���]    [������]    [������]     [...]     [...]    ... <br/>
	 *    1       xxxx      xxxxxxx      xxxx       xx          <br/>
	 *    2       xxxx      xxxxxxx      xxxx       xx          <br/>
	 *    3       xxxx      xxxxxxx      xxxx       xx          <br/>
	 * <br/><br/>
	 * 
	 * ����û����username��Ч��Ϊ��ʱ,ָ�г������û��Ķ���.
	 * 
	 * @param username �û����, ��Ϊ�� (��Ч��Ϊ��ʱ,ָ�г������û��Ķ���)
	 * @param isOrderIdOnly �Ƿ��ض����ŵ��б�(Ϊtrueʱ), ΪfalseʱӦ������ϸ�Ķ�����Ϣ. 
	 * 
	 * @return �����÷ֺ�';'�ָ�Ķ������б�, ����ϸ�Ķ�����Ϣ, ����ִ�(û���κζ�����Ϣ).
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
		 def orders = "[��� Index]\t[������Order]\t[������Date]\t[��S]\t[����D]\t[����F]\t[��λSeat]\t[����״̬Status]\t[��Total]\t[�ܼ�Amount Due]\r\n"
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
	 * �������е��ۿ۲��Զ�����Ϣ
	 * 
	 * <br/><br/>
	 * ����֮��, �������������,�µĲ��Զ����Ӧ�ñ�Ӧ��.<br/>
	 * <br/>
	 * ֻ�з����ṩ�̲���Ȩά���ۿ۲��Զ�����Ϣ, Ӧ��ֹ�����û�ִ�иò���.<br/>
	 * ���ø÷���ʱ, ����ָ����Ч���û����/����.
	 * <br/>
	 * 
	 * @param username �û����, ��ӦΪ��
	 * @param password �û�����, ��ӦΪ��
	 * @param newStrategies �µĲ��Զ���, ����XML��ʽ����, ��StrategySchema.xsdԼ����StrategyDemo.xmlʾ��.
	 * 
	 * @return �Ƿ���³ɹ�. >0: �ɹ�; <=0: ʧ��. 
	 * 
	 * @see #listStrategies()
	 * 
	 */
	public int updateStrategies( String username, String password, String n )
	 {
		 def stra = Strategy.findByType("wsRule")
		 stra.rule = n
		 try{
		 //if(username != "partner" || password != "12345678")return -1
			 def member = Member.findByNick(username)
			 
			 if(member == null)
			 {
				 println "no such user"
				 return "no such user"
			 }
			 
			 if(member.password != password){
				 println "auth failed"
				 return "auth failed"
			 }
		 
			 def builder     = DocumentBuilderFactory.newInstance().newDocumentBuilder()
			 println n
			 def inputStream = new StringBufferInputStream(n)
			 def records     = builder.parse(inputStream).documentElement
			 def stra_s = new HashMap()
		 try{
			 //transform xml into hashmap for easy access
			 records.childNodes.each
			 {
				 if(it.nodeName == "strategy")
				 {
					 def tx = it.attributes.getNamedItem("type").nodeValue
					 
					 //println it.nodeName+"\t"+tx
					 stra_s[tx] = []
				 	it.childNodes.each
				 	{
						 if(it.nodeName == "rule")
						 {
							 def rule = new HashMap()
							 if(it.attributes.getNamedItem("arg1")!=null)
							 {
								 rule.arg1 = it.attributes.getNamedItem("arg1").nodeValue
							 }
							 if(it.attributes.getNamedItem("arg2")!=null)
							 {
								 rule.arg2 = it.attributes.getNamedItem("arg2").nodeValue
							 }
							 if(it.attributes.getNamedItem("arg3")!=null)
							 {
								 rule.arg3 = it.attributes.getNamedItem("arg3").nodeValue
							 }
							// println "\t"+it.nodeName+" "+" "+it.attributes.getNamedItem("arg1")+" "+it.attributes.getNamedItem("arg2")+" "+it.attributes.getNamedItem("arg3")
							 it.childNodes.each
							 {
								 rule.value = it.nodeValue
								 //println "\t\tvalue="+it.nodeValue
							 }
							 stra_s[tx]+= rule
						 }
				 	}
				 }
			 }
			 
			 //println stra_s
			 //def newstrats = new org.mortbay.xml.XmlParser().parse(new StringBufferInputStream(n))
			 //println newstrats
	
			 if(stra.save())
			 {
				 //only accept t1 to t3
				 def i
				 def tt1 = Strategy.findAllWhere(type:"t1")
				 
				 i = 0
				 stra_s.t1.each
				 {
					 def ffpLevel=["","Normal","VIP","Gold","Platium","Premier"]
					 def tt11 = tt1[i]
					 
					 if(tt11 == null)tt11 = new Strategy()
					 tt11.rule = "ffpLevel == \""+ffpLevel[Integer.parseInt(it.arg1)]+"\""
					 tt11.type = "t1"
					 tt11.description = "Frequent Flyer"
					 tt11.discount = 100-(new Double(it.value))*100
					 
					 println tt11
					 tt11.save()
					 ++i
				 }
				 
				 i = 0
				 def tt2 = Strategy.findAllWhere(type:"t2")
				 stra_s.t2.each
				 {
					def tt21 = tt2[i]
					 
					 if(tt21 == null)tt21 = new Strategy()
					 tt21.rule = "travelTime >= new Date(\"2000/1/1 "+it.arg1+"\") && travelTime <= new Date(\"2000/1/1 "+it.arg2+"\")"
					 tt21.type = "t2"
					 tt21.description = "t2 Specific range"
					 tt21.discount = 100-(new Double(it.value))*100
					 
					 println tt21
					 tt21.save()
					 ++i
				 }
				 
				 i = 0
				 def tt3 = Strategy.findAllWhere(type:"t3")
				 stra_s.t3.each
				 {
					 def tt31 = tt3[i]
					 
					 if(tt31 == null)tt31 = new Strategy()
					 
					 if(it.arg3 == "0")
					 {
						 tt31.rule = "true"
					 }
					 else
					 {
						 tt31.rule = "travelDate >= new Date(\""+it.arg1.replace('-', '/')+"\") && travelDate <= new Date(\""+it.arg2.replace('-', '/')+"\")"
					 }
					 tt31.type = "t3"
					 tt31.description = "t3 Busy season or not"
					 tt31.discount = 100-(new Double(it.value))*100
					 
					 println tt31
					 tt31.save()
					 ++i
				 }
				 
				 return 1
			 }
			 else
			 {
				 stra.errors.each { error ->
		          println error
				 }
			 }
			 }catch(Exception ex)
			 {
				 println ex
		 	}
		 }catch(Exception )
		 {
			 println "save xml string of strategies"
			 stra.save()
			 return 1
		 }
		 return -1
	 }

	/**
	 * ���һ���µ�, ��������еĺ���/��Ʊ��Ϣ
	 * 
	 * <br/><br/>
	 * ��4���һ���µĺ���/��Ʊ��Ϣ(���Flight.id�ֶε�ֵ��Ч/������), ����<br/>
	 * �������еĺ���/��Ʊ��Ϣ(���Flight.id�ֶε�ֵ����).<br/>
	 * <br/>
	 * ֻ�з����ṩ�̲���Ȩά������/��Ʊ������Ϣ, Ӧ��ֹ�����û�ִ�иò���.<br/>
	 * ���ø÷���ʱ, ����ָ����Ч���û����/����.
	 * <br/>
	 * 
	 * @param username �û����, ��ӦΪ��
	 * @param password �û�����, ��ӦΪ��
	 * @param flight   һ���µ�, �����еĺ���/��Ʊ������Ϣ
	 * 
	 * @return �Ƿ����/���³ɹ�. >0: �ɹ�; <=0: ʧ��. 
	 * 
	 */
	public int updateData( String username, String password, net.seproject.ws.flight.Flight f )
	 {
		 try{
			 //if(username != "partner" || password != "12345678")return -1
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
			 
			 def flight = Flight.findByNumber(f.id)
			 if(flight == null)
			 {
				 flight = new Flight()
			 }
		 
		 
			 flight.STD = f.STD.substring(f.STD.indexOf(' ')+1)
			 flight.STA = f.STA.substring(f.STA.indexOf(' ')+1)
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
			 if(f.economyClassPrice == 0){
				 flight.basePrice = f.businessClassPrice*0.9
				 if(ff.businessClassPrice == 0)flight.basePrice = f.firstClassPrice*0.8
			 }
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
	public int getServiceState(int flag)
	 {
		 if(flag == 0)internalCounter = 0;
		 
		 return ++internalCounter
	 }
	 
	private static int internalCounter = 0
}

