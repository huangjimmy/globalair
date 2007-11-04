import java.sql.*

class Flight {
	String number//Flight Number
	String description
	Airport from
	Airport to 
	String STD//Scheduled Time Depature
	String STA//Scheduled Time of Arrival
	String schedule
	double basePrice;
	static hasMany = [seatClasses:SeatClass]
	static mappedBy = [seatClasses: "flight"]
	
	double discount
	String reason
	
	static transients = ['discount', 'reason']
	
	String toString()
	{
		return number+": "+from + "->" + to;
	}
}	
