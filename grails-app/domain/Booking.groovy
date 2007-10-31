class Booking { 
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

	static belongsTo = Member;
	static constraints = {
		status(inList:["Submitted", "Cancelled", "OK"])
		total(inRange:[1,50])
	}
}	
