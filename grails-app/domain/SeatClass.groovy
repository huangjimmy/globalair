class SeatClass { 
	String type;//First, Busines, Economy
	String code;//Correspond to discount;
	String description;
	double discount;//apply to base price
	int capacity;
	
	Flight flight;
	static belongsTo = Flight
	
	String toString()
	{
		return flight.number+" - "+type+":("+code+")";
	}
}	
