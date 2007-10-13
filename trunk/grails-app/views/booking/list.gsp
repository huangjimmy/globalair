  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Booking List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <g:if test="${session.member == null}">
            <span class="menuButton"><g:link class="create" action="create">New Flight</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New Member</g:link></span>
            <span class="menuButton"><g:link class="create" action="login">Member Login</g:link></span>
            </g:if>
            <g:if test="${session.member != null}">
            <span class="menuButton"><g:link class="create" action="logout">Logout ${session.member}</g:link></span>
            </g:if>
            <span class="menuButton"><g:link class="create" action="create">New Booking</g:link></span>
        </div>
        <div class="body">
            <h1>Booking List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="address" title="Address" />
                        
                   	        <g:sortableColumn property="date" title="Date" />
                        
                   	        <g:sortableColumn property="email" title="Email" />
                        
                   	        <th>Member</th>
                   	    
                   	        <g:sortableColumn property="mobilephone" title="Mobilephone" />
                   	        
                   	        <th>Total</th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${bookingList}" status="i" var="booking">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${booking.id}">${booking.id?.encodeAsHTML()}</g:link></td>
                        
                            <td>${booking.address?.encodeAsHTML()}</td>
                        
                            <td>${booking.date?.encodeAsHTML()}</td>
                        
                            <td>${booking.email?.encodeAsHTML()}</td>
                        
                            <td>${booking.member?.encodeAsHTML()}</td>
                        
                            <td>${booking.mobilephone?.encodeAsHTML()}</td>
                            <td><Strong>Price: ${booking.seatClass.flight.basePrice*(100-booking.seatClass.discount)/100}</Strong><BR />
                    <Strong>Tax and Fee: ${booking.seatClass.taxAndFees}</Strong><BR />
                    <Strong>Total: ${booking.seatClass.flight.basePrice*(100-booking.seatClass.discount)/100+booking.seatClass.taxAndFees}</Strong><BR /></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${Booking.count()}" />
            </div>
        </div>
    </body>
</html>
