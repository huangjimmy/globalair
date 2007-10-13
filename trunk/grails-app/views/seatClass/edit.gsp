  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit SeatClass</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">SeatClass List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New SeatClass</g:link></span>
        </div>
        <div class="body">
            <h1>Edit SeatClass</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${seatClass}">
            <div class="errors">
                <g:renderErrors bean="${seatClass}" as="list" />
            </div>
            </g:hasErrors>
            <g:form controller="seatClass" method="post" >
                <input type="hidden" name="id" value="${seatClass?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
				            <tr class='prop'><td valign='top' class='name'><label for='capacity'>Capacity:</label></td><td valign='top' class='value ${hasErrors(bean:seatClass,field:'capacity','errors')}'><input type='text' id='capacity' name='capacity' value="${seatClass?.capacity}" /></td></tr>
                        
				            <tr class='prop'><td valign='top' class='name'><label for='code'>Code:</label></td><td valign='top' class='value ${hasErrors(bean:seatClass,field:'code','errors')}'><input type="text" id='code' name='code' value="${seatClass?.code?.encodeAsHTML()}"/></td></tr>
                        
				            <tr class='prop'><td valign='top' class='name'><label for='description'>Description:</label></td><td valign='top' class='value ${hasErrors(bean:seatClass,field:'description','errors')}'><input type="text" id='description' name='description' value="${seatClass?.description?.encodeAsHTML()}"/></td></tr>
                        
				            <tr class='prop'><td valign='top' class='name'><label for='discount'>Discount:</label></td><td valign='top' class='value ${hasErrors(bean:seatClass,field:'discount','errors')}'><input type='text' id='discount' name='discount' value="${seatClass?.discount}" /></td></tr>
                        
				            <tr class='prop'><td valign='top' class='name'><label for='flight'>Flight:</label></td><td valign='top' class='value ${hasErrors(bean:seatClass,field:'flight','errors')}'><g:select optionKey="id" from="${Flight.list()}" name='flight.id' value="${seatClass?.flight?.id}" ></g:select></td></tr>
                        
				            <tr class='prop'><td valign='top' class='name'><label for='taxAndFees'>Tax And Fees:</label></td><td valign='top' class='value ${hasErrors(bean:seatClass,field:'taxAndFees','errors')}'><input type='text' id='taxAndFees' name='taxAndFees' value="${seatClass?.taxAndFees}" /></td></tr>
                        
				            <tr class='prop'><td valign='top' class='name'><label for='type'>Type:</label></td><td valign='top' class='value ${hasErrors(bean:seatClass,field:'type','errors')}'><input type="text" id='type' name='type' value="${seatClass?.type?.encodeAsHTML()}"/></td></tr>
                        
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
