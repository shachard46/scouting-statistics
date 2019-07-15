<%@page import="scouting.DatabaseManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String gameId = request.getParameter("gameId").substring(0, request.getParameter("gameId").indexOf(","));
	String compLevel = request.getParameter("gameId").substring(request.getParameter("gameId").indexOf(",") + 1,
			request.getParameter("gameId").charAt(request.getParameter("gameId").length()));
	DatabaseManager.get().getGameScoutingRepository().exportToTablet(gameId, compLevel);
	response.sendRedirect(request.getContextPath());
%>