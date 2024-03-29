package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Arrays;

public class Runner implements Navigation{                      //The class that travels through the maze with the right hand rule

    int[] coordinates = {0,0};
    dir facingDirection = dir.East;

    public Maze maze2D = new Maze();
    ScanMaze scanner = new ScanMaze(maze2D);

    private static final Logger log = LogManager.getLogger();

    public void explore(String file) throws IOException {
        
        log.info("Exploring Maze");

        maze2D.build(file);
        
        coordinates = scanner.returnWestEntrance();
        int[] exitCoord = scanner.returnEastEntrance();

        String pathTaken = "";
        String previousMove = "";

        while(!Arrays.equals(coordinates, exitCoord)){                        //until you reach the exit,
            String newMove = decideMove(previousMove);          //Decide on a move and perform it

            previousMove = newMove;
            pathTaken += newMove;
            switch(newMove){
                case "F":
                    move();
                    break;
                case "L":
                    rotate(moves.L);
                    break;
                case "R":
                    rotate(moves.R);
                    break;
                default:
                    log.info("Impossible move");
                    break;
            }            
        }
        log.info(Translator.factorize(pathTaken));
    }

    public void bothWaysVerify(String file, String path) throws IOException{
        maze2D.build(file);

        pathVerify(file, path, dir.East);
        pathVerify(file, path, dir.West);
    }

    private void pathVerify(String file, String path,dir direction) throws IOException {       //Tries the inputted path by the user
        
        path = Translator.canonize(path);                                                   //Translate to canonical form

        facingDirection = direction;
        int[] exitCoord = {0,0};

        if(direction == dir.East){
            log.info("Checking path from WEST entrance towards EAST exit");

            coordinates = scanner.returnWestEntrance();
            exitCoord = scanner.returnEastEntrance();
        }else{
            log.info("Checking path from EAST entrance towards WEST exit");

            coordinates = scanner.returnEastEntrance();
            exitCoord = scanner.returnWestEntrance();
        }
        
        int wallsHit = 0;
        boolean passedExit= false;

        for(int i=0;i<path.length();i++){                                           //For each character, 
            switch(path.charAt(i)){
                case 'F':
                    if(scanner.wallCheck(moves.F, coordinates, facingDirection)){   //If you move into a wall, count the wall
                        wallsHit++; 
                    }
                    move();
                    break;
                case 'L':
                    rotate(moves.L);
                    break;
                case 'R':
                    rotate(moves.R);
                    break;
                default:
                    log.info("Not a real move: Please put F,L or R");
                    break;
            }

            if(!scanner.isInMaze(coordinates)){                                     //Check if it's still in the maze bounds
                log.info("Left maze bounds.");
                break;
            }
            if(Arrays.equals(coordinates,exitCoord)){                               //If it reaches the exit at some point
                passedExit = true;
            }
        }
        if((wallsHit>0)&(passedExit==false)){                                 //Display a message that tells what the user did right/wrong
            log.info("NOT a correct path! You miss the exit and hit "+wallsHit+" walls!");
        }else if((wallsHit>0)&(passedExit==true)){
            log.info("NOT a correct path! You went through "+wallsHit+" walls!");
        }else if((wallsHit==0)&(passedExit==false)){
            log.info("NOT a correct path! You didn't reach the exit");
        }else{
            log.info("Correct path");
        }
    }

    private void move(){
        int[] movement = Compass.dirInt(moves.F, facingDirection);
        int[] newCoords = {0,0};
        newCoords[0] = coordinates[0] + movement[0];
        newCoords[1] = coordinates[1] + movement[1];

        coordinates = newCoords;
    }

    private void rotate(moves move){
        facingDirection = Compass.calculateDirection(move, facingDirection);
    }

    private String decideMove(String previousMove){                           //Right hand algorithm implementation
        if(scanner.wallCheck(moves.R,coordinates,facingDirection)){                 
            if(scanner.wallCheck(moves.F,coordinates,facingDirection)){             
                return "L";                     //Turn Left if there's walls to the right and front
            }else{                              
                return "F";                     //Forward if there's no wall in front
            }
        }else{                                 
            if(scanner.wallCheck(moves.F,coordinates,facingDirection)){
                return "R";                     //Right if wall in front but none to the right
            }else{
                if(previousMove.equals("R")||previousMove.equals("")){
                    return "F";                 //We must have turned the corner already, so go forward
                }else{                          
                    return "R";                 //We just ran into a right corner, so turn right
                }
            }
        }
    }
}