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
		<tr class="header">
			<%
				for (HashMap<String, String> teamBar : DatabaseManager.get().getGameScoutingRepository()
						.getPropsAvarage()) {
					for (String propId : teamBar.keySet()) {
			%>
			<td class="tooltip"><%=teamBar.get(propId)%>
				<span class="tooltiptext"><%=teamBar.get("teamId") %></span></td>

			<%
				}
				}
			%>
		</tr>
	</table>
</body>

</html>