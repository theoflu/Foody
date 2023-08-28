package com.yasu.Foody.product.domain;

public enum moneyType {
    USD("Dollar","$"),
    EUR("Euro","€"),
    TL("Türk Lirası","₺");

    private String label;
    private String symbol;

     moneyType(String label,String symbol){
        this.label=label;
        this.symbol=symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
