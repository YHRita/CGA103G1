package com.cart.model;

import java.util.List;

public interface I_CartDAO {
	public void insert(CartVO cartVO);
    public void update(CartVO cartVO);
//    public void delete(Integer cartVO);
    public CartVO findByPrimaryKey(Integer pk_cart);
    public List<CartVO> getAll();
}
