package ru.job4j.decorator;

public class Decorator {
    public static void main(String[] args) {
        Product milk = new Milk(50);
        Product milkDisc = new MilkDiskount(new MilkDiskount(milk));
        System.out.println(milkDisc.getPrice());
    }
}

interface Product {
    int getPrice();
}

class Milk implements Product {
    int price;

    public Milk(int price) {
        this.price = price;
    }

    @Override
    public int getPrice() {
        return price;
    }
}

abstract class Decorator2 implements Product {
    Product product;

    public Decorator2(Product product) {
        this.product = product;
    }
}

class MilkDiskount extends Decorator2 {
    public MilkDiskount(Product product) {
        super(product);
    }

    @Override
    public int getPrice() {
        return product.getPrice() - 15;
    }
}