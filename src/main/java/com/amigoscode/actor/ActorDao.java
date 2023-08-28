package com.amigoscode.actor;

import java.util.List;
import java.util.Optional;

public interface ActorDao {
     List<Actor> selectAllActors();
     Optional<Actor> getActorById(Integer id);
     Integer insertActor (Actor actor);
     Integer deleteActorById(Integer id);
     void deleteAllActors();
     int updateActor(Integer id, Actor actor);

}
