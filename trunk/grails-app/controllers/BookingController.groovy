
class BookingController {
	
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        
        [ bookingList: search() ]
    }
    
    def search()
    {
    	if(!params.max)params.max = 10
    	if(session.member == null)
    		return Booking.list(params)
    		
    	return Booking.findAllWhere(member:session.member)
    }

    def show = {
        [ booking : Booking.get( params.id ) ]
    }

    def delete = {
        def booking = Booking.get( params.id )
        if(booking) {
            booking.delete()
            flash.message = "Booking ${params.id} deleted."
            redirect(action:list)
        }
        else {
            flash.message = "Booking not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def booking = Booking.get( params.id )

        if(!booking) {
                flash.message = "Booking not found with id ${params.id}"
                redirect(action:list)
        }
        else {
            return [ booking : booking ]
        }
    }

    def update = {
        def booking = Booking.get( params.id )
        if(booking) {
             booking.properties = params
            if(booking.save()) {
                flash.message = "Booking ${params.id} updated."
                redirect(action:show,id:booking.id)
            }
            else {
                render(view:'edit',model:[booking:booking])
            }
        }
        else {
            flash.message = "Booking not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def booking = new Booking()
        booking.flightList = Flight.list()
        booking.properties = params
        return ['booking':booking]
    }

    def save = {
        def booking = new Booking()
        booking.properties = params
        
        booking.strategy = booking.seatClass.chooseStrategy(booking.date, session.member);
        
		 
        if(booking.save()) {
            flash.message = "Booking ${booking.id} created."
            redirect(action:show,id:booking.id)
        }
        else {
            render(view:'create',model:[booking:booking])
        }
    }
    
    def search = {
    	 def booking = new Booking()
         booking.properties = params
         println params
         session.search_from = Airport.get(params["from.id"])
         session.search_to = Airport.get(params["to.id"])
         println params.search_from
         println params.search_to
         booking.flightList = Flight.findAllWhere(from:session.search_from, to:session.search_to)
 		 render(view:'create',model:[booking:booking])
        }
    
    def cancelBooking = {
            def booking = Booking.get(params.id)
            booking.status = "Cancelled"
            if(booking.save()) {
                flash.message = "Booking ${booking.id} Cancelled."
                redirect(action:show,id:booking.id)
            }
            else {
                
            }
        }

}