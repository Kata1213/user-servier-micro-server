package com.thoughworks.traning.jingyli.user_service.repository;

import com.thoughworks.traning.jingyli.user_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
    User findById(Long id);

}
