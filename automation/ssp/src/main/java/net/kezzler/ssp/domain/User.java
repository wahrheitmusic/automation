package net.kezzler.ssp.domain;

public class User {

    private String userName;
    private String password;

    public User(final String userName, final String password) {
        this.userName = userName;
        this.password = password;
    }

    public static User getNewDefaultUser() {
        return new User(System.getProperty("default.ssp.username"), System.getProperty("default.ssp.password"));
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
