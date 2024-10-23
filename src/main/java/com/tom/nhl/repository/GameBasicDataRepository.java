package com.tom.nhl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tom.nhl.dao.GameBasicDataDAO;
import com.tom.nhl.entity.view.GameBasicData;

public interface GameBasicDataRepository extends JpaRepository<GameBasicData, Integer>, GameBasicDataDAO {

}
