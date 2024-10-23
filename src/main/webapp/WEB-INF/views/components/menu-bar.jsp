<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="title" data-title-container>
	<div id="logo" tabindex="0"><img src="${pageContext.request.contextPath}/img/global/nhl-logo.png"></div>
	<div id="headline"><p>NHL</p></div>
</div>

<div id="menubar">

	<div id="menu-season-selector">
		<select id="season" data-season-selector name="season">
			<c:forEach items="${seasons}" var="s">
				<c:choose>
					<c:when test="${s == selectedSeason}"> <option selected> ${s} </option>	</c:when>
					<c:otherwise> <option> ${s} </option> </c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
	</div>
	
	<div id="main-menu-nav" data-menu-nav-container>
		<button>Home</button>
		<button>Statistics</button>
		<button>TODO</button>
	</div>
	
	<div id="menu-user">
		<div><img src=""></div>
	</div>
	
</div>