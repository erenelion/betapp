package com.example.appdb;

public class UserModel {

    private int id;
    private String name;
    private int bet;
    private boolean isPublic;

    //constructors

    public UserModel(int id, String name, int bet, boolean isPublic) {

        this.id = id;
        this.name = name;
        this.bet = bet;
        this.isPublic = isPublic;
    }

    //constructor aux
    public UserModel() {
    }

    //toString for printing

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bet=" + bet +
                ", isPublic=" + isPublic +
                '}';
    }


    //getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public boolean getIsPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }
}
