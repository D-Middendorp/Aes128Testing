package com.Aes256Testing;

public class Main {
    private static final String[] SBOX = {
            "63", "7c", "77", "7b", "f2", "6b", "6f", "c5", "30", "01", "67", "2b", "fe", "d7", "ab", "76",
            "ca", "82", "c9", "7d", "fa", "59", "47", "f0", "ad", "d4", "a2", "af", "9c", "a4", "72", "c0",
            "b7", "fd", "93", "26", "36", "3f", "f7", "cc", "34", "a5", "e5", "f1", "71", "d8", "31", "15",
            "04", "c7", "23", "c3", "18", "96", "05", "9a", "07", "12", "80", "e2", "eb", "27", "b2", "75",
            "09", "83", "2c", "1a", "1b", "6e", "5a", "a0", "52", "3b", "d6", "b3", "29", "e3", "2f", "84",
            "53", "d1", "00", "ed", "20", "fc", "b1", "5b", "6a", "cb", "be", "39", "4a", "4c", "58", "cf",
            "d0", "ef", "aa", "fb", "43", "4d", "33", "85", "45", "f9", "02", "7f", "50", "3c", "9f", "a8",
            "51", "a3", "40", "8f", "92", "9d", "38", "f5", "bc", "b6", "da", "21", "10", "ff", "f3", "d2",
            "cd", "0c", "13", "ec", "5f", "97", "44", "17", "c4", "a7", "7e", "3d", "64", "5d", "19", "73",
            "60", "81", "4f", "dc", "22", "2a", "90", "88", "46", "ee", "b8", "14", "de", "5e", "0b", "db",
            "e0", "32", "3a", "0a", "49", "06", "24", "5c", "c2", "d3", "ac", "62", "91", "95", "e4", "79",
            "e7", "c8", "37", "6d", "8d", "d5", "4e", "a9", "6c", "56", "f4", "ea", "65", "7a", "ae", "08",
            "ba", "78", "25", "2e", "1c", "a6", "b4", "c6", "e8", "dd", "74", "1f", "4b", "bd", "8b", "8a",
            "70", "3e", "b5", "66", "48", "03", "f6", "0e", "61", "35", "57", "b9", "86", "c1", "1d", "9e",
            "e1", "f8", "98", "11", "69", "d9", "8e", "94", "9b", "1e", "87", "e9", "ce", "55", "28", "df",
            "8c", "a1", "89", "0d", "bf", "e6", "42", "68", "41", "99", "2d", "0f", "b0", "54", "bb", "16"};

    public static void main(String[] args) {
        String[][] testCypherKey = new String[4][44];
        testCypherKey[0][0] = "2b";
        testCypherKey[1][0] = "7e";
        testCypherKey[2][0] = "15";
        testCypherKey[3][0] = "16";

        testCypherKey[0][1] = "28";
        testCypherKey[1][1] = "ae";
        testCypherKey[2][1] = "d2";
        testCypherKey[3][1] = "a6";

        testCypherKey[0][2] = "ab";
        testCypherKey[1][2] = "f7";
        testCypherKey[2][2] = "15";
        testCypherKey[3][2] = "88";

        testCypherKey[0][3] = "09";
        testCypherKey[1][3] = "cf";
        testCypherKey[2][3] = "4f";
        testCypherKey[3][3] = "3c";

        String[][] testPlainMessage = new String[4][4];
        testPlainMessage[0][0] = "19";
        testPlainMessage[1][0] = "3d";
        testPlainMessage[2][0] = "e3";
        testPlainMessage[3][0] = "be";

        testPlainMessage[0][1] = "a0";
        testPlainMessage[1][1] = "f4";
        testPlainMessage[2][1] = "e2";
        testPlainMessage[3][1] = "2b";

        testPlainMessage[0][2] = "9a";
        testPlainMessage[1][2] = "c6";
        testPlainMessage[2][2] = "8d";
        testPlainMessage[3][2] = "2a";

        testPlainMessage[0][3] = "e9";
        testPlainMessage[1][3] = "f8";
        testPlainMessage[2][3] = "48";
        testPlainMessage[3][3] = "08";

        generateKeySchedule(testCypherKey);
        messageEncryption(testPlainMessage,testCypherKey);
    }

    public static void messageEncryption(String[][] testPlainMessage, String[][] testCypherKey) {
        boolean youtubeTest = false;
        if (youtubeTest) {
            //noinspection SpellCheckingInspection
            System.out.println("Before subbytes:");
            print16Block(testPlainMessage);
            System.out.println("---------------");
            subBytesTransformation(testPlainMessage);
            //noinspection SpellCheckingInspection
            System.out.println("After subbytes:");
            print16Block(testPlainMessage);
            System.out.println("---------------");

            shiftRows(testPlainMessage);

            System.out.println("After shiftRows");
            print16Block(testPlainMessage);
            System.out.println("---------------");

            System.out.println("After mixColumns");
            mixColumns(testPlainMessage);
            System.out.println("---------------");

            System.out.println("After addRoundKey");
            addRoundKey(testPlainMessage, testCypherKey, 1);
            System.out.println("---------------");
        }

        // Initial Round.
        addRoundKey(testPlainMessage,testCypherKey,0);

        // 9 Main Rounds.
        for (int i = 1; i <= 9; i++) {
            subBytesTransformation(testPlainMessage);
            shiftRows(testPlainMessage);
            mixColumns(testPlainMessage);
            addRoundKey(testPlainMessage,testPlainMessage,i);
        }

        subBytesTransformation(testPlainMessage);
        shiftRows(testPlainMessage);
        addRoundKey(testPlainMessage,testPlainMessage,10);
    }




    private static void print16Block(String[][] block) {
        for (int i = 0; i < 4; i++) {
            StringBuilder msg = new StringBuilder();
            for (int j = 0; j < 4; j++) {
                msg.append(block[i][j]).append(" ");
            }
            System.out.println(msg);
        }
    }

    private static String[] getColumn2dArray(String[][] arr, int x) {
        String[] colArr = new String[4];
        colArr[0] = arr[0][x];
        colArr[1] = arr[1][x];
        colArr[2] = arr[2][x];
        colArr[3] = arr[3][x];
        return colArr;
    }

    private static void setColumn2dArray(String[][] message, String[] col, int x) {
        message[0][x] = col[0];
        message[1][x] = col[1];
        message[2][x] = col[2];
        message[3][x] = col[3];
    }

    private static void shiftRows(String[][] message) {
        String[] hold = new String [4];
        for (int i = 1; i < 4; i++) {
            hold[0] = message[i][0];
            hold[1] = message[i][1];
            hold[2] = message[i][2];
            hold[3] = message[i][3];

            for (int j = 0; j < 4; j++) {
                if (j-i < 0) {
                    message[i][4-i+j] = hold[j];
                } else {
                    message[i][j-i] = hold[j];
                }
            }
        }
    }

    private static void mixColumns(String[][] message) {
        for (int i = 0; i < 4; i++) {
            String[] multipliedCol = mixColumnsMultiplyEntireColumn(getColumn2dArray(message, i));
            setColumn2dArray(message,multipliedCol,i);
        }
        print16Block(message);
    }

    // Taken from https://github.com/ajaytee/Comp3260
    private static byte mixColumnsMultiply(byte numRGF, byte messageInput) {
        byte returnValue = 0;
        byte temp;
        while (numRGF != 0) {
            if ((numRGF & 1) != 0)
                returnValue = (byte) (returnValue ^ messageInput);
            temp = (byte) (messageInput & 0x80);
            messageInput = (byte) (messageInput << 1);
            if (temp != 0)
                messageInput = (byte) (messageInput ^ 0x1b);
            numRGF = (byte) ((numRGF & 0xff) >> 1);
        }
        return returnValue;
    }

    private static String[] mixColumnsMultiplyEntireColumn(String[] messageCol) {
        char[] a = new char[4];
        char[] b = new char[4];
        int[] intResult = new int[4];
        String[] stringResult = new String[4];
        int highBit;

        for (int i = 0; i < 4; i++) {
            a[i] = (char) Integer.parseInt(messageCol[i],16);
            highBit = Integer.parseInt(messageCol[i],16) >> 7;
            if (highBit == 1) highBit = 0xff;
            b[i] = (char) (Integer.parseInt(messageCol[i],16) << 1);
            b[i] ^= 0x1b & highBit;
        }

        intResult[0] = b[0] ^ a[3] ^ a[2] ^ b[1] ^ a[1];
        intResult[1] = b[1] ^ a[0] ^ a[3] ^ b[2] ^ a[2];
        intResult[2] = b[2] ^ a[1] ^ a[0] ^ b[3] ^ a[3];
        intResult[3] = b[3] ^ a[2] ^ a[1] ^ b[0] ^ a[0];

        for (int i = 0; i < 4; i++) {
            if (intResult[i] > 255) intResult[i] ^= 256;
            stringResult[i] = String.format("%02x",intResult[i]);
        }

        return stringResult;

    }

    private static void addRoundKey(String[][] message, String[][] cypherKey, int round) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int numMessage = Integer.parseInt(message[j][i],16);
                int numCypher = Integer.parseInt(cypherKey[j][i+4*round],16);
                message[j][i] = String.format("%02x",numMessage ^ numCypher);
            }
        }
        print16Block(message);
    }

    private static void generateKeySchedule(String[][] cypherKey){
        for (int i = 4; i < 44; i++) {
            String[] previousFour = getColumn2dArray(cypherKey, i-1);

            if (i % 4 == 0) {
                rotWordTransformation(previousFour);
                subBytesTransformation(previousFour);
            }

            String[] colBack4 = getColumn2dArray(cypherKey, i - 4);
            hexXORTransformation(colBack4, previousFour, i);

            if (i > 0) {
                System.out.println("After XOR: " + i);
                System.out.println(previousFour[0]);
                System.out.println(previousFour[1]);
                System.out.println(previousFour[2]);
                System.out.println(previousFour[3]);
                System.out.println("-----------------------");
            }

            cypherKey[0][i] = previousFour[0];
            cypherKey[1][i] = previousFour[1];
            cypherKey[2][i] = previousFour[2];
            cypherKey[3][i] = previousFour[3];

        }
    }

    private static void rotWordTransformation(String[] arr) {
        String hold = arr[0];
        arr[0] = arr[1];
        arr[1] = arr[2];
        arr[2] = arr[3];
        arr[3] = hold;
    }

    private static void subBytesTransformation(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int x = Integer.parseInt(arr[i].substring(0,1),16);
            int y = Integer.parseInt(arr[i].substring(1,2),16);
            int index = x * 16 + y;
            arr[i] = SBOX[index];
        }
    }

    private static void subBytesTransformation(String[][] message) {
        for (int i = 0; i <4; i++) {
            for (int j = 0; j < 4; j++) {
                int x = Integer.parseInt(message[j][i].substring(0, 1), 16);
                int y = Integer.parseInt(message[j][i].substring(1, 2), 16);
                int index = x * 16 + y;
                message[j][i] = SBOX[index];
            }
        }
    }

    private static void hexXORTransformation(String[] colBack4 , String[] arr, int x) {
        final String[] RCON = {"01","00","00","00","02","00","00","00","04","00","00","00","08","00","00","00",
                               "10","00","00","00","20","00","00","00","40","00","00","00","80","00","00","00",
                               "1b","00","00","00","36","00","00","00"};

        for (int i = 0; i < 4; i++) {
            int numColBack4 = Integer.parseInt(colBack4[i], 16);
            int numArr = Integer.parseInt(arr[i], 16);
            int numRCON = 0;
            if (x % 4 == 0) numRCON = Integer.parseInt(RCON[x - 4 + i], 16);
            arr[i] = String.format("%02x", numColBack4 ^ numArr ^ numRCON);
        }
    }
}