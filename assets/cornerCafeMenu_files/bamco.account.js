var Bamco = (function(Bam){
	
	/**
	 * Launch the account JS
	 */
	Bam.initBamcoAccount = function()
	{
		/**
		 * Load the fancybox on this element
		 */
		$(".share-email-anchor").fancybox({
			hideOnContentClick: false,
			scrolling : 'no',
			onStart: function() {
				//$("#fancybox-inner").css('overflow-y', 'hidden !important');
			},
			onClosed: function() {
				//$("#fancybox-inner").css('overflow-y', 'hidden !important');
			},
			onComplete: function() {
				//$("#fancybox-inner").css('overflow-y', 'hidden !important');
			}
		});
		
		/**
		 * Load the fancybox on this element
		 */
		$(".print-menu-anchor").fancybox({
			hideOnContentClick: false,
			scrolling : 'no',
			titleShow : false,
			onStart: function() {
				//$("#fancybox-inner").css('overflow-y', 'hidden !important');
			},
			onClosed: function() {
				//$("#fancybox-inner").css('overflow-y', 'hidden !important');
			},
			onComplete: function() {
				//$("#fancybox-inner").css('overflow-y', 'hidden !important');
			}
		});
		
		$("#button-share-email").click(function() {
			Bam.initAccountFancybox();
		});
		
		$("#button-print-menu").click(function() {
			Bam.initPrintMenuFancybox();
		});
		
	}
	
	
	Bam.initAccountFancybox = function()
	{
		$("#share-email-loading").fadeIn("fast");
		
		var error = false;
		
		if ($("#your-name").val()=="") {
			$("#your-name-error").slideDown();
			error = true;
		}else{
			$("#your-name-error").slideUp();
		}
		if ($("#friends-name").val()=="") {
			$("#friends-name-error").slideDown();			
			error = true;
		}else{
			$("#friends-name-error").slideUp();
		}
		if ($("#friends-email").val()=="") {
			$("#friends-email-error").slideDown();
			error = true;
		}else if( !/^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/.test($("#friends-email").val()) ) {
			$("#friends-email-error").slideDown();
			error = true;
		}else{
			$("#friends-email-error").slideUp();
		}
		
		if (!error) {
			$.post("/ajax/public/send_share_email", $("#frmShareEmail").serialize(),
				function(data) {
					if (data=="success") { 
						$("#share-email-lightbox-form").fadeOut("fast", function() {
							$("#share-email-lightbox-response").fadeIn("fast");
						});
						setTimeout("$.fancybox.close()", 2500);
					}else{
						$("#share-email-loading").hide();
						alert("An error occured whilst trying to send your message. Please try again");
					}
				}
			);
		}else{
			$("#share-email-loading").hide();
		}
		
		setTimeout("$.fancybox.resize()", 440);
		
	}
	
	
	Bam.initPrintMenuFancybox = function()
	{
		$("#print-menu-loading").fadeIn("fast");
		
		var error = false;
		
		if( $('input[name=days]').val() == "" ) {
			$("#days-error").slideDown();
			error = true;
		}
		else
		{
			$("#days-error").slideUp();
		}
		
		if (!error)
		{
			$("#print-menu-loading").hide();
			// //e.preventDefault();
			
			var url = '/print-menu/';
			url += 'cafe/' + $('#cafe_id').val() + '/';
			url += 'menu/' + $('#menu_id').val() + '/';
			url += 'days/' + $('input[name=days]:checked').val() + '/';
			url += 'pgbrks/' + $('input[name=page_break]:checked').val() + '/';
			
			// $('#frmPrintMenu').attr('action', url);
			// $('#frmPrintMenu').submit();
			
			window.open(url, '', '');
			
			//$('#sudo-print-menu-anchor').live('click', function() { alert("hi"); });
			
			//$(this).attr('href', url);
		}
		else
		{
			$("#print-menu-loading").hide();
		}
		
		setTimeout("$.fancybox.resize()", 440);
		
	}
	
	
	return Bam;
	
}(Bamco || {}));

$(document).ready( function() {
	Bamco.initBamcoAccount();
});