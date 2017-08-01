package mantoo.dbcent.mantoo.Information;

/**
 * Created by dbcent91 on 26/7/17.
 */

public class InventoryInformation {


    private String id;
    private String inventoryName;
    private Double tax;
    private Double gstRate;
    private Double rate;
    private Double mrp;
    private Double purcahsePrice;

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    private Double discount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInventoryName() {
        return inventoryName;
    }

    public void setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getGstRate() {
        return gstRate;
    }

    public void setGstRate(Double gstRate) {
        this.gstRate = gstRate;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getMrp() {
        return mrp;
    }

    public void setMrp(Double mrp) {
        this.mrp = mrp;
    }

    public Double getPurcahsePrice() {
        return purcahsePrice;
    }

    public void setPurcahsePrice(Double purcahsePrice) {
        this.purcahsePrice = purcahsePrice;
    }
}
