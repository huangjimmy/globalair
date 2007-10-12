  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Staff List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New Staff</g:link></span>
        </div>
        <div class="body">
            <h1>Staff List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="email" title="Email" />
                        
                   	        <g:sortableColumn property="mobile" title="Mobile" />
                        
                   	        <g:sortableColumn property="name" title="Name" />
                        
                   	        <g:sortableColumn property="phone" title="Phone" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${staffList}" status="i" var="staff">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${staff.id}">${staff.id?.encodeAsHTML()}</g:link></td>
                        
                            <td>${staff.email?.encodeAsHTML()}</td>
                        
                            <td>${staff.mobile?.encodeAsHTML()}</td>
                        
                            <td>${staff.name?.encodeAsHTML()}</td>
                        
                            <td>${staff.phone?.encodeAsHTML()}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${Staff.count()}" />
            </div>
        </div>
    </body>
</html>
