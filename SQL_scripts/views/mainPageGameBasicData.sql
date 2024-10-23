create or replace view MAIN_PAGE_GAME_BASIC_DATA
as
select g.g_id, g.game_date as game_date, g.game_type as game_type, gs.name as game_status, homeT.t_id as home_id, homeT.name as home_team, 
        homeT.abbreviation as home_abr, g.home_score as home_score, awayT.t_id as away_id, awayT.name as away_team, awayT.abbreviation as away_abr, 
        g.away_score as away_score, endPeriod.periodNum as period_num, periodType.periodType as period_type
from Games g
    inner join Teams homeT on g.home_team_id = homeT.t_id
    inner join Teams awayT on g.away_team_id = awayT.t_id
    inner join Game_Status gs on g.game_status = gs.code
    left join (
        select distinct g.g_id as gameId, ge.period_type as periodType
        from Games g 
            left join Game_Events ge on g.g_id = ge.game_id 
            inner join Events e on ge.event_id = e.e_id
        where e.name = 'Goal' and ge.period_number = (
                select max(period_number) 
                from Game_Events ev join Events evnt on evnt.e_id = ev.event_id 
                where evnt.name = 'Goal' and ev.game_id = g.g_id)
    ) periodType on g.g_id = periodType.gameId
    inner join (
        select g.g_id, max(ev.period_number) as periodNum
        from Games g inner join Game_Events ev on g.g_id = ev.game_id
        group by g.g_id
    ) endPeriod on endPeriod.g_id = g.g_id
order by g.game_date desc
with read only;