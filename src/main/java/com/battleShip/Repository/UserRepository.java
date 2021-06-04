package com.battleShip.Repository;
import com.battleShip.Model.Entities.UserLogin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface UserRepository  extends CrudRepository<UserLogin,Integer> {

    @Query("SELECT user FROM UserLogin user")
    List<UserLogin> getUser();

    @Query("SELECT user FROM UserLogin user WHERE user.email=?1 AND user.password=?2")
    UserLogin getByLogin(String email, String password);

    @Query("SELECT user FROM UserLogin user WHERE user.id=?1")
    UserLogin getUserById(int id);
    }

