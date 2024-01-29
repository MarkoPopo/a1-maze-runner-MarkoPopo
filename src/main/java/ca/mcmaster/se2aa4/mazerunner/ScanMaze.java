package ca.mcmaster.se2aa4.mazerunner;

public class ScanMaze implements Navigation{    //Class to get information about the maze (Eg. height, width, entrance location, wall)

    Maze maze2D;

    public ScanMaze(Maze maze){
        maze2D = maze;
    }
    
    public boolean wallCheck(moves move, int[] coordinates, dir facingDirection){   //Check if a position in the maze is a wall or not
        int[] movement = Compass.dirInt(move, facingDirection);
        int[] newCoords = {0,0};
        newCoords[0] = coordinates[0] + movement[0];
        newCoords[1] = coordinates[1] + movement[1];

        try {                           
            return maze2D.rowsList.get(newCoords[1]).get(newCoords[0]).equals('#');
        } catch (Exception e) {
            return false;               //If out of bounds, return no wall
        }
        
    }

    public boolean isInMaze(int[] coords){      //Check if location in maze is in bounds
        if(coords[0]<0||coords[0]>=mazeWidth()){
            return false;
        }else if(coords[1]<0||coords[1]>=mazeHeight()){
            return false;
        }else{
            return true;
        }
    }

    public int[] returnWestEntrance(){      //Loops through the rows (y) to find the first space at x = 0 
        int[] coords = {0,0};
        for(int i = 0;i<maze2D.rowsList.size();i++){
            if (maze2D.rowsList.get(i).get(0).equals(' ')){
                coords[1] = i;
            }
        }
        return coords;
    }
    public int[] returnEastEntrance(){      //Loops through the rows (y) to find the first space at x = width 
        int[] coords = {0,0};
        coords[0] = mazeWidth()-1;
        for(int i = 0;i<maze2D.rowsList.size();i++){
            if (maze2D.rowsList.get(i).get(mazeWidth()-1).equals(' ')){
                coords[1] = i;
            }
        }
        return coords;
    }
    public int mazeWidth(){
        return maze2D.rowsList.get(0).size();
    }
    public int mazeHeight(){
        return maze2D.rowsList.size();
    }
}
