package com.courseProject;

public class Total {
    int yes;
    int no;
    int dontCare;

    public Total(){
        yes = 0;
        no = 0;
        dontCare = 0;
    }

    public Total(int yes, int no, int dontCare){
        this.yes = yes;
        this.no = no;
        this.dontCare = dontCare;
    }

    public void setYes(int yes){
        this.yes = yes;
    }

    public void setNo(int no){
        this.no = no;
    }

    public void setDontCare(int dontCare){
        this.dontCare = dontCare;
    }

    public int getYes(){
        return yes;
    }

    public int getNo(){
        return no;
    }

    public int getDontCare(){
        return dontCare;
    }
}
