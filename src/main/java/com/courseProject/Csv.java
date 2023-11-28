package com.courseProject;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

//Gregory Sylvester
//Csv file is meant to initialize the interaction on the server side

public class Csv {

        //csv is the global file name
        private File CSV = null;

        //scanner is to take in the file
        private Scanner sc = null;

        //total line count needs to be updated when read file
        private int lineCount = 0;

        //Writer meant to write to the csv file
        private Writer wcsv = null;

        //constructor for initializing the file
        public Csv(String CSVpath){

                //try block to catch errors
            try {
                    //initilize global varibales
                CSV = new File(CSVpath);
                sc = new Scanner(CSV);
                wcsv = new BufferedWriter(new FileWriter(CSVpath, true));
                while (sc.hasNextLine()) {
                        //linecount global variable
                    sc.nextLine();
                    lineCount++;
                }
                resetScanner();
            } catch(Exception e) {
                    //error catch
                System.err.println("Error by CSV constructor: " + e.getMessage());
            }
        }

        //reset Scanner helps to reset the scanner when needed
        private void resetScanner(){
            try {
                    //close and reopen global csv file
                sc.close();
                sc = new Scanner(CSV);
            } catch (FileNotFoundException e) {
                System.err.println(e.getMessage());
            }
        }
        public String[][] readCSV(){
                //create a 2d array to return the id and vote value to the caller
                //tokenizer break up the file to read the file in
            String[][] dump = new String[lineCount][2];
            try {
                for(int i = 0; i < lineCount; i++){
                    StringTokenizer strTok = new StringTokenizer(sc.nextLine(), ",");
                    while (strTok.hasMoreTokens()) {
                            //fill the 2d array with the tokens
                        dump[i][0] = strTok.nextToken();
                        dump[i][1] = strTok.nextToken();
                    }
                }
            } catch(Exception e){
                    //return error message
                System.err.println("Error by readCSV method: " + e.getMessage());
            }
            return dump;
        }

        //append vote is the write method to the csv File
        //int Vote Value is the passed vote value to be recorded
        //id is the ID of the submission
        public void appendVote(int voteValue, String id){
            try{
                    //properly encode the write value to csv file
                String dumpStr = "\n" + Integer.toString(voteValue) + "," + id;
                wcsv.write(dumpStr);
                wcsv.close();
                    //reopen the writer
                wcsv = new BufferedWriter(new FileWriter(CSV, true));
            } catch (Exception e){
                    //return error message
                System.err.println("Error by appendVote method: " + e.getMessage());
            }
        }
}

