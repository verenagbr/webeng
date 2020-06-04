package de.hsduesseldorf.webengshop;

import java.util.UUID;

public class Article {
    private int uuid;
    private String name;
    private float price;
    private int stock;

    public Article(final String name, final float price, final int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public int getUuid() {
        return uuid;
    }

    public void setUuid(final int uuid) {
        this.uuid = uuid;
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
