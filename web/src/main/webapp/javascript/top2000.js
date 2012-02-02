/*
 * Knockout config.  
 */
document.write('<script type="text/javascript" src="javascript/registerModel.js"></script>');

$(document).ready(function(){
	ko.applyBindings(new RegisterModel());
});



/*
 * Common functions
 */
function validEmail() {
	var emailRegex = '^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$';
	return $(register_email).val().toUpperCase().match(emailRegex) == null ? false : true;
}

