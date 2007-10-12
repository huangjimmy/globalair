            
class StaffController {
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max)params.max = 10
        [ staffList: Staff.list( params ) ]
    }

    def show = {
        [ staff : Staff.get( params.id ) ]
    }

    def delete = {
        def staff = Staff.get( params.id )
        if(staff) {
            staff.delete()
            flash.message = "Staff ${params.id} deleted."
            redirect(action:list)
        }
        else {
            flash.message = "Staff not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def staff = Staff.get( params.id )

        if(!staff) {
                flash.message = "Staff not found with id ${params.id}"
                redirect(action:list)
        }
        else {
            return [ staff : staff ]
        }
    }

    def update = {
        def staff = Staff.get( params.id )
        if(staff) {
             staff.properties = params
            if(staff.save()) {
                flash.message = "Staff ${params.id} updated."
                redirect(action:show,id:staff.id)
            }
            else {
                render(view:'edit',model:[staff:staff])
            }
        }
        else {
            flash.message = "Staff not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def staff = new Staff()
        staff.properties = params
        return ['staff':staff]
    }

    def save = {
        def staff = new Staff()
        staff.properties = params
        if(staff.save()) {
            flash.message = "Staff ${staff.id} created."
            redirect(action:show,id:staff.id)
        }
        else {
            render(view:'create',model:[staff:staff])
        }
    }

}