package com.tom.nhl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tom.nhl.dao.GameEventDAO;
import com.tom.nhl.entity.GameEvent;

public interface GameEventRepository extends JpaRepository<GameEvent, Integer>, GameEventDAO {

}
