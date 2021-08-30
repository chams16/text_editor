import java.util.Scanner;

public class CesarCipher {
    public static String encrypt(String input, int key){
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMOPQRSTUVWXYZ";

        String shiftedAlphabet = alphabet.substring(key)+alphabet;

        for (int i=0; i<encrypted.length();i++){
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(currChar);

            if (idx != -1){
                char newChar = shiftedAlphabet.charAt(idx);
                encrypted.setCharAt(i,newChar);
            }
        }
        return encrypted.toString();
    }
    public static void main(String args[]){
        int key = 17;
        int inv = 26-key;
        Scanner sc = new Scanner(System.in);
        System.out.println("entrer la phrase a crypter");
        String input = sc.nextLine();
        String encrypted = encrypt(input,key);
        System.out.println("phrase crypté:" + encrypted);
        String decrypted = encrypt(encrypted,inv);
        System.out.println("phrase decrypté: " + decrypted);
    }
}
