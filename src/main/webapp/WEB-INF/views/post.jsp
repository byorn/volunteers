<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<%@ include file="header.jsp" %>
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/freelancer.css" />"  />
        <link href='http://fonts.googleapis.com/css?family=Sofadi+One' rel='stylesheet' type='text/css'>
      
        <script src="<c:url value="/resources/js/jquery.form.js" />"></script>   
        <script src="<c:url value="/resources/js/popup.js" />"></script>
		<link rel="stylesheet" type="text/css"	href="<c:url value="/resources/css/popup.css" />" />
    </head>
    
    
    <style>
    
    .postform{
   
    	margin: 0 auto;
    	width: 30%;
    }
    
    table td{
    padding-bottom: 20px;
    }
    
    </style>
    

<title></title>
  
        <script type="text/javascript">
        $(function(){
          
            
             
            var options = { 
            	
                     success:       showResponse 
            };    
             
            $('#newProjectForm').ajaxForm(options);
            
            
            $('.radio').change(function() {
            	
            	$("#newUser").empty();
            });
            
            $('#idUserName').blur(function() {
            	$("#usernameAndPassword").empty();
            			
            });
            
            $('#idPassword').blur(function() {
            	$("#usernameAndPassword").empty();
            			
            });
            
            $('#idProjectTitle').blur(function() {
            	$("#projectTitle").empty();
            			
            });
            
            $('#idProjectDetail').blur(function() {
            	$("#projectDetail").empty();
            			
            });
           
        
        });
        
        
        function showResponse(responseText, statusText, xhr, $form)  { 
         	  var isSuccess;
         	  $.each(responseText, function(key, val) {
         		 
         		
         		  //@see: JSonRespone POJO
         		  if(key=="success"){
         			 isSuccess = val; 
         			 
         		  }
         		  else {
         			
         			 $.each(val, function(key1,val1){
         			
         				 $("#"+key1).html(val1);
          					
          			});
          			
         		  }
         		  
         	  });
         	  
         	  if(isSuccess==true)
         	  {
         		  popup();
         	  }
         
         	
        }
        
        
       
        
        
        
        </script>
    
    </head>
    
    
    
    
    <body>
     
    
        
        <div class="centerContent">
                
            <section class="postform">
                
                <div class="errorText">
                	<span id="errorResponse" class="errorText"></span>
                </div>
                <form id="newProjectForm" method="post" action="postProject">
                
                 
					<sec:authorize access="!hasRole('ROLE_USER')">
                     
                    <table>
                        <tr>
                            <td colspan="2">
                            	<span id="newUser" class="errorText"></span><br/>
                                <label> I am  a &nbsp;&nbsp;&nbsp; </label>
                                <input type="radio"  name="userIsNew" value="NEW" title="New User"  class="radio" />New User &nbsp; &nbsp; &nbsp; &nbsp;  <input type="radio" name="userIsNew" value="EXISTING" class="radio"/>Existing User
                            </td>
                        </tr>
                        
                       
                        <tr>
                        <td colspan="2">	<span id="usernameAndPassword" class="errorText"/></td>
                        </tr>
                        
                        <tr>
                            <td>
	                            <label> User Name: (Email Address)</label>
	                            <br/>
	                            <input id="idUserName" type="text" name="userName"/>
	                            
                            </td>
                         
                        </tr>
                       
                        <tr>
                            <td> <label>Password</label>
                            <br/>
                            <input id="idPassword" type="text" name="password"/>
                            </td>
                                                      
                        </tr>
                     
                     </table>
                   </sec:authorize>
                    
                    
                <table>
            
                    
                    
                    
                    <tr>
                        <td>
                            <label>Project Name</label>
                            <br/><span class="errorText" id="projectTitle"></span>
                             <input id="idProjectTitle" type="text" name="projectTitle" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                       <label> Select a Category (Optional)</label>
                        <span class="errorText" id="categoryCode"></span>
                        <br/>
                         <div class="styled-select">
								<select id="category" name="categoryCode">
										<c:forEach var="c" items="${categoryList}">
										<c:choose>
											<c:when test="${c.categoryCode=='SEL'}">
												<option value='${c.categoryCode}' selected />
												<c:out value="${c.categoryDescription}" />
												</option>
											</c:when>
											<c:otherwise>
												<option value='${c.categoryCode}' />
												<c:out value="${c.categoryDescription}" />
												</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                        <label>Describe your Project in detail</label>
                        <br/>
                        <span class="errorText" id="projectDetail"></span>
                          <textarea id="idProjectDetail" name="projectDetail"> </textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <button class="button" type="submit" >Post Project</button>
                        </td>
                    </tr>
                    
                    
                </table>
            
            </form>
            
                  
                

             </section>
            
            
            
        </div>



     

    
    </body>
    
   

 	<div id="dialog-overlay"></div>
		
		<div id="dialog-box">
		
		<div class="dialog-content">
			
			<div id="dialog-message"><p id="successResponse"></p></div>
			
			<a href="welcome" class="button" onclick="gotoPage();">OK</a>
			
			<script>
				
				function gotoPage(){
					window.location="welcome";
				}
			
			</script>
			
		</div>
	</div>
</html>
