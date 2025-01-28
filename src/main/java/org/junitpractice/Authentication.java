package org.junitpractice;

public class Authentication {


    public boolean authenticate(UserClass user, String enteredUserName, String enteredPassword) {
        if (user.getUserName().equals(enteredUserName) && user.getPassword().equals(enteredPassword)) {
            return true;
        }
        return false;
    }


    public static UserClass registerUser(int userId, String userName, String password) {
        UserClass newUser = new UserClass(userId, userName, password);
        return newUser;
    }
}
