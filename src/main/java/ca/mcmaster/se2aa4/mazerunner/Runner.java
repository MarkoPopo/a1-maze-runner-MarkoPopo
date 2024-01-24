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
                log.info("Found entrance at y = "+i);
            }
        }
        String previousMove = "";
        while(coordinates[0] != exitCoord){
            try {
                String newMove = decideMove(previousMove);

            previousMove = newMove;
            pathTaken += newMove;
            switch(newMove){
                case "F":
                    move();
                    log.info("Moved Forward");
                    break;
                case "L":
                    rotate(moves.L);
                    log.info("Rotated Left");
                    break;
                case "R":
                    rotate(moves.R);
                    log.info("Rotated Right");
                    break;
                default:
                    log.info("Impossible move");
                    break;
            }
            log.info(coordinates[0]+" "+coordinates[1]);
            log.info(facingDirection);
            } catch (Exception e) {
                log.error("Left Maze!");
                break;
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
        }else{//No wall on right
            log.error("no wall on right");
            if(wallCheck(moves.F)){//Wall in front
                return "R";
            }else{//No wall in front
                if(previousMove.equals("R")||previousMove.equals("")){//we just turned right or just started
                    return "F";
                }else{//we came to a right corner
                    return "R";
                }
            }
        }
    }

    private void move(){
        int[] movement = dirInt(calculateDirection(moves.F));
        int[] newCoords = {0,0};
        newCoords[0] = coordinates[0] + movement[0];
        newCoords[1] = coordinates[1] + movement[1];

        coordinates = newCoords;
    }

    private void rotate(moves move){
        facingDirection = calculateDirection(move);
    }

    private boolean wallCheck(moves move){
        int[] movement = dirInt(calculateDirection(move));
        int[] newCoords = {0,0};
        newCoords[0] = coordinates[0] + movement[0];
        newCoords[1] = coordinates[1] + movement[1];

        //Return if the viewed location is a wall
        return maze2D.get(newCoords[1]).get(newCoords[0]).equals('#');
    }

    private dir calculateDirection(moves move){
        dir direction = facingDirection;
        if(move == moves.R){
            switch(direction){
                case North:
                    direction = dir.East;
                    break;
                case West:
                    direction = dir.North;
                    break;
                case South:
                    direction = dir.West;
                    break;
                case East:
                    direction = dir.South;
                    break;
                default:
                    log.error("invalid direction R");
                    break;
            }
        }else if(move == moves.L){
            switch(direction){
                case North:
                    direction = dir.West;
                    break;
                case West:
                    direction = dir.South;
                    break;
                case South:
                    direction = dir.East;
                    break;
                case East:
                    direction = dir.North;
                    break;
                default:
                    log.error("invalid direction L");
                    break;
            }
        }
        return direction;
    }
    private int[] dirInt(dir direction){
        int[] dirInt = {0,0};
        switch(direction){
            case North:
                dirInt[1] = -1;
                break;
            case West:
                dirInt[0] = -1;
                break;
            case South:
                dirInt[1] = 1;
                break;
            case East:
                dirInt[0] = 1;
                break;
            default:
                log.error("invalid direction int[]");
                break;
        }
        return dirInt;
    }
}