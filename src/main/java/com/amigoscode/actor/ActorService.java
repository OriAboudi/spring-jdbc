package com.amigoscode.actor;

import com.amigoscode.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorService {

    private final ActorDao actorDao;

    public ActorService(ActorDao actorDao) {
        this.actorDao = actorDao;
    }


    public List<Actor> getAllActors() {
        return actorDao.selectAllActors();
    }

    public Actor getActorById(Integer id) {
      return actorDao.getActorById(id)
                .orElseThrow(() ->
                        new NotFoundException(String.format(
                                "The Actor with id" + " %s Not Found", id
                        )));

    }

    public void insertActor(Actor actor) {
        int result = actorDao.insertActor(actor);
        if (result != 1) {
            throw new IllegalStateException("oops something went wrong");
        }

    }

    public Optional<Actor> updateActor(Integer id, Actor updatedActor) {
        Optional<Actor> actor = actorDao.getActorById(id);
        actor.ifPresentOrElse(existActor->{
            int result = actorDao.updateActor(id,updatedActor);
            if(result!=1)
                throw new IllegalStateException("Oops, could not update actor");
        },()-> {
             throw new NotFoundException(String.format
                    ("Actor With Id %s Not Found", id));
        });

       return actorDao.getActorById(id);
    }

    public void deleteActorById(Integer id) {
        Optional<Actor> actor = actorDao.getActorById(id);

        actor.ifPresentOrElse(existingActor->{
            int result  = actorDao.deleteActorById(id);
            if(result!=1){
                throw new IllegalStateException("Oops could not delete this actor");
            }
        },()->{
            throw new NotFoundException(String.format("Actor With Id %s Not Found", id));
        });

    }

    public void deleteAllActors() {
        actorDao.deleteAllActors();
    }
}
