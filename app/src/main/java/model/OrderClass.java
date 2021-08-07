package model;

public class OrderClass {
    private String ProductID;
    private String ProductName;
    private String Quantity;
    private String Price;
    private String TotalMoney;
    private String ID;
    private String IsAnnounced;
    private String Note;
    private String IsPrioritize;

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getTotalMoney() {
        return TotalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        TotalMoney = totalMoney;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getIsAnnounced() {
        return IsAnnounced;
    }

    public void setIsAnnounced(String isAnnounced) {
        IsAnnounced = isAnnounced;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public String getIsPrioritize() {
        return IsPrioritize;
    }

    public void setIsPrioritize(String isPrioritize) {
        IsPrioritize = isPrioritize;
    }
}
