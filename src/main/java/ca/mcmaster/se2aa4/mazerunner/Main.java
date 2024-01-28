package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.*;

public class Main {
    //mvn clean package
    //java -jar target/mazerunner.jar ./examples/direct.maz.txt

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws IOException, ParseException {
        Runner runner = new Runner();
        String mode = "explore";
        String path = "";
        String file = "";

        Options options = new Options();
        options.addOption("i", true, "Input file");
        options.addOption("p", true, "User path");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmdline = parser.parse(options, args);

        if (cmdline.hasOption("i")) {
            file = cmdline.getOptionValue("i");
        } else {
            throw new IllegalArgumentException("-i argument required");
        }

        if (cmdline.hasOption("p")) {
            mode = "verify";
            String pathSpaces = cmdline.getOptionValue("p");
            for (int i = 0; i<pathSpaces.length(); i++) {
                if (pathSpaces.charAt(i) != ' ') {
                    path += pathSpaces.charAt(i);
                }
            }
        }

        logger.info("** Starting Maze runner");

        if(mode.equals("explore")){
            runner.explore(file);
        }else if(mode.equals(("verify"))){
            runner.pathVerify(file, path);
        }
    }
}
