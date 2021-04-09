package com.spring.demo.controllers;

import com.spring.demo.exceptions.CartNotFoundException;
import com.spring.demo.models.Deliveries;
import com.spring.demo.models.Payments;
import com.spring.demo.models.Product;
import com.spring.demo.models.ShoppingCart;
import com.spring.demo.repositories.DeliveryRepository;
import com.spring.demo.repositories.PaymentRepository;
import com.spring.demo.repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class ShoppingServiceController {

    private final ShoppingCartRepository cartRepo;
    private final DeliveryRepository delRepo;
    private final PaymentRepository payRepo;

    //Users are connected to carts, we need to know which cart to add items to.
    //Another way to know this is to have the cart assigned from this instance startup
    //private final String userId;

    @Autowired
    public ShoppingServiceController(ShoppingCartRepository cartRepo, DeliveryRepository delRepo, PaymentRepository payRepo){
        this.cartRepo = cartRepo;
        this.delRepo = delRepo;
        this.payRepo = payRepo;

    }

    @PutMapping("/addItem")
    public ShoppingCart addProductToCart(@RequestBody Product prod, @RequestParam String id){
        try{
            var userOpt = cartRepo.findById(id);
            var cart = userOpt.get();
            cart.appendItemToProducts(prod);

            cartRepo.save(cart);
            return cart;
        } catch(NoSuchElementException e){
            e.printStackTrace();
            throw new CartNotFoundException(id);
        }

    }

    @PutMapping("/removeItem")
    public ShoppingCart removeProductFromCart(@RequestBody Product prod, @RequestParam String id){
        try{
            var userOpt = cartRepo.findById(id);
            var cart = userOpt.get();
            cart.removeItemFromProducts(prod);

            cartRepo.save(cart);
            return cart;
        } catch(NoSuchElementException e){
            e.printStackTrace();
            throw new CartNotFoundException(id);
        }
    }

    public double summarizeCost(@RequestParam String id){
        double sum = 0;
        try {
            var userOpt = cartRepo.findById(id);
            var cart = userOpt.get();
            List<Product> prods = cart.getProducts();

            for(Product prod : prods){
                sum += prod.getPrice();
            }

            return sum;
        } catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public List<Product> removeAllProductsFromCart(@RequestParam String id){
        var userOpt = cartRepo.findById(id);
        var cart = userOpt.get();
        List<Product> list = cart.getProducts();
        cart.removeAll();

        cartRepo.save(cart);
        return list;
    }

    public void createDelivery(@RequestParam String address, @RequestParam String id){
        Deliveries deliv = new Deliveries();
        deliv.setDeliveryAddress(address);
        deliv.setShoppingCart(cartRepo.findById(id).get());
        var cart = cartRepo.findById(id).get();
        cart.appendDelivery(deliv);
        cartRepo.save(cart);
        delRepo.save(deliv);
    }

    //Payments are handled client side, all we care about is getting the delivery and some info on the payment method
    public void createPayment(@RequestBody Deliveries deliv, @RequestParam String info){
        Payments pay = new Payments();
        pay.setDelivery(deliv);
        pay.setInfo(info);
        pay.setDate(LocalDateTime.now());
        payRepo.save(pay);
    }

}
