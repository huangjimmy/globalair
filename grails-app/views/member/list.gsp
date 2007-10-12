  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Member List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New Member</g:link></span>
        </div>
        <div class="body">
            <h1>Member List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="password" title="Password" />
                        
                   	        <g:sortableColumn property="email" title="Email" />
                        
                   	        <g:sortableColumn property="address" title="Address" />
                        
                   	        <g:sortableColumn property="description" title="Description" />
                        
                   	        <g:sortableColumn property="landPhone" title="Land Phone" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${memberList}" status="i" var="member">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${member.id}">${member.id?.encodeAsHTML()}</g:link></td>
                        
                            <td>${member.password?.encodeAsHTML()}</td>
                        
                            <td>${member.email?.encodeAsHTML()}</td>
                        
                            <td>${member.address?.encodeAsHTML()}</td>
                        
                            <td>${member.description?.encodeAsHTML()}</td>
                        
                            <td>${member.landPhone?.encodeAsHTML()}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${Member.count()}" />
            </div>
        </div>
    </body>
</html>
