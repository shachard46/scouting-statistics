<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="scouting.*"%>
<%
	Map<String, Object> m = new HashMap<String, Object>();
	m.put("props", DatabaseManager.get().getGameScoutingRepository().getPropsAvarage());
%>
<html>

<head>
	<link rel="stylesheet" type="text/css" href="style.css" />
	<script type="text/javascript">
		function orderBy(propId) {
			var avgs = <%=JSONObject.toJSONString(m) %>;
			avgs = avgs.props;
			if (avgs[0][propId][avgs[0][propId].length - 1] === "%") {
				avgs.sort((a, b) => {
					var first = a[propId].substring(a[propId].indexOf("|") + 2, a[propId].length - 1);
					console.log("f " + first);
					var second = b[propId].substring(b[propId].indexOf("|") + 2, b[propId].length - 1);
					console.log("s " + second);
					if (Number(first) > Number(second)) {
						return -1;
					}
					if (Number(first) < Number(second)) {
						return 1;
					}
					return 0;
				});
				avgs.forEach(element => {
				});
			} else {
				avgs.sort((a, b) => (a[propId] > b[propId]) ? -1 : ((b[propId] > a[propId]) ? 1 : 0));
			}
			var i = 0;
			document.getElementsByClassName("container")[0].childNodes.forEach(tr => {
				if (tr.tagName == "TR") {
					if (tr.className != "header") {
						var j = 0;
						tr.childNodes.forEach(td => {
							if (td.className != "header") {
								td.textContent = avgs[i][j];
								j++;
							} else {
								td.textContent = avgs[i].teamId;
							}
						});
						i = i + 1;
					}
				}
			});
			return false;
		}
	</script>
	<title>FirstForum</title>
</head>

<body class="avarage">
	<%@include file="header.jsp"%>

	<table class="container">
		<tr class="header">
			<td>מספר קבוצה</td>
			<%
				SortedSet<Integer> sortedKeys = new TreeSet<Integer>(
						DatabaseManager.get().getGameScoutingPropsRepository().getPropNameInHebrew().keySet());

				for (Integer propId : sortedKeys) {
			%>
			<td><button
					onclick="orderBy('<%=propId%>')"><%=DatabaseManager.get().getGameScoutingPropsRepository().getPropNameInHebrew().get(propId)%></button>
			</td>
			<%
				}
			%>
		</tr>
	</table>
	<script>
		var avgs = <%=JSONObject.toJSONString(m) %>;
		avgs = avgs.props;
		avgs.sort((a, b) => (a.teamId > b.teamId) ? 1 : ((b.teamId > a.teamId) ? -1 : 0));
		avgs.forEach(team => {
			var tr = document.createElement("tr");
			var teamTd = document.createElement("td");
			teamTd.className = "header";
			var value = document.createTextNode(team.teamId);
			teamTd.appendChild(value);
			tr.appendChild(teamTd);
			for (var prop in team) {
				if (prop != "teamId") {
					var td = document.createElement("td");
					var value = document.createTextNode(team[prop]);
					td.appendChild(value);
					tr.appendChild(td);
				}
			}
			document.getElementsByClassName("container")[0].appendChild(tr);
		});
	</script>
</body>

</html>