<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ include file="header.jsp" %>

    
    
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/global.css" />"  />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/dialogbox.css" />"  />
        
      
       	<script src="<c:url value="/resources/js/jquery.form.js" />"></script>
       	<script src="<c:url value="/resources/js/dialogbox.js" />"></script>




<script type="text/javascript">
	$(function() {

		/* When you post a message  */
		$('.postButton').click(function() {

			var options = {
				success : showResponse

			};

			$('#formVolunteer').ajaxForm(options);

			function showResponse(responseText, statusText, xhr, $form) {

				$.each(responseText, function(key, val) {

					//@see: JSonRespone POJO
					if (key == "success") {
						isSuccess = val;
						$('#postButton').hide('slow');
						$('#returnToVol').show('slow');

					} else {

						$.each(val, function(key1, val1) {

							$("#" + key1).html(val1);

						});

					}

				});

			}

		});

		/* When volunteer link is clicked*/
		$('.openvoly').click(function() {

			//clear the text area 
			$("#txtAreaForMessage").val("");

			//project id, and set it to a hidden variable
			var projectId = $(this).attr('id');
			$("#hdnProjectId").val(projectId);

			//get project details from server.
			$.ajax({
				type : "GET",
				url : "getPostedProjectDetails",
				data : {
					projectId : projectId
				}
			}).done(function(msg) {
				$("#projectinfo").html(msg);
			});

		});

		$('article').hover(function() {

			$(this).addClass('artcss');

		}, function() {

			$(this).removeClass('artcss');
		});
		
		
		$("#searchbtn").click(function(){
			var catCode = $("#skill_category-fixed").val();
			window.location="volunteer?pageId=-1&cat="+catCode;
		});
		

	});
</script>






<body>
        
	<div class="centerContent">

		<div class="searchSection" >

			<div class="innerSearchSection">

				<div class="styled-select" style="float:left">
					<select id="skill_category-fixed">
						<c:forEach var="category" items="${categories}">
							<option value="${category.categoryCode}">${category.categoryDescription}</option>
						</c:forEach>
					</select>
				</div>



				<button id="searchbtn" class="button">Search</button>


			</div>
			<br/><br/>
			<nav class="paging">
					
					<c:forEach var="pageNum" items="${pages}">

						<a class="navButton" href="volunteer?pageId=${pageNum}">${pageNum}</a>
					</c:forEach>
					
				

			</nav>

		</div>
		
		
		

		

	


		<table class="articles">


			<tr>
				<%
					int i = 0;
				%>

				<c:forEach var="project" items="${projectList}">

					<%
						if (i > 2) {
					%>
				
			</tr>
			<tr>
				<%
					i = 0;
						}
				%>

				<td>
					<article id="art${project.projectId}">
						<span></span>

						<div id="articlecontent${project.projectId}"
							class="articlecontent">
							

							<header>Posted By: ${project.postedBy.username} On:
								${project.postedDate}</header>

                                                        <h2> ${project.projectTitle} </h2>
                                                        
							<p>${project.projectDetail}</p>

							<footer style="margin-top:10%">



								<a id="${project.projectId}" href="#dialog" name="modal"
									class="button">Volunteer</a>

							</footer>

						</div>
					</article>
				</td>

				<%
					i++;
				%>

				</c:forEach>

			</tr>
		</table>


	</div>
    
    
         




	<!-- New Modal Window | Dialog Box -->
	<div id="boxes">

	<div id="dialog" class="window">		     
				     
				     
				     			<div id="projectinfo"></div>
				     
				     			 			
				     
				     
				     			<form id="formVolunteer" action="postMessage">
				  							
				  				
				  				<input id="hdnProjectId" type="hidden" name="projectId"/>




			<!-- On The GO Authenticate -->
			<sec:authorize access="!hasRole('ROLE_USER')">
				<table>
					<tr>
						<td colspan="2"><span id="newUser" class="errorText"></span><br />
							I am a</td>
					</tr>

					<tr>
						<td><input type="radio" name="userIsNew" value="NEW"
							title="New User" class="radio" />New User</td>

						<td><input type="radio" name="userIsNew" value="EXISTING"
							class="radio" />Existing User</td>
					</tr>
					<tr>
						<td colspan="2"><span id="usernameAndPassword"
							class="errorText" /></td>
					</tr>

					<tr>
						<td>User Name: (Email Address)</td>

					</tr>
					<tr>
						<td><input id="idUserName" type="text" name="userName" /></td>
					</tr>
					<tr>
						<td>Password</td>

					</tr>
					<tr>
						<td><input id="idPassword" type="text" name="password" /></td>
					</tr>
				</table>
			</sec:authorize>



								<table style="width: 100%">
									<tr>
										<td>
										<textarea id="txtAreaForMessage" name="messageText">Type message here</textarea><br/>
										<span id="messageText" class="errorText"></span>
										</td>
									</tr>
									<tr>
										<td>
											<button class="button" type="submit" id="postButton" class="postButton">POST</button>
											
										</td>
									</tr>
								</table>
								
				  		</form>
			 <span id="errorResponse" class="errorText"></span>
            <span id="successResponse" class="successText"></span>
            <a id="returnToVol" href="#" class="close" style="display:none;" />Return to volunteers page</a>
		 </div>
		 </div>
        
        
    <div id="mask"></div>
    
    
	</body>
	</html>

