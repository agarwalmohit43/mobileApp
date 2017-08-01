package mantoo.dbcent.mantoo.Information;

import java.util.Date;

/**
 * Created by dbcent91 on 31/7/17.
 */

public class TransactionsInformation {


    private String id;
    private String txnNumber;
    private String partyId;
    private String firmId;
    private Double amount;
    private Double additionalDiscount;
    private String status;
    private String comment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTxnNumber() {
        return txnNumber;
    }

    public void setTxnNumber(String txnNumber) {
        this.txnNumber = txnNumber;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    public String getFirmId() {
        return firmId;
    }

    public void setFirmId(String firmId) {
        this.firmId = firmId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAdditionalDiscount() {
        return additionalDiscount;
    }

    public void setAdditionalDiscount(Double additionalDiscount) {
        this.additionalDiscount = additionalDiscount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
