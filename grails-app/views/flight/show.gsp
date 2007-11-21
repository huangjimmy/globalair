  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show Flight</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Flight List</g:link></span>
            <g:if test="${session.member == null}">
            <span class="menuButton"><g:link class="create" action="create">New Flight</g:link></span>
            </g:if>
            
            <g:if test="${session.member == null}">
            <span class="menuButton"><g:link class="create" action="create">New Member</g:link></span>
            <span class="menuButton"><g:link class="create" action="login">Member Login</g:link></span>
            </g:if>
            <g:if test="${session.member != null}">
            <span class="menuButton"><g:link class="create" action="logout">Logout ${session.member}</g:link></span>
            </g:if>
        </div>
        <div class="body">
            <h1>Show Flight</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${flight.id}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">STA:</td>
                            
                            <td valign="top" class="value">${flight.STA}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">STD:</td>
                            
                            <td valign="top" class="value">${flight.STD}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Base Price:</td>
                            
                            <td valign="top" class="value">${flight.basePrice}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Description:</td>
                            
                            <td valign="top" class="value">${flight.description}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">From:</td>
                            
                            <td valign="top" class="value"><g:link controller="airport" action="show" id="${flight?.from?.id}">${flight?.from}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Number:</td>
                            
                            <td valign="top" class="value">${flight.number}</td>
                            
                        </tr>
                    
                    	<tr class="prop">
                            <td valign="top" class="name">Company:</td>
                            
                            <td valign="top" class="value">${flight.company}</td>
                            
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name">Seat Classes:</td>
                            
                            <td  valign="top" style="text-align:left;" class="value">
                                <ul>
                                <g:each var="s" in="${flight.seatClasses}">
                                    <li><g:link controller="seatClass" action="show" id="${s.id}">${s}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">To:</td>
                            
                            <td valign="top" class="value"><g:link controller="airport" action="show" id="${flight?.to?.id}">${flight?.to}</g:link></td>
                            
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="schedule">Schedule:</td>
                            
                            <td valign="top" class="value">${flight?.schedule}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <g:if test="${session.member == null}">
            <div class="buttons">
                <g:form controller="flight">
                    <input type="hidden" name="id" value="${flight?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
            </g:if>
        </div>
    </body>
</html>
