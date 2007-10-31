  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show Booking</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Booking List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New Booking</g:link></span>
        </div>
        <div class="body">
            <h1>Show Booking</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${booking.id}</td>
                            
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name">Ticket Count:</td>
                            
                            <td valign="top" class="value">${booking.total}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Address:</td>
                            
                            <td valign="top" class="value">${booking.address}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Date:</td>
                            
                            <td valign="top" class="value">${booking.date}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Email:</td>
                            
                            <td valign="top" class="value">${booking.email}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Member:</td>
                            
                            <td valign="top" class="value"><g:link controller="member" action="show" id="${booking?.member?.id}">${booking?.member}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Mobilephone:</td>
                            
                            <td valign="top" class="value">${booking.mobilephone}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Name:</td>
                            
                            <td valign="top" class="value">${booking.name}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Phone:</td>
                            
                            <td valign="top" class="value">${booking.phone}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Photo Id:</td>
                            
                            <td valign="top" class="value">${booking.photoId}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Remark:</td>
                            
                            <td valign="top" class="value">${booking.remark}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Seat Class:</td>
                            
                            <td valign="top" class="value"><g:link controller="seatClass" action="show" id="${booking?.seatClass?.id}">${booking?.seatClass}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Status:</td>
                            
                            <td valign="top" class="value">${booking.status}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Zip Code:</td>
                            
                            <td valign="top" class="value">${booking.zipCode}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
                <Strong>Price: ${booking.seatClass.flight.basePrice*(100-booking.seatClass.discount)/100}</Strong><BR />
                    <Strong>Tax and Fee: ${booking.seatClass.taxAndFees}</Strong><BR />
                    <Strong>Total: ${booking.seatClass.flight.basePrice*(100-booking.seatClass.discount)/100+booking.seatClass.taxAndFees}</Strong><BR />
            </div>
            <div class="buttons">
                <g:form controller="booking">
                    <input type="hidden" name="id" value="${booking?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <g:if test="${booking.status =='Submitted'}">
                    <span class="button"><g:actionSubmit class="edit" onclick="return confirm('Are you sure?');" value="Cancel Booking" /></span>
                    </g:if>
                    <g:if test="${session.member == null}">
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                    </g:if>
                </g:form>
            </div>
        </div>
    </body>
</html>
