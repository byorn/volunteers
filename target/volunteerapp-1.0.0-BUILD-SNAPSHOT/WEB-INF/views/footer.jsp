<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
    


<head>

	<!-- *************************************** -->
	<!-- GLOBAL LESS STYLE SHEET. REQUIRES FOOTER.JSP TO INIT LESS.JS -->
	<!-- *************************************** -->
	<link rel="stylesheet/less" type="text/css"
		href="<c:url value="/resources/css/global.css" />" />
	
	
	
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
	.logo{
	position: fixed;
	top:0px;
	left:0px;
	  height: 75px;
	
	}
	</style>
	
	
       
</head>


<img src="<c:url value="/resources/imgs/logo.jpg" />" class="logo"/>	

<div id="headerbar">




<!-- ************************************* -->
<!-- LOGIN FORM -->
<!-- ************************************* -->
	<div id="windowLogin" style="display: none;">

		<form name='f' action="<c:url value='j_spring_security_check' />"
			method='POST'>

			<table id="loginTable">
				<tr>
					<td><label>User:</label></td>
					<td><input type="text" name='j_username'></td>
				</tr>
				<tr>
					<td><label>Password:</label></td>
					<td><input type='password' name='j_password' /></td>
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
			<li><a href="welcome">About</a> <c:if
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


