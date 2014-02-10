if(!eni){
	var eni = {};
}

eni.getDayOfweek = function(){
	var d = new Date();
	return d.getDay(); // sun = 0, mon = 1, ...
};

$(document).ready(function(){
	
	$(document).bind('updateEniMenuDisplay', function(e, type){

        /*
		switch(type){
		
			case 'show_todays_items_only': 
				$('.eni-menu-day').hide();
				$('.eni-menu-day-' + eni.getDayOfweek()).show();
				break;
	
			case 'full_week_visible': 
				$('.eni-menu-day').show();
				break;
		
		}*/
		
	});
	
	
	$('.eni-menu-nutrition-info').hide();
	$('.eni-menu-more-info').click(function(e){
		e.preventDefault();
		$(this).parents('tr').find('.eni-menu-nutrition-info').toggle();
	});
	
});
