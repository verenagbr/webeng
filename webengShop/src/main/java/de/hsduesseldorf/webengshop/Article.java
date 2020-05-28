package de.hsduesseldorf.webengshop;

import java.util.UUID;

public class Article {
    private final UUID uuid;
    private String name;
    private float preis;
    private int quantity;

    public Article(final UUID uuid, final String name, final float preis, final int quantity) {
        this.uuid = uuid;
        this.name = name;
        this.preis = preis;
        this.quantity = quantity;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public float getPreis() {
        return preis;
    }

    public void setPreis(final float preis) {
        this.preis = preis;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }
}
