package org.mql.repository;

import org.mql.model.AccountStatus;
import org.mql.model.AccountType;
import org.mql.model.BankAccount;
import org.mql.model.BankDirector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AccountRepositoryIDefault implements AccountRepository {

    // Singleton Implementation
    // private static final AccountRepositoryIDefault accountRepository;
    private static AccountRepositoryIDefault accountRepository;

    private Map<Long, BankAccount> accounts = new HashMap<>();

    private long accountsCount = 0;

    static {
        System.out.println("Singleton instantiation");
        accountRepository = new AccountRepositoryIDefault();
    }

    private AccountRepositoryIDefault() {

    }

    public static AccountRepositoryIDefault getInstance() {
//        if (accountRepository == null) {
//            System.out.println("Singleton instantiation");
//            accountRepository = new AccountRepositoryIDefault();
//            //accountRepository.populateData();
//        }
        return accountRepository;
    }


//    @Override
//    public BankAccount save(BankAccount bankAccount) {
//
//        // First solution synchronize an bloc :
//        Long accountId;
//        synchronized (this){
//            accountId = ++accountsCount; // Critical zone
//            bankAccount.setAccountId(accountId);
//            accounts.put(accountId , bankAccount);
//        }
//
//        //Long accountId = accountId = ++accountsCount;

    /// /        bankAccount.setAccountId(accountId);
    /// /        accounts.put(accountId , bankAccount);
//
//        return bankAccount;
//    }

    // Third solution
    @Override
    public BankAccount save(BankAccount bankAccount) {
        Long accountId;
        synchronized (this) {
            accountId = ++accountsCount;
        }
        bankAccount.setAccountId(accountId);
        synchronized (this) {
            accounts.put(accountId, bankAccount);

        }
        return bankAccount;
    }


    @Override
    public List<BankAccount> findAll() {

        return accounts.values().stream().toList();
    }

    @Override
    public Optional<BankAccount> findById(Long id) {
        BankAccount account = accounts.get(id);
        if (account == null)
            return Optional.empty();
        else
            return Optional.of(account);


    }

    @Override
    public List<BankAccount> searchAccounts(Predicate<BankAccount> predicate) {
        return accounts.values().stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public BankAccount update(BankAccount account) {
        accounts.put(account.getAccountId(), account);
        return account;
    }

    @Override
    public void deleteAccountById(Long id) {
        accounts.remove(id);

    }

    public synchronized void populateData() {
        BankAccount bankAccount = null;
        for (int i = 0; i < 10; i++) {
            bankAccount = BankDirector.accountBuilder()
                    .currency(Math.random() > 0.5 ? "MAD" : "USD")
                    .status(Math.random() > 0.5 ? AccountStatus.CREATED : AccountStatus.ACTIVATED)
                    .type(Math.random() > 0.5 ? AccountType.SAVING_ACCOUNT : AccountType.CURRENT_ACCOUNT)
                    .balance(10000 + Math.random() * 90000)
                    .build();
            save(bankAccount);

        }
        System.out.println("*************************************** ");
        System.out.println("Nom du Thread : " + Thread.currentThread().getName());
        System.out.println("Account count : " + accountsCount);
        System.out.println("Size : " + accounts.values().size());

    }
}
