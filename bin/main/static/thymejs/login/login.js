function validation() {
	var login = $("#login").val();
	var password = $("#password").val();

	if (login == "" || login == null || login == undefined) {
		errorMessage("error","User Name is required","Required Field"); 
		return false;
	}

	if (password == "" || password == null || password == undefined) {
		errorMessage("error","Password is required","Required Field");
		return false;
	}

	return true;
}

function login(){ 
	if(validation()){
		document.loginForm.method="post";
		document.loginForm.action=contextPath +"/login";
		document.loginForm.submit(); 
	}
}


