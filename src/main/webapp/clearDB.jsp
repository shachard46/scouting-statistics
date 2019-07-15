<%@page import="scouting.DatabaseManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	if (request.getParameter("password").equals("ScouterDagan")) {
		DatabaseManager.get().getGameScoutingRepository().clearTable();
	} else {
%>
<script type="text/javascript">
	alert("you were wrong, you are stupid");
	location.href = "avg.jsp";
</script>
<%
	}
%>