package com.amigoscode.actor;

import org.springframework.data.relational.core.sql.In;

import java.util.List;
import java.util.Optional;

public interface ActorDao {
    public List<Actor> selectAllActors();
    public Optional<Actor> getActorById(Integer id);
    public Integer insertActor (Actor actor);
    public Integer deleteActorById(Integer id);
    public Integer deleteAllActors();
    public Actor updateActor(Integer id);

}
