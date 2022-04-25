package ru.nsu.ccfit.matus.task4.factory;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class CarBodyStorage {
    private final int size;
    private final ArrayList<CarBody> carBodies;
    private final AtomicLong id;

    public CarBodyStorage(int size) {
        this.size = size;
        carBodies = new ArrayList<>();
        id = new AtomicLong(0);
    }

    public CarBody get() {
        synchronized (carBodies) {
            while (carBodies.isEmpty()) {
                try {
                    carBodies.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Со склада кузовов забрали кузов");
            System.out.println("Кузовов на складе: " + (carBodies.size() - 1));
            carBodies.notifyAll();

            return carBodies.remove(0);
        }
    }
    public void put(CarBody carBody) {
        synchronized (carBodies) {
            while (carBodies.size() == size) {
                try {
                    carBodies.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            carBodies.add(carBody);
            System.out.println("На склад кузовов доставили кузов");
            System.out.println("Кузовов на складе: " + carBodies.size());
            carBodies.notifyAll();
        }
    }

    public long getId(){
        return id.incrementAndGet();
    }

    public int getNumCarBody() {
        synchronized (carBodies) {
            return carBodies.size();
        }
    }
}
