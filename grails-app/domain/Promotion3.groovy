class Promotion3 {
	
	String reason = "Not busy season"
	
	double getDiscount(int year, int month, int day, String ffp)
	{
		//淡季优惠, 10月1日不优惠
		if(month == 1 && day == 1)return 0
		if(month == 10 && day == 1)return -0.01
		if(month < 4 || month > 10)return 8
		
		return -0.01;
	}
	
	String toString()
	{
		return reason
	}
}	
