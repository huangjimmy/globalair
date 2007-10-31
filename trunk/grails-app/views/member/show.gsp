  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show Member</title>
    </head>
    <body>
        <div class="nav">
        	<span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
        	<g:if test="${session.member == null}">
            <span class="menuButton"><g:link class="create" action="create">New Member</g:link></span>
            <span class="menuButton"><g:link class="create" action="login">Member Login</g:link></span>
            </g:if>
            <g:if test="${session.member != null}">
            <span class="menuButton"><g:link class="create" action="logout">Logout ${session.member}</g:link></span>
            </g:if>
            <span class="menuButton"><g:link class="list" action="list">Member List</g:link></span>
        </div>
        <div class="body">
            <h1>Show Member</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${member.id}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Password:</td>
                            
                            <td valign="top" class="value">${member.password}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Email:</td>
                            
                            <td valign="top" class="value">${member.email}</td>
                            
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name">FFP:</td>
                            
                            <td valign="top" class="value">${member.ffpLevel}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Address:</td>
                            
                            <td valign="top" class="value">${member.address}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Bookings:</td>
                            
                            <td  valign="top" style="text-align:left;" class="value">
                                <ul>
                                <g:each var="b" in="${member.bookings}">
                                    <li><g:link controller="booking" action="show" id="${b.id}">${b}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Description:</td>
                            
                            <td valign="top" class="value">${member.description}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Land Phone:</td>
                            
                            <td valign="top" class="value">${member.landPhone}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Mobile Phone:</td>
                            
                            <td valign="top" class="value">${member.mobilePhone}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Name:</td>
                            
                            <td valign="top" class="value">${member.name}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Zip Code:</td>
                            
                            <td valign="top" class="value">${member.zipCode}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form controller="member">
                    <input type="hidden" name="id" value="${member?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
