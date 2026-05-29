package org.mql.repository;

import org.mql.model.BankAccount;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface AccountRepository {

    BankAccount save (BankAccount bankAccount);

    List<BankAccount> findAll();

    Optional<BankAccount> findById(Long id);

    // predicate contient une methods test qui retourne oui ou non
    List<BankAccount> searchAccounts(Predicate<BankAccount> predicate);

    BankAccount update(BankAccount account);

    void deleteAccountById(Long id);

}
