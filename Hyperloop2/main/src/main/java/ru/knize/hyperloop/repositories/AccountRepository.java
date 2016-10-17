package ru.knize.hyperloop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.knize.hyperloop.entities.AccountEntity;

import java.util.List;

/**
 * Created by knize on 05.10.16.
 */
public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {

    AccountEntity findById(int id);

    @Query("SELECT a FROM AccountEntity AS a WHERE a.email = :email")
    List<AccountEntity> findByEmail(@Param("email") String email);

    @Query("SELECT a FROM AccountEntity AS a " +
            "WHERE a.username =:username AND a.password =:password")
    List<AccountEntity> findByLoginPassword(@Param("username") String username,
                                            @Param("password") String password);
}