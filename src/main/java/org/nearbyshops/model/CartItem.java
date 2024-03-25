package org.nearbyshops.model;

/**
 * Created by sumeet on 30/5/16.
 */
public class CartItem {


    // Table Name
    public static final String TABLE_NAME = "CART_ITEM";

    // column Names
    public static final String CART_ID = "CART_ID";
    public static final String ITEM_ID = "ITEM_ID";
    public static final String ITEM_QUANTITY = "ITEM_QUANTITY";




    // create table statement
    public static final String createtableCartItemPostgres = "CREATE TABLE IF NOT EXISTS " + CartItem.TABLE_NAME + "("
            + " " + CartItem.ITEM_ID + " INT,"
            + " " + CartItem.CART_ID + " INT,"
            + " " + CartItem.ITEM_QUANTITY + " FLOAT NOT NULL Default 0,"
            + " FOREIGN KEY(" + CartItem.ITEM_ID +") REFERENCES " + Item.TABLE_NAME + "(" + Item.ITEM_ID + ") ON DELETE CASCADE,"
            + " FOREIGN KEY(" + CartItem.CART_ID +") REFERENCES " + Cart.TABLE_NAME + "(" + Cart.CART_ID + ") ON DELETE CASCADE,"
            + " PRIMARY KEY (" + CartItem.ITEM_ID + ", " + CartItem.CART_ID + ")"
            + ")";





    // instance variables
    private int cartID;
    private int itemID;

    private Cart cart;
    private Item item;

    private double itemQuantity;


    // rt stands for real time.
    // variables with "rt" prefix are those variables which are not stored in the database. They are computed in real time
    // when the api call is made.

    private int rtAvailableItemQuantity;
    private double rtItemPrice;
    private String rtQuantityUnit;









    // getter and setter methods


    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }


    public double getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(double itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public int getRtAvailableItemQuantity() {
        return rtAvailableItemQuantity;
    }

    public void setRtAvailableItemQuantity(int rtAvailableItemQuantity) {
        this.rtAvailableItemQuantity = rtAvailableItemQuantity;
    }

    public double getRtItemPrice() {
        return rtItemPrice;
    }

    public void setRtItemPrice(double rtItemPrice) {
        this.rtItemPrice = rtItemPrice;
    }

    public String getRtQuantityUnit() {
        return rtQuantityUnit;
    }

    public void setRtQuantityUnit(String rtQuantityUnit) {
        this.rtQuantityUnit = rtQuantityUnit;
    }
}
