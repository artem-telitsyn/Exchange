package my.pack.MenuCommand;

public enum MenuCommand {

    HELP("help - Вызвать список доступных команд", "help"),
    LOGIN("login <логин> - Вход в систему под пользователем с логином <логин>", "login"),
    LOGOUT("logout - Выйти из системы", "logout"),
    CREATE_ACCOUNT("createAccount <логин> <роль> - Создание счета для нового пользователя <логин> с правами <ролью>", "createAccount"),
    ACCOUNT_STATUS_CURRENCY("accountStatusCurrency <валюта> - Проверить состояния <валюта> части счета (ALL для проверки общего счета)", "accountStatusCurrency"),
    DEPOSIT_RUB("depositRub <сумма> - <Сумма> положить на счет рубли", "depositRub"),
    EXCHANGE_RATE("exchangeRate - Узнать курс валют", "exchangeRate"),
    CHANGE_RATE("changeRate <валюта> <новый курс к рублю> - Изменить курс валюты", "changeRate"),
    PURCHASE_CURRENCY("purchaseCurrency <сумма> <1 валюта> <2 валюта> - Обменять <сумма> <1 валюта> на <2 валюта>", "purchaseCurrency"),
    TRANSACTION_HISTORY("transactionHistory - История всех транзакций отсортированные по дням", "transactionHistory"),
    TRANSACTION_HISTORY_ORDER_BY_AMOUNT("transactionHistoryOrderByAmount - История всех транзакций отсортированная по размеру транзакции", "transactionHistoryOrderByAmount"),
    TRANSACTION_HISTORY_BY_LOGIN("transactionHistoryByLogin - История всех транзакций по клиенту", "transactionHistoryByLogin"),
    MAX_THREE_TRANSACTION_OF_CLIENTS("maxThreeTransaction - Три самых больших транзакций по всех клиентам", "maxThreeTransaction"),
    EXIT("exit - Выйти из программы", "exit"),
    DEFAULT("Данная команда не поддерживается", "default");

    private String description;
    private String command;

    MenuCommand(String description, String command) {
        this.description = description;
        this.command = command;
    }

    public String getDescription() {
        return description;
    }

    public String getCommand() {
        return command;
    }

    public static MenuCommand findCommandByName(String consoleCommand) {
        for (MenuCommand command : MenuCommand.values()){
            if (command.command.equals(consoleCommand)) {
                return command;
            }
        }
        return MenuCommand.DEFAULT;
    }
}

