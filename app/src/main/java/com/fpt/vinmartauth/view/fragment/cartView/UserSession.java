package com.fpt.vinmartauth.view.fragment.cartView;

public class UserSession {
    private  String UID;
    private  String cartID;
    private static UserSession session = new UserSession();

    private UserSession() {
    }

    public static UserSession getInstance() {
        if (session != null) {
            return session;
        } return new UserSession();
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getCartID() {
        return cartID;
    }

    public void setCartID(String cartID) {
        this.cartID = cartID;
    }
}
