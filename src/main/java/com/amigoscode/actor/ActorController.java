package com.amigoscode.actor;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/actor")
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }


    @GetMapping("")
    public List<Actor> getAllActors(){
        return actorService.getAllActors();
    }

    @GetMapping("/{id}")
    public Actor getActorById(@PathVariable("id") Integer id){
        return actorService.getActorById(id);

    }

    @PostMapping("")
    public void insertActor(
            @RequestBody Actor actor
    ){
        actorService.insertActor(actor);
    }

    @PutMapping("/{id}")
    public Optional<Actor> updateActor(
            @PathVariable("id") Integer id,
            @RequestBody Actor updatedActor
    ){
       return actorService.updateActor(id,updatedActor);
    }

    @DeleteMapping("/{id}")
    public void deleteActorById(
            @PathVariable("id") Integer id
    ){
         actorService.deleteActorById(id);
    }

    @DeleteMapping("")
    public void deleteAllActors(){
         actorService.deleteAllActors();
    }
}
