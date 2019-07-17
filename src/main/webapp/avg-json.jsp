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
					var second = b[propId].substring(b[propId].indexOf("|") + 2, b[propId].length - 1);
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
			document.getElementsByTagName("tbody")[0].childNodes.forEach(tr => {
				if (tr.tagName == "TR") {
					var j = 0;
					tr.childNodes.forEach(td => {
						if (td.className != "header") {
							td.textContent = avgs[i][j];
							var tooltip = document.createElement("span");
							tooltip.className = "tooltiptext";
							tooltip.textContent = avgs[i].teamId;
							td.appendChild(tooltip);
							j++;
						} else {
							td.textContent = avgs[i].teamId;
						}
					});
					i = i + 1;
				}
			});

			document.getElementsByTagName("thead")[0].childNodes.forEach(tr => {
				tr.childNodes.forEach(th => {
					if (th.tagName == "TH") {
						if (th.id == propId) {
							th.childNodes[0].className = "selected-th";
						} else {
							th.childNodes[0].className = "not-selected-th";
						}
					}
				});
			});
			return false;
		}
	</script>
	<title>FirstForum</title>
</head>

<body class="avarage">
	<%@include file="header.jsp"%>

	<table class="container">
		<thead>
			<tr class="header">
				<td>מספר קבוצה</td>
				<%
				SortedSet<Integer> sortedKeys = new TreeSet<Integer>(
						DatabaseManager.get().getGameScoutingPropsRepository().getPropNameInHebrew().keySet());

				for (Integer propId : sortedKeys) {
					%>
				<th id="<%=propId%>"><button style="color: #b4b4b4;"
						onclick="orderBy('<%=propId%>')"><%=DatabaseManager.get().getGameScoutingPropsRepository().getPropNameInHebrew().get(propId)%></button>
				</th>
				<%
		}
		%>
			</tr>
		</thead>
		<tbody>
		</tbody>
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
					td.className = "tooltip";
					var value = document.createTextNode(team[prop]);
					var tooltip = document.createElement("span");
					tooltip.className = "tooltiptext";
					tooltip.textContent = teamTd.textContent;
					td.appendChild(value);
					td.appendChild(tooltip);
					tr.appendChild(td);
				}
			}
			document.getElementsByTagName("tbody")[0].appendChild(tr);
		});
	</script>
</body>

</html>