package com.Aes256Testing;

import java.util.*;

public class Main {

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

        /*System.out.println((char) Integer.parseInt("2b",16));
        System.out.println((char) Integer.parseInt("7e",16));
        System.out.println((char) Integer.parseInt("15",16));
        System.out.println((char) Integer.parseInt("16",16));
        char ch = 'n';
        String hex = String.format("%02x", (int) ch);
        System.out.println(hex);
        System.out.println("-----------------------");*/





        //generateKeySchedule(testCypherKey);
        messageEncryption(testPlainMessage);

    }

    public static void messageEncryption(String[][] testPlainMessage) {
        for (int i = 0; i < 4; i++) {
            String[] colPM = getColumn2dArray(testPlainMessage, i);
            System.out.println("Before subbytes " + i);
            System.out.println(colPM[0]);
            System.out.println(colPM[1]);
            System.out.println(colPM[2]);
            System.out.println(colPM[3]);
            System.out.println("---------------");
            subBytesTransformation(colPM);
            System.out.println("After subbytes " + i);
            System.out.println(colPM[0]);
            System.out.println(colPM[1]);
            System.out.println(colPM[2]);
            System.out.println(colPM[3]);
            System.out.println("---------------");
            setColumn2dArray(testPlainMessage, colPM, i);
        }

        print16Block(testPlainMessage);
        System.out.println("---------------");

        shiftRows(testPlainMessage);

        System.out.println("After shiftRows");
        print16Block(testPlainMessage);
        System.out.println("---------------");

        System.out.println("After mixColumns");
        mixColumns(testPlainMessage);
        System.out.println("---------------");


    }

    public static void print16Block(String[][] block) {
        for (int i = 0; i < 4; i++) {
            String msg = "";
            for (int j = 0; j < 4; j++) {
                msg += block[i][j] + " ";
            }
            System.out.println(msg);
        }
    }

    public static String[] getColumn2dArray(String[][] arr, int x) {
        String[] colArr = new String[4];
        colArr[0] = arr[0][x];
        colArr[1] = arr[1][x];
        colArr[2] = arr[2][x];
        colArr[3] = arr[3][x];
        return colArr;
    }

    public static void setColumn2dArray(String[][] message, String[] col, int x) {
        message[0][x] = col[0];
        message[1][x] = col[1];
        message[2][x] = col[2];
        message[3][x] = col[3];
    }

    public static void shiftRows(String[][] message) {
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

    public static void mixColumns(String[][] message) {
        final String[] RGF = {"02","03","01","01",
                               "01","02","03","01",
                               "01","01","02","03",
                               "03","01","01","02",};
        String[] mod = new String[4];
        //int sum = 0;
        for (int i = 0; i < 4; i++) {
            /*System.out.println(Integer.parseInt(message[i][0],16));
            System.out.println(Integer.parseInt(RGF[i],16));
            sum ^= Integer.parseInt(message[i][0],16) * Integer.parseInt(RGF[i],16);
            System.out.println("Sum = " + sum);*/
            if (RGF[0] == "02") {
                int holdInt = Integer.parseInt(message[i][0],16);
                System.out.println(holdInt);
                System.out.println(holdInt<<1);
                //System.out.println(String.format("%02x", holdInt));
                int xorint = Integer.parseInt("1b",16);
                System.out.println(xorint);
                System.out.println(holdInt ^ xorint);
                System.out.println(String.format("%02x", holdInt ^ xorint));
                System.out.println(Integer.parseInt("b3",16));
                //System.out.println(String.format("%02x", holdInt));
                System.exit(1);
            }


        }
        //System.out.println(String.format("%02x", sum));
    }

    public static void generateKeySchedule(String[][] cypherKey){
        for (int i = 4; i < 44; i++) {
            String[] previousFour = getColumn2dArray(cypherKey, i-1);
            /*previousFour[0] = cypherKey[0][i-1];
            previousFour[1] = cypherKey[1][i-1];
            previousFour[2] = cypherKey[2][i-1];
            previousFour[3] = cypherKey[3][i-1];*/

            /*if (i > 0) {
                System.out.println("Previousfour 8");
                System.out.println(previousFour[0]);
                System.out.println(previousFour[1]);
                System.out.println(previousFour[2]);
                System.out.println(previousFour[3]);
                System.out.println("-----------------------");
            }*/


            if (i % 4 == 0) {
                rotWordTransformation(previousFour);
                /*if (i == 8) {
                    System.out.println("After rotWord");
                    System.out.println(previousFour[0]);
                    System.out.println(previousFour[1]);
                    System.out.println(previousFour[2]);
                    System.out.println(previousFour[3]);
                    System.out.println("-----------------------");
                }*/
                subBytesTransformation(previousFour);
            }

            /*if (i == 8) {
                System.out.println("After subBytes");
                System.out.println(previousFour[0]);
                System.out.println(previousFour[1]);
                System.out.println(previousFour[2]);
                System.out.println(previousFour[3]);
                System.out.println("-----------------------");
            }*/

            String[] colBack4 = getColumn2dArray(cypherKey, i - 4);
            /*colBack4[0] = cypherKey[0][i-4];
            colBack4[1] = cypherKey[1][i-4];
            colBack4[2] = cypherKey[2][i-4];
            colBack4[3] = cypherKey[3][i-4];*/
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

    public static void rotWordTransformation(String[] arr) {
        String hold = arr[0];
        arr[0] = arr[1];
        arr[1] = arr[2];
        arr[2] = arr[3];
        arr[3] = hold;
    }

    public static void subBytesTransformation(String[] arr) {
        final String[] SBOX = {
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

        for (int i = 0; i < arr.length; i++) {
            int x = Integer.parseInt(arr[i].substring(0,1),16);
            int y = Integer.parseInt(arr[i].substring(1,2),16);
            int index = x * 16 + y;
            arr[i] = SBOX[index];
        }
    }

    public static void hexXORTransformation(String[] colBack4 , String[] arr, int x) {
        final String[] RCON = {"01","00","00","00","02","00","00","00","04","00","00","00","08","00","00","00",
                               "10","00","00","00","20","00","00","00","40","00","00","00","80","00","00","00",
                               "1b","00","00","00","36","00","00","00"};

        if (x % 4 == 0) {
            for (int i = 0; i < 4; i++) {
                int numColBack4 = Integer.parseInt(colBack4[i], 16);
                int numArr = Integer.parseInt(arr[i], 16);
                int numRCON = Integer.parseInt(RCON[x - 4 + i], 16);
                arr[i] = String.format("%02x", numColBack4 ^ numArr ^ numRCON);
            }
        } else {
            for (int i = 0; i < 4; i++) {
                int numColBack4 = Integer.parseInt(colBack4[i], 16);
                int numArr = Integer.parseInt(arr[i], 16);
                arr[i] = String.format("%02x", numColBack4 ^ numArr);
            }
        }
    }


}