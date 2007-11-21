  
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
            <span class="menuButton"><g:link class="create" controller="member" action="create">New Member</g:link></span>
            <span class="menuButton"><g:link class="create" controller="member" action="login">Member Login</g:link></span>
            </g:if>
            <g:if test="${session.member != null}">
            <span class="menuButton"><g:link class="create" controller="member" action="logout">Logout ${session.member}</g:link></span>
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
            <g:form action="search">
            <label for='date'>Date:</label><g:datePicker name='date' value="${booking.date}" ></g:datePicker><BR/>
            <label for='date'>Depature Time:</label><input name='dep_time' value="" /><BR/>
            <label for='date'>Price:</label><g:select from="${['>','=','<', 'Any']}" name="booking.price_oper"></g:select><input id="expect_price" name='booking.expect_price' value="" /><BR/>
            <label for='from'>From:</label><g:select optionKey="id" from="${Airport.list()}" name='from.id' value="${session.search_from?.id}" ></g:select>
            <label for='to'>To:</label><g:select optionKey="id" from="${Airport.list()}" name='to.id' value="${session.search_to?.id}" ></g:select>
            <BR/>
            <g:actionSubmit value="Search"/>
            <g:link action="create">All Flights</g:link>
            <BR/>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                        	<g:sortableColumn property="STD" title="Departure" />
                        	
                        	<g:sortableColumn property="STA" title="Arrival" />
                        	
                   	        <th>Details</th>
                        
                   	        <g:sortableColumn property="description" title="Description" />
                   	    
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${booking.flightList}" status="i" var="flight">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" target="_blank" id="${flight.id}">${flight.id?.encodeAsHTML()}</g:link></td>
                        
                       		<td>${flight.STD?.encodeAsHTML()}</td>
                            <td>${flight.STA?.encodeAsHTML()}</td>

                            <td>${flight.from?.encodeAsHTML()} to 
                            ${flight.to?.encodeAsHTML()}
                            
                            <ul>
                            <g:findAll in='${flight.seatClasses}' expr="${booking.withCondition(s)}" 
                             var="s" >
						        <li>
						        <strong>Available: ${s.getAvailable(["date":booking?.date, "seatClass":s])}</strong>
					       		<g:link class="button" controller='booking' action='create' params='["seatClass.id":s?.id]'>
					       		<input type="button" value="Book"
					       		onClick="javascript:location.href='../booking/create?seatClass.id=${s?.id}'"
					       		/>
					       		</g:link>
						        <g:link controller='seatClass' action='show' id='${s.id}'>${s} Price: ${flight.basePrice*(100-s.discount)/100}</g:link>
						        <BR/>
						        </li>
						    </g:findAll>
						    </ul>
                            </td>
                        
                            <td>${flight.description?.encodeAsHTML()}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${Flight.count()}" />
            </div>
            </g:form>           
            
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
                    <Strong>Price: ${booking.getFinalBasePrice()}</Strong><BR />
                    <Strong>Tax and Fee: ${booking.seatClass.taxAndFees}</Strong><BR />
                    <Strong>Unit Price Total: ${booking.getFinalPrice()}</Strong><BR />
                    <Strong>Total Amount: ${booking.getFinalTotalPrice()}</Strong><BR />
                    </g:if>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Create"></input></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
