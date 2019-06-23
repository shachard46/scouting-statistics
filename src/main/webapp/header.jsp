<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ page import="scouting.*"%>
<!DOCTYPE html>

<div class="menuBar">
    <div class="menuBarOption search">
        <form>
            <button type="submit"><img src="img/search.PNG" /></button>
            <input type="text" placeholder="Search team stats" name="team" />
        </form>
    </div>
    <div class="menuBarOption avg"><a href="avg.jsp">Avarages</a></div>
    <div class="menuBarOption avg"><a href="avg.jsp">Graphs</a></div>
    <div class="menuBarOption search">
        <form>
            <input type="text" placeholder="Search team pit stats" name="teamPit" />
            <button type="submit"><img src="img/search.PNG" /></button>
        </form>
    </div>
</div>