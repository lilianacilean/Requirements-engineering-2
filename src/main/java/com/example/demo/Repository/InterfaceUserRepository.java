package com.example.demo.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface InterfaceUserRepository {


    @Query("SELECT u.id FROM User u WHERE " +
            "u.email= :email AND " +
            "u.password= :password ")
    Long getUser(@Param("email") String email, @Param("password") String password);
}
