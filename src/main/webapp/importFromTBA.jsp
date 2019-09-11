<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="scouting.*"%>
<%
	DatabaseManager.get().getEventMatchRepository().importFromTBA(request.getParameter("comp-name"));
%>