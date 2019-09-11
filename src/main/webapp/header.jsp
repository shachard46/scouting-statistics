<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="scouting.*"%>
<!DOCTYPE html>
<script>
  function displayByClassName(className, display) {
    document.getElementsByClassName(className)[0].style.display = display;
  }
  function displayById(id, display) {
    document.getElementById(id).style.display = display;
  }
</script>
<div class="menuBar">
  <div class="menuBarOption search">
    <form action="oneTeam.jsp">
      <button type="submit">
        <img src="img/search.PNG" />
      </button>
      <input type="text" placeholder="Search team stats" name="team" />
    </form>
  </div>

  <%
		if (request.getQueryString() != null && request.getQueryString().contains("team")) {
	%>
  <div class="menuBarOption avg">
    <a href="averages.jsp">Averages</a>
  </div>

  <div class="menuBarOption avg">
    <a href='graph.jsp?team=<%=request.getParameter("team")%>'>Graph</a>
  </div>
  <%
		} else {
	%>
  <%
		if (!request.getRequestURI().equals("/averages.jsp")) {
	%>
  <div class="menuBarOption double-avg">
    <a href="averages.jsp">Averages</a>
  </div>
  <%
		} else {
	%>
  <div class="menuBarOption search">
    <button onclick="createAvgsTable(filterByTeamNumbers(avgs))">
      <img src="img/search.PNG" />
    </button>
    <input type="text" placeholder="Filter teams" name="filterTeams" />
  </div>
  <%
		}
	%>
  <%
		}
	%>
  <div class="menuBarOption search">
    <form action="pitScouting.jsp">
      <input type="text" placeholder="Search team pit stats" name="team" />
      <button type="submit">
        <img src="img/search.PNG" />
      </button>
    </form>
  </div>
  <%@include file="dropdown.html"%>
</div>
<!-- 
    
 -->