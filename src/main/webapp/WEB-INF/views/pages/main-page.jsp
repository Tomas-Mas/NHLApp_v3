<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/global/root.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/component/menubar.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/page/main-page.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/component/main-page-games-tbl.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/component/sidebar-stats-standings.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/component/sidebar-playoff-spider.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/component/game-keyevents-td.css">
	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sub-component/sidebar-stats-subnav.css">

	<script src="${pageContext.request.contextPath}/js/page/main-page.js"></script>
	<script src="${pageContext.request.contextPath}/js/global/navigation.js"></script>
	<script src="${pageContext.request.contextPath}/js/component/menubar.js"></script>

	<title>NHL</title>
</head>

<body>

	<header data-page-header></header>
	
	<main>
		<div id="game-list-section" data-game-list-section></div>
		<div id="stats-section" data-stats-section></div>
	</main>

</body>
</html>