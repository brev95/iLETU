var Bamco = (function(){
	
	var	Bam = {};
	var bmi_format = 'imperial';
	
	/**
	 * This function is the basis of Bamco. It registers all GLOBAL events. suck as click events in common includes.
	 * If your event is only related to a particular section of the site. Include an extension module and put the code there!
	 * 
	 */
	Bam.initBamco = function()
	{
		/**
		 * Load any coloured rows
		 */
		Bam.row_colors();
		
		/**
		 * Load imperial units on the bmi calculator
		 */
		Bamco.showImperial();
		
		/**
		 * Show more toggle for promos
		 */
		Bam.togglePromoContent();
		
		/**
		 * Load any placeholder text across the site
		 */
		if( typeof placeholder == 'function' )
		{
			$('input[placeholder],textarea[placeholder]').placeholder();
		}
		
		/**
		 * Stop the BMI calculator from submitting
		 */
		$('form[name=bmi]').submit(function() {
			return false;
		});
		
		$("#right .inner .padding .content .three-quarters .padding.account-cafe form select[name=location]").live('change', function() {
			Bam.updateCommentsForm( $(this).val() );
		});
		
		Bam.updateCommentsForm( $('#right .inner .padding .content .three-quarters .padding.account-cafe form select[name=location]').val() );
	}
	
	
	Bam.screen_center_left = function(w, h)
	{
		return (screen.width / 2) - (w / 2);
	}
	
	
	Bam.screen_center_top = function(w, h)
	{
		return (screen.height / 2) - (h / 2);
	}
	
	
	Bam.row_colors = function()
	{
		$('table').each(function() {
			$(this).find('tr:visible:odd').css({ 'background' : '#ffffff' });
			$(this).find('tr:visible:even').css({ 'background' : '#f0f0f0' });
		});

		$('table.my-day-menu-table').each(function() {
			$(this).find('tr:visible').css({ 'background' : '#ffffff' });
			$(this).find('tr.always-show-me').css({ 'background' : '#ffff99' });
		});

		//[class^=\'my-day-menu-table\']
		// $('.table tr').removeClass('even').removeClass('odd');
		// $('.table tr:nth-child(even)').addClass('even');
		// $('.table tr:nth-child(odd)').addClass('odd');
	}
	
	
	Bam.toggle = function (div)
	{
		if (document.getElementById) {
			this.obj = document.getElementById(div);
			this.style = this.obj.style;
		} else if (document.all) {
			this.obj = document.all[div];
			this.style = this.obj.style;
		} else if (document.layers) {
			this.obj = document.layers[div];
			this.style = this.obj;
		} else {
			return false
		}

		if (this.style.display=="block") this.style.display="none";
		else this.style.display="block";
	}

	Bam.showMetric = function()
	{
		Bam.turnOff('imperial');
		Bam.turnOn('metric');
	}

	Bam.showImperial = function()
	{
		Bam.turnOff('metric');
		Bam.turnOn('imperial');
	}

	Bam.reset_energy = function()
	{
		Bam.turnOff('result');
		return true;
	}

	Bam.getRadioValue = function(ref)
	{
		var found_it;
		for (var i=0; i<ref.length; i++)
		{ 
			if (ref[i].checked)  {
				found_it = ref[i].value
			}
		}
		return found_it;
	}

	Bam.calculate_enery = function()
	{
		// Get reference to the form
		var ref = document.energy;

		// Check for values
		var message = "";
		if (!Bam.isInteger(ref.age)) {
			message += "> Please enter a number for your age.\n";
		}
		if (!Bam.isInteger(ref.weight)) {
			message += "> Please enter a value for your weight in pounds.\n";
		}
		if (!Bam.isInteger(ref.height_feet)) {
			message += "> Please enter a value for your height in feet.\n";
		}
		if (!Bam.isInteger(ref.height_inches)) {
			message += "> Please enter a number for your height in inches. If this is zero inches please enter '0'\n";
		}
		if (message) {
			alert("Please check the following and try again:\n\n"+message);
			return false;
		}
		var age = parseInt(ref.age.value);
		var gender = Bam.getRadioValue(ref.gender);
		var weight = parseInt(ref.weight.value/2.22);
		var height = (parseInt(ref.height_feet.value*12)+parseInt(ref.height_inches.value))*0.0254;
		var pa = Bam.getRadioValue(ref.pa);

		Bam.turnOn('result');
		Bam.writeDiv(Bam.cal_energy(age, gender, weight, height, pa), 'resultvalue');
		return true;
	}

	Bam.cal_energy = function(age, gender, weight, height, pa)
	{
		var result;
		switch (gender) {
			case "F":
			default:
				result = Math.round((354-(6.91*age))+(pa*(9.36*weight))+(726*height));
				break;
			case "M":
				result = Math.round((662-(9.53*age))+(pa*(15.91*weight))+(539.6*height));
				break;
		}
		return result;
	}

	Bam.writeDiv = function(text, id)
	{
		if (document.getElementById) {
			x = document.getElementById(id);
			x.innerHTML = '';
			x.innerHTML = text;
		} else if (document.all) {
			x = document.all[id];
			x.innerHTML = text;
		} else if (document.layers) {
			x = document.layers[id];
			text2 = '<P CLASS="testclass">' + text + '</P>';
			x.document.open();
			x.document.write(text2);
			x.document.close();
		}
	}

	Bam.calculate = function(format)
	{
		// Get reference to the form
		var ref = document.bmi;

		if (format == "metric") {

			// Check for values
			var message = "";
			if (!Bam.isInteger(ref.weight_k)) {
				//ref.weight_k.focus();
				message += "> Please enter a number for your weight.\n";
			}
			if (!Bam.isInteger(ref.height_c)) {
				//ref.height_c.focus();
				message += "> Please enter a number for your height.\n";
			}
			if (message) {
				alert("Please check the following and try again:\n\n"+message);
				return false;
			}
			$('#bmi').html(Bam.cal_bmi(ref.weight_k.value, ref.height_c.value, format));
			//ref.metricresult.focus();

		} else {

			// Check for values
			var message = "";
			if (!Bam.isInteger(ref.weight_p)) {
				//ref.weight_p.focus();
				message += "> Please enter a number for your weight.\n";
			}
			if (!Bam.isInteger(ref.height_f)) {
				//ref.height_f.focus();
				message += "> Please enter a number for your height in feet.\n";
			}
			if (!Bam.isInteger(ref.height_i)) {
				//ref.height_i.focus();
				message += "> Please enter a number for your height in inches.\n";
			}
			if (message) {
				alert("Please check the following and try again:\n\n"+message);
				return false;
			}

			height_t = parseInt((ref.height_f.value)*12)+parseInt(ref.height_i.value);
			$('#bmi').html(Bam.cal_bmi(ref.weight_p.value, height_t, format));
			//ref.imperialresult.focus();
		}

		return false;
	}

	Bam.cal_bmi = function(weight, height, format)
	{
		if (format == "metric") {
			m = height/100;
			h2 = m * m;
			s_bmi = weight/h2;
		} else {
			h2 = height * height;
			s_bmi = weight/h2 * 703
		}

		f_bmi = Math.floor(s_bmi);
		diff  = s_bmi - f_bmi;
		diff = diff * 10;
		diff = Math.round(diff);

		if (diff == 10) {
			f_bmi += 1;
			diff = 0;
		}
		s_bmi = f_bmi + "." + diff;
		return s_bmi;
	}

	Bam.isDigit = function(c)
	{
		return ((c >= "0") && (c <= "9"));
	}

	Bam.empty = function(v)
	{
		return (v=="" || v==null || !Bam.defined(v));
	}

	Bam.defined = function(v)
	{
		var undefined;
		return typeof v != 'undefined';
	}

	var defaultEmptyOK = false;
	Bam.isInteger = function(reference)
	{
		var s = reference.value;
		var i;

	    if (Bam.empty(s))
	       if (Bam.isInteger.arguments.length == 1) return defaultEmptyOK;
	       else return (Bam.isInteger.arguments[1] == true);

	    for (i = 0; i < s.length; i++) {
	    	// Check that current character is number.
			var c = s.charAt(i);

			if (!Bam.isDigit(c)){
				return false;
			}
		}
		return true;
	}

	Bam.turnOff = function(DivID)
	{
		$('#' + DivID).hide();
		/*if (document.getElementById) { //gecko(NN6) & IE 5+
			document.getElementById(DivID).style.visibility = "hidden";
			document.getElementById(DivID).style.display = "none";
		} else if (document.all) { // IE 4+
			document.all[DivID].style.visibility = "hidden";
			document.all[DivID].style.display = "none";
		} else if (document.layers) { // NS4+
			document.layers[DivID].visibility = "hide";
			document.layers[DivID].display = "none";
		} else {
			// nothing
		}*/
	}

	Bam.turnOn = function(DivID)
	{
		$('#' + DivID).show();
		/*if (document.getElementById) { //gecko(NN6) & IE 5+
			document.getElementById(DivID).style.visibility = "visible";
			document.getElementById(DivID).style.display = "block";
		} else if (document.all) { // IE 4+
			document.all[DivID].style.visibility = "visible";
			document.all[DivID].style.display = "block";
		} else if (document.layers) { // NS4+
			document.layers[DivID].visibility = "show";
			document.layers[DivID].display = "block";
		} else {
			// nothing
		}*/
	}

	Bam.bookmark = function(url, who)
	{
		var url = url;
		if (!who) var who = "Circle of Responsibility";

		var ver = navigator.appName
		var num = parseInt(navigator.appVersion)
		if ((navigator.platform.indexOf("Win32") != -1) && (ver == "Microsoft Internet Explorer") && (num >= 4)) 
		{
	   		window.external.AddFavorite(url,who);
		}
		else if (navigator.platform=="MacPPC" || navigator.platform=="Mac68K")
		{
			alert("Press Command and D together to bookmark this page.");
		}
		else
		{
	   		alert("Press Control and D together to bookmark this page.");
		} 
	}

	Bam.switchMap = function(newmap)
	{
		if (document.getElementById) {
			var mapref = document.getElementById("map");
		} else if (document.all) {
			var mapref = document.all["map"];
		} else {
			// nothing
		}
		mapref.src = "/images/maps/"+newmap+".jpg";

	}


	Bam.togglePromoContent = function()
	{
		//console.log('togglePromoContent');
		// if ($('#promo-box-lightbox').length)
		// {
			// $(document).on('click', 'a.more:not(#show-menu)', function(ev) {
				// ev.preventDefault();

				// var el = $(this);
				// var parent = el.parent();
				
				// //console.log($(this).html());

				// parent.siblings('.info:not(.attachment)').toggle();

				// if (parent.siblings('.info').css('display') == 'block')
				// {
					// el.html('Hide'); 
				// }
				// else
				// {
					// el.html('Show more'); 
				// }
			// });
		// }
		$(document).on('click', 'a.more.more-with-id', function(ev) {
			var el = $(this);
			var parent = el.parent();
			var id = $(this).attr('id');
			
			if( typeof id != 'undefined' && id != null )
			{
				ev.preventDefault();
				
				var more_content = $('.' + id.replace('show-', ''));
				more_content.toggle();

				if(el.html() == 'Show more')
				{
					el.html('Hide'); 
				}
				else
				{
					el.html('Show more'); 
				}
			}
		});
		
		$(document).on('click', 'a.more.more-cafe-description', function(ev) {
			var el = $(this);
			var parent = el.parent();
			var id = $(this).attr('id');
			
			if( el.hasClass('open') )
			{
				parent.siblings('.more-description').slideToggle(500, function() {
					el.removeClass('open').text("More");
				});
			}
			else
			{
				parent.siblings('.more-description').slideToggle(500, function() {
					el.addClass('open').text("Less");
				});
			}
		});
	}
	
	
	Bam.updateCommentsForm = function( cafe_name )
	{
		var cafes = typeof CAFES_EMAILREQUIRED != 'undefined' ? $.parseJSON(CAFES_EMAILREQUIRED) : {};
		cafe_name = cafe_name || false;
		
		var td = $('#right .inner .padding .content .three-quarters .padding.account-cafe').find('form table tr:last-child td:first-child');
		if( td.length > 0 )
		{
			td.html(td.html().replace('<strong>*Required fields</strong>', '<strong>* required field</strong>'));
		}
		
		var $form = $('#right .inner .padding .content .three-quarters .padding.account-cafe').find('form:not([name=SafeBuck]):not([name=GiftCards])');
		$form.find('table tr td:first-child').css('width', 120);
		$form.find('input[name="required[email]"]').remove();
		
		var $td = $form.find('table tr:last-child td:first-child');
		//console.log($form.html());
		if( $td.length > 0 )
		{
			if( cafe_name != false )
			{
				//var td_html = $td.html().indexOf('Required fields') != -1 ? '<strong>* required field</strong>' : '<strong>&nbsp;</strong>';
				var td_html = '<strong>&nbsp;</strong>';
				var $td_email = false;
				$.each($form.find('table tr td:first-child'), function(k, v) {
					if( $(v).html().indexOf('E-Mail:') != -1 )
					{
						$td_email = $(v);
						$(v).html('<strong>E-Mail: </strong>');
					}
					else
					{
						$(v).html($(v).html().replace('* ', '').replace('*', ''));
					}
				});
				
				$.each(cafes, function(cafe, email_required) {
					if( cafe == cafe_name && email_required )
					{
						$form.append('<input type="hidden" name="required[email]" value="trim|valid_email" />');
						$td_email != false && $td_email.html('<strong>*E-Mail: </strong>');
						
						td_html = '<strong>* required field</strong>';
						return;
					}
				});
				$td.html(td_html);
			}
		}
	}
	
	return Bam;
	
})();

$(document).ready( function() {
	Bamco.initBamco();
	
	//deleteCookie('timezone');
	if( getCookie('timezone') == null )
	{
		//console.log('cookie does not exist');
		var visitortime = new Date();
		//var visitortimezone = "UTC " + -visitortime.getTimezoneOffset() / 60;
		var timezoneoffset = visitortime.getTimezoneOffset() / 60;
		if( visitortime.toString().indexOf('GMT Daylight Time') != -1 )
		{
			timezoneoffset += 1;
		}
		$.ajax({
			type: "GET",
			url: "/timezone.php",
			data: 'offset='+ timezoneoffset,
			success: function(data) {
				//console.log(data);
				//location.reload();
			}
		});
	}

});