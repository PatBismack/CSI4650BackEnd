package com.courseProject;

public class Votes {
    int vote;
    String id;
    public Votes(){
        int vote = 0;
        String id = "";
    }

    public Votes(int vote, String id){
        this.vote = vote;
        this.id = id;
    }

    public int getVote(){
        return vote;
    }
    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }
    public void setValue(int vote){
        this.vote = vote;
    }
}
