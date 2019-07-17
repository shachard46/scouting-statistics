<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ page import="scouting.*"%>
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
            <button type="submit"><img src="img/search.PNG" /></button>
            <input type="text" placeholder="Search team stats" name="team" />
        </form>
    </div>
    <div class="menuBarOption avg"><a href="avg-json.jsp">Avarages</a></div>
    <div class="menuBarOption avg"><a href="avg-json.jsp">Graphs</a></div>
    <div class="menuBarOption search">
        <form action="oneTeam.jsp">
            <input type="text" placeholder="Search team pit stats" name="teamPit" />
            <button type="submit"><img src="img/search.PNG" /></button>
        </form>
    </div>
    <div class="dropdown menuBarOption">
        <div class="dropbtn" onmouseover="displayByClassName('dropright-content', 'none')"><img class="dropdown_img"
                src="img/dropdown2.png"></div>

        <div class="dropright">

            <div class="dropdown-content">
                <button form="export" type="submit" id="tablet-a" onmouseover="displayByClassName('dropright-content', 'block');
                        displayById('tablet-input', 'block');
                        displayById('comp-name', 'none');
                        displayById('password', 'none');">Export to Tablet</button>
                <button form="import" type="submit" onmouseover="document.getElementsByClassName('dropright-content')[0].style.display = 'block';
                        displayById('tablet-input', 'none'); 
                        displayById('comp-name', 'block');
                        displayById('password', 'none');">Import from TBA</button>
                <button form="clear" type="submit" onmouseover="
                        displayById('tablet-input', 'none'); 
                        displayById('comp-name', 'none');
                        displayById('password', 'block');">Clear DB</a>
            </div>
            <div class="dropright-content" onmouseout="displayByClassName('dropright-content', 'none')">
                <form id="export" action="exportToTablet.jsp" method="GET" style="margin: 0px">
                    <input type="text" name="game_id" id="tablet-input" placeholder="Enter team number and comp level">
                </form>
                <form id="import" action="importFromTBA.jsp" method="GET" style="margin: 0px">
                    <input type="text" name="comp-name" id="comp-name" placeholder="Enter comp name">
                </form>
                <form id="clear" action="clearDB.jsp" method="POST" style="margin: 0px">
                    <input type="password" name="password" id="password" placeholder="Enter delete password">
                </form>
            </div>
        </div>
    </div>
</div>
</div>
<!-- 
    
 -->