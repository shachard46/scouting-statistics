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
	<script src="functions.js"></script>
	<script type="text/javascript">
		var avgs = <%=JSONObject.toJSONString(m) %>;
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
						onclick="orderBy(avgs, '<%=propId%>')"><%=DatabaseManager.get().getGameScoutingPropsRepository().getPropNameInHebrew().get(propId)%></button>
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
		createAvgsTable(avgs);
	</script>
</body>

</html>