<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@page import="scouting.*"%> 
<html>

<head>
    <link rel="stylesheet" type="text/css" href="style.css" />
    <%--
    <script src="functions.js"></script>
    --%>
    <title>FirstForum</title>
</head>

<body class="avarage">
    <%@include file="header.jsp"%>

    <table class="container">
        <tr class="header">
        <%for(int i = 0; i <= 5; i++){%>
            <td class="tooltip"><%=DatabaseManager.get().getGameScoutingRepository().getPropsAvarageByTeam().get(0).get(i)%> <span class="tooltiptext">2630</span></td>

        <%}%>
        </tr>
    </table>
</body>

</html>