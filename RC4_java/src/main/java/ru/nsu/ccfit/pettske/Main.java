package ru.nsu.ccfit.pettske;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.println("Input a test line: ");
        String testLine = console.nextLine();

        System.out.println("Input a key: ");
        String key = console.nextLine();

        RC4 rc4Encode = new RC4(key.toCharArray());
        char[] encodedLine = rc4Encode.Encode(testLine.toCharArray(), testLine.length());
        System.out.println("Encoded line: ");
        System.out.println(encodedLine);

        RC4 rc4Decode = new RC4(key.toCharArray());
        char[] decodedLine = rc4Decode.Decode(encodedLine, encodedLine.length);
        System.out.println("Decoded line: ");
        System.out.println(decodedLine);
    }
}
