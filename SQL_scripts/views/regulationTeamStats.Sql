create or replace view Regulation_Team_Stats
as
select regulationData.teamId as team_id, regulationData.season as season, t.name as team_name, t.abbreviation as team_abr_name, c.name as conference, d.name as division, 
        regulationData.home_games, regulationData.home_goalsFor as home_goals_for, regulationData.home_goalsAgainst as home_goals_against, 
        regulationData.home_points, regulationData.home_regWins as home_reg_wins, regulationData.home_regLoses as home_reg_loses, 
        regulationData.home_otWins as home_ot_wins, regulationData.home_otLoses as home_ot_loses,
        regulationData.away_games, regulationData.away_goalsFor as away_goals_for, regulationData.away_goalsAgainst as away_goals_against, 
        regulationData.away_points, regulationData.away_regWins as away_reg_wins, regulationData.away_regLoses as away_reg_loses, 
        regulationData.away_otWins as away_ot_wins, regulationData.away_otLoses as away_ot_loses
from (
    select *
    from (
        select season, game_type, team, teamId, count(g_id) as gamesPlayed, sum(goalsFor) as goalsFor, sum(goalsAgainst) as goalsAgainst, sum(points) as points,
                sum(case when periods <= 3 and goalsFor > goalsAgainst then 1 else 0 end) as regulationWins,
                sum(case when periods <= 3 and goalsFor < goalsAgainst then 1 else 0 end) as regulationLoses,
                sum(case when periods > 3 and goalsFor > goalsAgainst then 1 else 0 end) as overtimeWins,
                sum(case when periods > 3 and goalsFor < goalsAgainst then 1 else 0 end) as overtimeLoses
        from (
            select *
            from (
                select g.season, g.g_id, g.game_type, homeT.t_id as homeTeam, awayT.t_id as awayTeam, myGames.periods, g.home_score as homeScore, g.away_score as awayScore,
                        case 
                            when home_score > away_score then 2
                            when home_score < away_score and periods <=3 then 0 else 1
                        end as homePoints,
                        case 
                            when away_score > home_score then 2
                            when away_score < home_score and periods <=3 then 0 else 1
                        end as awayPoints
                from (
                    select g.g_id, max(gEv.period_number) as periods
                    from games g inner join game_Events gev on g.g_id = gev.game_id 
                    group by g.g_id
                ) myGames 
                    inner join Games g on g.g_id = myGames.g_id
                    inner join Teams homeT on homeT.t_id = g.home_team_id
                    inner join Teams awayT on awayT.t_id = g.away_team_id
                where g.game_type = 'R' --and g.season = 20152016
                order by g.g_id
            )
            unpivot (
                (teamId, goalsFor, goalsAgainst, points)
                for team
                in (
                    (homeTeam, homeScore, awayScore, homePoints) as 'home',
                    (awayTeam, awayScore, homeScore, awayPoints) as 'away'
                )
            )
        )
        group by season, game_type, team, teamId
    )
    pivot (
        sum(gamesPlayed) as games, sum(goalsFor) as goalsFor, sum(goalsAgainst) as goalsAgainst, sum(points) as points, sum(regulationWins) as regWins, 
                sum(regulationLoses) as regLoses, sum(overtimeWins) as otWins, sum(overtimeLoses) as otLoses
        for team in ('home' as home, 'away' as away)
    )
) regulationData
    inner join Teams t on t.t_id = regulationData.teamId
    inner join Conference_Teams confT on t.t_id = confT.team_id
    inner join Conferences c on c.c_id = confT.conference_id
    inner join Division_Teams divT on t.t_id = divT.team_id
    inner join Divisions d on d.d_id = divT.division_id
where confT.season = regulationData.season and divT.season = regulationData.season
with read only;