package com.hm.picture_of_patten.factoryMethod.idcard;

import com.hm.picture_of_patten.factoryMethod.framework.Factory;
import com.hm.picture_of_patten.factoryMethod.framework.Product;

import java.util.ArrayList;
import java.util.List;

public class IDCardFactory extends Factory {

    private List owners = new ArrayList();

    protected Product createProduct(String owner) {
        return new IDCard(owner);
    }

    protected void registerProduct(Product product) {
        owners.add(((IDCard) product).getOwner());
    }

    public List getOwners() {
        return owners;
    }
}
