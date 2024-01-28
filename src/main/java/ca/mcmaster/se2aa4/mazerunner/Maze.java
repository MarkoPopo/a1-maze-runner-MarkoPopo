package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Maze {             //Class that builds and stores the maze as characters
    

    public List<List<Character>> rowsList = new ArrayList<List<Character>>();  
    
    public void build(String file) throws IOException{

        int initialWidth = 0;
        final Logger log = LogManager.getLogger();

        log.info("**** Reading the maze from file " + file);

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;

        while ((line = reader.readLine()) != null) {    //Takes the file and builds a 2D array

            List<Character> row = new ArrayList<Character>();

            for (int idx = 0; idx < line.length(); idx++) {
                row.add(line.charAt(idx));
            }
            if(rowsList.size()==0){                      //If it's the first row, set that as the initial width
                initialWidth = row.size();
            }
            if(rowsList.size()>0){
                while(row.size()<initialWidth){          //If the row ends, fill it up with space characters
                    row.add(' ');
                }
            }

            rowsList.add(row);
            System.out.print(System.lineSeparator());
        }
    }

    public void debugprintMaze(){ //Quickly print the 2D array maze
        for(int i = 0;i<rowsList.size();i++){
            for(int j = 0;j<rowsList.get(i).size();j++){
                System.out.print(rowsList.get(i).get(j));
            }
            System.out.println("");
        }
    }
}
