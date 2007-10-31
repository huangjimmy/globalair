  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Flight List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
 
            <g:if test="${session.member == null}">
            <span class="menuButton"><g:link class="create" action="create">New Flight</g:link></span>
            <span class="menuButton"><g:link class="create" controller="member" action="create">New Member</g:link></span>
            <span class="menuButton"><g:link class="create" controller="member" action="login">Member Login</g:link></span>
            </g:if>
            <g:if test="${session.member != null}">
            <span class="menuButton"><g:link class="create" controller="member" action="logout">Logout ${session.member}</g:link></span>
            </g:if>
        </div>
        <div class="body">
            <h1>Flight List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:form action="list">
            <label for='date'>Date:</label><g:datePicker name='date' value="${date}" ></g:datePicker><BR/>
            <label for='date'>Depature Time:</label><input name='dep_time' value="" /><BR/>
            <label for='date'>Price:</label><g:select from="${['>','=','<', 'Any']}" name="price_oper"></g:select><input id="expect_price" name='expect_price' value="" /><BR/>
            <label for='from'>From:</label><g:select optionKey="id" from="${Airport.list()}" name='from.id' value="${search_from?.id}" ></g:select>
            <label for='to'>To:</label><g:select optionKey="id" from="${Airport.list()}" name='to.id' value="${search_to?.id}" ></g:select>
            <BR/>
            <g:actionSubmit value="Search"/>
            <g:link action="list">All Flights</g:link>
            <BR/>
            </g:form>           
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
                    <g:each in="${flightList}" status="i" var="flight">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" target="_blank" id="${flight.id}">${flight.id?.encodeAsHTML()}</g:link></td>
                        
                       		<td>${flight.STD?.encodeAsHTML()}</td>
                            <td>${flight.STA?.encodeAsHTML()}</td>

                            <td>${flight.from?.encodeAsHTML()} to 
                            ${flight.to?.encodeAsHTML()}
                            
                            <ul>
                            <g:findAll in='${flight?.seatClasses}' expr="${((100-it.discount)/100*flight.basePrice > session.expect_price && session.search_oper == '>') || ((100-it.discount)/100*flight.basePrice < session.expect_price && session.search_oper == '<') || ((100-it.discount)/100*flight.basePrice == session.expect_price && session.search_oper == '=') || (session.search_oper=='Any')}" 
                             var="s" >
						        <li>
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
        </div>
    </body>
</html>
