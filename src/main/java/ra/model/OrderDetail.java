package ra.model;

public class OrderDetail {
    private int id;
    private Order order;
    private int user_id;
    private int quantity;
    private double total;
    private boolean status;

    public OrderDetail() {
    }

    public OrderDetail(int id, Order order,int user_id, int quantity, double total, boolean status) {
        this.id = id;
        this.order = order;
        this.user_id = user_id;
        this.quantity = quantity;
        this.total = total;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
