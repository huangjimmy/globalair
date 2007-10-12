  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit Staff</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Staff List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New Staff</g:link></span>
        </div>
        <div class="body">
            <h1>Edit Staff</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${staff}">
            <div class="errors">
                <g:renderErrors bean="${staff}" as="list" />
            </div>
            </g:hasErrors>
            <g:form controller="staff" method="post" >
                <input type="hidden" name="id" value="${staff?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
				            <tr class='prop'><td valign='top' class='name'><label for='email'>Email:</label></td><td valign='top' class='value ${hasErrors(bean:staff,field:'email','errors')}'><input type="text" id='email' name='email' value="${staff?.email?.encodeAsHTML()}"/></td></tr>
                        
				            <tr class='prop'><td valign='top' class='name'><label for='mobile'>Mobile:</label></td><td valign='top' class='value ${hasErrors(bean:staff,field:'mobile','errors')}'><input type="text" id='mobile' name='mobile' value="${staff?.mobile?.encodeAsHTML()}"/></td></tr>
                        
				            <tr class='prop'><td valign='top' class='name'><label for='name'>Name:</label></td><td valign='top' class='value ${hasErrors(bean:staff,field:'name','errors')}'><input type="text" id='name' name='name' value="${staff?.name?.encodeAsHTML()}"/></td></tr>
                        
				            <tr class='prop'><td valign='top' class='name'><label for='phone'>Phone:</label></td><td valign='top' class='value ${hasErrors(bean:staff,field:'phone','errors')}'><input type="text" id='phone' name='phone' value="${staff?.phone?.encodeAsHTML()}"/></td></tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" value="Update" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
