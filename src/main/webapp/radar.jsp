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
	<%List<HashMap<String, String>> teamBars = DatabaseManager.get().getGameScoutingRepository().getPropsAvarage();%>
		var ctx = document.getElementById('myChart').getContext('2d');
		var myChart = new Chart(ctx,
				{
					type : 'radar',
					data : {
						labels : [<%for(int key = 0; key < DatabaseManager.get().getGameScoutingPropsRepository().getPropNameInHebrew()
						.keySet().size(); key++){%><%="'"+DatabaseManager.get().getGameScoutingPropsRepository().getPropNameInHebrew().get(key)+"', "%><%}%>],
						datasets : [
							<%for(HashMap<String, String> teamBar : teamBars){%>
								{
									label : '<%=teamBar.get("teamId")%>',
									fill: true, 
									data : [
									<%try{
										for (int i = 0; i < teamBar.size(); i++) {
											String value = teamBar.get(String.valueOf(i)).equals("text")? "0" : teamBar.get(String.valueOf(i));
									%>
									<%= " ,"+ (value.contains("|") ? Double.parseDouble(value.substring(value.indexOf("|") + 1, value.indexOf("%")))/100 : value)%>
									<%}}catch(Exception e){}%>],
									borderColor : [
										getRandomColor()
									],
									backgroundColor : [getRandomColor()], 
									borderWidth : 2
								},
								<%}%>
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