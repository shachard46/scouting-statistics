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
	<script src="https://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.9.1/underscore-min.js"></script>
	<script src="functions.js"></script>
	<script type="text/javascript">
		var avgs = <%=JSONObject.toJSONString(m) %>;
		avgs = avgs.props;
	</script>
	<title>ScoutingStatictics</title>
</head>

<body class="avarage">
	<%@include file="header.jsp"%>

	<table class="container">
		<thead>
			<tr class="header">
				<td>מספר קבוצה</td>
				<%
				SortedSet<Integer> sortedKeys = new TreeSet<Integer>(
						GameScoutingPropsRepository.getPropNameInHebrew().keySet());

				for (Integer propId : sortedKeys) {
					%>
				<th id="<%=propId%>"><button style="color: #b4b4b4;"
						onclick="orderBy(avgs, '<%=propId%>')"><%=GameScoutingPropsRepository.getPropNameInHebrew().get(propId)%></button>
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