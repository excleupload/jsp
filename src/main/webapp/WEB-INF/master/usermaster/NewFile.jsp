<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

     <!DOCTYPE html>
<html>

<div id='navbar' class='main-nav' >  		
	<nav class='navbar navbar-expand-lg'>
		<div class='collapse navbar-collapse' id='navbarNavDropdown'>
			<ul class='navbar-nav' id="menuListtest">
<%-- 	<th:block th:if="${session.menuList.size()>0}" > --%>
<%-- <c:if test="${session.menuList.size()>0}"> --%>
	
	<c:forEach items="${sessionScope.menuList}" var="item" >
		<%-- <c:out value="${item[0]}"/> --%>
		<li class='nav-item' id='${item[5]}' >
			<a class='nav-link'  href="${item[2]}">
			<span class='active-item-here'></span>
			<i class='fa fa-upload mr-5'></i> <span><c:out value="${item[1]}" /></span>
			</a>
		</li>  
	</c:forEach>
			</ul>
		</div>
	</nav> 
</div>
