<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="scouting.*"%>
<%
	Map<String, Object> props = new HashMap<String, Object>();
	props.put("props", DatabaseManager.get().getPitScoutingRepository()
			.getPitScoutingByTeam(Integer.parseInt(request.getParameter("team"))));
%>
<html>

<head>
<link rel="stylesheet" type="text/css" href="style.css" />
<script src="functions.js"></script>
<script type="text/javascript">
	var pitScouting = <%=JSONObject.toJSONString(props)%>;
</script>
<title>ScoutingStatictics</title>
</head>

<body class="avarage">
	<%@include file="header.jsp"%>

	<table class="container pit-container">

	</table>
	<script>
		createPitScoutingTable(pitScouting, <%=request.getParameter("team")%>);
	</script>
</body>

</html>