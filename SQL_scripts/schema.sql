/*data schema*/
CREATE TABLE Games
  (
    g_id       INTEGER NOT NULL ,
    g_jsonId   INTEGER NOT NULL ,
    gameType   VARCHAR2(5) NOT NULL ,
    season     INTEGER NOT NULL ,
    gameDate   Timestamp NOT NULL ,
    awayScore  INTEGER NOT NULL ,
    awayTeamId INTEGER NOT NULL ,
    homeScore  INTEGER NOT NULL ,
    homeTeamId INTEGER NOT NULL ,
    venueId    INTEGER NOT NULL ,
    gameStatus INTEGER NOT NULL
  );
ALTER TABLE Games ADD CONSTRAINT Games_PK PRIMARY KEY ( g_id );
ALTER TABLE Games ADD CONSTRAINT Games_JsonId UNIQUE ( g_jsonId );

CREATE TABLE GameStatus
  (
    code INTEGER NOT NULL,
    name VARCHAR2 (15)
  );
ALTER TABLE GameStatus ADD CONSTRAINT GameStatus_PK PRIMARY KEY ( code );

CREATE TABLE Teams
  (
    t_id         INTEGER NOT NULL ,
    t_jsonId     INTEGER NOT NULL ,
    name         VARCHAR2 (50) NOT NULL ,
    abbreviation VARCHAR2 (5) NOT NULL ,
    teamName     VARCHAR2 (15) NOT NULL ,
    shortName    VARCHAR2 (15) NOT NULL ,
    venueId      INTEGER NOT NULL ,
    timeZoneId   INTEGER NOT NULL ,
    location     VARCHAR2 (25) NOT NULL ,
    firstYear    INTEGER ,
    active       VARCHAR2 (5)
  );
ALTER TABLE Teams ADD CONSTRAINT Teams_PK PRIMARY KEY ( t_id );
ALTER TABLE Teams ADD CONSTRAINT Teams_jsonId UNIQUE ( t_jsonId );
ALTER TABLE Teams ADD CONSTRAINT Teams_abbr UNIQUE ( abbreviation );

CREATE TABLE Venues
  (
    v_id INTEGER NOT NULL ,
    name VARCHAR2 (50) NOT NULL ,
    city VARCHAR2 (25)
  );
ALTER TABLE Venues ADD CONSTRAINT Venues_PK PRIMARY KEY ( v_id );

CREATE TABLE TimeZones
  (
    tz_id  INTEGER NOT NULL ,
    name   VARCHAR2 (30) NOT NULL ,
    offset INTEGER NOT NULL
  );
ALTER TABLE TimeZones ADD CONSTRAINT TimeZones_PK PRIMARY KEY ( tz_id );

CREATE TABLE Divisions
  (
    d_id     INTEGER NOT NULL ,
    d_jsonId INTEGER ,
    name     VARCHAR2 (15) NOT NULL
  );
ALTER TABLE Divisions ADD CONSTRAINT Divisions_PK PRIMARY KEY ( d_id );
ALTER TABLE Divisions ADD CONSTRAINT Divisions_Name UNIQUE ( name );

CREATE TABLE Division_Teams (
    division_id INTEGER NOT NULL,
    team_id INTEGER NOT NULL,
    season INTEGER NOT NULL
);
ALTER TABLE Division_Teams ADD CONSTRAINT Division_Teams_PK PRIMARY KEY (division_id, team_id, season);

CREATE TABLE Conferences
  (
    c_id     INTEGER NOT NULL ,
    c_jsonId INTEGER ,
    name     VARCHAR2 (15) NOT NULL
  ) ;
ALTER TABLE Conferences ADD CONSTRAINT Conferences_PK PRIMARY KEY ( c_id );
ALTER TABLE Conferences ADD CONSTRAINT Conferences_Name UNIQUE ( name );

CREATE TABLE Conference_Teams (
    conference_id INTEGER NOT NULL,
    team_id INTEGER NOT NULL,
    season INTEGER NOT NULL
);
ALTER TABLE conference_Teams ADD CONSTRAINT Conference_Teams_PK PRIMARY KEY (conference_id, team_id, season);

CREATE TABLE Conference_Division
  (
    conference_id INTEGER NOT NULL ,
    division_id   INTEGER NOT NULL ,
    season INTEGER NOT NULL
  ) ;
ALTER TABLE Conference_Division ADD CONSTRAINT Conference_Division_PK PRIMARY KEY ( conference_id, division_id, season );

CREATE TABLE Events(
    e_id INTEGER NOT NULL,
    name VARCHAR2(15),
    secondaryType VARCHAR2(50),
    strength VARCHAR2(20),
    emptyNet VARCHAR2(10),
    penaltySeverity VARCHAR2(30),
    penaltyMinutes INTEGER
);
ALTER TABLE Events ADD CONSTRAINT Events_PK PRIMARY KEY (e_id);

CREATE TABLE GameEvents(
    ge_id INTEGER NOT NULL,
    gameId INTEGER NOT NULL,
    gameEventId INTEGER NOT NULL,
    eventId INTEGER NOT NULL,
    periodNumber INTEGER NOT NULL,
    periodType VARCHAR2(15) NOT NULL,
    periodTime VARCHAR2(10) NOT NULL,
    coordX INTEGER,
    coordY INTEGER
);
ALTER TABLE GameEvents ADD CONSTRAINT GameEvents_PK PRIMARY KEY(ge_id);
ALTER TABLE GameEvents ADD CONSTRAINT GameEvents_game_event_UN UNIQUE(gameId, gameEventId);

CREATE TABLE Positions
  (
    p_id         INTEGER NOT NULL ,
    name         VARCHAR2 (15) NOT NULL ,
    type         VARCHAR2 (15) NOT NULL ,
    abbreviation VARCHAR2 (2)
  );
ALTER TABLE Positions ADD CONSTRAINT Positions_PK PRIMARY KEY ( p_id );
ALTER TABLE Positions ADD CONSTRAINT Conferences_Unique UNIQUE ( name, type );

CREATE TABLE Players
  (
    p_id          INTEGER NOT NULL ,
    p_jsonId      INTEGER NOT NULL ,
    firstName     VARCHAR2 (25) NOT NULL ,
    lastName      VARCHAR2 (25) NOT NULL ,
    primaryNumber INTEGER ,
    age           INTEGER,
    birthDate     DATE ,
    birthCountry  VARCHAR2 (3) ,
    currentTeamId INTEGER ,
    positionId    INTEGER NOT NULL
  );
ALTER TABLE Players ADD CONSTRAINT Players_PK PRIMARY KEY ( p_id );
ALTER TABLE Players ADD CONSTRAINT Players_jsonId UNIQUE ( p_jsonId );

CREATE TABLE Rosters(
    r_id INTEGER NOT NULL,
    g_id INTEGER NOT NULL,
    t_id INTEGER NOT NULL,
    p_id INTEGER NOT NULL,
    pos_id INTEGER NOT NULL,
    timeOnIce VARCHAR2(10) NOT NULL,
    plusMinus INTEGER NOT NULL
);
ALTER TABLE Rosters ADD CONSTRAINT Rosters_PK PRIMARY KEY(r_id);
ALTER TABLE rosters ADD CONSTRAINT Rosters_unique_gtp UNIQUE (g_id, t_id, p_id);
ALTER TABLE rosters ADD CONSTRAINT Rosters_unique_gp UNIQUE (g_id, p_id);

CREATE TABLE EventPlayers(
    event_id INTEGER NOT NULL,
    roster_id INTEGER NOT NULL,
    role VARCHAR2(50)
);
ALTER TABLE EventPlayers ADD CONSTRAINT EventPlayers_PK PRIMARY KEY(event_id, roster_id);

ALTER TABLE Teams ADD CONSTRAINT Teams_Venues_FK FOREIGN KEY ( venueId ) REFERENCES Venues ( v_id );
ALTER TABLE Teams ADD CONSTRAINT Teams_TimeZones_FK FOREIGN KEY ( timeZoneId ) REFERENCES TimeZones ( tz_id );
ALTER TABLE Games ADD CONSTRAINT Games_Venues_FK FOREIGN KEY ( venueId ) REFERENCES Venues ( v_id );
ALTER TABLE Games ADD CONSTRAINT Games_GameStatus_FK FOREIGN KEY ( gameStatus ) REFERENCES GameStatus ( code );
ALTER TABLE Games ADD CONSTRAINT Games_Teams_FK_Away FOREIGN KEY ( awayTeamId ) REFERENCES Teams ( t_id );
ALTER TABLE Games ADD CONSTRAINT Games_Teams_FK_Home FOREIGN KEY ( homeTeamId ) REFERENCES Teams ( t_id );
ALTER TABLE Division_Teams ADD CONSTRAINT Division_Teams_Teams_FK FOREIGN KEY (team_id) REFERENCES Teams (t_id);
ALTER TABLE Division_Teams ADD CONSTRAINT Division_Teams_Divisions_FK FOREIGN KEY (division_id) REFERENCES Divisions (d_id);
ALTER TABLE Conference_Teams ADD CONSTRAINT Conference_Teams_Conferences_FK FOREIGN KEY (conference_id) REFERENCES Conferences (c_id);
ALTER TABLE Conference_Teams ADD CONSTRAINT Conference_Teams_Teams_FK FOREIGN KEY (team_id) REFERENCES Teams (t_id);
ALTER TABLE Conference_Division ADD CONSTRAINT Conference_Div_Con_FK FOREIGN KEY ( conference_id ) REFERENCES Conferences ( c_id );
ALTER TABLE Conference_Division ADD CONSTRAINT Con_Div_Division_FK FOREIGN KEY ( division_id ) REFERENCES Divisions ( d_id );
ALTER TABLE GameEvents ADD CONSTRAINT GameEvents_Games_FK FOREIGN KEY(gameId) REFERENCES Games(g_id);
ALTER TABLE GameEvents ADD CONSTRAINT GameEvents_Events_FK FOREIGN KEY(eventId) REFERENCES Events(e_id);
ALTER TABLE Players ADD CONSTRAINT Players_Positions_FK FOREIGN KEY ( positionId ) REFERENCES Positions ( p_id );
ALTER TABLE Players ADD CONSTRAINT Players_Teams_FK FOREIGN KEY ( currentTeamId ) REFERENCES Teams ( t_id );
ALTER TABLE Rosters ADD CONSTRAINT Rosters_Games_FK FOREIGN KEY (g_id) REFERENCES Games (g_id);
ALTER TABLE Rosters ADD CONSTRAINT Rosters_Teams_FK FOREIGN KEY (t_id) REFERENCES Teams (t_id);
ALTER TABLE Rosters ADD CONSTRAINT Rosters_Players_FK FOREIGN KEY (p_id) REFERENCES Players (p_id);
ALTER TABLE Rosters ADD CONSTRAINT Rosters_Positions_FK FOREIGN KEY (pos_id) REFERENCES Positions(p_id);
ALTER TABLE EventPlayers ADD CONSTRAINT EventPlayers_GameEvents_FK FOREIGN KEY(event_id) REFERENCES GameEvents (ge_id);
ALTER TABLE EventPlayers ADD CONSTRAINT EventPlayers_Rosters_FK FOREIGN KEY(roster_id) REFERENCES Rosters(r_id);

/

CREATE SEQUENCE seq_games_id
START WITH 200000
INCREMENT BY 1
MINVALUE 200000
NOMAXVALUE
NOCACHE
NOCYCLE;

/

CREATE SEQUENCE seq_teams_id
START WITH 100
INCREMENT BY 1
MINVALUE 100
MAXVALUE 499
NOCACHE
NOCYCLE;

/

CREATE SEQUENCE seq_venues_id
START WITH 500
INCREMENT BY 1
MINVALUE 500
MAXVALUE 999
NOCACHE
NOCYCLE;

/

CREATE SEQUENCE seq_timeZones_id
START WITH 1
INCREMENT BY 1
MINVALUE 1
MAXVALUE 1000
NOCACHE
NOCYCLE;

/

CREATE SEQUENCE seq_divisions_id
START WITH 1
INCREMENT BY 1
MINVALUE 1
MAXVALUE 29
NOCACHE
NOCYCLE;

/

CREATE SEQUENCE seq_conferences_id
START WITH 30
INCREMENT BY 1
MINVALUE 30
MAXVALUE 49
NOCACHE
NOCYCLE;

/

CREATE SEQUENCE seq_events_id
START WITH 1
INCREMENT BY 1
MINVALUE 1
MAXVALUE 10000
NOCACHE
NOCYCLE;

/

CREATE SEQUENCE seq_gameEvents_id
START WITH 1
INCREMENT BY 1
MINVALUE 1
MAXVALUE 9999999999
CACHE 20
NOCYCLE;

/

CREATE SEQUENCE seq_positions_id
START WITH 100
INCREMENT BY 1
MINVALUE 100
MAXVALUE 1000
NOCACHE
NOCYCLE;

/

CREATE SEQUENCE seq_players_id
START WITH 1000
INCREMENT BY 1
MINVALUE 1000
MAXVALUE 999999
NOCACHE
NOCYCLE;

/

create sequence seq_rosters_id
start with 1
increment by 1
minvalue 1
maxvalue 9999999999
cache 5
nocycle;

/*user stuff schema*/
CREATE TABLE APP_ROLES(
    r_id INTEGER NOT NULL,
    r_name VARCHAR2(20) NOT NULL
);
ALTER TABLE APP_ROLES ADD CONSTRAINT APP_ROLES_PK PRIMARY KEY (r_id);
ALTER TABLE APP_ROLES ADD CONSTRAINT APP_ROLES_UK_NAME UNIQUE (r_name);
INSERT INTO APP_ROLES VALUES(1, 'GUEST');
INSERT INTO APP_ROLES VALUES(2, 'USER');
INSERT INTO APP_ROLES VALUES(3, 'ADMIN');


CREATE TABLE APP_USERS(
    u_id INTEGER NOT NULL,
    username VARCHAR2(50) NOT NULL,
    u_password VARCHAR2(255) NOT NULL,
    first_name VARCHAR2(25),
    last_name VARCHAR2(25),
    birth_date DATE,
    u_locked CHAR(1) NOT NULL,
    enabled CHAR(1) NOT NULL,
    role_id INTEGER NOT NULL,
    created_date TIMESTAMP NOT NULL,
    last_modified_date TIMESTAMP
);
ALTER TABLE APP_USERS ADD CONSTRAINT APP_USERS_PK PRIMARY KEY(u_id);
ALTER TABLE APP_USERS ADD CONSTRAINT APP_USERS_UK_USERNAME UNIQUE(username);
ALTER TABLE APP_USERS ADD CONSTRAINT APP_USERS_ROLES_FK FOREIGN KEY (role_Id) REFERENCES APP_ROLES (r_id);
CREATE SEQUENCE SEQ_APPUSERS_ID
    START WITH 10 INCREMENT BY 1 MINVALUE 10
    NOMAXVALUE NOCACHE NOCYCLE;


CREATE TABLE TOKENS(
    t_id INTEGER NOT NULL,
    token VARCHAR2(255) NOT NULL,
    user_id INTEGER NOT NULL,
    created TIMESTAMP NOT NULL,
    expires TIMESTAMP NOT NULL,
    validated TIMESTAMP
);
ALTER TABLE TOKENS ADD CONSTRAINT TOKENS_PK PRIMARY KEY (t_id);
ALTER TABLE TOKENS ADD CONSTRAINT TOKENS_USERS_FK FOREIGN KEY (user_id) REFERENCES APP_USERS (u_id);
CREATE SEQUENCE SEQ_TOKENS_ID
    START WITH 1 INCREMENT BY 1 MINVALUE 1
    NOMAXVALUE NOCACHE NOCYCLE;


/* session schema */
CREATE TABLE SPRING_SESSION (
	PRIMARY_ID CHAR(36) NOT NULL,
	SESSION_ID CHAR(36) NOT NULL,
	CREATION_TIME NUMBER(19,0) NOT NULL,
	LAST_ACCESS_TIME NUMBER(19,0) NOT NULL,
	MAX_INACTIVE_INTERVAL NUMBER(10,0) NOT NULL,
	EXPIRY_TIME NUMBER(19,0) NOT NULL,
	PRINCIPAL_NAME VARCHAR2(100 CHAR),
	CONSTRAINT SPRING_SESSION_PK PRIMARY KEY (PRIMARY_ID)
);

CREATE UNIQUE INDEX SPRING_SESSION_IX1 ON SPRING_SESSION (SESSION_ID);
CREATE INDEX SPRING_SESSION_IX2 ON SPRING_SESSION (EXPIRY_TIME);
CREATE INDEX SPRING_SESSION_IX3 ON SPRING_SESSION (PRINCIPAL_NAME);

CREATE TABLE SPRING_SESSION_ATTRIBUTES (
	SESSION_PRIMARY_ID CHAR(36) NOT NULL,
	ATTRIBUTE_NAME VARCHAR2(200 CHAR) NOT NULL,
	ATTRIBUTE_BYTES BLOB NOT NULL,
	CONSTRAINT SPRING_SESSION_ATTRIBUTES_PK PRIMARY KEY (SESSION_PRIMARY_ID, ATTRIBUTE_NAME),
	CONSTRAINT SPRING_SESSION_ATTRIBUTES_FK FOREIGN KEY (SESSION_PRIMARY_ID) REFERENCES SPRING_SESSION(PRIMARY_ID) ON DELETE CASCADE
);