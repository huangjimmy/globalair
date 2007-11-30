class Booking { 
	
	PromotionService promotionService = new PromotionService()
	static transients = ['promotionService', 'flightList', 'price_oper', 'expect_price', 'search_from', 'search_to', 'search_from', 'search_to']
	String price_oper
	double expect_price
	Airport search_from
	Airport search_to
	
	Date date;//travel date
	int total
	Member member;
	SeatClass seatClass
	String name;
	String photoId;
	String email;
	String phone;
	String mobilephone;
	String address;
	String zipCode;
	String remark;
	String status;
	
	Strategy strategy
	
	double finalBasePrice = 0

	def flightList
	
	def getFinalBasePrice()
	{
		if(finalBasePrice > 0)return finalBasePrice
		
		double additional = 1.0
		if(strategy != null)
		{
			additional = (100-strategy.getDiscount())/100.0
		}
		
		if(seatClass == null)return 0.0;
		
		finalBasePrice = additional*seatClass.flight.basePrice*(100-seatClass.discount)/100.0
		
		return finalBasePrice
	}
	
	def getFinalPrice()
	{
		return getFinalBasePrice()+seatClass.taxAndFees
	}
	
	def getFinalTotalPrice()
	{
		return total*getFinalPrice()
	}
	
	def getCalcReason()
	{
		if(strategy != null)return strategy.getReason()
		return ""
	}
	
	
	boolean withCondition(SeatClass s)
	{
		if(expect_price == 0)return true;
		switch(price_oper)
		{
		case '>':
			return (100-s.discount)*s.flight.basePrice/100>expect_price
			break;
		case '<':
			return (100-s.discount)*s.flight.basePrice/100<expect_price
			break;
		case '=':
			return (100-s.discount)*s.flight.basePrice/100 - expect_price < 0.1
			break;
		}
		
		return true;
	}
	
	static belongsTo = Member;
	static constraints = {
		status(inList:["Submitted", "Cancelled", "OK"])
		total(inRange:[1,50])
	}
}	
