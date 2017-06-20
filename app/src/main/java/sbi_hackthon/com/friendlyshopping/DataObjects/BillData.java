package sbi_hackthon.com.friendlyshopping.DataObjects;

/**
 * Created by akriti on 20/6/17.
 */

public class BillData {
    private Long id;
    private String customer_name, bill_amount, bill_msg, bill_date;


    public BillData(Long id, String customer_name, String bill_amount, String bill_msg, String bill_date) {
        this.id = id;
        this.customer_name = customer_name;
        this.bill_amount = bill_amount;
        this.bill_msg = bill_msg;
        this.bill_date = bill_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getBill_amount() {
        return bill_amount;
    }

    public void setBill_amount(String bill_amount) {
        this.bill_amount = bill_amount;
    }

    public String getBill_msg() {
        return bill_msg;
    }

    public void setBill_msg(String bill_msg) {
        this.bill_msg = bill_msg;
    }

    public String getBill_date() {
        return bill_date;
    }

    public void setBill_date(String bill_date) {
        this.bill_date = bill_date;
    }
}
