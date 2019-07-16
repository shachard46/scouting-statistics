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
<title>FirstForum</title>
</head>

<body class="avarage">
	<%@include file="header.jsp"%>
	
	<table class="container">
	<tr class="header" >
			<td>מספר קבוצה</td>
			<%
	        	SortedSet<Integer> sortedKeys = new TreeSet<Integer>(DatabaseManager.get().getGameScoutingPropsRepository().getPropNameInHebrew()
						.keySet());
				
				for (Integer propId : sortedKeys) {
			%>
			<td><a href="avg.jsp?orderBy=<%= propId %>"><%=DatabaseManager.get().getGameScoutingPropsRepository().getPropNameInHebrew().get(propId)%></a></td>
			<%
				}
			%>
		</tr>
		<%
			for (HashMap<String, String> teamBar : DatabaseManager.get().getGameScoutingRepository()
					.getPropsAvarage()) {
		%>
		<tr>
			<td class="tooltip header"><%=teamBar.get("teamId")%></td>
			<%
				for (Integer propId : sortedKeys) {
			%>
			<td class="tooltip"><%=teamBar.get(String.valueOf(propId))%> <span
				class="tooltiptext"><%=teamBar.get("teamId")%></span></td>

			<%
				}
			%>

		</tr>
		<%
			}
		%>

	</table>
</body>

</html>