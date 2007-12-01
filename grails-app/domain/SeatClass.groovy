
class SeatClass { 
	String type//First, Busines, Economy
	String code//Correspond to discount;
	String description
	double discount//apply to base price
	int capacity
	double taxAndFees
	
	Flight flight;
	static belongsTo = Flight
	
	String toString()
	{
		return flight.number+" - "+type+":("+code+")";
	}
	
	int getAvailable(params)
	{
		def d = params.date
		//if(d == null)d = new Date()
		int booked = 0
		def bookings = Booking.findAllWhere(seatClass: params.seatClass)
		bookings.each
		{
			if(d!=null)
			{
				println d
				if(it.date.year == d.year && it.date.month==d.month && it.date.date == d.date)booked += it.total
			}
			else
			{
				booked += it.total
			}
		}
		
		println params
		println d
		return capacity - booked
	}
	
	Strategy chooseStrategy(d, member)
	{
		return new FlightService().chooseBestStrategy(["flight":flight,"date":d, "member":member])
	}
}	
