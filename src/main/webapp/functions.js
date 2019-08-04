function getRandomColor() {
  var letters = "0123456789A";
  var color = "#";
  for (var i = 0; i < 6; i++) {
    color += letters[Math.floor(Math.random() * 11)];
  }
  return color;
}
function orderBy(avgs, propId) {
  avgs = avgs.props;
  if (avgs[0][propId][avgs[0][propId].length - 1] === "%") {
    avgs.sort((a, b) => {
      var first = a[propId].substring(
        a[propId].indexOf("|") + 2,
        a[propId].length - 1
      );
      var second = b[propId].substring(
        b[propId].indexOf("|") + 2,
        b[propId].length - 1
      );
      if (Number(first) > Number(second)) {
        return -1;
      }
      if (Number(first) < Number(second)) {
        return 1;
      }
      return 0;
    });
    avgs.forEach(element => {});
  } else {
    avgs.sort((a, b) =>
      a[propId] > b[propId] ? -1 : b[propId] > a[propId] ? 1 : 0
    );
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

function createAvgsTable(avgs) {
  avgs = avgs.props;
  avgs.sort((a, b) => (a.teamId > b.teamId ? 1 : b.teamId > a.teamId ? -1 : 0));
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
}
function createPitScoutingTable(pitScouting, teamId) {
  pitScouting = pitScouting.props;
  var table = document.getElementsByTagName("table")[0];
  var team = document.createElement("tr");
  team.insertCell(0);
  team.insertCell(1);
  team.cells[0].className = "header";
  team.cells[1].className = "header";
  team.cells[0].append("מספר קבוצה");
  team.cells[1].append(teamId);
  table.appendChild(team);
  for (var prop in pitScouting) {
    var tr = document.createElement("tr");
    tr.insertCell(0);
    tr.insertCell(1);
    tr.cells[0].append(prop);
    tr.cells[0].className = "header";
    tr.cells[1].append(pitScouting[prop]);
    table.appendChild(tr);
  }
}
function createGraph(labels, propsLabels, data) {
  var ctx = document.getElementById("myChart").getContext("2d");
  var myChart = new Chart(ctx, {
    type: "line",
    data: {
      labels: labels,
      datasets: Object.keys(propsLabels).map(key => ({
        label: propsLabels[key].name,
        fill: false,
        data: labels.map(label => data[labels.indexOf(label)][key]),
        borderColor: [getRandomColor()],
        borderWidth: 4
      }))
    },
    options: {
      title: {
        display: true,
        text: "קבוצה מספר " + data[0].teamId,
        fontSize: 45,
        fontFamily: "tahoma"
      },
      legend: {
        labels: {
          fontColor: "#b4b4b4",
          fontSize: 15,
          fontFamily: "tahoma"
        }
      },
      scales: {
        xAxes: [
          {
            ticks: {
              fontColor: "#b4b4b4",
              fontSize: 20,
              fontFamily: "tahoma"
            },
            gridLines: {
              color: "#b4b4b4"
            },
            legend: {
              fontColor: "#b4b4b4",
              fontSize: 15,
              fontFamily: "tahoma",
              text: "מספרי משחק"
            }
          }
        ],
        yAxes: [
          {
            ticks: {
              fontColor: "#b4b4b4",
              fontSize: 20,
              fontFamily: "tahoma",
              beginAtZero: true
            },
            gridLines: { color: "#b4b4b4" }
          }
        ]
      }
    }
  });
}

function doAsyncCall() {
  axios.get("radar.jsp").then(res => {
    console.log(res.data);
  });
}
