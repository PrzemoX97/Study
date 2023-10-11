package com.demo.springboot;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class FileDTO {
    private static String FILENAME = "email_backup.csv";

    public static void addUserToFile(Mail mail) {
        File file = null;
        BufferedWriter bw = null;
        file = new File(FILENAME);
        try {
            Send.send(mail.getTo(),mail.getSubject(),mail.getBody());
            bw = new BufferedWriter(new FileWriter(file, true));
            bw.write(getId() + ";" + mail.getTo() + ";" + mail.getSubject() + ";" + mail.getBody()+";");
            bw.newLine();
            bw.flush();
        } catch (IOException err) {
            err.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (Exception err) {
                err.printStackTrace();
            }
        }
    }

    private static int getId(){
        FileReader fr = null;
        BufferedReader br = null;
        String idd="";
        try{
            br = new BufferedReader(new FileReader(FILENAME));
            String Line;
            while ((Line = br.readLine()) != null) {
                idd = Line.split(";")[0];
            }
        }catch(IOException err){
            err.printStackTrace();
        }finally {
            try{
                if(br != null){br.close();}
            }catch(IOException err){
                err.printStackTrace();
            }
        }
        if(idd.equals("ID")) return 1;
        return Integer.parseInt(idd)+1;
    }


}
