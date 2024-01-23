package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    //mvn clean package
    //java -jar target/mazerunner.jar ./examples/small.maz.txt
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {

        String file = args[0];
        for(int i=0;i<args.length;i++){
            if((args[i].equals("-i"))||(args[i].equals("--input"))){
                file = args[i+1];
                break;
            }
        }

        logger.info("** Starting Maze runner");
        try {
            logger.info("**** Reading the maze from file " + file);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        logger.info("WALL ");
                    } else if (line.charAt(idx) == ' ') {
                        logger.info("PASS ");
                    }
                }
                System.out.print(System.lineSeparator());
            }
        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }
        logger.info("**** Computing path");
        logger.info("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}
