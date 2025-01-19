package com.api.user.service.services;

import com.api.user.service.entities.MyUser;

import java.util.List;

public interface UserService {

    //Save user in database
    MyUser saveUser(MyUser myUser);

    //Get all the users
    List<MyUser> getAllUsers();

    //Get a user from database
    MyUser getUser(String id);

    //Delete a user from database
    void delete(MyUser myUser);

    //Update a user
    MyUser updateUser(String id, MyUser myUser);

}
