package ca.mcmaster.se2aa4.mazerunner;

import ca.mcmaster.se2aa4.mazerunner.Navigation.dir;
import ca.mcmaster.se2aa4.mazerunner.Navigation.moves;

public interface scan {

    public boolean isInMaze(int[] coords);
    public boolean wallCheck(moves move, int[] coordinates, dir facingDirection);
    
}
