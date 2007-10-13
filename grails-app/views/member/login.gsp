<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main" />
<title>Member Login</title>
</head>
<body>
<div class="body">
<g:form action="login" method="post">
<div class="dialog">
<p>Enter your login details below:</p>
<table class="userForm">
<tr class='prop'>
<td valign='top' style='text-align:left;' width='20%'>
<label for='email'>Email:</label>
</td>
<td valign='top' style='text-align:left;' width='80%'>
<input id="email" type='text' name='email' value='${member?.email}' />
</td>
</tr>
<tr class='prop'>
<td valign='top' style='text-align:left;' width='20%'>
<label for='password'>Password:</label>
</td>
<td valign='top' style='text-align:left;' width='80%'>
<input id="password" type='password' name='password'
value='${member?.password}' />
</td>
</tr>
</table>
<input type="submit" value="Login"></input>
</div>
	<div class="nav">
	<span class="menuButton"><a href="${createLinkTo(dir:'')}">Home</a></span>
	<g:link class="create" action="create">New Member</g:link>
	</div>
</div>
</g:form> 
</div>
</body>