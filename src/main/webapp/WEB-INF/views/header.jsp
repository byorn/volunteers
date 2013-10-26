<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>

	<!-- *************************************** -->
	<!-- GLOBAL LESS STYLE SHEET. REQUIRES FOOTER.JSP TO INIT LESS.JS -->
	<!-- *************************************** -->
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/global.css" />" />
	
	
	
	<!-- *************************************** -->
	<!-- JQUERY INIT -->
	<!-- *************************************** -->
	<link rel="stylesheet" type="text/css"
		href="<c:url value= "/resources/css/jquery-thickbox.css" />" />
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
	<script src="<c:url value="/resources/js/jquery-thickbox.js" />"></script>
	<script
		src="//ajax.googleapis.com/ajax/libs/jqueryui/1.9.2/jquery-ui.min.js"></script>
	
	
	<script>
		$(function() {
	
		});
	</script>
	
	<style>
.logo1 {
	position: absolute;
	top: 0px;
	left: 0px;
	height: 45px;
}

.logo {
	position: absolute;
	top: 0px;
	left: 0px;
	height: 65px;
	float: left;
        padding-right: 100px;
}

.logotext{
    text-shadow: 0 1px 0 #ccc,
               0 2px 0 #c9c9c9,
               0 3px 0 #bbb,
               0 4px 0 #b9b9b9,
               0 5px 0 #aaa,
               0 6px 1px rgba(0,0,0,.1),
               0 0 5px rgba(0,0,0,.1),
               0 1px 3px rgba(0,0,0,.3),
               0 3px 5px rgba(0,0,0,.2),
               0 5px 10px rgba(0,0,0,.25),
               0 10px 10px rgba(0,0,0,.2),
               0 20px 20px rgba(0,0,0,.15);
    font-family:Helvetica, Arial, sans-serif;
  font-size:25px;
  color:white; padding-left: 100px;
}

</style>
	
	
       
</head>




<div id="headerbar">
    <div>
    <img src="<c:url value="/resources/imgs/logo.png" />" class="logo"/> 
    <div class="logotext"> Volunteers.Com </div>
    </div>



<!-- ************************************* -->
<!-- LOGIN FORM -->
<!-- ************************************* -->
	<div id="windowLogin" style="display: none;">

		<form name='f' action="<c:url value='j_spring_security_check' />"
			method='POST'>

			<table id="loginTable">
				<tr>
					<td><label>User:</label></td>
                                        <td><input type="text" name='j_username' value="byorn"></td>
				</tr>
				<tr>
					<td><label>Password:</label></td>
					<td><input type='password' name='j_password' value="thejacket"/></td>
				</tr>
				<tr>
					<td><button class="button" type="submit">Login</button></td>
					

					<td><button class="button">Cancel</button></td>
				</tr>

			</table>

		</form>

		<div id="output1"></div>

		<a href="#">forgot account details?</a><br /> <a href="#">sign up
			for a new account for free</a>
	</div>




	<!-- **********************************************
	MENU BAR
	********************************************** -->
	<nav class="menuwrapper">

		<c:set var="showLogin" value="true" />
		<ul class="menu-bar">


			<!-- If Logged In user then show the Dashboard button -->
			<sec:authorize access="hasRole('ROLE_USER')">
				<li><a href="dashboardRecruiter">Dashboard</a> <input
					id="isLoggedIn" type="hidden" value="yes" /> <c:set
						var="showLogin" value="false" />
			</sec:authorize>



			<li><a href="post">Post Project</a></li>
			<li><a href="volunteer?pageId=-1"
				title="Search projects and volunteer">Volunteer</a></li>
			<c:if
					test="${showLogin=='true'}">
					<li><a href="#TB_inline?width=300px&inlineId=windowLogin"
						class="thickbox">Login</a>
				</c:if> <c:if test="${showLogin=='false'}">
					<li><a href="<c:url value="j_spring_security_logout" />">
							Logout</a>
				</c:if>
		</ul>


	</nav>


</div>

