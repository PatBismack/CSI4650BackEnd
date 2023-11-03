package com.courseProject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class Controller {

    @GetMapping("/getAllVotes") List<Votes> Read(){
        int vote;
        String voteValue;
        String id;

        String CSVpath = "src/main/java/com/courseProject/Votes.csv";

        List <Votes> voteList = new ArrayList<>();
        Csv file = new Csv(CSVpath);
            String[][] data = file.readCSV();
        for (int i = 0; i < data.length; i++){
            voteValue = data[i][0];
                vote = Integer.parseInt(voteValue);
            id = data[i][1];
                voteList.add(new Votes(vote, id));
        }
            return voteList;
    }

    @GetMapping("/getTotal") Total voteTotal(@RequestBody Total total){
        int vote;
        int yes = 0;
        int no = 0;
        int dontCare = 0;
        String voteValue;
        String id;

        String CSVpath = "src/main/java/com/courseProject/Votes.csv";

        Total voteTotal = new Total();

        Csv file = new Csv(CSVpath);
        String[][] data = file.readCSV();
        for (int i = 0; i < data.length; i++){
            voteValue = data[i][0];
            vote = Integer.parseInt(voteValue);
            if(vote == 0){
                yes++;
                voteTotal.setYes(yes);
            }
            else if(vote == 1){
                no++;
                voteTotal.setNo(no);
            }
            else if(vote == 2){
                dontCare++;
                voteTotal.setDontCare(dontCare);
            }
        }
            return voteTotal;
    }

    @PostMapping("/vote") String Write(@RequestBody Votes votes){
        int vote = votes.getVote();
        String id = votes.getId();

        String CSVpath = "src/main/java/com/courseProject/Votes.csv";

        Csv file = new Csv(CSVpath);
            file.appendVote(vote, id);
                return "Vote was recorded successfully: Vote " + vote + " Id: " + id;
    }
}
