package org.jetbrains.assignment.endpoints;

import org.jetbrains.assignment.model.Location;
import org.jetbrains.assignment.model.Move;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Moves {
    @PostMapping("/moves")
    public List<Move> moves(@RequestBody List<Location> locations) {
        var moves = new ArrayList<Move>();
        var currentLoc = locations.get(0);
        for(Location l : locations) {
            if(currentLoc.equals(l)) continue;
            moves.add(currentLoc.findMove(l));
            currentLoc = l;
        }
        return moves;
    }
}
