            
class StrategyController {
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        if(!params.max)params.max = 10
        [ strategyList: Strategy.list( params ) ]
    }

    def show = {
        [ strategy : Strategy.get( params.id ) ]
    }

    def delete = {
        def strategy = Strategy.get( params.id )
        if(strategy) {
            strategy.delete()
            flash.message = "Strategy ${params.id} deleted."
            redirect(action:list)
        }
        else {
            flash.message = "Strategy not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def strategy = Strategy.get( params.id )

        if(!strategy) {
                flash.message = "Strategy not found with id ${params.id}"
                redirect(action:list)
        }
        else {
            return [ strategy : strategy ]
        }
    }

    def update = {
        def strategy = Strategy.get( params.id )
        if(strategy) {
             strategy.properties = params
            if(strategy.save()) {
                flash.message = "Strategy ${params.id} updated."
                redirect(action:show,id:strategy.id)
            }
            else {
                render(view:'edit',model:[strategy:strategy])
            }
        }
        else {
            flash.message = "Strategy not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def strategy = new Strategy()
        strategy.properties = params
        return ['strategy':strategy]
    }

    def save = {
        def strategy = new Strategy()
        strategy.properties = params
        if(strategy.save()) {
            flash.message = "Strategy ${strategy.id} created."
            redirect(action:show,id:strategy.id)
        }
        else {
            render(view:'create',model:[strategy:strategy])
        }
    }

}