  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit Flight</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Flight List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New Flight</g:link></span>
        </div>
        <div class="body">
            <h1>Edit Flight</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${flight}">
            <div class="errors">
                <g:renderErrors bean="${flight}" as="list" />
            </div>
            </g:hasErrors>
            <g:form controller="flight" method="post" >
                <input type="hidden" name="id" value="${flight?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
				            <tr class='prop'><td valign='top' class='name'><label for='STA'>STA:</label></td><td valign='top' class='value ${hasErrors(bean:flight,field:'STA','errors')}'><input type="text" id='STA' name='STA' value="${flight?.STA?.encodeAsHTML()}"/></td></tr>
                        
				            <tr class='prop'><td valign='top' class='name'><label for='STD'>STD:</label></td><td valign='top' class='value ${hasErrors(bean:flight,field:'STD','errors')}'><input type="text" id='STD' name='STD' value="${flight?.STD?.encodeAsHTML()}"/></td></tr>
                        
				            <tr class='prop'><td valign='top' class='name'><label for='basePrice'>Base Price:</label></td><td valign='top' class='value ${hasErrors(bean:flight,field:'basePrice','errors')}'><input type='text' id='basePrice' name='basePrice' value="${flight?.basePrice}" /></td></tr>
                        
				            <tr class='prop'><td valign='top' class='name'><label for='description'>Description:</label></td><td valign='top' class='value ${hasErrors(bean:flight,field:'description','errors')}'><input type="text" id='description' name='description' value="${flight?.description?.encodeAsHTML()}"/></td></tr>
                        
				            <tr class='prop'><td valign='top' class='name'><label for='from'>From:</label></td><td valign='top' class='value ${hasErrors(bean:flight,field:'from','errors')}'><g:select optionKey="id" from="${Airport.list()}" name='from.id' value="${flight?.from?.id}" ></g:select></td></tr>
                        
				            <tr class='prop'><td valign='top' class='name'><label for='number'>Number:</label></td><td valign='top' class='value ${hasErrors(bean:flight,field:'number','errors')}'><input type="text" id='number' name='number' value="${flight?.number?.encodeAsHTML()}"/></td></tr>
                        
				            <tr class='prop'><td valign='top' class='name'><label for='seatClasses'>Seat Classes:</label></td><td valign='top' class='value ${hasErrors(bean:flight,field:'seatClasses','errors')}'><ul>
    <g:each var='s' in='${flight?.seatClasses?}'>
        <li><g:link controller='seatClass' action='show' id='${s.id}'>${s}</g:link></li>
    </g:each>
</ul>
<g:link controller='seatClass' params='["flight.id":flight?.id]' action='create'>Add SeatClass</g:link>
</td></tr>
                        
				            <tr class='prop'><td valign='top' class='name'><label for='to'>To:</label></td><td valign='top' class='value ${hasErrors(bean:flight,field:'to','errors')}'><g:select optionKey="id" from="${Airport.list()}" name='to.id' value="${flight?.to?.id}" ></g:select></td></tr>
                        	<tr class='prop'><td valign='top' class='name'><label for='schedule'>Schedule:</label></td><td valign='top' class='value ${hasErrors(bean:flight,field:'schedule','errors')}'><input type="text" id='schedule' name='schedule' value="${flight?.schedule?.encodeAsHTML()}"/></td></tr>
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
