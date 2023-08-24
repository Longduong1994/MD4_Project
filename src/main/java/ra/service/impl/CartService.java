package ra.service.impl;


import org.springframework.stereotype.Service;
import ra.model.Cart;
import ra.service.IGenericService;

import java.util.List;

@Service
public class CartService implements IGenericService<Cart,Integer> {
    List<Cart> carts;

    public CartService(List<Cart> carts) {
        this.carts = carts;
    }

    @Override
    public List<Cart> findAll() {
        return carts;
    }

    @Override
    public Cart findById(Integer id) {
        for (Cart cart :carts){
            if(cart.getProduct().getId()==id){
                return cart;
            }
        }
        return null;
    }

    @Override
    public void save(Cart cart) {
        if (findById(cart.getId())==null){
            carts.add(cart);
        }else{
            carts.set(carts.indexOf(findById(cart.getId())),cart);
        }
    }

    @Override
    public void delete(Integer id) {
        carts.remove(findById(id));
    }

    public int newId() {
        int max=0;
        for (Cart cart :carts){
            if (cart.getId()>max){
                max = cart.getId();
            }
        }
        return max+1;
    }
}
