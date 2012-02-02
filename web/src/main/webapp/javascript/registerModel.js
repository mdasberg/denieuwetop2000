var register_form = "#userForm";
var register_cancel = "#cancel";
var register_login = "#login";
var register_email = "#email";
var register_send = "#send";
var register_register = "#register";
var register_result = "#registrationResult";
var register_password = "#password";
var register_password_verify = "#verifyPassword";
var register_password_verify_info = "#verifyPasswordInfo";
var register_error_info = "#registrationError";

function RegisterModel() {
	this.email = ko.observable("");
	this.password = ko.observable("");
	this.emailError = ko.observable(false);
	this.passwordFormatError = ko.observable(false);
	this.passwordEqualError = ko.observable(false);
	
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
    		this.emailError(true);
    	}
        if ($(register_password).val().length < 5) {
        	this.passwordFormatError(true);
        } 
        if ($(register_password).val() != $(register_password_verify).val()) {
        	this.passwordEqualError(true);
        }
        if (this.emailError() == "" && this.passwordEqualError() == "" && this.passwordFormatError() == "") {
        	$(register_result).show();
			$(register_result).html("Even geduld...");
        	$(register_send).attr("disabled", "true");
        	$.ajax({
        		url: "rest/register/new",
        		type: "post",
        		data: "email=" + this.email() + "&password=" + this.password(),
        		contentType: "text/html",
        		success: function(result) {
        			$(register_result).html(result);
        		}
        	});
		}
    };
    this.clearErrors = function() {
    	this.emailError(false);
    	this.passwordFormatError(false);
    	this.passwordEqualError(false);
    };
    this.cancel = function() {
    	$(register_result).hide();
    	this.clearErrors();
        if ($(register_send).is(":visible")) {
        	this.toggleRegistration();
        } else {
        	this.toggleLogin();
        }
    };  
}