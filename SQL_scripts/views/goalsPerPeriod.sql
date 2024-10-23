create or replace view GOALS_PER_PERIOD
as
select sys_guid() as uuid, data.*
from (
    select gameData.gameId as game_id, gameData.team, gameData.period, count(*) as goals
    from (
        select g.g_id as gameId, ge.period_number as period,
                case
                    when r.t_id = g.home_team_id then 'Home'
                    else 'Away'
                end as team
        from Games g
            left join Game_Events ge on g.g_id = ge.game_id
            inner join Events e on ge.event_id = e.e_id
            left join Event_Players ep on ge.ge_id = ep.event_id
            inner join Rosters r on r.r_id = ep.roster_id
        where e.name = 'Goal' and ep.role = 'Scorer'
    ) gameData
    group by gameData.gameId, gameData.team, gameData.period
) data
with read only;