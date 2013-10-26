<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<%@ include file="header.jsp" %>

<head>
<style>
.volunteersTable {
	font-family: verdana, arial, sans-serif;
	font-size: 14px;
	color: #333333;
	border-width: 1px;
	border-collapse: collapse;
}

.volunteersTable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}

.volunteersTable td {
	border-width: 1px;
	padding: 8px;
}

section header {
	font-family: verdana, arial, sans-serif;
	font-size: 18px;
}

section p {
	font-family: verdana, arial, sans-serif;
	font-size: 11px;
}


.heading {
	background-color: green;
	color: white;
	padding-bottom: 10px;
}

#postedProjects {
	font-family: verdana, arial, sans-serif;
	font-size: 13px;
}

#subList {
	border: solid 1px;
	margin-left: 20%;
	margin-right: 20%;
}
</style>


<link rel="stylesheet" type="text/css"
	href="<c:url value= "/resources/css/vtable.css" />" />
<script src="<c:url value="/resources/js/vtable.js" />"></script>
<link rel="stylesheet" type="text/css"
	href="<c:url value= "/resources/css/vtable.css" />" />
<script src="<c:url value="/resources/js/vtable.js" />"></script>
<script src="<c:url value="/resources/js/dialogbox.js" />"></script>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/dialogbox.css" />" />
<script src="<c:url value="/resources/js/popup.js" />"></script>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/popup.css" />" />




<script type="text/javascript">
	/* <!--global vars--> */
	var selectedProjectId = '';
	var selectedUsersProjecVolId = '';

	/* JQUERY Page On load */
	$(function() {

		// When view volunteer messages is clicked
		$('a[name=modal]').click(function(e) {

			var projVolId = $(this).attr('value');

			selectedUsersProjecVolId = projVolId;

			$.ajax({
				type : "GET",
				url : "getVolunteerMessages",
				data : {
					projectVolunteerId : projVolId
				}
			}).done(function(msg) {
				$("#messages").html(msg);
			});

		});

		//When user decides to select this volunteer
		$(".selectVolunteer").click(function() {

			//current table
			var table = $(this).parent().parent().parent();
			//find within the current table
			$(table).find(".selectVolunteer").hide();

			selectedUsersProjecVolId = $(this).attr('value');
			popup();

		});

		//Popup window YES Button will call this
		$("#selectVolunteer").click(
				function(e) {

					$.ajax({
						type : "POST",
						url : "selectVolunteer",
						data : {
							projectVolunteerId : selectedUsersProjecVolId
						}
					}).done(
							function(msg) {

								$("#img" + selectedUsersProjecVolId).show(
										'slow');

								$("#img" + selectedUsersProjecVolId).parent()
										.parent().find('td').css(
												'background-color', 'white');

								updateStatusToAssigned();

							});

				});

		//Post a new message
		$("#send").click(function() {

			var messageTxt = $("#txtAreaMsg").val();

			$.ajax({
				type : "GET",
				url : "postMessageToProjectVolunteer",
				data : {
					projectVolunteerId : selectedUsersProjecVolId,
					message : messageTxt
				}
			}).done(function(msg) {
				$("#messages").html(msg);
			});
		});

	});
	//--------------end of jquery load ----------------------------------------

</script>


</head>




<body>
        <%@ include file="header.jsp" %>
      
    <%@ include file="dashboard-menu.jsp" %>
    
          
          	<div class="centerContent">  
          	   
     		
         
            <table class="tablelayout">
            <tr>
           		<div class="mainPanelHeadning"><h2>Project I have Posted</h2></div>
            </tr>
        	<c:forEach var="Project" items="${projectsPostedList}">
       		<tr>
       			<td>
       				<div class="mainPanel">
       				<table>
  					
  					<tr>
  						<td>Project:</td>
  						<td class="projectTitleCell"> ${Project.projectTitle} </td>  
  					</tr>
					
					<tr>
						
						<td> Posted On: </td>  					
  						<td> ${Project.postedDate} </td> 
  						 
  					</tr>
  					
  					<tr>
  						<td> Status: </td>
  						<td>${Project.status}</td>
  					</tr>
  				
  					<tr>
  						<td> Detail: </td>
  						<td class="projectdetailcell">${Project.projectDetail}</td>
  					</tr>
  					<tr>
  						<td>Volunteers: </td>
  						
  						<td>
  							<table>
  								<c:forEach var="ProjectVolunteer" items="${Project.projectVolunteers}">
  								
  										<tr>
  											<td>
  												<a id="${ProjectVolunteer.projectVolunteersId}" href="#dialog" name="modal" class="openvoly">${ProjectVolunteer.postedBy.username}</a>
  											</td>
  										</tr>
  							
  								</c:forEach>
  							</table>
  						</td>
  						
  					</tr>
  					</table>
  					</div>
  					</td></tr>		
		    </c:forEach>
                
            </table>
            </div>
            
           
     	
	<!-- ---------------------------------------- -->
    <!-- For Messages Window use the below html -->
    <!-- ---------------------------------------- --> 	
  	<div id="boxes">
	<div id="dialog" class="window" style="overflow:scroll">	
     	
     	
                
    


          <div id="messages">
          
          
          		
          </div>
          


		 <div>
		 
   		 <textarea id="txtAreaMsg" rows='2' cols="20"></textarea>
         <button id="send">send</button>
        
         </div>
    </div></div>
    <div id="mask"></div>
    <!-- ---------------------------------------- -->
    
    
    
    	<div id="dialog-overlay"></div>
		<div id="dialog-box">
		<div class="dialog-content">
		<div id="dialog-message">Are you sure ?</div>
			<a href="#" class="button">Close</a>
			<a id="selectVolunteer" href="#" class="button">Yes</a>
		</div>
		</div>

 
    </body>
     

 </html>

