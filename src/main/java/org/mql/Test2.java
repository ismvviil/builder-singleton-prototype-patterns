package org.mql;

import org.mql.model.AccountStatus;
import org.mql.model.AccountType;
import org.mql.model.BankAccount;
import org.mql.model.Customer;

public class Test2 {

    static void main(String[] args) throws CloneNotSupportedException {

        // Without Prototype
        BankAccount account1 = new BankAccount();
        account1.setAccountId(1L);
        account1.setCurrency("MAD");
        account1.setType(AccountType.CURRENT_ACCOUNT);
        account1.setStatus(AccountStatus.ACTIVATED);

//        BankAccount account2 = new BankAccount();
//        account2.setAccountId(account1.getAccountId());
//        account2.setCurrency(account1.getCurrency());
//        account2.setType(account1.getType());
//        account2.setStatus(account1.getStatus());

        // Prototype
        Customer customer = new Customer(1L, "ISMAIL");
        account1.setCustomer(customer);


        BankAccount account3 = account1.clone();

        System.out.println(account1);
        System.out.println(account3);
        System.out.println(account1 == account3);

        account1.getCustomer().setName("Moahmmed");
        System.out.println(account1);
        System.out.println(account3);
        
        System.out.println(account1.getCustomer().hashCode());
        System.out.println(account3.getCustomer().hashCode());
    }
}
