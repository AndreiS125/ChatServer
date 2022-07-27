package org.andreis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class History {
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static void wrtEnd(Message m){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String msg = gson.toJson(m);
        msg=msg.replaceAll("\n","");
        byte[] inData={};
        try {
            FileInputStream in = new FileInputStream("history.txt");
             inData= in.readAllBytes();
             in.close();
        }
        catch (Exception e){

        }

        byte[] outData = msg.getBytes();
        try (FileOutputStream out = new FileOutputStream("history.txt")) {

            System.out.println(new String(inData));
            byte[] combined = new byte[outData.length + inData.length];

            for (int i = 0; i < combined.length; ++i)
            {
                combined[i] = i < inData.length ? inData[i] : outData[i - inData.length];
            }

            out.write(new String(combined).replaceAll("\r\n","").replaceAll("}","}\r\n").getBytes());

            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<Message> readEnd(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        byte[] inData;
        try (FileInputStream in = new FileInputStream("history.txt")) {
            inData=in.readAllBytes();

            in.close();
            String[] list = new String(inData, StandardCharsets.UTF_8).split(System.lineSeparator());
            ArrayList<Message> exit = new ArrayList<>();
            for (String s : list){
                Message mes=gson.fromJson(s,Message.class);
                exit.add(mes);
                in.close();
            }
            return exit;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
