  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>SeatClass List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New SeatClass</g:link></span>
        </div>
        <div class="body">
            <h1>SeatClass List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="capacity" title="Capacity" />
                        
                   	        <g:sortableColumn property="code" title="Code" />
                        
                   	        <g:sortableColumn property="description" title="Description" />
                        
                   	        <g:sortableColumn property="discount" title="Discount" />
                        
                   	        <th>Flight</th>
                   	    
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${seatClassList}" status="i" var="seatClass">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${seatClass.id}">${seatClass.id?.encodeAsHTML()}</g:link></td>
                        
                            <td>${seatClass.capacity?.encodeAsHTML()}</td>
                        
                            <td>${seatClass.code?.encodeAsHTML()}</td>
                        
                            <td>${seatClass.description?.encodeAsHTML()}</td>
                        
                            <td>${seatClass.discount?.encodeAsHTML()}</td>
                        
                            <td>${seatClass.flight?.encodeAsHTML()}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${SeatClass.count()}" />
            </div>
        </div>
    </body>
</html>
