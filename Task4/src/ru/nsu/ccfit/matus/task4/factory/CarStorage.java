package ru.nsu.ccfit.matus.task4.factory;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicLong;

public class CarStorage {

    private final static Logger log = LogManager.getLogger(CarStorage.class);
    private final int size;
    private int allCars = 0;
    private final ArrayList<Car> cars;
    private final int isLog;
    private final AtomicLong id;

    public CarStorage(int size, int isLog) {
        this.size = size;
        cars = new ArrayList<>();
        id = new AtomicLong(0);
        this.isLog = isLog;
    }

    public Car get(int id) {
        synchronized (cars) {
            while (cars.isEmpty()) {
                try {
                    cars.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Со склада автомобилей забрали автомобиль");
            System.out.println("Автомобилей на складе: " + (cars.size() - 1));
            cars.notifyAll();

            Car car = cars.remove(0);

            if (isLog == 1) {
                log.info("Dealer:<" + id + ">:Auto<" + car.getId() + ">(Body<" + car.getBodyId() + ">,Motor<" + car.getEngineId() + ">,Accessory<" + car.getAccessoryId() + ">)");
            }

            return car;
        }
    }
    public void put(Car car) {
        synchronized (cars) {
            while (cars.size() == size) {
                try {
                    cars.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            allCars++;
            cars.add(car);
            System.out.println("На склад автомобилей доставили автомобиль");
            System.out.println("Автомобилей на складе: " + cars.size());
            cars.notifyAll();
        }
    }

    public long getId(){
        return id.incrementAndGet();
    }

    public int getFreePlace() {
        synchronized (cars) {
            return size - cars.size();
        }
    }

    public int getNumCars() {
        synchronized (cars) {
            return cars.size();
        }
    }

    public int getNumAllCars() {
        return allCars;
    }
}

