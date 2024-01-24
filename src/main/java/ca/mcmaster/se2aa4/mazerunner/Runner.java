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
        String pathTaken = "4F";
        int exitCoord = rowsList.get(0).size();

        log.info("Exploring Maze");

        log.info("Looking for West entrance");
        for(int i = 0;i<rowsList.size();i++){
            if (rowsList.get(i).get(0).equals(' ')){
                coordinates[1] = i;
            }
        }
        log.info(pathTaken);
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
    private String decideMove(String previousMove){
        if(wallCheck(moves.R)){ //Wall to your right
            if(wallCheck(moves.F)){ //Wall in front
                return "L";
            }else{//No wall in front
                return "F";
            }
        }else{//No wall
            if(wallCheck(moves.F)){//Wall in front
                return "R";
            }else{//No wall in front
                if(previousMove.equals("R")){//we just turned right
                    return "F";
                }else{//we came to a right corner
                    return "R";
                }
            }
        }
    }
    private boolean wallCheck(moves move){
        int[] movement = calculateDirection(move);
        int[] newCoords = {0,0};
        newCoords[0] = coordinates[0] + movement[0];
        newCoords[1] = coordinates[1] + movement[1];

        //Return if the viewed location is a wall
        return maze2D.get(newCoords[1]).get(newCoords[0]).equals('#');
    }

    private int[] calculateDirection(moves move){
        dir direction = facingDirection;
        int[] dirInt = {0,0};
        if(move == moves.R){
            switch(direction){
                case North:
                    direction = dir.East;
                case West:
                    direction = dir.North;
                case South:
                    direction = dir.West;
                case East:
                    direction = dir.South;
                default:
                    log.error("invalid direction R");
            }
        }else if(move == moves.L){
            switch(direction){
                case North:
                    direction = dir.West;
                case West:
                    direction = dir.South;
                case South:
                    direction = dir.East;
                case East:
                    direction = dir.North;
                default:
                    log.error("invalid direction L");
            }
        }
        switch(direction){
            case North:
                dirInt[1] = -1;
            case West:
                dirInt[0] = -1;
            case South:
                dirInt[1] = 1;
            case East:
                dirInt[0] = 1;
            default:
                log.error("invalid direction int[]");
        }
        return dirInt;
    }
}