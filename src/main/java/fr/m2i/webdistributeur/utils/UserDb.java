package fr.m2i.webdistributeur.utils;

import fr.m2i.webdistributeur.entity.User;
import java.util.List;

public class UserDb {

    private static UserDb _instance;
    private static String _dbUser = "root";
    private static String _dbPass = "root";

    private List<User> users;

    private UserDb() {

    }

    public static UserDb getInstance(String dbUser, String dbPass) {
        return null;
    }

    public List<User> getUsers() {
        return users;
    }
}
