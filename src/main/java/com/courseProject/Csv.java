package com.courseProject;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Csv {
        private File CSV = null;
        private Scanner sc = null;
        private int lineCount = 0;
        private Writer wcsv = null;
        public Csv(String CSVpath){
            try {
                CSV = new File(CSVpath);
                sc = new Scanner(CSV);
                wcsv = new BufferedWriter(new FileWriter(CSVpath, true));
                while (sc.hasNextLine()) {
                    sc.nextLine();
                    lineCount++;
                }
                resetScanner();
            } catch(Exception e) {
                System.err.println("Error by CSV constructor: " + e.getMessage());
            }
        }

        //readCSV - returns a 2d array which contains the current instances of voted values
        private void resetScanner(){
            try {
                sc.close();
                sc = new Scanner(CSV);
            } catch (FileNotFoundException e) {
                System.err.println(e.getMessage());
            }
        }
        public String[][] readCSV(){
            String[][] dump = new String[lineCount][2];
            try {
                for(int i = 0; i < lineCount; i++){
                    StringTokenizer strTok = new StringTokenizer(sc.nextLine(), ",");
                    while (strTok.hasMoreTokens()) {
                        dump[i][0] = strTok.nextToken();
                        dump[i][1] = strTok.nextToken();
                    }
                }
            } catch(Exception e){
                System.err.println("Error by readCSV method: " + e.getMessage());
            }
            return dump;
        }
        public void appendVote(int voteValue, String id){
            try{
                String dumpStr = "\n" + Integer.toString(voteValue) + "," + id;
                wcsv.write(dumpStr);
                wcsv.close();
                wcsv = new BufferedWriter(new FileWriter(CSV, true));
            } catch (Exception e){
                System.err.println("Error by appendVote method: " + e.getMessage());
            }
        }
}

