            
class FlightController {
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        [ flightList: search() ]
    }
    
    def search_from = new Airport()
    def search_to = new Airport()
    def search()
    {
    	println "Search flights"
    	println params
    	
    	session.expect_price = 0.0
    	session.search_oper  = ">"
    	
    	if(params.expect_price != null && params.expect_price != "")
    	{
    		session.search_oper = params.price_oper
    		session.expect_price = 0.0+Integer.parseInt(params.expect_price)
    		println session.search_oper
    	}
    	
    	if(params["from.id"] != null && params["to.id"] != null)
    	{
    		search_from = Airport.get(params["from.id"])
        	search_to = Airport.get(params["to.id"])
    		return Flight.findAllWhere(from:search_from, to:search_to);
    	}
    	
    	if(!params.max)params.max = 10
    	return Flight.list( params )
    }
    
    def show = {
        [ flight : Flight.get( params.id ) ]
    }

    def delete = {
        def flight = Flight.get( params.id )
        if(flight) {
            flight.delete()
            flash.message = "Flight ${params.id} deleted."
            redirect(action:list)
        }
        else {
            flash.message = "Flight not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def flight = Flight.get( params.id )

        if(!flight) {
                flash.message = "Flight not found with id ${params.id}"
                redirect(action:list)
        }
        else {
            return [ flight : flight ]
        }
    }

    def update = {
        def flight = Flight.get( params.id )
        if(flight) {
             flight.properties = params
            if(flight.save()) {
                flash.message = "Flight ${params.id} updated."
                redirect(action:show,id:flight.id)
            }
            else {
                render(view:'edit',model:[flight:flight])
            }
        }
        else {
            flash.message = "Flight not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def flight = new Flight()
        flight.properties = params
        return ['flight':flight]
    }

    def save = {
        def flight = new Flight()
        flight.properties = params
        if(flight.save()) {
            flash.message = "Flight ${flight.id} created."
            redirect(action:show,id:flight.id)
        }
        else {
            render(view:'create',model:[flight:flight])
        }
    }

    def book = {
    	def seatId = params.id;
    	println "book id:"+seatId
    	session.seatClass = SeatClass.get(seatId);
    	redirect(controller:"booking",action:create)
    }
}