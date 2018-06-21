package my.pack;

import my.pack.Account.Account;

import java.math.BigDecimal;
// Кажется, что данный класс лучше подходит для переводов, чем accountManager. AccoutManager Отвечает за создание, логин, логаут. 
// А финансовые операции логично делать в одном месте. Пополнение, плюс переводы со счета на счет
public class DepositCurrency {
    public void depositRub(Account account, BigDecimal amount) {
        if (account != null) {
            BigDecimal amountOnAccount;
            amountOnAccount = account.getAccountCurrency("RUB");
            //amountOnAccount = account.getRub();
            //account.setRub(amountOnAccount.add(amount));
            account.setAccountCurrency("RUB", amountOnAccount.add(amount));
            System.out.println("Успешное пополнение счета на " + amount + " RUB");
        } else {
            System.out.println("Необходимо создать счет");
        }
    }
}
