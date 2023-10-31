package com.courseProject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class Controller {

    @GetMapping("/get") List<Votes> Read(){
        String voteValue;
        String id;
        int vote;

        String CSVpath = "src/main/java/com/courseProject/Votes.csv";

        List <Votes> voteList = new ArrayList<>();
        Csv file = new Csv(CSVpath);
            String[][] data = file.readCSV();
        for (int i = 0; i < data.length; i++) {
            voteValue = data[i][0];
                vote = Integer.parseInt(voteValue);
            id = data[i][1];
                voteList.add(new Votes(vote, id));
        }
        return voteList;
    }

    @PostMapping("/post") String Write(@RequestBody Votes votes){
        String id = votes.getId();
        int vote = votes.getVote();

        String CSVpath = "src/main/java/com/courseProject/Votes.csv";

        Csv file = new Csv(CSVpath);
            file.appendVote(vote, id);
        return "Vote was recorded successfully: Vote " + vote + " Id: " + id;
    }
}
