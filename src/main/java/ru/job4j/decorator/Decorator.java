package ru.job4j.decorator;

public class Decorator {
    public static void main(String[] args) {
        Product milk = new Milk(50);
        Product milkDisc = new Discount2((new Discount1(milk)));
        System.out.println(milkDisc.getPrice());
    }

    interface Product {
        int getPrice();
    }

    public static class Milk implements Product {
        int price;

        public Milk(int price) {
            this.price = price;
        }

        @Override
        public int getPrice() {
            return price;
        }
    }

    public static class Discount1 implements Product {
        Product p;

        public Discount1(Product p) {
            this.p = p;
        }

        @Override
        public int getPrice() {
            return p.getPrice() - 10;
        }
    }

    public static class Discount2 implements Product {
        Product p;

        public Discount2(Product p) {
            this.p = p;
        }

        @Override
        public int getPrice() {
            return p.getPrice() - 20;
        }
    }
}



