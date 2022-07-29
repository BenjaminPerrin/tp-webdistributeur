package fr.m2i.webdistributeur.utils;

import fr.m2i.webdistributeur.entity.User;
import fr.m2i.webdistributeur.entity.Role;
import java.util.ArrayList;
import java.util.List;

   public class UserDb {

    private static UserDb _instance;
    private static String _dbUser = "root";
    private static String _dbPass = "root";
    


    private List<User> users= new ArrayList() {
        {
            add(new User(1,"admin@admin.com", "admin",25, Role.ADMIN));
            add(new User(2,"user@user.com", "user",25, Role.USER));
            add(new User(2,"provider@provider.com", "provider",25, Role.PROVIDER));
        }
    };

    private UserDb() {
        
    }

    public static UserDb getInstance(String dbUser, String dbPass) {
        if (!_dbUser.equals(dbUser) || !_dbPass.equals(dbPass)) {
            System.out.println("Les identifiants pour accéder à la base de données sont erronés");
            return null;
        }

        if (_instance == null) {
            _instance = new UserDb();
        }

        return _instance;
    }
    public User checkUser(String email, String password) {

        if (email == null || password == null) {
            return null;
        }

        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }

        return null;
    }

    public List<User> getUsers() {
        return users;
    }
}
