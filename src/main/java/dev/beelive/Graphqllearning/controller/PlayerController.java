package dev.beelive.Graphqllearning.controller;

import dev.beelive.Graphqllearning.model.Player;
import dev.beelive.Graphqllearning.service.PlayerService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class PlayerController {

     private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
         this.playerService = playerService;
    }

    @QueryMapping
    public List<Player> findAll(){
         return playerService.findAll();
    }

    @QueryMapping
    public Optional<Player> findPlayerByID(@Argument Integer id){
        return playerService.findOne(id);
    }

}
