<%@page import="model.User"%>
<%@page import="model.Groupp"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login Success Page</title>

</head>



<body>

<%
	String userName = null;
    Cookie[] cookies = request.getCookies();
    if(cookies !=null){
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("username")) userName = cookie.getValue();
        }
    }
    if(userName == null)
    	response.sendRedirect("login.html");
%>

<%
	User user = (User) session.getAttribute("user");
%>



<form action="LogoutServlet" method="post" class="login">
<p style="text-align: right;">
    <label>Hi <%=userName %>, Login was successful.</label>
    <br>
    <button type="submit">Logout</button>
</p>
</form>


<p style="text-align: center;">
	<select multiple="false" id="groupps" onchange="fillMembers();">
		<%

		java.util.Iterator<Groupp> itr = (user!=null) ?
			user.getGroupps().iterator():null;
		while(itr!=null && itr.hasNext()) {
			Groupp groupp = itr.next();
			String gname = groupp.getGroupName();
			
			String nameList= "";
			java.util.Iterator<User> gitr = groupp.getUsers().iterator();
			while(gitr.hasNext()) {
				User next = gitr.next();
				nameList = nameList + next.getUserName() + "<br>";
			}

	    %>	
			<option value="<%=nameList%>"><%=gname%></option>
		<%
		}
		%>
		
		
	</select>
</p>
<p style="text-align: center;" id="gMembers">outmembers</p>


<br>
</body>

<script language="javascript"> 

function fillMembers(){ 
	var groupps = document.getElementById("groupps");
	var selectedValue = groupps.options[groupps.selectedIndex].value;
 
	document.getElementById("gMembers").innerHTML = selectedValue;
} 

</script> 

</html>