package com.imooc.mall.service.impl;

import com.google.gson.Gson;
import com.imooc.mall.dao.ProductMapper;
import com.imooc.mall.enums.ProductEnum;
import com.imooc.mall.enums.ResponseEnum;
import com.imooc.mall.form.CartAddFrom;
import com.imooc.mall.form.CartUpdateForm;
import com.imooc.mall.pojo.Cart;
import com.imooc.mall.pojo.Product;
import com.imooc.mall.service.ICartService;
import com.imooc.mall.vo.CartProductVo;
import com.imooc.mall.vo.CartVo;
import com.imooc.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CartServiceImpl implements ICartService {

    @Autowired
    private ProductMapper productMapper;

    private final static String CART_REDIS_KEY_TEMPLATE = "cart_%d";

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public ResponseVo<CartVo> add(Integer uid, CartAddFrom cartAddFrom) {

        Gson gson = new Gson();

        Integer quantity = 1;

        //查询到商品
        Product product = productMapper.selectByPrimaryKey(cartAddFrom.getProductId());
        //查看是否有商品
        if (product==null){
            return ResponseVo.error(ResponseEnum.PRODUCT_NOT_EXIT);
        }
        //查看商品状态是否正确
        if (!product.getStatus().equals(ProductEnum.ON_SALE.getCode())){
            return ResponseVo.error(ResponseEnum.PRODUCT_OFF_SALE_OR_DELETE);
        }
        //商品库存是否充足
        if (product.getStock()<=0){
            return ResponseVo.error(ResponseEnum.PRODUCT_STOCK_ERROR);
        }
        //使用Map的方式进行存储  购物车
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE,uid);
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        //将商品存储在Redis中
        String value = opsForHash.get(redisKey, String.valueOf(product.getId()));
        Cart cart = new Cart();
        if (StringUtils.isEmpty(value)){
            cart = new Cart(product.getId(),quantity,cartAddFrom.getSelected());
        }else{
            cart = gson.fromJson(value, Cart.class);
            cart.setQuantity(quantity+1);
        }
        opsForHash.put(redisKey,String.valueOf(product.getId()),
                 new Gson().toJson(cart));
        log.info("==========打印日志===============");
        return list(uid);
    }

    @Override
    public ResponseVo<CartVo> list(Integer uid) {
        //从redis中取出Map的productId 和 cart的值
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, uid);
        //get 是拿到一个数据   这个是拿到所有的数据
        Map<String, String> entries = opsForHash.entries(redisKey);
        Gson gson = new Gson();
        CartVo cartVo = new CartVo();
        boolean selectAll = false;
        Integer cartTotalQuentity = 0;
        BigDecimal cartTotalPrice = BigDecimal.ZERO;
        List<CartProductVo> cartProductVoList = new ArrayList<>();
        for (Map.Entry<String,String> entry:entries.entrySet()){
            //查出productId的商品
            Integer productId  = Integer.parseInt(entry.getKey());
            Cart cart = gson.fromJson(entry.getValue(), Cart.class);

            Product product = productMapper.selectByPrimaryKey(productId);
            if (product!=null){
                //将productId的商品查出的数据赋值到CartProductVo
                CartProductVo cartProductVo = new CartProductVo(
                      productId,
                      cart.getQuantity(),
                      product.getName(),
                      product.getSubtitle(),
                      product.getMainImage(),
                      product.getPrice(),
                      product.getStatus(),
                      product.getPrice().multiply(BigDecimal.valueOf(cart.getQuantity())),
                      product.getStock(),
                      cart.getProductSelected()
                );
                cartProductVoList.add(cartProductVo);
                if (!cart.getProductSelected()){
                    selectAll = false;
                }
                cartTotalPrice = cartTotalPrice.add(cartProductVo.getProductTotalPrice());
            }
            cartTotalQuentity += cart.getQuantity();
        }
        cartVo.setCartTotalPrice(cartTotalPrice);
        cartVo.setCartTotalQuantity(cartTotalQuentity);
        //有一个没有选中就不是全选
        cartVo.setSelectedAll(selectAll);
        cartVo.setCartProductVoList(cartProductVoList);
        //将CartProductVo的值赋值到CartVo
        return ResponseVo.success(cartVo);
    }

    @Override
    public ResponseVo<CartVo> update(Integer uid, Integer productId,
                                     CartUpdateForm cartUpdateForm) {
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, uid);

        String value = opsForHash.get(redisKey, String.valueOf(productId));
        Gson gson = new Gson();
        if (StringUtils.isEmpty(value)){
            return ResponseVo.error(ResponseEnum.CART_PRODUCT_NOT_EXIST);
        }else {
            //修改Cart里面的值
            Cart cart = gson.fromJson(value, Cart.class);
            if (cartUpdateForm.getQuantity()!=null&&cartUpdateForm.getQuantity()>=0){
                cart.setQuantity(cartUpdateForm.getQuantity());
            }
            if (cartUpdateForm.getSelected()!=null){
                cart.setProductSelected(cartUpdateForm.getSelected());
            }
            opsForHash.put(redisKey,String.valueOf(productId),gson.toJson(cart));
        }
        return list(uid);
    }

    @Override
    public ResponseVo<CartVo> delete(Integer uid, Integer productId) {
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, uid);
        String value = opsForHash.get(redisKey, String.valueOf(productId));
        if (StringUtils.isEmpty(value)){
            return ResponseVo.error(ResponseEnum.CART_PRODUCT_NOT_EXIST);
        }
        opsForHash.delete(redisKey,String.valueOf(productId));
        return list(uid);
    }

    @Override
    public ResponseVo<CartVo> selectAll(Integer uid) {
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, uid);
        Gson gson = new Gson();
        for (Cart cart:listForCart(uid)){
            cart.setProductSelected(true);
            opsForHash.put(redisKey,String.valueOf(cart.getProductId()),gson.toJson(cart));
        }
        return list(uid);
    }

    @Override
    public ResponseVo<CartVo> unSelectAll(Integer uid) {
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, uid);
        Gson gson = new Gson();
        for (Cart cart:listForCart(uid)){
            cart.setProductSelected(false);
            opsForHash.put(redisKey,String.valueOf(cart.getProductId()),gson.toJson(cart));
        }
        return list(uid);
    }

    @Override
    public ResponseVo<Integer> sum(Integer uid) {
        Integer num = listForCart(uid).stream()
                .map(Cart::getQuantity)
                .reduce(0, Integer::sum);
        return ResponseVo.success(num);
    }

    private List<Cart> listForCart(Integer uid){
        Gson gson = new Gson();
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, uid);
        Map<String, String> entries = opsForHash.entries(redisKey);
        List<Cart> cartList = new ArrayList<>();
        for (Map.Entry<String,String> entry:entries.entrySet()){
            cartList.add(gson.fromJson(entry.getValue(),Cart.class));
        }
        return cartList;
    }
}
