package ru.nsu.ccfit.matus.task4.factory;

import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicLong;

public class AccessoryStorage {
    private final int size;
    private final ArrayList<Accessory> accessories;
    private final AtomicLong id;

    public AccessoryStorage(int size) {
        this.size = size;
        accessories = new ArrayList<>();
        id = new AtomicLong(0);
    }

    public Accessory get() {
        synchronized (accessories) {
            while (accessories.isEmpty()) {
                try {
                    accessories.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Со склада аксессуаров забрали аксессуар");
            System.out.println("Аксессуаров на складе: " + (accessories.size() - 1));
            accessories.notifyAll();

            return accessories.remove(0);
        }
    }
    public synchronized void put(Accessory accessory) {
        synchronized (accessories) {
            while (accessories.size() == size) {
                try {
                    accessories.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            accessories.add(accessory);
            System.out.println("На склад аксессуаров доставили аксессуар");
            System.out.println("Аксессуаров на складе: " + accessories.size());
            accessories.notifyAll();
        }
    }

    public long getId(){
        return id.incrementAndGet();
    }

    public int getNumAccessories() {
        synchronized (accessories) {
            return accessories.size();
        }
    }
}
