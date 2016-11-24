package util;


import server.Deer;
import server.DeerTest;

import java.util.*;

/**
 * Created by white on 2016-11-07.
 */
public class DeerDashboard {
    public final static String STOP_COMMAND="stop server";
    public final static String EMPTY_Line=" Type \"help\", \"copyright\", \"credits\", \"license\" for more information or \"stop server\" for stoping server!";
    private static Scanner scanner;
    private static String command="";
    private static DeerDashboard mDeerDashbord;
    private static ArrayList<String> history;
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_GREEN = "\u001B[41m";
    public static final String ANSI_RESET = "\u001B[0m";


    private DeerDashboard(){
        scanner=new Scanner(System.in);
        history=new ArrayList<String>();
    }

    public static void startDashboard(){
        if(mDeerDashbord==null){
            mDeerDashbord=new DeerDashboard();
            System.out.println("");
        }
        while (!command.equals(DeerDashboard.STOP_COMMAND)){
            try {
                command="";
                System.out.print(" > ");
                command=scanner.nextLine();
                if(command.length() <1){
                    System.out.println(" > "+ANSI_PURPLE+ EMPTY_Line+ANSI_RESET);
                }else {
                    history.add(command);
                    System.out.println(" > "+ANSI_GREEN+commandParser(command)+ANSI_RESET);
                }

            }catch (Exception e){

            }


        }
    }

    private static String commandParser(String command){

        if(command.equals("t")){
            DeerTest.getInstance(Deer.getHostName(),Deer.getPort()).connectToServer();
            return "connection tested!";
        }else if(command.startsWith("history")){
            for(int i=0;i<history.size();i++){
                System.out.println(i+" - "+history.get(i));
            }
        }else if(command.startsWith("client number")){
            ArrayList<UUID> uuids=UserManager.getClientsId();
            if(uuids!=null){
                if(uuids.size()<1)
                    System.out.println("no client connected!");
                else
                    System.out.println("size = "+uuids.size());
            }
        }
        history.remove(command);
        return "cant resolve command!";
    }
}
