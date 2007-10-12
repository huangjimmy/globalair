  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create Airport</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Airport List</g:link></span>
        </div>
        <div class="body">
            <h1>Create Airport</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${airport}">
            <div class="errors">
                <g:renderErrors bean="${airport}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class='prop'><td valign='top' class='name'><label for='city'>City:</label></td><td valign='top' class='value ${hasErrors(bean:airport,field:'city','errors')}'><input type="text" id='city' name='city' value="${airport?.city?.encodeAsHTML()}"/></td></tr>
                        
                            <tr class='prop'><td valign='top' class='name'><label for='code'>Code:</label></td><td valign='top' class='value ${hasErrors(bean:airport,field:'code','errors')}'><input type="text" id='code' name='code' value="${airport?.code?.encodeAsHTML()}"/></td></tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Create"></input></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
