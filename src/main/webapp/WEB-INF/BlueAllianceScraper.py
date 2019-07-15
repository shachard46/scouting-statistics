import sqlite3
import requests
import argparse
import re

# ---------------------------------------
# Load arguments from command line
# ---------------------------------------
parser = argparse.ArgumentParser()
parser.add_argument("-e", default="2018isde2", dest="event_key", action="store",
                    help="Provide the event key according to TBA site")
parser.add_argument("-d", default="ScoutingData.db", dest="database_path", action="store",
                    help="Location of scouting database")
args = parser.parse_args()

# ---------------------------------------
# Establish connection to the database
# ---------------------------------------
conn = sqlite3.connect(args.database_path)
# Create the database for the match information
c = conn.cursor()
c.execute("DROP Table IF EXISTS [EventMatches];")
conn.commit()
c.execute("""CREATE TABLE [EventMatches] (
             comp_level TEXT(5),
             match_id INTEGER,
             alliance INTEGER,
             TeamId   INTEGER,
             PRIMARY KEY(`comp_level`,`match_id`,`TeamId`))""")

# ---------------------------------------
# create request to TBA
# ---------------------------------------
tba_base_url = "https://www.thebluealliance.com/api/v3/"
request_url = tba_base_url + "event/" + args.event_key+ "/matches"
payload = {"X-TBA-Auth-Key": "XTONkkO0b81Cuof2WPFV1LUAXdO1OOv2rq0lq23xenS0lWDiEsxVtQQRo3REWkYp"}
request = requests.get(request_url, params=payload).json()

# ---------------------------------------
# fill the database with the received information
# ---------------------------------------
sql = "INSERT INTO [EventMatches](comp_level, match_id, alliance, TeamID) values (?,?,?,?)"
for ev in request:
    match_number = ev["match_number"]
    red = []
    blue = []
    for team in ev["alliances"]["blue"]["team_keys"]:
        for t in re.findall("\d+", team):
            blue.append(int(t))
            c.execute(sql, (ev["comp_level"], match_number, 0, int(t)))
    for team in ev["alliances"]["red"]["team_keys"]:
        for t in re.findall("\d+", team):
            red.append(int(t))
            c.execute(sql, (ev["comp_level"], match_number, 1, int(t)))
    conn.commit()
    print(ev["comp_level"], match_number, blue, red)