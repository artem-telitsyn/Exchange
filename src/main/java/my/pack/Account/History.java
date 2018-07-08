package my.pack.Account;

import my.pack.DateTime;
import my.pack.MenuCommand.Permission;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;


public class History {

    private DateTime dateTime;

    public History(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void getTransactionAccountOrderByDate(Account account) {
        if (account.getAllTransactionHistory().keySet().size() > 0) {
            for (Long date : account.getAllTransactionHistory().keySet()) {
                System.out.println(dateTime.currentDateTime(date) + " "
                        + account.getTransactionHistory(date).getNote() + " "
                        + account.getTransactionHistory(date).getAmount() + " "
                        + account.getTransactionHistory(date).getCurrency());
            }
        } else {
            System.out.println("На данный момент не было осуществлено ни одной транзакции");
        }
    }

    public void getTransactionAccountOrderByAmount(Account account) {
        int size = account.getAllTransactionHistory().keySet().size();
        if (size > 0) {
            ArrayList<Long> listDate = new ArrayList<Long>();
            int i = 0;
            TreeMap<Long, AccountHistory> copyAccountHistory = (TreeMap<Long, AccountHistory>) account.getAllTransactionHistory().clone();
            listDate = maxAccountTransaction(copyAccountHistory);
            for (int j = 0; j < size; j++) {
                System.out.println(dateTime.currentDateTime(listDate.get(j)) + " "
                        + account.getTransactionHistory(listDate.get(j)).getNote() + " "
                        + account.getTransactionHistory(listDate.get(j)).getAmount() + " "
                        + account.getTransactionHistory(listDate.get(j)).getCurrency());
            }
        } else {
            System.out.println("На данный момент не было осуществлено ни одной транзакции");
        }
    }

    public void getMaxThreeTransaction(HashMap<String, Account> accountByLogin) {
        int size = 0;
        for (String login : accountByLogin.keySet()) {
            if (accountByLogin.get(login).getRole() == Permission.CLIENT) {
                size++;
            }
        }
        if (size > 0) {
            TreeMap<String, ArrayList<Long>> maxTransactionByLogin = new TreeMap<String, ArrayList<Long>>();
            HashMap<String, Long> maxThreeTransaction = new HashMap<String, Long>();
            for (String login : accountByLogin.keySet()) {
                if (accountByLogin.get(login).getRole() == Permission.CLIENT) {
                    ArrayList<Long> listDate;
                    int i = 0;
                    TreeMap<Long, AccountHistory> copyAccountHistory =
                            (TreeMap<Long, AccountHistory>) accountByLogin.get(login).getAllTransactionHistory().clone();
                    listDate = maxAccountTransaction(copyAccountHistory);
                    if (listDate.size() > 0) {
                        maxTransactionByLogin.put(login, listDate);
                    }
                }
            }
            int i = 0;
            if (maxTransactionByLogin.size() > 0) {
                while (i < 3) {
                    int sizeTransactionOfLogin = 0;
                    String firstLogin = maxTransactionByLogin.firstKey();
                    int l = maxTransactionByLogin.get(firstLogin).size();
                    Long dateOfMaxAmount = maxTransactionByLogin.get(firstLogin).get(0);
                    BigDecimal maxAmount = accountByLogin.get(firstLogin).getTransactionHistory(dateOfMaxAmount).getAmount()
                            .multiply(accountByLogin.get(firstLogin).getTransactionHistory(dateOfMaxAmount).getRate());
                    for (String login : maxTransactionByLogin.keySet()) {
                        //Определяем количество транзакций в аккаунте, есле меньше 3, то ищем по их количеству
                        if (maxTransactionByLogin.get(login).size() > 2) {
                            sizeTransactionOfLogin = 3;
                        } else {
                            sizeTransactionOfLogin = maxTransactionByLogin.get(login).size();
                        }
                        for (int k = 0; k < sizeTransactionOfLogin; k++) {
                            BigDecimal amount = accountByLogin.get(login)
                                    .getTransactionHistory(maxTransactionByLogin.get(login).get(k)).getAmount()
                                    .multiply(accountByLogin.get(login)
                                            .getTransactionHistory(maxTransactionByLogin.get(login).get(k)).getRate());
                            if (maxAmount.compareTo(amount) == -1 || maxAmount.compareTo(amount) == 0) {
                                firstLogin = login;
                                dateOfMaxAmount = maxTransactionByLogin.get(login).get(k);
                                maxAmount = amount;
                            }
                        }
                    }
                    maxTransactionByLogin.get(firstLogin).remove(dateOfMaxAmount);
                    if (maxTransactionByLogin.get(firstLogin).size() == 0) {
                        maxTransactionByLogin.remove(firstLogin);
                    }
                    System.out.println(dateTime.currentDateTime(dateOfMaxAmount) + " "
                            + firstLogin + " "
                            + accountByLogin.get(firstLogin).getTransactionHistory(dateOfMaxAmount).getNote() + " "
                            + accountByLogin.get(firstLogin).getTransactionHistory(dateOfMaxAmount).getAmount() + " "
                            + accountByLogin.get(firstLogin).getTransactionHistory(dateOfMaxAmount).getCurrency());
                    if (maxTransactionByLogin.size() != 0) {
                        i++;
                    } else {
                        i = 3;
                    }
                }
            }else {
                System.out.println("На данный момент нет транзакций");
            }
        } else {
            System.out.println("На данный момент нет доступных аккаунтов с ролью Клиент");
        }
    }
    public void transactionHistoryByLogin (HashMap<String, Account> accountByLogin, String login) {
        if (accountByLogin.containsKey(login)) {
            Account account = accountByLogin.get(login);
            if (account.getRole() == Permission.CLIENT) {
                if (account.getAllTransactionHistory().keySet().size() > 0) {
                    for (Long date : account.getAllTransactionHistory().keySet()) {
                        System.out.println(dateTime.currentDateTime(date) + " "
                                + account.getTransactionHistory(date).getNote() + " "
                                + account.getTransactionHistory(date).getAmount() + " "
                                + account.getTransactionHistory(date).getCurrency());
                    }
                } else {
                    System.out.println("На данный момент не было осуществлено ни одной транзакции");
                }
            } else {
                System.out.println("Данный пользователь не зарегистрирован в системе как Клиент");
            }
        } else {
            System.out.println("Данный пользователь не зарегистрирован в системе");
        }
    }

    private ArrayList<Long> maxAccountTransaction(TreeMap<Long, AccountHistory> copyAccountHistory) {
        int i = 0;
        int size = copyAccountHistory.size();
        ArrayList<Long> listDate = new ArrayList<Long>();
        while (i < size) {
            Long dateOfMaxAmount = copyAccountHistory.firstKey();
            BigDecimal maxAmount = copyAccountHistory.get(dateOfMaxAmount).getAmount()
                    .multiply(copyAccountHistory.get(dateOfMaxAmount).getRate());
            for (Long date : copyAccountHistory.keySet()) {
                if (maxAmount.compareTo(copyAccountHistory.get(date).getAmount()
                        .multiply(copyAccountHistory.get(date).getRate())) == -1 ||
                        maxAmount.compareTo(copyAccountHistory.get(date).getAmount()
                                .multiply(copyAccountHistory.get(date).getRate())) == 0) {
                    maxAmount = copyAccountHistory.get(date).getAmount()
                            .multiply(copyAccountHistory.get(date).getRate());
                    dateOfMaxAmount = date;
                }
            }
            listDate.add(i, dateOfMaxAmount);
            i++;
            copyAccountHistory.remove(dateOfMaxAmount);
        }
        return listDate;
    }
}

