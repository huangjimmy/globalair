  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Strategy List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New Strategy</g:link></span>
        </div>
        <div class="body">
            <h1>Strategy List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="description" title="Description" />
                        
                   	        <g:sortableColumn property="discount" title="Discount" />
                        
                   	        <g:sortableColumn property="rule" title="Rule" />
                        
                   	        <g:sortableColumn property="type" title="Type" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${strategyList}" status="i" var="strategy">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${strategy.id}">${strategy.id?.encodeAsHTML()}</g:link></td>
                        
                            <td>${strategy.description?.encodeAsHTML()}</td>
                        
                            <td>${strategy.discount?.encodeAsHTML()}</td>
                        
                            <td>${strategy.rule?.encodeAsHTML()}</td>
                        
                            <td>${strategy.type?.encodeAsHTML()}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${Strategy.count()}" />
            </div>
        </div>
    </body>
</html>
