package com.tom.nhl.dto;

import java.util.List;

/**
 * Season manager is loaded with ordered list of all seasons from db and contains basic methods to get all seasons or to check if given season exists.
 */
public class SeasonManagerDTO {
	
	private List<Integer> seasonList;
	
	public SeasonManagerDTO(List<Integer> seasonList) {
		this.seasonList = seasonList;
	}
	
	public List<Integer> getSeasons() {
		return this.seasonList;
	}
	
	public boolean isSeasonValid(int season) {
		if(this.seasonList.contains(season))
			return true;
		
		return false;
	}
	
	/**
	 * default season = the latest one that exists in db
	 */
	public int getDefaultSeason() {
		return seasonList.get(0);
	}

}
