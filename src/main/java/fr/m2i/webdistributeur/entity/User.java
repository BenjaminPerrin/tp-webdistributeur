package fr.m2i.webdistributeur.entity;

public class User {

    // déclarer les attributs ci-dessous
    private int id;
    private String email;
    private String password;
    private int credit;
    private Role role;

    public User() {

    }
    public User(int id, String email, String password, int credit, Role role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.credit = credit;
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
       
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
}
