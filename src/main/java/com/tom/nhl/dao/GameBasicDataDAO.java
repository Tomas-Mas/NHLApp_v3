package com.tom.nhl.dao;

import java.util.List;

import com.tom.nhl.entity.view.GameBasicData;

public interface GameBasicDataDAO {
	
	public List<GameBasicData> getBySeasonWithPeriodGoals(int season);
	public List<GameBasicData> getPlayoffGamesBySeason(int season);

}
