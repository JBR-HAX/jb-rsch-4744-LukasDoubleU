package org.jetbrains.assignment.endpoints;

import org.jetbrains.assignment.model.Location;
import org.jetbrains.assignment.model.Move;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Locations {

    @PostMapping("/locations")
    public List<Location> locations(@RequestBody List<Move> moves) {
        var locations = new ArrayList<Location>();
        var location = Location.start();
        locations.add(location);
        for(Move move : moves) {
            location = location.move(move);
            locations.add(location);
        }
        return locations;
    }


}

