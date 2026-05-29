package org.mql;

import org.mql.model.AccountStatus;
import org.mql.model.AccountType;
import org.mql.model.BankAccount;
import org.mql.model.BankDirector;
import org.mql.repository.AccountRepository;
import org.mql.repository.AccountRepositoryIDefault;
import org.mql.util.JsonSerializer;

import java.util.List;
import java.util.function.Predicate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public Main() {
        exp01();
    }

    void exp01(){

        JsonSerializer<BankAccount> bankAccountJsonSerializer = new JsonSerializer<>();


        AccountRepositoryIDefault accountRepositoryIDefault = new AccountRepositoryIDefault();
        accountRepositoryIDefault.populateData();

//        List<BankAccount> accounts = accountRepositoryIDefault.findAll();
//        accounts.forEach(bankAccountJsonSerializer::toJson);
//
////        accounts.stream()
////                .map(acc->bankAccountJsonSerializer.toJson(acc))
////                .forEach(System.out::println);
//
//
//        accounts.stream()
//                .map(bankAccountJsonSerializer::toJson) // pour chaque obj de bankaccount je vais applique la method tojson
//                .forEach(System.out::println);

        System.out.println("==================================");

        BankAccount account = accountRepositoryIDefault.findById(1L).orElse(null);

        if(account != null){
            System.out.println(bankAccountJsonSerializer.toJson(account));
        }

        System.out.println("==================================");

//        List<BankAccount> accountsSearch = accountRepositoryIDefault.searchAccounts(new Predicate<BankAccount>() {
//            @Override
//            public boolean test(BankAccount bankAccount) {
//                return bankAccount.getType().equals(AccountType.CURRENT_ACCOUNT);
//            }
//        });

        List<BankAccount> accountsSearch = accountRepositoryIDefault
                .searchAccounts(bankAccount -> bankAccount.getType().equals(AccountType.CURRENT_ACCOUNT) && bankAccount.getBalance()>1000);

        accountsSearch.forEach(bankAccountJsonSerializer::toJson);

        accountsSearch.stream()
                .map(bankAccountJsonSerializer::toJson) // pour chaque obj de bankaccount je vais applique la method tojson
                .forEach(System.out::println);


    }

    static void main() {
        new Main();
    }
}
