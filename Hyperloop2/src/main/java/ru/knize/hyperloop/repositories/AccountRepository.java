package ru.knize.hyperloop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.knize.hyperloop.entities.AccountEntity;

/**
 * Created by knize on 05.10.16.
 */
public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {

    AccountEntity findById(int id);
}