<%@ page pageEncoding="UTF-8" %>
<%@include file="header.jsp"%>
<font color=white>

				<table>
					<form action="login" method="post">
						<tr><td>Login:</td><td><input type="text" name="login"/></td></tr>
						<tr><td>Password:</td><td><input type="password" name="password"/></td></tr>
						<tr><td> </td><td><input type="submit" value="Sign In"/></td></tr>
					</form>
				</table>
				<div>

				</div>

    <h1 style='font-size:25px' align='center'>
            ${messageActivation}
    </h1>


				<tr> <td> </td> </tr>
				<a href="/v1/horoscope/registration">Add new user</a>

</font>
<%@include file="footer2.jsp"%>

