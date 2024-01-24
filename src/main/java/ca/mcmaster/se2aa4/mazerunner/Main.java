package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    //mvn clean package
    //java -jar target/mazerunner.jar ./examples/small.maz.txt
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws IOException {

        Maze maze = new Maze();
        String file = args[0];
        String mode = "explore";
        String path = "";

        for(int i=0;i<args.length;i++){
            if((args[i].equals("-i"))||(args[i].equals("--input"))){
                file = args[i+1];
                break;
            }else if(args[i].equals("-p")){
                mode = "verify";
                path = args[i+1];
            }
        }

        if(mode.equals("explore")){
            maze.explorePath(file);
        }else{
            maze.checkPath(file, path);
        }


        logger.info("** Starting Maze runner");
        
    }
}
