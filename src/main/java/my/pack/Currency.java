package my.pack;

public enum Currency {
    USD,
    EUR,
    RUB,
    DEFAULT;

    public Currency getCurrency(String currency) {
        switch (currency) {
            case "USD":
                return Currency.USD;
            case "EUR":
                return Currency.EUR;
            case "RUB":
                return Currency.RUB;
            default:
                return Currency.DEFAULT;
        }
    }
}
