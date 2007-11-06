class Booking { 
	
	PromotionService promotionService = new PromotionService()
	static transients = ['promotionService']
	
	Date date;//travel date
	int total
	Member member;
	SeatClass seatClass;
	String name;
	String photoId;
	String email;
	String phone;
	String mobilephone;
	String address;
	String zipCode;
	String remark;
	String status;

	//double additional_discount
	//String discount_reason
	
	static belongsTo = Member;
	static constraints = {
		status(inList:["Submitted", "Cancelled", "OK"])
		total(inRange:[1,50])
	}
}	
