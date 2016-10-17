package ru.knize.hyperloop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.knize.hyperloop.entities.AccountEntity;
import ru.knize.hyperloop.repositories.AccountRepository;

import java.util.List;

/**
 * Created by knize on 15.10.16.
 */
@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Transactional
    public AccountEntity getById(int id) {
        return accountRepository.findById(id);
    }

    @Transactional
    public boolean add(AccountEntity accountEntity) {
        List<AccountEntity> namesakes = accountRepository.findByEmail(accountEntity.getEmail());
        if (namesakes.isEmpty()) {
            accountRepository.save(accountEntity);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean check(String username, String password) {
        List<AccountEntity> accounts = accountRepository.findByLoginPassword(username, password);
        if (accounts.isEmpty()) {
            return false;
        } else {
            return true;
        }

    }

}
