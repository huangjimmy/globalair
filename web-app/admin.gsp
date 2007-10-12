<html>
    <head>
        <title>Welcome to Globa Air Flight Booking</title>
		<meta name="layout" content="main" />
    </head>
    <body>
        <h1 style="margin-left:20px;">Welcome to Globa Air Flight Booking</h1>
        <p style="margin-left:20px;width:80%">Congratulations, 
        you have successfully started your booking with us! At the moment
        this is the default page, feel free to choose an action:</p>
        <div class="dialog" style="margin-left:20px;width:60%;">
            <ul>
              <!--<g:each var="c" in="${grailsApplication.controllerClasses}">
                    <li class="controller"><a href="${c.logicalPropertyName}">${c.fullName}</a></li>
              </g:each>
              -->
             

                    <li class="controller"><a href="airport">Manage Airport</a></li>
					<li class="controller"><a href="seatClass">SeatClass</a></li>
					<li class="controller"><a href="staff">Manage Staff</a></li>
                    <li class="controller"><a href="flight">Manage Flight</a></li>    
           		
            </ul>
        </div>
    </body>
</html>