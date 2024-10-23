<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--
	@modelObject - List<GamePeriodKeyEventsDTO> gameEvents - game events formated by each period
--%>

<td class="matchData">
	<c:forEach items="${gameEvents}" var="periodEvents" varStatus="loop">
		<div class="periodHeader">
			<div> ${periodEvents.periodNumber}. period</div>
			<div> ${periodEvents.periodScore} </div>
		</div>
		<c:forEach items="${periodEvents.events}" var="event">
			<div class="eventLine${event.actedBy.type}">
				<c:choose>
					<c:when test="${event.name == 'Goal'}" >
						<div class="periodEventTime">${event.periodTime}</div>
						<div class="periodEventIcon"><img src="${pageContext.request.contextPath}/img/game-detail-icons/goal-icon.png" title="${event.secondaryType}"></div>
						<div class="periodEventMainPlayer">
							<a class="gameDetailPlayerName" tabindex="0" data-clickable-player="${event.mainActor.id}">
								${event.mainActor.firstName} ${event.mainActor.lastName}
							</a>
						</div>
						<c:if test="${event.strength != 'Even'}">
							<div class="periodEventStrength"> (${event.strength}) </div>
						</c:if>
						<div class="periodEventAssist">
							<c:set value="true" var="isFirst"/>
							<c:forEach items="${event.secondaryActors}" var="player">
								<c:choose>
									<c:when test="${isFirst}"> 
										${player.firstName} ${player.lastName} 
										<c:set value="false" var="isFirst"/>
									</c:when>
									<c:otherwise>
										+ ${player.firstName} ${player.lastName} 
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</div>
					</c:when>
					<c:otherwise>
						<div class="periodEventTime">${event.periodTime}</div>
						<div class="periodEventIcon">
							<img src="${pageContext.request.contextPath}/img/game-detail-icons/penalty${event.penaltyMinutes}.png" 
									title="${event.penaltyMinutes} minutes ${event.penaltySeverity} - ${event.secondaryType}">
						</div>
						<div class="periodEventMainPlayer">
							<a class="gameDetailPlayerName" tabindex="0" data-clickable-player="${event.mainActor.id}">
								${event.mainActor.firstName} ${event.mainActor.lastName}
							</a>
						</div>
						<div class="periodEventPenaltyType"> (${event.secondaryType})</div>
					</c:otherwise>
				</c:choose>
			</div>
		</c:forEach>
	</c:forEach>
</td>