<%@page import="java.util.HashMap"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="scouting.*"%>
<%
	if (request.getParameter("team").isEmpty()) {
		response.sendRedirect("averages.jsp");
		return;
	}

	Map<String, Object> avgs = new HashMap<String, Object>();
	Map<Integer, Map<String, String>> headers = DatabaseManager.get().getGameScoutingPropsRepository()
			.getPropsAsMap();
	avgs.put("props", DatabaseManager.get().getGameScoutingRepository()
			.getTeamPropsByGame(Integer.parseInt(request.getParameter("team"))));
%>
<html>

<head>
	<link rel="stylesheet" type="text/css" href="style.css" />
	<script src="https://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.9.1/underscore-min.js"></script>
	<script src="functions.js"></script>
	<script>
		var avgs = <%=JSONObject.toJSONString(avgs) %>;
		avgs = avgs.props;
		var headers =<%=JSONObject.toJSONString(headers) %>;
		addCombination(headers, avgs, [2, 6, 7], 7.5, "סה״כ כדורים", "number");
		addCombination(headers, avgs, [3, 4, 5, 10, 11, 9], 11.5, "סה״כ דיסקים", "number");
	</script>
	<title>ScoutingStatictics</title>
</head>

<body class="avarage">
	<%@include file="header.jsp"%>

	<table class="container">
		<tbody>

		</tbody>
	</table>
	<script>
		createStatisticsTable(avgs, true, false)
		createStatisticsHeaders(headers, true);
	</script>
</body>

</html>