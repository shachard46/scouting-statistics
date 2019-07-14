<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="scouting.DatabaseManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>

<head>
<!-- <link rel="stylesheet" type="text/css" href="style.css" /> -->
<script
	src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0/dist/Chart.min.js"></script>
<title>FirstForum</title>
</head>

<body>
	<div style="width: 500px; height: 500px;">
		<canvas id="myChart" width="400" height="400"></canvas>
	</div>
	<script>
	function getRandomColor() {
		  var letters = '0123456789ABCDEF';
		  var color = '#';
		  for (var i = 0; i < 6; i++) {
		    color += letters[Math.floor(Math.random() * 16)];
		  }
		  return color;
		}
	</script>
	<script>
	<%List<HashMap<String, String>> gameBars = DatabaseManager.get().getGameScoutingRepository()
					.getTeamPropsByGame(Integer.parseInt(request.getParameter("team")));%>
		var ctx = document.getElementById('myChart').getContext('2d');
		var myChart = new Chart(ctx,
				{
					type : 'line',
					data : {
						labels : <%=DatabaseManager.get().getGameScoutingRepository().getAllTeamGameNumbers(Integer.parseInt(request.getParameter("team")))%>,
						datasets : [
						<%for (String propId : gameBars.get(0).keySet()) {
							if(!propId.equals("teamId") && !propId.equals("gameId")){
						%>
								{
									label : '<%=propId%>',
									fill: false, 
									data : [<%for (int i = 0; i < 3; i++) {%>
									<%=gameBars.get(i).get(propId) + ", "%>
									<%}%>],
									borderColor : [
							        		getRandomColor(),
									],
									borderWidth : 1
								},
								<%}}%>
]
					},
					options : {
						scales : {
							yAxes : [ {
								ticks : {
									beginAtZero : true
								}
							} ]
						}
					}
				});
	</script>

</body>
</html>