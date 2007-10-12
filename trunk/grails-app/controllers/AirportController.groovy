            
class AirportController {
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max)params.max = 10
        [ airportList: Airport.list( params ) ]
    }

    def show = {
        [ airport : Airport.get( params.id ) ]
    }

    def delete = {
        def airport = Airport.get( params.id )
        if(airport) {
            airport.delete()
            flash.message = "Airport ${params.id} deleted."
            redirect(action:list)
        }
        else {
            flash.message = "Airport not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def airport = Airport.get( params.id )

        if(!airport) {
                flash.message = "Airport not found with id ${params.id}"
                redirect(action:list)
        }
        else {
            return [ airport : airport ]
        }
    }

    def update = {
        def airport = Airport.get( params.id )
        if(airport) {
             airport.properties = params
            if(airport.save()) {
                flash.message = "Airport ${params.id} updated."
                redirect(action:show,id:airport.id)
            }
            else {
                render(view:'edit',model:[airport:airport])
            }
        }
        else {
            flash.message = "Airport not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def airport = new Airport()
        airport.properties = params
        return ['airport':airport]
    }

    def save = {
        def airport = new Airport()
        airport.properties = params
        if(airport.save()) {
            flash.message = "Airport ${airport.id} created."
            redirect(action:show,id:airport.id)
        }
        else {
            render(view:'create',model:[airport:airport])
        }
    }

}