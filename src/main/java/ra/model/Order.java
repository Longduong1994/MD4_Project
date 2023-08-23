package ra.model;

import java.util.Date;

public class Order {
    private int id;
    private int product_id;
    private Date export_Date;

    public Order() {
    }

    public Order(int id,int product_id, Date export_Date) {
        this.id = id;
        this.product_id = product_id;
        this.export_Date = export_Date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public Date getExport_Date() {
        return export_Date;
    }

    public void setExport_Date(Date export_Date) {
        this.export_Date = export_Date;
    }
}
