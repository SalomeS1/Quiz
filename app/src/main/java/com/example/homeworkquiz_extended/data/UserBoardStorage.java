package com.example.homeworkquiz_extended.data;

import java.util.ArrayList;

public class UserBoardStorage {

    private ArrayList<User> users = new ArrayList<User>();

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setQuestions(ArrayList<User> users) {
        this.users = users;
    }
}
