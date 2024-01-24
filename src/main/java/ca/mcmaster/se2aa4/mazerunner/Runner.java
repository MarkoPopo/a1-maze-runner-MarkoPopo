package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Runner {

    int[] coordinates = {0,0};
    dir facingDirection = dir.East;

    public List<List<Character>> maze2D = new ArrayList<List<Character>>();

    enum dir{
        North,
        West,
        South,
        East
    }
    enum moves{
        F,
        L,
        R
    }

    private static final Logger log = LogManager.getLogger();

    public void explore(List<List<Character>> rowsList) {
        maze2D = rowsList;
        String pathTaken = "";
        int exitCoord = rowsList.get(0).size();

        log.info("Exploring Maze");
        
        log.info("Looking for West entrance");
        for(int i = 0;i<rowsList.size();i++){
            if (rowsList.get(i).get(0).equals(' ')){
                coordinates[1] = i;
            }
        }
        
    }

    public void pathVerify(List<List<Character>> rowsList, String path) {
        maze2D = rowsList;
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