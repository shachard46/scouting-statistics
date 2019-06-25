<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="scouting.*"%>
<html>

<head>
<link rel="stylesheet" type="text/css" href="style.css" />
<%--
    <script src="functions.js"></script>
    --%>
<title>FirstForum</title>
</head>

<body class="avarage">
	<%@include file="header.jsp"%>

	<table class="container">
		<%
			for (HashMap<String, String> gameBar : DatabaseManager.get().getGameScoutingRepository()
					.getTeamPropsByGame(Integer.parseInt(request.getParameter("team")))) {
		%><tr class="header">
			<%
				for (String propId : gameBar.keySet()) {
			%>
			<td class="tooltip"><%=gameBar.get(propId)%> <span
				class="tooltiptext"><%=gameBar.get("teamId")%></span></td>

			<%
				}
				}
			%>
		</tr>
	</table>
</body>

</html>