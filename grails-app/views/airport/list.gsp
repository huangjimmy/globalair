  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Airport List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New Airport</g:link></span>
        </div>
        <div class="body">
            <h1>Airport List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="city" title="City" />
                        
                   	        <g:sortableColumn property="code" title="Code" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${airportList}" status="i" var="airport">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${airport.id}">${airport.id?.encodeAsHTML()}</g:link></td>
                        
                            <td>${airport.city?.encodeAsHTML()}</td>
                        
                            <td>${airport.code?.encodeAsHTML()}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${Airport.count()}" />
            </div>
        </div>
    </body>
</html>
