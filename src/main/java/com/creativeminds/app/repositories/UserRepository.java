package com.creativeminds.app.repositories;

import com.creativeminds.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    User findByAuth0Id(String aoth0Id);
    User findByImage(String Image);

}
