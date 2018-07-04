package my.pack.Account;

import my.pack.DateTime;

import java.util.Set;


public class History {

    private DateTime dateTime;

    public History (DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void getTransactionAccountOrderByDate(Account account) {
        if (account.getAllTransactionHistory().keySet().size() > 0) {
            for (Long date : account.getAllTransactionHistory().keySet()) {
                System.out.println(dateTime.currentDateTime(date) + " " + account.getTransactionHistory(date).getNote() + " "
                        + account.getTransactionHistory(date).getAmount() + " " + account.getTransactionHistory(date).getCurrency());
            }
        } else {
            System.out.println("На данный момент не было осуществлено ни одной транзакции");
        }
    }

    public void getTransactionAccounOrderByAmount(Account account) {
        if (account.getAllTransactionHistory().keySet().size() > 0) {
            Set<Long> key = account.getAllTransactionHistory().keySet();
            key.size();
        } else {
            System.out.println("На данный момент не было осуществлено ни одной транзакции");
        }
    }

    public void getMaxThreeTransaction() {

    }
}
