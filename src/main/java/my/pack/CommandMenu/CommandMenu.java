package my.pack.CommandMenu;

public enum CommandMenu {

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

    CommandMenu(String value, String command) {
        this.description = value;
        this.command = command;
    }

    public String getDescription() {
        return description;
    }

    public String getCommand() {
        return command;
    }

    public CommandMenu findCommandByName(String command1){
        CommandMenu c = CommandMenu.valueOf(command1);
        CommandMenu d = CommandMenu.valueOf(command1);
        return CommandMenu.valueOf(command1);
    }

    // Если добавить название команды к enum'у, то такого большого switch'a удастся сбежать, сделав один метод findCommandByName
    public static CommandMenu getMenu(String command) {
        switch (command) {
            case "help":
                return CommandMenu.HELP;
            case "login":
                return CommandMenu.LOGIN;
            case "logout":
                return CommandMenu.LOGOUT;
            case "createAccount":
                return CommandMenu.CREATE_ACCOUNT;
            case "depositRub":
                return CommandMenu.DEPOSIT_RUB;
            case "accountStatusCurrency":
                return CommandMenu.ACCOUNT_STATUS_CURRENCY;
            case "exchangeRate":
                return CommandMenu.EXCHANGE_RATE;
            case "changeRate":
                return CommandMenu.CHANGE_RATE;
            case "purchaseCurrency":
                return CommandMenu.PURCHASE_CURRENCY;
            case "exit":
                return CommandMenu.EXIT;
            default:
                return CommandMenu.DEFAULT;
        }
    }
}

