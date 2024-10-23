<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<%--
	@modelObject - List<GameBasicDataDTO games - list with basic game data
--%>

<!--id="headerRow${game.id}" id="dataRow${game.id}"  -->

<table id="games-table">	<!-- id=mainFinishedTableRegulation class=mainFinishedTable -->
	<c:forEach items="${games}" var="game">
		<tr class="clickableTr" data-expandable-row-header data-expandable-header-id="${game.id}" tabindex="0">
			<td>
				<table class="resultsHeader" data-expandable-table>
					<tr>
						<td rowspan="2" class="resultsHeaderDate">${game.gameDate}</td>
						<td class="resultsPics">
							<div class="teamPic">
								<img src="${pageContext.request.contextPath}/img/team-icons/${game.homeTeamAbr}.png"
									title="${game.homeTeamName}" alt="${game.homeTeamName}">
							</div>
						</td>
						<td class="resultsTeamName">${game.homeTeamName}</td>
						<td class="resultsScore">${game.homeScore}</td>
						<td rowspan="2" class="resultsDetail">${game.endPeriodType}</td>
						<c:forEach items="${game.homePeriodsScore}" var="score">
							<td class="resultsPeriodScore numeric">${score}</td>
						</c:forEach>
						<td rowspan="2" class="resultsButton">
							<button class="gamePageButton" data-game-btn="${game.id}">Game page
							</button>
						</td>
					</tr>
					<tr>
						<td class="resultsPics">
							<div class="teamPic">
								<img src="${pageContext.request.contextPath}/img/team-icons/${game.awayTeamAbr}.png"
									title="${game.awayTeamName}" alt="${game.awayTeamName}">
							</div>
						</td>
						<td class="resultsTeamName">${game.awayTeamName}</td>
						<td class="resultsScore">${game.awayScore}</td>
						<c:forEach items="${game.awayPeriodsScore}" var="score">
							<td class="resultsPeriodScore numeric">${score}</td>
						</c:forEach>
					</tr>
				</table>
			</td>
		</tr>

		<tr class="dataRow" data-expandable-content-id="${game.id}" style="display: none">

		</tr>
	</c:forEach>
</table>