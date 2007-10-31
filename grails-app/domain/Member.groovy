class Member { 
	String name
	String email
	String address
	String password
	String zipCode
	String landPhone
	String mobilePhone
	String description
	
	String ffpLevel
	
	static hasMany = [bookings:Booking]
	static mappedBy = [bookings: "member"]
	static constraints= {
		password(length:5..20,blank:false)
    	email(email:true,blank:false,unique:true)
    	ffpLevel(inList:["Normal","VIP","Gold","Platium","Premier"])
	}
	String toString()
	{
		return name+"("+email+")"
	}
}	
