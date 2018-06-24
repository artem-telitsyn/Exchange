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
    EXIT("exit - Выйти из программы", "exit"),
    DEFAULT("Данная команда не поддерживается", "default");

    private String description;
    private String command;

    MenuCommand(String value, String command) {
        this.description = value;
        this.command = command;
    }

    public String getDescription() {
        return description;
    }

    public String getCommand() {
        return command;
    }

    public MenuCommand findCommandByName(String command1){
        MenuCommand c = MenuCommand.valueOf(command1);
        MenuCommand d = MenuCommand.valueOf(command1);
        return MenuCommand.valueOf(command1);
    }

    public static MenuCommand getMenu(String command) {
        switch (command) {
            case "help":
                return MenuCommand.HELP;
            case "login":
                return MenuCommand.LOGIN;
            case "logout":
                return MenuCommand.LOGOUT;
            case "createAccount":
                return MenuCommand.CREATE_ACCOUNT;
            case "depositRub":
                return MenuCommand.DEPOSIT_RUB;
            case "accountStatusCurrency":
                return MenuCommand.ACCOUNT_STATUS_CURRENCY;
            case "exchangeRate":
                return MenuCommand.EXCHANGE_RATE;
            case "changeRate":
                return MenuCommand.CHANGE_RATE;
            case "purchaseCurrency":
                return MenuCommand.PURCHASE_CURRENCY;
            case "exit":
                return MenuCommand.EXIT;
            default:
                return MenuCommand.DEFAULT;
        }
    }
}

