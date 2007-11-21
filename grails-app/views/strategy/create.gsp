  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create Strategy</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Strategy List</g:link></span>
        </div>
        <div class="body">
            <h1>Create Strategy</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${strategy}">
            <div class="errors">
                <g:renderErrors bean="${strategy}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class='prop'><td valign='top' class='name'><label for='description'>Description:</label></td><td valign='top' class='value ${hasErrors(bean:strategy,field:'description','errors')}'><input type="text" id='description' name='description' value="${strategy?.description?.encodeAsHTML()}"/></td></tr>
                        
                            <tr class='prop'><td valign='top' class='name'><label for='discount'>Discount:</label></td><td valign='top' class='value ${hasErrors(bean:strategy,field:'discount','errors')}'><input type='text' id='discount' name='discount' value="${strategy?.discount}" /></td></tr>
                        
                            <tr class='prop'><td valign='top' class='name'><label for='rule'>Rule:</label></td><td valign='top' class='value ${hasErrors(bean:strategy,field:'rule','errors')}'><input type="text" id='rule' name='rule' value="${strategy?.rule?.encodeAsHTML()}"/></td></tr>
                        
                            <tr class='prop'><td valign='top' class='name'><label for='type'>Type:</label></td><td valign='top' class='value ${hasErrors(bean:strategy,field:'type','errors')}'><input type="text" id='type' name='type' value="${strategy?.type?.encodeAsHTML()}"/></td></tr>
                        
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
