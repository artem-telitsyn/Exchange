package my.pack;

public enum Currency {
    USD("USD"),
    EUR("EUR"),
    RUB("RUB"),
    ALL("ALL"),
    DEFAULT("DEFAULT");

    private String description;

    Currency(String description) {
        this.description = description;
    }

    public static Currency getCurrency(String currency) {
        for (Currency cur : Currency.values()) {
            if (cur.description.equals(currency)) {
                return cur;
            }
        }
        return Currency.DEFAULT;
    }
}
