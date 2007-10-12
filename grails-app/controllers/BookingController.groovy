            
class BookingController {
	
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max)params.max = 10
        [ bookingList: Booking.list( params ) ]
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
        booking.properties = params
        return ['booking':booking]
    }

    def save = {
        def booking = new Booking()
        booking.properties = params
        if(booking.save()) {
            flash.message = "Booking ${booking.id} created."
            redirect(action:show,id:booking.id)
        }
        else {
            render(view:'create',model:[booking:booking])
        }
    }

}