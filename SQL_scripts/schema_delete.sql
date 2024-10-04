/*delete data*/
DELETE FROM TOKENS;
DELETE FROM APP_USERS;
DELETE FROM APP_ROLES;

DELETE FROM eventPlayers;
DELETE FROM gameEvents;
DELETE FROM events;
DELETE FROM rosters;
DELETE FROM players;
DELETE FROM positions;
DELETE FROM games;
DELETE FROM gamestatus;
DELETE FROM division_teams;
DELETE FROM conference_teams;
DELETE FROM conference_division;
DELETE FROM divisions;
DELETE FROM conferences;
DELETE FROM teams;
DELETE FROM venues;
DELETE FROM timeZones;

/*references*/
ALTER TABLE APP_USERS DROP CONSTRAINT APP_USERS_ROLES_FK;
ALTER TABLE TOKENS DROP CONSTRAINT TOKENS_USERS_FK;

ALTER TABLE Teams DROP CONSTRAINT Teams_Venues_FK;
ALTER TABLE Teams DROP CONSTRAINT Teams_TimeZones_FK;
ALTER TABLE Games DROP CONSTRAINT Games_Venues_FK;
ALTER TABLE Games DROP CONSTRAINT Games_GameStatus_FK;
ALTER TABLE Games DROP CONSTRAINT Games_Teams_FK_Away;
ALTER TABLE Games DROP CONSTRAINT Games_Teams_FK_Home;
ALTER TABLE Division_Teams DROP CONSTRAINT Division_Teams_Teams_FK;
ALTER TABLE Division_Teams DROP CONSTRAINT Division_Teams_Divisions_FK;
ALTER TABLE Conference_Teams DROP CONSTRAINT Conference_Teams_Conferences_FK;
ALTER TABLE Conference_Teams DROP CONSTRAINT Conference_Teams_Teams_FK;
ALTER TABLE Conference_Division DROP CONSTRAINT Conference_Div_Con_FK;
ALTER TABLE Conference_Division DROP CONSTRAINT Con_Div_Division_FK;
ALTER TABLE GameEvents DROP CONSTRAINT GameEvents_Games_FK;
ALTER TABLE GameEvents DROP CONSTRAINT GameEvents_Events_FK;
ALTER TABLE Players DROP CONSTRAINT Players_Positions_FK;
ALTER TABLE Players DROP CONSTRAINT Players_Teams_FK;
ALTER TABLE Rosters DROP CONSTRAINT Rosters_Games_FK;
ALTER TABLE Rosters DROP CONSTRAINT Rosters_Teams_FK;
ALTER TABLE Rosters DROP CONSTRAINT Rosters_Players_FK;
ALTER TABLE Rosters DROP CONSTRAINT Rosters_Positions_FK;
ALTER TABLE EventPlayers DROP CONSTRAINT EventPlayers_GameEvents_FK;
ALTER TABLE EventPlayers DROP CONSTRAINT EventPlayers_Rosters_FK;


DROP SEQUENCE SEQ_APPUSERS_ID;
DROP SEQUENCE SEQ_TOKENS_ID;

DROP SEQUENCE seq_rosters_id;
DROP SEQUENCE seq_players_id;
DROP SEQUENCE seq_positions_id;
DROP SEQUENCE seq_gameEvents_id;
DROP SEQUENCE seq_events_id;
DROP SEQUENCE seq_conferences_id;
DROP SEQUENCE seq_divisions_id;
DROP SEQUENCE seq_timeZones_id;
DROP SEQUENCE seq_venues_id;
DROP SEQUENCE seq_teams_id;
DROP SEQUENCE seq_games_id;


DROP TABLE TOKENS;
DROP TABLE APP_USERS;
DROP TABLE APP_ROLES;

DROP TABLE gameStatus;
DROP TABLE venues;
DROP TABLE teams;
DROP TABLE games;
DROP TABLE timeZones;
DROP TABLE divisions;
DROP TABLE division_Teams;
DROP TABLE conference_division;
DROP TABLE conference_teams;
DROP TABLE conferences;
DROP TABLE events;
DROP TABLE gameEvents;
DROP TABLE positions;
DROP TABLE players;
DROP TABLE rosters;
DROP TABLE eventPlayers;