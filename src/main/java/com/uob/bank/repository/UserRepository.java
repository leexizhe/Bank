package com.uob.bank.repository;

import com.uob.bank.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Query(value = "SELECT id FROM user u WHERE u.email = :email", nativeQuery = true)
    Long findIdByEmail(@Param("email") String email);
}
