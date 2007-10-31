  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create Booking</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Booking List</g:link></span>
            
            <g:if test="${session.member == null}">
            <span class="menuButton"><g:link class="create" action="create">New Member</g:link></span>
            <span class="menuButton"><g:link class="create" action="login">Member Login</g:link></span>
            </g:if>
            <g:if test="${session.member != null}">
            <span class="menuButton"><g:link class="create" action="logout">Logout ${session.member}</g:link></span>
            </g:if>
        </div>
        <div class="body">
            <h1>Create Booking</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${booking}">
            <div class="errors">
                <g:renderErrors bean="${booking}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                        	<tr class='prop'><td valign='top' class='name'><label for='count'>Ticket Count:</label></td><td valign='top' class='value ${hasErrors(bean:booking,field:'total','errors')}'><input type="text" id='total' name='total' value="${booking?.total?.encodeAsHTML()}"/></td></tr>
                        	
                            <tr class='prop'><td valign='top' class='name'><label for='address'>Address:</label></td><td valign='top' class='value ${hasErrors(bean:booking,field:'address','errors')}'><input type="text" id='address' name='address' value="${booking?.address?.encodeAsHTML()}"/></td></tr>
                        
                            <tr class='prop'><td valign='top' class='name'><label for='date'>Date:</label></td><td valign='top' class='value ${hasErrors(bean:booking,field:'date','errors')}'><g:datePicker name='date' value="${booking?.date}" ></g:datePicker></td></tr>
                        
                            <tr class='prop'><td valign='top' class='name'><label for='email'>Email:</label></td><td valign='top' class='value ${hasErrors(bean:booking,field:'email','errors')}'><input type="text" id='email' name='email' value="${booking?.email?.encodeAsHTML()}"/></td></tr>
                        
                            <tr class='prop'><td valign='top' class='name'><label for='member'>Member:</label></td><td valign='top' class='value ${hasErrors(bean:booking,field:'member','errors')}'>
                            <g:if test="${session.member != null}">
                            <g:select optionKey="id" from="${Member.findAllWhere(id:session.member.id)}" name='member.id' value="${booking?.member?.id}" ></g:select>
                            </g:if>
                            <g:if test="${session.member == null}">
                            <g:select optionKey="id" from="${Member.list()}" name='member.id' value="${booking?.member?.id}" ></g:select>
                            </g:if>
                            </td></tr>
                            
                        
                            <tr class='prop'><td valign='top' class='name'><label for='mobilephone'>Mobilephone:</label></td><td valign='top' class='value ${hasErrors(bean:booking,field:'mobilephone','errors')}'><input type="text" id='mobilephone' name='mobilephone' value="${booking?.mobilephone?.encodeAsHTML()}"/></td></tr>
                        
                            <tr class='prop'><td valign='top' class='name'><label for='name'>Name:</label></td><td valign='top' class='value ${hasErrors(bean:booking,field:'name','errors')}'><input type="text" id='name' name='name' value="${booking?.name?.encodeAsHTML()}"/></td></tr>
                        
                            <tr class='prop'><td valign='top' class='name'><label for='phone'>Phone:</label></td><td valign='top' class='value ${hasErrors(bean:booking,field:'phone','errors')}'><input type="text" id='phone' name='phone' value="${booking?.phone?.encodeAsHTML()}"/></td></tr>
                        
                            <tr class='prop'><td valign='top' class='name'><label for='photoId'>Photo Id:</label></td><td valign='top' class='value ${hasErrors(bean:booking,field:'photoId','errors')}'><input type="text" id='photoId' name='photoId' value="${booking?.photoId?.encodeAsHTML()}"/></td></tr>
                        
                            <tr class='prop'><td valign='top' class='name'><label for='remark'>Remark:</label></td><td valign='top' class='value ${hasErrors(bean:booking,field:'remark','errors')}'><input type="text" id='remark' name='remark' value="${booking?.remark?.encodeAsHTML()}"/></td></tr>
                        
                            <tr class='prop'><td valign='top' class='name'><label for='seatClass'>Seat Class:</label></td><td valign='top' class='value ${hasErrors(bean:booking,field:'seatClass','errors')}'><g:select optionKey="id" from="${SeatClass.list()}" name='seatClass.id' value="${booking?.seatClass?.id}" ></g:select></td></tr>
                        
                            <tr class='prop'><td valign='top' class='name'><label for='status'>Status:</label></td><td valign='top' class='value ${hasErrors(bean:booking,field:'status','errors')}'>
                            <!--<input type="text" id='status' name='status' value="${booking?.status?.encodeAsHTML()}"/> -->
                            <g:if test="${session.member != null}">
				            <g:select name='status' from='["Submitted"]' value="Submitted"></g:select>
				            </g:if>
				            <g:if test="${session.member == null}">
				            <g:select name='status' from='${booking.constraints.status.inList.collect{it.encodeAsHTML()}}' value="${booking.status?.encodeAsHTML()}"></g:select>
				            </g:if>
                            </td></tr>
                        
                            <tr class='prop'><td valign='top' class='name'><label for='zipCode'>Zip Code:</label></td><td valign='top' class='value ${hasErrors(bean:booking,field:'zipCode','errors')}'><input type="text" id='zipCode' name='zipCode' value="${booking?.zipCode?.encodeAsHTML()}"/></td></tr>
                        
                        </tbody>
                    </table>
                    <g:if test="${booking.seatClass != null}" >
                    <Strong>Price: ${booking.seatClass.flight.basePrice*(100-booking.seatClass.discount)/100}</Strong><BR />
                    <Strong>Tax and Fee: ${booking.seatClass.taxAndFees}</Strong><BR />
                    <Strong>Total: ${booking.seatClass.flight.basePrice*(100-booking.seatClass.discount)/100+booking.seatClass.taxAndFees}</Strong><BR />
                    
                    </g:if>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Create"></input></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
