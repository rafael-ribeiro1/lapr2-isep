package isep.dei.lapr2.algorithms;

/**
 * Algorithm to generate random password with specified length
 */
public class PasswordGenerator {
    /**
     * Upper case letters
     */
   final private static String lettersUpperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /**
     * Lower case letters
     */
   final private static String lettersLowerCase =lettersUpperCase.toLowerCase();

    /**
     * Generates and returns a random password with the specified length
     * @param length size of the password
     * @return the random password
     */
   public String generatePassword(int length){
        String password ="";
        for(int x=0; x<length;x++ ){
            int p = generateRandomInt(1,3);
            switch(p){
                case 1 :
                    password+=lettersUpperCase.charAt(generateRandomInt(0,lettersLowerCase.length()-1));
                    break;
                case 2:
                    password+=lettersLowerCase.charAt(generateRandomInt(0,lettersLowerCase.length()-1));
                    break;
                case 3 :
                    password+=generateRandomInt(0,9);
                    break;
            }
        }
        return password;
   }

    /**
     * Generates a random integer number between two numbers
     * @param min minimum value to random number
     * @param max maximum value to random number
     * @return the randomized integer
     */
   private int generateRandomInt(int min, int max){
       return (int)(Math.random() * ((max - min) + 1)) + min;
   }


}
