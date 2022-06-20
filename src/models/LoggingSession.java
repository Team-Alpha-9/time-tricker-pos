/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author buddh
 */
public class LoggingSession {

    private static int userId = 1;
    private static String userName = "admin";
    private static String userType = "Admin";

    /**
     * @return the userName
     */
    public static String getUserName() {
        return userName;
    }

    /**
     * @param aUserName the userName to set
     */
    public static void setUserName(String aUserName) {
        userName = aUserName;
    }

    /**
     * @return the userType
     */
    public static String getUserType() {
        return userType;
    }

    /**
     * @param aUserType the userType to set
     */
    public static void setUserType(String aUserType) {
        userType = aUserType;
    }

    /**
     * @return the userId
     */
    public static int getUserId() {
        return userId;
    }

    /**
     * @param aUserId the userId to set
     */
    public static void setUserId(int aUserId) {
        userId = aUserId;
    }
}
