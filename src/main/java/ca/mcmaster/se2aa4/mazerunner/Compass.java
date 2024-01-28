package ca.mcmaster.se2aa4.mazerunner;

import ca.mcmaster.se2aa4.mazerunner.Navigation.dir;
import ca.mcmaster.se2aa4.mazerunner.Navigation.moves;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Compass implements Navigation{                             //Class for computing directions

    private static final Logger log = LogManager.getLogger();

    public static dir calculateDirection(moves move, dir facingDirection){    //Returns the new direction NWSE after a move FLR
        dir direction = facingDirection;
        if(move == moves.R){
            switch(direction){
                case North:
                    direction = dir.East;                                     //Example: if you face north and turn right you are now east
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
    public static int[] dirInt(moves move, dir direction){                  //Returns the [x,y] offset of a cardinal direction

        dir newDirection = calculateDirection(move, direction);

        int[] dirInt = {0,0};                                           
        switch(newDirection){
            case North:
                dirInt[1] = -1;                                             //Example: North would be -1 in the y component of the list
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
