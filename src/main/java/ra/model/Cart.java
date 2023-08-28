package ra.model;

public class Cart {
    private int id;
    private Product product;
    private int quantity;
    private Double totalPrice;


    public Cart() {
    }

    public Cart(int id, Product product,  int quantity) {
        this.id = id;
        this.product = product;

        this.quantity = quantity;
    }

    public Cart(int id, Product product, int quantity, Double totalPrice) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
