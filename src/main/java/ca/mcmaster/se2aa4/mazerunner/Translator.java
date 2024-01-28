package ca.mcmaster.se2aa4.mazerunner;

public class Translator {
    public static String canonize(String path){
        
        String canonized = "";

        for(int i = 0;i<path.length();i++){
            if(Character.isDigit(path.charAt(i))){          //If it's a number
                char duplicant = path.charAt(i+1);          //Get the next char
                int reps = Character.getNumericValue(path.charAt(i)) -1 ;

                for(int j = 0;j < reps;j++){                //Add it to the output repeated that many times
                    canonized+=duplicant;
                }
            }else if(Character.isAlphabetic(path.charAt(i))){
                char duplicant = path.charAt(i);            //If it's just a character, add it once
                canonized += duplicant;
            }
        }
        return canonized;
    }
    public static String factorize(String path){
        path += ' ';
        String factorized = "";
        char prevChar = ' ';
        int count = 1;

        for(int i = 0;i<path.length();i++){                 //Loop through the string
            if(path.charAt(i)==prevChar){                   //If it's the same as its previous, increase the current counter
                count++;
            }else{                                          //If not, combine the character with the number
                if(count>1){                                //For readability's sake, add when needed spaces and don't include 1s
                    factorized += ' ';
                    factorized += Integer.toString(count);
                    factorized += prevChar;
                    factorized += ' ';
                }else{
                    factorized += prevChar;
                }
                count = 1;                                  //Reset the counter
            }
            prevChar = path.charAt(i);
        }
        return factorized;
    }
}
