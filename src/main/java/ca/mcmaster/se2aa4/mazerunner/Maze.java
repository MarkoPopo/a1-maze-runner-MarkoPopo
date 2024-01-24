package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Maze {

    Runner runner = new Runner();

    public static List<List<Character>> rowsList = new ArrayList<List<Character>>();  
    
    private static void build(String file) throws IOException{
        final Logger log = LogManager.getLogger();

        //Takes the file and builds a 2D array

        log.info("**** Reading the maze from file " + file);

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {

            List<Character> row = new ArrayList<Character>(); //Create row

            for (int idx = 0; idx < line.length(); idx++) {
                row.add(line.charAt(idx));
                
                if (line.charAt(idx) == '#') {
                    log.info("WALL ");
                } else if (line.charAt(idx) == ' ') {
                    log.info("PASS ");
                }
            }
            rowsList.add(row);
            System.out.print(System.lineSeparator());
        }
    }

    public static void debugprintMaze(){ //Quickly print the 2D array maze
        for(int i = 0;i<rowsList.size();i++){
            for(int j = 0;j<rowsList.get(i).size();j++){
                System.out.print(rowsList.get(i).get(j));
            }
            System.out.println("");
        }
    }

    public void checkPath(String file, String path) throws IOException {
        build(file);
        runner.pathVerify(rowsList, path);
    }

    public void run(String file) throws IOException {
        build(file);
        runner.explore(rowsList);
    }
}
