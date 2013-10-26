$(function(){

	
	
	
	

    $('.vtable tr').click(function() {
       
    	onRowClick(this);
    		
    });
    
    
    $('.vtable tr').live("click", function(){
    	onRowClick(this);
    	});

	
	
});

