package mantoo.dbcent.mantoo.Information;

/**
 * Created by dbcent91 on 1/8/17.
 */

public class TransactionEntryInfo {

    private String txnEntryId;
    private String transactionsId;
    private String inventoryId;
    private int quantity;
    private Double rate;
    private Double mrp;
    private Double discount;
    private Double tax;
    private Double amount;
    private String comment;
    private long millisecond;




    public String getTxnEntryId() {
        return txnEntryId;
    }

    public void setTxnEntryId(String txnEntryId) {
        this.txnEntryId = txnEntryId;
    }

    public String getTransactionsId() {
        return transactionsId;
    }

    public void setTransactionsId(String transactionsId) {
        this.transactionsId = transactionsId;
    }

    public String getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(String inventoryId) {
        this.inventoryId = inventoryId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getMillisecond() {
        return millisecond;
    }

    public void setMillisecond(long millisecond) {
        this.millisecond = millisecond;
    }
}
