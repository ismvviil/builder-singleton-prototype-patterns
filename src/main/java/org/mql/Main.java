package org.mql;

import org.mql.model.AccountType;
import org.mql.model.BankAccount;
import org.mql.repository.AccountRepositoryIDefault;
import org.mql.util.JsonSerializer;

import java.io.IOException;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public Main() throws IOException {
        // exp01();
        exp02Singleton();
    }

    void exp01() {

        JsonSerializer<BankAccount> bankAccountJsonSerializer = new JsonSerializer<>();


        AccountRepositoryIDefault accountRepositoryIDefault = AccountRepositoryIDefault.getInstance();


        //accountRepositoryIDefault.populateData();

//        List<BankAccount> accounts = accountRepositoryIDefault.findAll();
//        accounts.forEach(bankAccountJsonSerializer::toJson);
//
////        accounts.stream()
////                .map(acc->bankAccountJsonSerializer.toJson(acc))
////                .forEach(System.out::println);
//
//
//        accounts.stream()
//                .map(bankAccountJsonSerializer::toJson) // pour chaque obj de bankaccount je vais appliquer la method tojson
//                .forEach(System.out::println);

        System.out.println("==================================");

        BankAccount account = accountRepositoryIDefault.findById(1L).orElse(null);

        if (account != null) {
            System.out.println(bankAccountJsonSerializer.toJson(account));
        }

        System.out.println("==================================");

//        List<BankAccount> accountsSearch = accountRepositoryIDefault.searchAccounts(new Predicate<BankAccount>() {
//            @Override
//            public boolean test(BankAccount bankAccount) {
//                return bankAccount.getType().equals(AccountType.CURRENT_ACCOUNT);
//            }
//        });

        List<BankAccount> accountsSearch = accountRepositoryIDefault.searchAccounts(bankAccount -> bankAccount.getType().equals(AccountType.CURRENT_ACCOUNT) && bankAccount.getBalance() > 1000);

        accountsSearch.forEach(bankAccountJsonSerializer::toJson);

        accountsSearch.stream().map(bankAccountJsonSerializer::toJson) // pour chaque obj de bankaccount je vais appliquer la method tojson
                .forEach(System.out::println);


    }

    void exp02Singleton() throws IOException {
        JsonSerializer<BankAccount> bankAccountJsonSerializer = new JsonSerializer<>();


        AccountRepositoryIDefault accountRepositoryIDefault = AccountRepositoryIDefault.getInstance();

        for (int i = 0; i < 10; i++) {
            /*new Thread(new Runnable() {
                @Override
                public void run() {

                }
            }).start();*/

            new Thread(() -> {
                accountRepositoryIDefault.populateData();
            }).start();
        }
        System.out.println("Tape a character : ");
        System.in.read();


        List<BankAccount> accountsSearch = accountRepositoryIDefault.findAll();

        accountsSearch.stream().map(bankAccountJsonSerializer::toJson) // pour chaque obj de bankaccount je vais appliquer la method tojson
                .forEach(System.out::println);
    }

    static void main() throws IOException {
        new Main();
    }
}
