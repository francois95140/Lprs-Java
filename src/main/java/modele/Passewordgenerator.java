package modele;
import java.util.Random;

public class Passewordgenerator {
    private static final String ALPHABET_LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String ALPHABET_UPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final String ALPHABET_AND_NUMBERS = ALPHABET_LOWERCASE + ALPHABET_UPERCASE + NUMBERS;
    private static Random  random = new Random();

    public static String codeGenerate (int length){
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < length ; i++){
            int randomIdex = random.nextInt(ALPHABET_AND_NUMBERS.length());
            sb.append(ALPHABET_AND_NUMBERS.charAt(randomIdex));
        }
        return sb.toString();
    }

}
