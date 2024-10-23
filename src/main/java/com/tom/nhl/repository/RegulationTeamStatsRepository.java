package com.tom.nhl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tom.nhl.dao.RegulationTeamStatsDAO;
import com.tom.nhl.entity.view.RegulationTeamStats;
import com.tom.nhl.entity.view.RegulationTeamStatsPK;

public interface RegulationTeamStatsRepository extends JpaRepository<RegulationTeamStats, RegulationTeamStatsPK>, RegulationTeamStatsDAO {
	
}
