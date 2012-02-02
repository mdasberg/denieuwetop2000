var register_form = "#userForm";
var register_cancel = "#cancel";
var register_login = "#login";
var register_email = "#email";
var register_send = "#send";
var register_register = "#register";
var register_password = "#password";
var register_password_verify = "#verifyPassword";
var register_password_verify_info = "#verifyPasswordInfo";
var register_error_info = "#registrationError";

function RegisterModel() {
	this.emailError = ko.observable("");
	this.passwordFormatError = ko.observable("");
	this.passwordEqualError = ko.observable("");
	
	this.toggleLogin = function() {
        $(register_form).css("top", "90px");
        $(register_form).css("left", $("#menu").width() - 100 + 'px');        
        $(register_form).toggle();
    };
    this.toggleRegistration = function() {
        $(register_password_verify_info).toggle();
        $(register_cancel).toggle();
        $(register_send).toggle();
        $(register_register).toggle();
        $(register_login).toggle();
    };
    this.sendRegistration = function() {
    	if (!validEmail()) {
    		this.emailError("Geen correct email adres.");
    	}
        if ($(register_password).val().length < 5) {
        	this.passwordFormatError("Paswoord is te kort. Minimaal 5 karakters.");
        } 
        if ($(register_password).val() != $(register_password_verify).val()) {
        	this.passwordEqualError("Paswoord velden niet gelijk.");
        }
        if (this.emailError() == "" && this.passwordEqualError() == "" && this.passwordFormatError() == "") {
        	// Make the ajax call.
        	alert("Send registration");
		}
    };
    this.clearErrors = function() {
    	this.emailError("");
    	this.passwordFormatError("");
    	this.passwordEqualError("");
    };
    this.cancel = function() {
    	this.clearErrors();
        if ($(register_send).is(":visible")) {
        	this.toggleRegistration();
        } else {
        	this.toggleLogin();
        }
    };  
}