public class DepositCurrency {
    public void depositRub (Account account, double amount) {
        try {
            account.setRub(amount);
            System.out.println("Успешное пополнение счета на " + account + " RUB");
        } catch (NullPointerException e) {
            System.out.println("Необходимо создать счет");
        }
    }
}
