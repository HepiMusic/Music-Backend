package com.hepi.music_api.utills;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class HelperUtility {
    public static final String EXISTS_CODE ="0003";
    public static final String EXISTS_MESSAGE ="already exist!!";
    public static final String NOT_EXISTS_MESSAGE ="resource does not exist!!";
    public static final String CREATION_CODE ="0000";
    public static final String UPDATE_CODE ="0001";
    public static final String DELETION_CODE ="0004";
    public static final String CREATION_MESSAGE ="success  created!!";
    public static final String UPDATED_MESSAGE ="updated success!!";
    public static final String DELETION_MESSAGE ="success  deleted!!";


    public  static  String hashPhoneNumber( String phoneNumber) {
        try {
            // Create an instance of the SHA-256 message digest algorithm
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");

            // Convert the phone number to bytes and apply the hash
            byte[] phoneNumberBytes = phoneNumber.getBytes();
            byte[] hashBytes = sha256.digest(phoneNumberBytes);

            // Convert the hashed bytes to a hexadecimal string
            StringBuilder hashedPhoneNumber = new StringBuilder();
            for (byte b : hashBytes) {
                hashedPhoneNumber.append(String.format("%02X", b));
            }

            return hashedPhoneNumber.toString();
        } catch (NoSuchAlgorithmException e) {
            // Handle exceptions, e.g., log an error or throw an exception
            e.printStackTrace();
            return "Error while hashing phone number";
        }
    }


}