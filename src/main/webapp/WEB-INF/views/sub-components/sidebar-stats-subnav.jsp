<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<%--
	@modelObject - SeasonScope seasonScope - season scope enum
--%>

<div id="seasonScope">
	<c:choose>
		<c:when test="${seasonScope.type=='Regulation'}">
			<input type="radio" id="regulation"	name="seasonScope" 
					data-radio-scope="season" data-radio-value="regulation" checked disabled />
		</c:when>
		<c:otherwise>
			<input type="radio" id="regulation" name="seasonScope" 
					data-radio-scope="season" data-radio-value="regulation" />
		</c:otherwise>
	</c:choose>
	<label for="regulation" tabindex="0" data-subnav-label>Regulation</label>

	<c:choose>
		<c:when test="${seasonScope.type=='Playoff'}">
			<input type="radio" id="playoff" name="seasonScope" 
					data-radio-scope="season" data-radio-value="playoff" checked disabled />
		</c:when>
		<c:otherwise>
			<input type="radio" id="playoff" name="seasonScope" 
					data-radio-scope="season" data-radio-value="playoff" />
		</c:otherwise>
	</c:choose>
	<label for="playoff" tabindex="0" data-subnav-label>Playoff</label>
</div>
