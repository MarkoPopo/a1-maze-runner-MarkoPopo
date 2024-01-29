package ca.mcmaster.se2aa4.mazerunner;

public class Translator implements Navigation{

    public static String canonize(String path){         //Factor to canonical form
        
        String canonized = "";
        String repString = "";

        for(int i = 0;i<path.length();i++){                 
            if(Character.isDigit(path.charAt(i))){              //add digits to a string buffer
                repString += path.charAt(i);       

            }else if(Character.isAlphabetic(path.charAt(i))){   //Until it is an alphabetical character
                int reps = 1;
                try {
                    reps = Integer.valueOf(repString);          //add that character (reps) many times
                } catch (Exception e) {
                    reps = 1;
                }
                
                char duplicant = path.charAt(i); 
                
                for(int j = 0;j<reps;j++){
                    canonized += duplicant;
                }           
                
                repString = "";
            }
        }
        return canonized;
    }
    
    public static String factorize(String path){            //Canonical to factorized
        path += ' ';
        String factorized = "";
        char prevChar = ' ';
        int count = 1;

        for(int i = 0;i<path.length();i++){                 //Loop through the string
            if(path.charAt(i)==prevChar){                   //If it's the same as its previous, increase the current counter
                count++;
            }else{                                          //If not, combine the character with the number
                if(count>1){                                //For readability's sake, add spaces when needed and don't include 1s
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
