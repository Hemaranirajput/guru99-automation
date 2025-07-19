package utils;

public class CardDetails {
    private String cardNumber;
    private String cvv;
    private String expMonth;
    private String expYear;

    public CardDetails(String cardNumber, String cvv, String expMonth, String expYear) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expMonth = expMonth;
        this.expYear = expYear;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public String getExpMonth() {
        return expMonth;
    }

    public String getExpYear() {
        return expYear;
    }
}
