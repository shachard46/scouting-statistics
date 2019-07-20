<%@page import="java.util.TreeSet"%>
<%@page import="java.util.SortedSet"%>
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
	<title>ScoutingStatictics</title>
</head>

<body class="avarage">
	<%@include file="header.jsp"%>

	<table class="container">
		<thead>
			<tr class="header">
				<td>מספר קבוצה</td>
				<td>מספר משחק</td>
				<%
					SortedSet<Integer> sortedKeys = new TreeSet<Integer>(DatabaseManager.get().getGameScoutingPropsRepository().getPropNameInHebrew()
							.keySet());
					
					for (Integer propId : sortedKeys) {
				%>
				<td><%=DatabaseManager.get().getGameScoutingPropsRepository().getPropNameInHebrew().get(propId)%></td>
				<%
					}
				%>
			</tr>
		</thead>
		<tbody>
			<%
			for (HashMap<String, String> gameBar : DatabaseManager.get().getGameScoutingRepository()
					.getTeamPropsByGame(Integer.parseInt(request.getParameter("team")))) {
		%><tr>
				<td class="tooltip header"><%=gameBar.get("teamId")%></td>
				<td class="tooltip header"><%=gameBar.get("gameId")%></td>
				<%
				for (Integer propId : sortedKeys) {
			%>
				<td class="tooltip">
					<%=gameBar.get(String.valueOf(propId)) == null ? "ריק" : gameBar.get(String.valueOf(propId))%> <span
						class="tooltiptext"><%=gameBar.get("gameId")%></span>
				</td>

				<%
				}
				}
			%>
			</tr>
		</tbody>
	</table>
</body>

</html>