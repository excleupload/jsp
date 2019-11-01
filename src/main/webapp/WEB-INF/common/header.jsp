<header class='main-header headroom'> 
	<div class='inside-header' >
		<a href='dashboard.html' class='logo'>
			<b class='logo-mini'>
				<span class='light-logo'><img src="${pageContext.request.contextPath}/assets/custom/image/e5logo.png" alt='logo' class='imglogo'></span>
<%-- 				<span class='dark-logo'><img src="${pageContext.request.contextPath}/assets/custom/image/e5logo.png" alt='logo'></span> --%>
			</b>
		</a>
		<nav class='navbar navbar-static-top'>
			<a href='#' class='sidebar-toggle d-block d-lg-none' data-toggle='push-menu' role='button'>
				<span class='sr-only'>Toggle navigation</span>
			</a>
			<ul class='navbar-nav mr-auto mt-md-0'>						
			</ul>
			<div class='navbar-custom-menu'>
				<ul class='nav navbar-nav'>		
					<li class='dropdown user user-menu'>
                        <img src="${pageContext.request.contextPath}/assets/custom/image/user.png" class='user-image rounded-circle' alt='User Image'>
						<span class='header-name ml-2'>${session.userData.login}</span>

					</li>
					<li class='dropdown notifications-menu'>
					<a href='/salesupload/logout'>
							<i class='fa fa-sign-out text-black-50 font-size-26'></i>
						</a>
					</li>
				</ul>
			</div>
		</nav>	
	</div>
</header>