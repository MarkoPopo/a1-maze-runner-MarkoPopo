package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Runner {

    enum dir{
        F,
        L,
        R
    }

    private static final Logger log = LogManager.getLogger();

    public static void explore(List<List<Character>> rowsList) {
        String pathTaken = "";
        log.info("Exploring Maze");
    }

    public static void check(List<List<Character>> rowsList, String path) {
        log.info("Checking path" + path);
        log.info("correct path");
    }
    private static String canonize(String path){
        return "";
    }
    private static String factorize(String path){
        return "";
    }

}
