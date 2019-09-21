<%@page import="scouting.DatabaseManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String gameId = request.getParameter("game_id").substring(0, request.getParameter("game_id").indexOf(","));
	String compLevel = request.getParameter("game_id").substring(request.getParameter("game_id").indexOf(",") + 1,
			request.getParameter("game_id").length());
	DatabaseManager.get().getGameScoutingRepository().exportToTablet(gameId, compLevel);
	response.sendRedirect("averages.jsp");
%>