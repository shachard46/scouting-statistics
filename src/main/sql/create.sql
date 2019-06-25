CREATE TABLE TeamScouting
(
    game_id int
(11) NOT NULL,
    team_id int
(11) NOT NULL,
    prop_id int
(11) NOT NULL,
    prop_value varchar
(64) COLLATE utf8mb4_general_ci NOT NULL,
    id int
(11)
    AUTO_INCREMENT NOT NULL ,
  PRIMARY KEY
    (`id`)
);
    CREATE TABLE TeamScoutingProps
(
  prop_id int
(11) NOT NULL,
  prop_desc varchar
(64) COLLATE utf8mb4_general_ci NOT NULL,
  prop_style varchar
(64) COLLATE utf8mb4_general_ci NOT NULL,
  prop_type varchar
(64) COLLATE utf8mb4_general_ci NOT NULL,
  prop_child int
(11) DEFAULT NULL,
  PRIMARY KEY
(`prop_id`)
);
insert into TeamScoutingProps
    (`prop_id`,`prop_desc
`,`prop_style`,`prop_type`, `prop_child`)  values
(0, "crossed_line_autonomous", "Checkbox", "boolean", );
insert into TeamScoutingProps
    (`prop_id`,`prop_desc
`,`prop_style`,`prop_type`, `prop_child`)  values
(1, "scale_cubes_amount_autonomous", "Buttons(+1,-1)", "number", );
insert into TeamScoutingProps
    (`prop_id`,`prop_desc
`,`prop_style`,`prop_type`, `prop_child`)  values
(2, "switch_cubes_amount_autonomous", "Buttons(+1,-1)", "number", );
insert into TeamScoutingProps
    (`prop_id`,`prop_desc
`,`prop_style`,`prop_type`, `prop_child`)  values
(3, "scale_cubes_amount", "Buttons(+1,-1)", "number", );
insert into TeamScoutingProps
    (`prop_id`,`prop_desc
`,`prop_style`,`prop_type`, `prop_child`)  values
(4, "switch_cubes_amount", "Buttons(+1,-1)", "number", );
insert into TeamScoutingProps
    (`prop_id`,`prop_desc
`,`prop_style`,`prop_type`, `prop_child`)  values
(5, "lost_cube_count", "Buttons(+1,-1)", "number", );
insert into TeamScoutingProps
    (`prop_id`,`prop_desc
`,`prop_style`,`prop_type`, `prop_child`)  values
(6, "exchange_cubes_amount", "Buttons(+1,-1)", "number", );
insert into TeamScoutingProps
    (`prop_id`,`prop_desc
`,`prop_style`,`prop_type`, `prop_child`)  values
(7, "lift_cube_speed", "Scale(0-5)", "number", );
insert into TeamScoutingProps
    (`prop_id`,`prop_desc
`,`prop_style`,`prop_type`, `prop_child`)  values
(8, "climbed", "Checkbox", "boolean", 2);
insert into TeamScoutingProps
    (`prop_id`,`prop_desc
`,`prop_style`,`prop_type`, `prop_child`)  values
(9, "climbing_style", "Select( ,טיפס, תמך,הרים,הורם)", "text", );
insert into TeamScoutingProps
    (`prop_id`,`prop_desc
`,`prop_style`,`prop_type`, `prop_child`)  values
(10, "climbing_speed", "Select( ,מהר, בינוני,איטי)", "text", );
insert into TeamScoutingProps
    (`prop_id`,`prop_desc
`,`prop_style`,`prop_type`, `prop_child`)  values
(11, "drivers_quality", "Scale(1-5)", "number", );
insert into TeamScoutingProps
    (`prop_id`,`prop_desc
`,`prop_style`,`prop_type`, `prop_child`)  values
(12, "foul_committed", "Checkbox", "boolean", 1);
insert into TeamScoutingProps
    (`prop_id`,`prop_desc
`,`prop_style`,`prop_type`, `prop_child`)  values
(13, "foul_description", "Textarea", "text", );
insert into TeamScoutingProps
    (`prop_id`,`prop_desc
`,`prop_style`,`prop_type`, `prop_child`)  values
(14, "crashed", "Checkbox", "boolean", 1);
insert into TeamScoutingProps
    (`prop_id`,`prop_desc
`,`prop_style`,`prop_type`, `prop_child`)  values
(15, "crash_description", "Textarea", "text", );
insert into TeamScoutingProps
    (`prop_id`,`prop_desc
`,`prop_style`,`prop_type`, `prop_child`)  values
(16, "defender", "Checkbox", "boolean", 1);
insert into TeamScoutingProps
    (`prop_id`,`prop_desc
`,`prop_style`,`prop_type`, `prop_child`)  values
(17, "defence_description", "Scale(1-5)", "number", );
insert into TeamScoutingProps
    (`prop_id`,`prop_desc
`,`prop_style`,`prop_type`, `prop_child`)  values
(18, "comments", "Textarea", "text", );
insert into TeamScoutingProps
    (`prop_id`,`prop_desc
`,`prop_style`,`prop_type`, `prop_child`)  values
(20, "SS_autonomous_1", "Textarea", "text", );
insert into TeamScoutingProps
    (`prop_id`,`prop_desc
`,`prop_style`,`prop_type`, `prop_child`)  values
(21, "SS_fieldteam_1", "Textarea", "text", );
insert into TeamScoutingProps
    (`prop_id`,`prop_desc
`,`prop_style`,`prop_type`, `prop_child`)  values
(22, "SS_robot_1", "Textarea", "text", );
insert into TeamScoutingProps
    (`prop_id`,`prop_desc
`,`prop_style`,`prop_type`, `prop_child`)  values
(23, "SS_comments_1", "Textarea", "text", );
insert into TeamScoutingProps
    (`prop_id`,`prop_desc
`,`prop_style`,`prop_type`, `prop_child`)  values
(34, "SS_alliance_comment", "Textarea", "text", );
    