package ca.mcmaster.se2aa4.mazerunner;

public class Translator {
    public static String canonize(String path){
        
        String canonized = "";

        for(int i = 0;i<path.length();i++){
            if(Character.isDigit(path.charAt(i))){      //If it's a number
                char duplicant = path.charAt(i+1);      //Get the next char
                int reps = Character.getNumericValue(path.charAt(i)) -1 ;

                for(int j = 0;j < reps;j++){                 //Add it to the path i times
                    canonized+=duplicant;
                }
            }else if(Character.isAlphabetic(path.charAt(i))){
                char duplicant = path.charAt(i);
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

        for(int i = 0;i<path.length();i++){
            if(path.charAt(i)==prevChar){
                count++;
            }else{
                if(count>1){
                    factorized += ' ';
                    factorized += Integer.toString(count);
                    factorized += prevChar;
                    factorized += ' ';
                }else{
                    factorized += prevChar;
                }
                count = 1;
            }
            prevChar = path.charAt(i);
        }
        return factorized;
    }
}
