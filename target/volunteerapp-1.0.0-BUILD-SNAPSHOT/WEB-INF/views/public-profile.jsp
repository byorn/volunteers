<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<%@ include file="header.jsp"%>
    <head>
	    <style>
	    
	    body {
			overflow-x: hidden;
    		background-color: white;
      		margin: 0 auto;
          
		}
	    
	    
	    .wrapper{
	    
	    	background-image: -webkit-gradient(linear, center top, center bottom, from(#fcfcfc), to(#bfbfbf), color-stop(3%, #f7f7f7), color-stop(12%, #f2f2f2), color-stop(90%, #d9d9d9));
			background-image: -webkit-linear-gradient(top, #fcfcfc, #f7f7f7 3%, #f2f2f2 12%, #d9d9d9 90%, #bfbfbf);
			background-image: -moz-linear-gradient(top, #fcfcfc, #f7f7f7 3%, #f2f2f2 12%, #d9d9d9 90%, #bfbfbf);
			background-image: -o-linear-gradient(top, #fcfcfc, #f7f7f7 3%, #f2f2f2 12%, #d9d9d9 90%, #bfbfbf);
			background-image: -ms-linear-gradient(top, #fcfcfc, #f7f7f7 3%, #f2f2f2 12%, #d9d9d9 90%, #bfbfbf);
			background-image: linear-gradient(to bottom, #fcfcfc, #f7f7f7 3%, #f2f2f2 12%, #d9d9d9 90%, #bfbfbf);
			-webkit-box-shadow: inset -4px 6px 15px 9px white;
			-moz-box-shadow: inset -4px 6px 15px 9px white;
			box-shadow: inset -4px 6px 15px 9px white;
			
			width:60%;
			height:100%;
			display: block;
		    margin-left: auto;
		    margin-right: auto;
	    
	    }
	    
	    #container{
		    width:50%;
		    margin-left: auto;
		    margin-right: auto;
		    padding-top: 10%; 	
	    }
	    
	    
	
		    
		    
	    
	    </style>
	    
	    
	    
	   
    
    </head>



     
	<body>
	
	
    
    
			   <div class="wrapper">
					<div id="container">
			
			
			
			
						<div id="imgdiv"
							style="width: 400px; height: 400px; overflow: auto; float: left">
							<img id="img" src="loadImage?userId=currentuser" />
						</div>
			
						<div>
							Name: Asanka de Silva<br /> Contact: Asanka@gmail.com
						</div>
			
			
						<div>
							<h3>Why I Volunteer</h3>
							To improve my skills and to contribute to the world. To get
							recommendation and recognition
						</div>
			
			
						<div>
							<h3>Skills</h3>
							<p>Java, java script, html, android, ios, .net</p>
						</div>
			
						<div>
							<h3>Projects Delivered</h3>
							<p>
							<table>
			
			
			
							</table>
			
						</div>
			
						<div>
							<h3>feedback</h3>
							<table>
			
			
			
							</table>
						</div>
			
			
			
			
					</div>
				</div>
		     
     
     	
     
       

</body>
</html>