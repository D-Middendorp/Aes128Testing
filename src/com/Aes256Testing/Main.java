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

    private static final String[] INVSBOX = {
            "52", "09", "6a", "d5", "30", "36", "a5", "38", "bf", "40", "a3", "9e", "81", "f3", "d7", "fb",
            "7c", "e3", "39", "82", "9b", "2f", "ff", "87", "34", "8e", "43", "44", "c4", "de", "e9", "cb",
            "54", "7b", "94", "32", "a6", "c2", "23", "3d", "ee", "4c", "95", "0b", "42", "fa", "c3", "4e",
            "08", "2e", "a1", "66", "28", "d9", "24", "b2", "76", "5b", "a2", "49", "6d", "8b", "d1", "25",
            "72", "f8", "f6", "64", "86", "68", "98", "16", "d4", "a4", "5c", "cc", "5d", "65", "b6", "92",
            "6c", "70", "48", "50", "fd", "ed", "b9", "da", "5e", "15", "46", "57", "a7", "8d", "9d", "84",
            "90", "d8", "ab", "00", "8c", "bc", "d3", "0a", "f7", "e4", "58", "05", "b8", "b3", "45", "06",
            "d0", "2c", "1e", "8f", "ca", "3f", "0f", "02", "c1", "af", "bd", "03", "01", "13", "8a", "6b",
            "3a", "91", "11", "41", "4f", "67", "dc", "ea", "97", "f2", "cf", "ce", "f0", "b4", "e6", "73",
            "96", "ac", "74", "22", "e7", "ad", "35", "85", "e2", "f9", "37", "e8", "1c", "75", "df", "6e",
            "47", "f1", "1a", "71", "1d", "29", "c5", "89", "6f", "b7", "62", "0e", "aa", "18", "be", "1b",
            "fc", "56", "3e", "4b", "c6", "d2", "79", "20", "9a", "db", "c0", "fe", "78", "cd", "5a", "f4",
            "1f", "dd", "a8", "33", "88", "07", "c7", "31", "b1", "12", "10", "59", "27", "80", "ec", "5f",
            "60", "51", "7f", "a9", "19", "b5", "4a", "0d", "2d", "e5", "7a", "9f", "93", "c9", "9c", "ef",
            "a0", "e0", "3b", "4d", "ae", "2a", "f5", "b0", "c8", "eb", "bb", "3c", "83", "53", "99", "61",
            "17", "2b", "04", "7e", "ba", "77", "d6", "26", "e1", "69", "14", "63", "55", "21", "0c", "7d"};

    private static String[][] cypherKey = new String[4][44];
    private static String[][] plainMessage = new String[4][4];
    private static boolean youTubeTest = false;

    public static void main(String[] args) {
        String testInput = "1234567890abcdef";
        String testCypher = "abcdef1234567890";

        if (!youTubeTest) {
            char[] testInputArray = testInput.toCharArray();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    int tempInt = testInputArray[(i*4)+j];
                    plainMessage[j][i] = String.format("%02x", tempInt);
                }
            }

            testInputArray = testCypher.toCharArray();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    int tempInt = testInputArray[(i*4)+j];
                    cypherKey[j][i] = String.format("%02x", tempInt);
                }
            }
        }

        if (youTubeTest) {
            cypherKey[0][0] = "2b";
            cypherKey[1][0] = "7e";
            cypherKey[2][0] = "15";
            cypherKey[3][0] = "16";

            cypherKey[0][1] = "28";
            cypherKey[1][1] = "ae";
            cypherKey[2][1] = "d2";
            cypherKey[3][1] = "a6";

            cypherKey[0][2] = "ab";
            cypherKey[1][2] = "f7";
            cypherKey[2][2] = "15";
            cypherKey[3][2] = "88";

            cypherKey[0][3] = "09";
            cypherKey[1][3] = "cf";
            cypherKey[2][3] = "4f";
            cypherKey[3][3] = "3c";

            plainMessage[0][0] = "19";
            plainMessage[1][0] = "3d";
            plainMessage[2][0] = "e3";
            plainMessage[3][0] = "be";

            plainMessage[0][1] = "a0";
            plainMessage[1][1] = "f4";
            plainMessage[2][1] = "e2";
            plainMessage[3][1] = "2b";

            plainMessage[0][2] = "9a";
            plainMessage[1][2] = "c6";
            plainMessage[2][2] = "8d";
            plainMessage[3][2] = "2a";

            plainMessage[0][3] = "e9";
            plainMessage[1][3] = "f8";
            plainMessage[2][3] = "48";
            plainMessage[3][3] = "08";
        }

        generateKeySchedule();
        messageEncryption();
    }

    public static void messageEncryption() {
        if (youTubeTest) {
            //noinspection SpellCheckingInspection
            System.out.println("Before subbytes:");
            print16Block(plainMessage);
            System.out.println("---------------");
            subBytesTransformation(plainMessage);
            //noinspection SpellCheckingInspection
            System.out.println("After subbytes:");
            print16Block(plainMessage);
            System.out.println("---------------");

            shiftRows(plainMessage);

            System.out.println("After shiftRows");
            print16Block(plainMessage);
            System.out.println("---------------");

            System.out.println("After mixColumns");
            mixColumns(plainMessage);
            System.out.println("---------------");

            System.out.println("After addRoundKey");
            addRoundKey(plainMessage, cypherKey, 1);
            System.out.println("---------------");
        }

        if (!youTubeTest) {
            // Initial Round.
            addRoundKey(plainMessage, cypherKey, 0);

            // 9 Main Rounds.
            for (int i = 1; i <= 9; i++) {
                subBytesTransformation(plainMessage);
                shiftRows(plainMessage);
                mixColumns(plainMessage);
                System.out.println(i);
                addRoundKey(plainMessage, cypherKey, i);
            }

            subBytesTransformation(plainMessage);
            shiftRows(plainMessage);
            addRoundKey(plainMessage, cypherKey, 10);

            print16Block(plainMessage);
            //print16Block();

            /*for (int i = 0; i < 4; i++) {
                StringBuilder msg = new StringBuilder();
                for (int j = 4; j < 8; j++) {
                    msg.append(cypherKey[i][j]).append(" ");
                }
                System.out.println(msg);
            }
            */
            System.out.println("---------------");
            System.out.println("Testing reverse");
            System.out.println("After inverse ShiftRows");
            invShiftRows(plainMessage);
            print16Block(plainMessage);
            System.out.println("---------------");
        }
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

    private static void setColumn2dArray(String[][] arr, String[] col, int x) {
        arr[0][x] = col[0];
        arr[1][x] = col[1];
        arr[2][x] = col[2];
        arr[3][x] = col[3];
    }

    private static void shiftRows(String[][] message) {
        String[] hold = new String[4];
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

    private static void invShiftRows(String[][] message) {
        String[] hold = new String[4];
        for (int i = 1; i < 4; i++) {
            hold[0] = message[i][0];
            hold[1] = message[i][1];
            hold[2] = message[i][2];
            hold[3] = message[i][3];

            for (int j = 0; j < 4; j++) {
                if (j-i < 0) {
                    message[i][j] = hold[4-i+j];
                } else {
                    message[i][j] = hold[j-i];
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

    private static void generateKeySchedule(){
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