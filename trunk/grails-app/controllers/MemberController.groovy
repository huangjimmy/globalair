            
class MemberController {
	def login = {
			if(params.email == null)return
			
			def member = Member.findByEmail(params.email);
			if(member != null && member.password == params.password)
			{
				session.member = member
				redirect(action:list);
				println member
			}
	}
	
	def logout = {
			session.member = null
			redirect(action:login)
	}

    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        [ memberList: listEx() ]
    }
	
	def listEx = {
			if(!params.max)params.max = 10
			
			if(session.member == null)
			{
				println "All Members"
				return Member.list( params )
			}
			else
			{
				println "Only Member"
				return Member.findByEmail(session.member.email);
			}
	}

    def show = {
        [ member : Member.get( params.id ) ]
    }

    def delete = {
        def member = Member.get( params.id )
        if(member) {
            member.delete()
            flash.message = "Member ${params.id} deleted."
            redirect(action:list)
        }
        else {
            flash.message = "Member not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def member = Member.get( params.id )

        if(!member) {
                flash.message = "Member not found with id ${params.id}"
                redirect(action:list)
        }
        else {
            return [ member : member ]
        }
    }

    def update = {
        def member = Member.get( params.id )
        if(member) {
             member.properties = params
            if(member.save()) {
                flash.message = "Member ${params.id} updated."
                redirect(action:show,id:member.id)
            }
            else {
            	flash.message = "Member ${params.id} was not updated."
            	println params
            	println member
                render(view:'edit',model:[member:member])
            }
        }
        else {
            flash.message = "Member not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def member = new Member()
        member.properties = params
        return ['member':member]
    }

    def save = {
        def member = new Member()
        member.properties = params
        if(member.save()) {
            flash.message = "Member ${member.id} created."
            redirect(action:show,id:member.id)
        }
        else {
        	println params
            render(view:'create',model:[member:member])
        }
    }

}