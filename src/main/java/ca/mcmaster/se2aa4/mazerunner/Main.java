package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    //mvn clean package
    //java -jar target/mazerunner.jar ./examples/small.maz.txt
    private static final Logger log = LogManager.getLogger();

    public static void main(String[] args) throws IOException {
        
        enum Mode {
            PathCheck,
            Solve
        }

        String file = args[0];
        String path = "";
        Mode currentMode = Mode.Solve;

        //Check -i and -p args
        for(int i=0;i<args.length;i++){
            if((args[i].equals("-i"))||(args[i].equals("--input"))){
                file = args[i+1];
                break;
            } else if(args[i].equals("-p")){
                currentMode = Mode.PathCheck;
                path = args[i+1];
            }
        }

        //Choose what to do based on mode
        if(currentMode == Mode.Solve){
            Maze.run(file);
        }else{
            Maze.checkPath(file,path);
        }
    }
}
