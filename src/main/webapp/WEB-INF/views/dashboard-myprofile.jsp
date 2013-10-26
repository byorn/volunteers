<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page session="false"%>

<%@ include file="header.jsp"%>


<head>

<title></title>


<link href='http://fonts.googleapis.com/css?family=Sofadi+One'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css"
	href="<c:url value= "/resources/css/vtable.css" />" />
<script src="<c:url value="/resources/js/vtable.js" />"></script>

<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.0/themes/base/jquery-ui.css" />

<link rel="stylesheet" type="text/css"
	href="<c:url value= "/resources/css/vtable.css" />" />
<script src="<c:url value="/resources/js/vtable.js" />"></script>
<script src="<c:url value="/resources/js/dialogbox.js" />"></script>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/dialogbox.css" />" />
<script src="<c:url value="/resources/js/jquery.form.js" />"></script>
<script src="<c:url value="/resources/js/jquery.Jcrop.min.js" />"></script>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/jquery.Jcrop.min.css" />" />
<script type="text/javascript">
         
         var otop = '';
         var oleft = '';
         var owidth = '';
         var oheight = '';
         
         $(function(){
        	 
        	 
        	 $( "#tabs" ).tabs();
        	 
 			$("#saveWhyVolunteer").click(function(){
        		 
        		 
        		 $.ajax({
                     type: "post",
                     url: "saveReasonForVolunteer",
                     data: { reasonForVolunteer: $("#addReasonForVolunteerTextArea").val()}
                     }).done(function( msg ) {
                    	 $("#saveWhyVolunteerResponse").html(msg);
    	             });
        	
        		 
        	 });
        	 
        	 
        	 
        	 
        	 
        	 
        	 $("#saveSkills").click(function(){
        		 
        		 
        		 $.ajax({
                     type: "post",
                     url: "saveUserSkills",
                     data: { skills: $("#addSkillTextArea").val()}
                     }).done(function( msg ) {
                    	 $("#saveSkillsResponse").html(msg);
    	             });
        	
        		 
        	 });
        	 
        	 
        	 
        	 
        	 
        	 $('#saveCropped').click(function(){
        		
        		 
        		  $.ajax({
                     type: "post",
                     url: "cropImage",
                     data: { top: otop, left:oleft, width:owidth, height:oheight}
                     }).done(function( msg ) {
                    	 $("#uploadResponse").html(msg);
    	             });
        	
        	 
        	 
        	 });
        	 
        	 
        	 
        	 
            var options = { 
            		
            		//beforeSubmit:  showRequest,
            		
            		success:    showResponse
            };    
             
            $('#uploadImage').ajaxForm(options);
            
            
            $("#updateProfileForm").ajaxForm(options);
            
         
         // Create variables (in this scope) to hold the API and image size
            var jcrop_api, boundx, boundy;
            
            $('#img').Jcrop({
              onChange: updatePreview,
              onSelect: updatePreview,
              aspectRatio: 1
            },function(){
              // Use the API to get the real image size
              var bounds = this.getBounds();
              boundx = bounds[0];
              boundy = bounds[1];
              // Store the API in the jcrop_api variable
              jcrop_api = this;
            });

            function updatePreview(c)
            {
              if (parseInt(c.w) > 0)
              {
                var rx = 100 / c.w;
                var ry = 100 / c.h;

                $('#preview').css({
                  width: Math.round(rx * boundx) + 'px',
                  height: Math.round(ry * boundy) + 'px',
                  marginLeft: '-' + Math.round(rx * c.x) + 'px',
                  marginTop: '-' + Math.round(ry * c.y) + 'px'
                });
                
                otop =c.y;
                console.log('toop' + otop);
                oleft = c.x
               owidth = c.w;
                oheight = c.h;
              }
            };
            
         
         });
         
      // post-submit callback 
         function showResponse(responseText, statusText, xhr, $form)  {
        	 	var d = new Date();	
			  	$("#img").attr('src',$("#img").attr('src')+"&param="+d.getTime());
			  	$("#uploadResponse").html(responseText);
			 	var d1 = new Date();
			 	$("#img").attr('src',$("#img").attr('src')+"&param="+d1.getTime());
         } 
         
      
      
         
          </script>
<style>

#tabs{
float:left;
width: 80%;
margin-left: 6%;
}


#updateProfileDiv {
	font-family: verdana, arial, sans-serif;
	font-size: 14px;
}


#images {
	float: left;
}


#imgdiv {
	width: 300px;
	height: 300px;
	overflow: auto;
	border: solid #c2bda9 1px;
}


.formTable td {
	padding-top: 5px;
	text-align: left;
}
</style>
</head>




<body>




	<%@ include file="dashboard-menu.jsp"%>
	<div class="centerContent">  
	
	<div id="tabs">
  <ul>
    <li><a href="#tabs-1">Upload a Profile Picture</a></li>
    <li><a href="#tabs-2">Edit User Details</a></li>
    <li><a href="#tabs-3">Edit Volunteer Details</a></li>
  </ul>
 
	

	<div id="tabs-1">
		
		<form id="uploadImage" action="uploadImage"
			enctype="multipart/form-data" method="post">
			<table>

				<tr>
					<td>
						<div id="imgdiv">
							<img id="img" src="loadImage?userId=currentuser" />
							<br/>
							<p>Drag the mouse to crop your image</p>
						</div>
					</td>
					<td style="padding-left: 20px;padding-right: 20px;padding-bottom: 20px;">
						<div style="width: 100px; height: 100px; overflow: hidden;">
							<img src="loadImage?userId=currentuser" id="preview"
								alt="Preview" class="jcrop-preview" />
						</div>
						<br/>
						<p>Your public profile picture</p>
					</td>

					<td><input type="file" name="image" /><br />
					<br />
						<button type="submit">Upload</button>
						<br />
					<br />
						<button id="saveCropped" />Save
						</button>
						<br />
					<br />
						<div id="uploadResponse" /></td>

				</tr>

			</table>

		</form>
	</div>




	<div id="tabs-2">
		

		<form id="updateProfileForm" action="updateProfile" method="POST">
			<table class='formTable'>
				<tr>
					<th>User Name:</th>
					<td>test@gmail.com</td>
				</tr>

				<tr>
					<th>Display Name:</th>
					<td><input type="text" name="displayName"
						value='<c:out value='${displayName}'/>'></td>
				</tr>

				<tr>
					<th>Current Password:</th>
					<td>************</td>
				</tr>


				<tr>
					<th>New Password:</th>
					<td><input type="text" name="password"></input></td>
				</tr>

				<tr>
					<th>Retype New Password:</th>
					<td><input type="text"></input></td>
				</tr>

				<tr>
					<td><button type="submit">Update Profile</button></td>
				</tr>
			</table>
		</form>
	</div>

	<div id="tabs-3">

	<div id="whyVolunteer">
		<h4>Why volunteer?</h4>

		<textarea rows="5" cols="5" id="addReasonForVolunteerTextArea">
  			<c:out value="${reasonForVolunteer}"></c:out>
  		</textarea>
		<button id="saveWhyVolunteer">Save Details</button>
		<div id="saveWhyVolunteerResponse" ></div>
	</div>



	<br />

	<div id="addSkills">
		<h4>Add Skills</h4>
		<h5>Type your skills here: e.g.: Content Writing, Web
			Programming, etc, etc</h5>
		<textarea rows="5" cols="5" id="addSkillTextArea">
  			<c:out value="${userskills}"></c:out>
  		</textarea>
		<button id="saveSkills">Save Skills</button>
		<div id="saveSkillsResponse" ></div>
	</div>
	</div>

 </div>
 </div>
</body>
