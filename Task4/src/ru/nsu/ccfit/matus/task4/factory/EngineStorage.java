package ru.nsu.ccfit.matus.task4.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class EngineStorage {
    private final int size;
    private final List<Engine> engines;
    public final AtomicLong id;

    public EngineStorage(int size) {
        this.size = size;
        engines = new ArrayList<>();
        id = new AtomicLong(0);
    }

    public Engine get() {
        synchronized (engines) {
            while (engines.isEmpty()) {
                try {
                    engines.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Со склада двигателей забрали двигатель");
            System.out.println("Двигателей на складе: " + (engines.size() - 1));
            engines.notifyAll();

            return engines.remove(0);
        }
    }
    public void put(Engine engine) {
        synchronized (engines) {
            while (engines.size() == size) {
                try {
                    engines.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            engines.add(engine);
            System.out.println("На склад двигателей доставили двигатель");
            System.out.println("Двигателей на складе: " + engines.size());
            engines.notifyAll();
        }
    }

    public long getId(){
        return id.incrementAndGet();
    }

    public int getNumEngines() {
        synchronized (engines) {
            return engines.size();
        }
    }
}
