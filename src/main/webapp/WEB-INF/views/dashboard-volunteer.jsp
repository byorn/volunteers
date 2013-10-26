<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

        <%@ include file="header.jsp" %>
        
      
        
        
    
         <script src="<c:url value="/resources/js/dialogbox.js" />"></script>
         <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/dialogbox.css" />"  />	
    
         <script type="text/javascript">
         
         var selectedUsersProjecVolId = '';
         
         
         
         $(function(){
        	 
        	 
        	 
        		// When view volunteer messages is clicked
         	$('a[name=modal]').click(function(e) {
         		
          		var projVolId = $(this).attr('value');
          		
          		selectedUsersProjecVolId = projVolId;
       
 	        	
         		
         		 $.ajax({
                      type: "GET",
                      url: "getVolunteerMessages",
                      data: { projectVolunteerId: projVolId}
                      }).done(function( msg ) {
                     	 $("#messages").html(msg);
     	             });
     		
         	});
        		
        		

        	//Post a new message
	    	$("#send").click(function(){
				
	    		var messageTxt = $("#txtAreaMsg").val();
	    	
		
	     		 $.ajax({
                     type: "GET",
                     url: "postMessageToProjectVolunteer",
                     data: { 
                    	 projectVolunteerId: selectedUsersProjecVolId,
                    	 message:messageTxt
            	           }
                     }).done(function( msg ) {
      		           	 $("#messages").html(msg);
          	         });
        	});
         	
         	
        	 
        	 
         });
        	 
        	 
        	
        	 
        	 
        	 
        	 
     
         
 

      
      
 </script>
 <style>           
   #userImageDiv{
   text-align: center ;
  
   width: 50%;
   display: block;
    margin-left: auto;
    margin-right: auto
   }
   
   #images{
   float:left;
   }
   
  
   
   #imgdiv{
    border: solid #c2bda9 1px;
   }
   
     
    </style>
    </head>    
        
  	
  	
  	
  	<body>

	<%@ include file="dashboard-menu.jsp" %>
  		 		   
  		<div class="centerContent">  
  		 		   
  		 <table class="tablelayout">
	            <tr>
	           		<div class="mainPanelHeadning"><h2>Project I have volunteerd for</h2></div>
	            </tr>
	        	<c:forEach var="ProjectVolunteer" items="${projectsVolunteered}">
	       		<tr>
	       			<td>
	       				<div class="mainPanel">
	       				
		       				<table>			
			       				<tr>
									<td><img class="profileimage" src="loadImage?userId=${ProjectVolunteer.project.postedBy.userId}"/>
									<br/>${ProjectVolunteer.project.postedBy.username}
									</td>
								</tr>
								<tr>
									<td>${ProjectVolunteer.project.projectTitle}</td>
								</tr>
								
								<tr>
									<td>${ProjectVolunteer.project.status}</td>
								</tr>
								
								<tr>
									<td class="projectdetailcell" hidden="hidden">${ProjectVolunteer.project.projectDetail}</td>
								</tr>
								
								
								<tr>
									<td><a href="#dialog" value="${ProjectVolunteer.projectVolunteersId}" name="modal" > View Recruiters message</a></td>
								</tr>
							</table>
						</div>
					</td>
				</tr>		
				</c:forEach>
       	</table>			
       				
       </div>


   <!-- ---------------------------------------- -->
    <!-- For Messages Window use the below html -->
    <!-- ---------------------------------------- --> 	
  	<div id="boxes">
	<div id="dialog" class="window" style="overflow:scroll">	
     	
     	
                
    


          <div id="messages"></div>
          


		 <div>
		 
   		 <textarea id="txtAreaMsg" rows='2' cols="20"></textarea>
         <button id="send">send</button>
      
         </div>
    </div></div>
    <div id="mask"></div>
    <!-- ---------------------------------------- -->



</body></html>