<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="scouting.DatabaseManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
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
	<title>FirstForum</title>
	<link rel="stylesheet" type="text/css" href="style.css" />
	<style>
		h1 {
			text-align: center;
			text-decoration: underline;
			font-size: 2.8em;
		}

		#myChart {}

		.div-container {
			background-color: #1b1b1b;
			border-radius: 7px;
			padding: 30px;
			width: 1050px;
			height: 555px;
			margin: 120px auto;
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
		function getRandomColor() {
			var letters = '0123456789A';
			var color = '#';
			for (var i = 0; i < 6; i++) {
				color += letters[Math.floor(Math.random() * 11)];
			}
			return color;
		}
	</script>
	<script>
		var ctx = document.getElementById('myChart').getContext('2d');
		var myChart = new Chart(ctx,
			{
				type: 'line',
				data: {
					labels: <%=gameNumbers%>,
				datasets: [
						<%for(String propId: keySet) {
					if (!propId.equals("teamId") && !propId.equals("gameId")
						&& DatabaseManager.get().getGameScoutingPropsRepository()
							.getEntityByPropId(Integer.parseInt(propId)).getPropType().equals("number")) {%>
								{
									label: '<%=DatabaseManager.get().getGameScoutingPropsRepository().getPropNameInHebrew().get(Integer.parseInt(propId)) %> ',
									fill: false,
									data: [<%for(int i = 0; i<gameNumbers.size(); i++) {%>
									<%=gameBars.get(i).get(propId) + ", " %>
									<%}%>],

		borderColor: [
			getRandomColor(),
		],
			borderWidth : 4
		},
								<%}
			}%>
		]
	},
		options: {
			title: {
				display: true,
					text: 'קבוצה מספר <%=gameBars.get(0).get("teamId")%>',
						fontSize: 45,
							fontFamily: "tahoma",
	        },
			legend: {
				labels: {
					fontColor: "#b4b4b4",
						fontSize: 15,
							fontFamily: "tahoma"
				},
			},
			scales: {
				xAxes: [{
					ticks: {
						fontColor: "#b4b4b4",
						fontSize: 20,
						fontFamily: "tahoma"
					},
					gridLines: {
						color: "#b4b4b4",
					},
				}],
					yAxes: [{
						ticks: {
							fontColor: "#b4b4b4",
							fontSize: 20,
							fontFamily: "tahoma",
							beginAtZero: true,
						},
						gridLines: { color: "#b4b4b4" }
					}]
			}

		}
				});
	</script>

</body>

</html>