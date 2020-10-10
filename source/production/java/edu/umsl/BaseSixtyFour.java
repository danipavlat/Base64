package edu.umsl;
import org.apache.commons.codec.binary.Base64;
import java.util.Scanner;

public class BaseSixtyFour {
    public static void main (String[] args){
        Scanner scanner = new Scanner(System.in);
        Base64 base64 = new Base64();
        char again;

        String encoded = "SSBsb3ZlIG15IENNUCBTQ0kgMjI2MSBjbGFzcyBzbyBtdWNoLCBJIHdpc2ggSSBjb3VsZCBiZSBqdXN0IGFzIGNvb2wgYXMgbXkgcHJvZmVzc29y";
        String decoded = new String(base64.decode(encoded.getBytes()));
        System.out.println("The coded message is:");
        System.out.println(decoded);

        do {
            encodeInput();
            again = Character.toUpperCase(scanner.next().charAt(0));
        } while (again == 'Y');

        encoded = new String(base64.encode("Goodbye!".getBytes()));
        decoded = new String(base64.decode(encoded.getBytes()));

        System.out.println(encoded + " (" + decoded + ")");
    }

    public static void encodeInput(){
        Scanner scanner = new Scanner(System.in);
        Base64 base64 = new Base64();

        System.out.println();
        System.out.println("Enter a string to encode your own message:");
        String input = scanner.nextLine();

        System.out.println();
        System.out.println("Here is your string in base64:");

        String encodedInput = new String(base64.encode(input.getBytes()));
        System.out.println(encodedInput);
        String decodedInput = new String(base64.decode(encodedInput.getBytes()));

        System.out.println("Here it is decoded again:");
        System.out.println(decodedInput);

        System.out.println();
        System.out.println("Would you like to encode something else? Enter Y for yes, or any other character to exit:");
    }
}
