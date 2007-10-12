            
class SeatClassController {
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max)params.max = 10
        [ seatClassList: SeatClass.list( params ) ]
    }

    def show = {
        [ seatClass : SeatClass.get( params.id ) ]
    }

    def delete = {
        def seatClass = SeatClass.get( params.id )
        if(seatClass) {
            seatClass.delete()
            flash.message = "SeatClass ${params.id} deleted."
            redirect(action:list)
        }
        else {
            flash.message = "SeatClass not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def seatClass = SeatClass.get( params.id )

        if(!seatClass) {
                flash.message = "SeatClass not found with id ${params.id}"
                redirect(action:list)
        }
        else {
            return [ seatClass : seatClass ]
        }
    }

    def update = {
        def seatClass = SeatClass.get( params.id )
        if(seatClass) {
             seatClass.properties = params
            if(seatClass.save()) {
                flash.message = "SeatClass ${params.id} updated."
                redirect(action:show,id:seatClass.id)
            }
            else {
                render(view:'edit',model:[seatClass:seatClass])
            }
        }
        else {
            flash.message = "SeatClass not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def seatClass = new SeatClass()
        seatClass.properties = params
        return ['seatClass':seatClass]
    }

    def save = {
        def seatClass = new SeatClass()
        seatClass.properties = params
        if(seatClass.save()) {
            flash.message = "SeatClass ${seatClass.id} created."
            redirect(action:show,id:seatClass.id)
        }
        else {
            render(view:'create',model:[seatClass:seatClass])
        }
    }

}