package mantoo.dbcent.mantoo.Information;

/**
 * Created by dbcent91 on 21/7/17.
 */

public class CustomerInformation {


    private String customerId;
    private String customerMantooId;
    private String customerName;
    private String customerAddress;
    private String customerContact;
    private double customerBalance;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerMantooId() {
        return customerMantooId;
    }

    public void setCustomerMantooId(String customerMantooId) {
        this.customerMantooId = customerMantooId;
    }


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(String customerContact) {
        this.customerContact = customerContact;
    }

    public Double getCustomerBalance() {
        return customerBalance;
    }

    public void setCustomerBalance(double customerBalance) {
        this.customerBalance = customerBalance;
    }
}
