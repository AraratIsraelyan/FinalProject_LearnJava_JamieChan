package com.fitnessClub;

import java.io.*;
import java.util.LinkedList;

public class FileHandler {

    public LinkedList<Member> readFile(){
        LinkedList<Member> members = new LinkedList<>();
        String lineRead;
        String[] splitLine;
        Member mem;

        try (BufferedReader reader = new BufferedReader(new FileReader("members.csv"))){
            lineRead = reader.readLine();
            while (lineRead != null){
                splitLine = lineRead.split(", ");

                int memberID = Integer.parseInt(splitLine[1]);
                String name = splitLine[2];
                double fees = Double.parseDouble(splitLine[3]);
                int parameter = Integer.parseInt(splitLine[4]);

                if (splitLine[0].equals("S")){
                    mem = new SingleClubMember('S', memberID, name, fees, parameter);
                }
                else {
                    mem = new SingleClubMember('M', memberID, name, fees, parameter);
                }
                members.add(mem);
                lineRead = reader.readLine();
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }

        return members;
    }

    public void appendFile(String mem){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("members.csv", true))){
            writer.write(mem + "\n");
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    };
    public void overWriteFile(LinkedList<Member> m){
        String s;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("members.temp", false))){
            for (int i = 0; i < m.size(); i++) {
                s = m.get(i).toString();
                writer.write(s + "\n");
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        try {
            File f = new File("members.csv");
            File tf = new File("members.temp");
            f.delete();
            tf.renameTo(f);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }



}
