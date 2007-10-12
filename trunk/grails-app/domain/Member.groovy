class Member { 
	String name
	String email
	String address
	String password
	String zipCode
	String landPhone
	String mobilePhone
	String description
	
	static hasMany = [bookings:Booking]
	static mappedBy = [bookings: "member"]
	static constraints= {
		password(length:5..20,blank:false)
    	email(email:true,blank:false,unique:true)
	}
	String toString()
	{
		return name+"("+email+")"
	}
}	
