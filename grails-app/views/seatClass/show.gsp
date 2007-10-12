  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show SeatClass</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">SeatClass List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New SeatClass</g:link></span>
        </div>
        <div class="body">
            <h1>Show SeatClass</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${seatClass.id}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Capacity:</td>
                            
                            <td valign="top" class="value">${seatClass.capacity}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Code:</td>
                            
                            <td valign="top" class="value">${seatClass.code}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Description:</td>
                            
                            <td valign="top" class="value">${seatClass.description}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Discount:</td>
                            
                            <td valign="top" class="value">${seatClass.discount}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Flight:</td>
                            
                            <td valign="top" class="value"><g:link controller="flight" action="show" id="${seatClass?.flight?.id}">${seatClass?.flight}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Type:</td>
                            
                            <td valign="top" class="value">${seatClass.type}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form controller="seatClass">
                    <input type="hidden" name="id" value="${seatClass?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
