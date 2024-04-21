package dev.beelive.Graphqllearning.service;

import dev.beelive.Graphqllearning.model.Player;
import dev.beelive.Graphqllearning.model.Team;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;


@Service
public class PlayerService {

    private List<Player> players = new ArrayList<>();
    AtomicInteger id = new AtomicInteger(0);


    public List<Player> findAll(){
        return players;
    }

    public Optional<Player> findOne(Integer id ){
       return players.stream().filter( player -> player.id() == id ).findFirst();
    }

    public Player create(String name , Team team ){
        Player player = new Player(id.incrementAndGet(), name, team);
        players.add(player);
        return player;
    }

    public  Player deletePlayer(Integer id){
        Player player = players.stream().filter( p-> p.id()==id ).findFirst().orElseThrow( ()-> new IllegalArgumentException());
        players.remove(player);
        return player;
    }

    public Player updatePlayerInfo( Integer id , String name , Team team){
        Player updatedPlayer = new Player(id, name,team);
        Optional<Player> optional =   players.stream().filter( p -> p.id()  == id ).findFirst();
        if(optional.isPresent()){
            Player player = optional.get();
            int indexOf = players.indexOf(player);
            players.set(indexOf, player);
        }else{
            throw new IllegalArgumentException("Invalid Player");
        }
        return updatedPlayer;
    }


    @PostConstruct
    private void init(){
        players.add(new Player(id.incrementAndGet() , "Ravi Parihar ", Team.RCB));
        players.add(new Player(id.incrementAndGet() , "Rohit Sharma ", Team.MI));
        players.add(new Player(id.incrementAndGet() , "Steve Smith ", Team.KKR));
        players.add(new Player(id.incrementAndGet() , "Virat Kohli ", Team.RCB));
        players.add(new Player(id.incrementAndGet() , "Sachin Tendulkar ", Team.DC));
    }

}
