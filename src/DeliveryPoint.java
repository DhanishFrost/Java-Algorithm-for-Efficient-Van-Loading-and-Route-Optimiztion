import java.io.Serializable;

public class DeliveryPoint implements Serializable {
    // Attributes
    private int index;
    private String address;
    private String itemName;
    private int invoiceId;
    private int goodsQuantity;

    // Constructor
    public DeliveryPoint(int index, String address, String itemName, int invoiceId, int goodsQuantity) {
        this.index = index; // Setting the index field
        this.address = address;   // Setting the address field
        this.itemName = itemName; // Setting the itemName field
        this.invoiceId = invoiceId; // Setting the invoiceId field
        this.goodsQuantity = goodsQuantity; // Setting the goodsQuantity field
    }

    // Getter method for index
    public int getIndex() {
        return index;
    }

    // Getter method for address
    public String getAddress() {
        return address;
    }

    // Getter method for itemName
    public String getItemName() {
        return itemName;
    }

    // Getter method for invoiceId
    public int getInvoiceId() {
        return invoiceId;
    }

    // Getter method for goodsQuantity
    public int getGoodsQuantity() {
        return goodsQuantity;
    }

    // Setter method for goodsQuantity
    public void setGoodsQuantity(int goodsQuantity) {
        this.goodsQuantity = goodsQuantity;
    }

    // toString method
    @Override
    public String toString() {
        return "DeliveryPoint [index=" + index + ", address=" + address + ", invoiceId=" + invoiceId + "]";
    } 
}
