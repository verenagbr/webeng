package de.hsduesseldorf.webengshop;

import java.util.UUID;

public class Article {
    private final String uuid;
    private String name;
    private float price;
    private int stock;

    public Article(final String uuid, final String name, final float price, final int stock) {
        this.uuid = uuid;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(final float price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(final int stock) {
        this.stock = stock;
    }
}
