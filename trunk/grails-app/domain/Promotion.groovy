class Promotion { 
	String reason = "Member Level"
	double getDiscount(int year, int month, int day, String ffp)
	{
		switch(ffp)
		{
		case "Normal":
			return -0.01
			break
		case "Gold":
			return 5.0
			break
		case "VIP":
			return 1.0
			break
		case "Platium":
			return 8.0
			break
		case "Premier":
			return 10.0
			break
		}
		
		return 0.0;
	}
	
	String toString()
	{
		return reason
	}
}	
