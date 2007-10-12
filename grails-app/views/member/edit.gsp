  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit Member</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Member List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New Member</g:link></span>
        </div>
        <div class="body">
            <h1>Edit Member</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${member}">
            <div class="errors">
                <g:renderErrors bean="${member}" as="list" />
            </div>
            </g:hasErrors>
            <g:form controller="member" method="post" >
                <input type="hidden" name="id" value="${member?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
				            <tr class='prop'><td valign='top' class='name'><label for='address'>Address:</label></td><td valign='top' class='value ${hasErrors(bean:member,field:'address','errors')}'><input type="text" id='address' name='address' value="${member?.address?.encodeAsHTML()}"/></td></tr>
                        
				            <tr class='prop'><td valign='top' class='name'><label for='description'>Description:</label></td><td valign='top' class='value ${hasErrors(bean:member,field:'description','errors')}'><input type="text" id='description' name='description' value="${member?.description?.encodeAsHTML()}"/></td></tr>
                        
				            <tr class='prop'><td valign='top' class='name'><label for='email'>Email:</label></td><td valign='top' class='value ${hasErrors(bean:member,field:'email','errors')}'><input type="text" id='email' name='email' value="${member?.email?.encodeAsHTML()}"/></td></tr>
                        
				            <tr class='prop'><td valign='top' class='name'><label for='landPhone'>Land Phone:</label></td><td valign='top' class='value ${hasErrors(bean:member,field:'landPhone','errors')}'><input type="text" id='landPhone' name='landPhone' value="${member?.landPhone?.encodeAsHTML()}"/></td></tr>
                        
				            <tr class='prop'><td valign='top' class='name'><label for='mobilePhone'>Mobile Phone:</label></td><td valign='top' class='value ${hasErrors(bean:member,field:'mobilePhone','errors')}'><input type="text" id='mobilePhone' name='mobilePhone' value="${member?.mobilePhone?.encodeAsHTML()}"/></td></tr>
                        
				            <tr class='prop'><td valign='top' class='name'><label for='name'>Name:</label></td><td valign='top' class='value ${hasErrors(bean:member,field:'name','errors')}'><input type="text" id='name' name='name' value="${member?.name?.encodeAsHTML()}"/></td></tr>
                        
				            <tr class='prop'><td valign='top' class='name'><label for='zipCode'>Zip Code:</label></td><td valign='top' class='value ${hasErrors(bean:member,field:'zipCode','errors')}'><input type="text" id='zipCode' name='zipCode' value="${member?.zipCode?.encodeAsHTML()}"/></td></tr>
                        
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
