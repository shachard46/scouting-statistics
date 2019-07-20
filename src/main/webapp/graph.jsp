<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.util.*"%>
<%@page import="scouting.DatabaseManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Map<String, Object> m = new HashMap<String, Object>();
	Map<String, Object> props = new HashMap<String, Object>();
	m.put("props", DatabaseManager.get().getGameScoutingRepository()
			.getTeamPropsByGame(Integer.parseInt(request.getParameter("team"))));
	props.put("props", DatabaseManager.get().getGameScoutingPropsRepository().getPropsAsMap());
	
	List<HashMap<String, String>> gameBars = DatabaseManager.get().getGameScoutingRepository()
			.getTeamPropsByGame(Integer.parseInt(request.getParameter("team")));
	
	List<Integer> gameNumbers = DatabaseManager.get().getGameScoutingRepository()
			.getAllTeamGameNumbers(Integer.parseInt(request.getParameter("team")));
	Set<String> keySet = DatabaseManager.get().getGameScoutingRepository().getPropsAvarage().get(0).keySet();
%>
<html>

<head>
	<!-- <link rel="stylesheet" type="text/css" href="style.css" /> -->
	<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0/dist/Chart.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.9.1/underscore-min.js"></script>
	<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
	<script src="functions.js"></script>
	<title>ScoutingStatictics</title>
	<link rel="stylesheet" type="text/css" href="style.css" />
	<style>
		.div-container {
			position: absolute;
			background-color: #1b1b1b;
			border-radius: 7px;
			padding: 30px;
			width: unset;
			/*1050px;*/
			height: 555px;
			top: 120;
			left: 150;
			right: 150;
			text-align: center;
		}
	</style>
</head>

<body>
	<%@include file="header.jsp" %>
	<div class="div-container">
		<canvas id="myChart"></canvas>
	</div>
	<script>

	</script>
	<script>
		let games = <%=JSONObject.toJSONString(m) %>.props;
		let props = <%=JSONObject.toJSONString(props) %>.props;
		games = games.map(game => _.pick(game, (value, key) => props[key] && props[key].propType === "number" || key === "teamId"));
		props = _.pick(props, (value, key) => props[key] && props[key].propType === "number");
		createGraph(<%=gameNumbers %>, props, games);
	</script>

</body>

</html>