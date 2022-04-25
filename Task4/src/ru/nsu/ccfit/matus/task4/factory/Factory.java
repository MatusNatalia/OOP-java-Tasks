package ru.nsu.ccfit.matus.task4.factory;

import ru.nsu.ccfit.matus.task4.threadpool.MyThreadPool;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Vector;

public class Factory {

    private final EngineStorage engineStorage;
    private final EngineSupplier engineSupplier;
    private final CarBodySupplier carBodySupplier;
    private final CarBodyStorage carBodyStorage;
    private final AccessoryStorage accessoryStorage;
    private final Vector<AccessorySupplier> accessorySuppliers;
    private final Vector<Dealer> dealers;
    private final CarStorage carStorage;
    private final Controller controller;
    private final MyThreadPool workers;


    public Factory() throws IOException {
        String file = "Config.txt";
        InputStream stream = Factory.class.getResourceAsStream(file);
        Properties config = new Properties();
        if (stream == null) {
            System.out.println("File '" + file + "' not found");
        } else {
            config.load(stream);
        }
        engineStorage = new EngineStorage(Integer.parseInt(config.getProperty("EngineStorageSize")));
        engineSupplier = new EngineSupplier(engineStorage);
        carBodyStorage = new CarBodyStorage(Integer.parseInt(config.getProperty("CarBodyStorageSize")));
        carBodySupplier = new CarBodySupplier(carBodyStorage);
        accessoryStorage = new AccessoryStorage(Integer.parseInt(config.getProperty("AccessoryStorageSize")));
        int suppliers = Integer.parseInt(config.getProperty("AccessorySuppliers"));
        accessorySuppliers = new Vector<>();
        for(int i = 0; i < suppliers; i++) {
            accessorySuppliers.add(new AccessorySupplier(accessoryStorage));
        }
        carStorage = new CarStorage(Integer.parseInt(config.getProperty("CarStorageSize")), Integer.parseInt(config.getProperty("Log")));
        int carDealers = Integer.parseInt(config.getProperty("Dealers"));
        dealers = new Vector<>();
        for(int i = 0; i < carDealers; i++) {
            dealers.add(new Dealer(carStorage, i + 1));
        }
        workers = new MyThreadPool(Integer.parseInt(config.getProperty("Workers")));
        controller = new Controller(workers, engineStorage, carBodyStorage, accessoryStorage, carStorage);
    }


    public void work() {
        new Thread(engineSupplier).start();
        new Thread(carBodySupplier).start();
        for (AccessorySupplier accessorySupplier : accessorySuppliers) {
            new Thread(accessorySupplier).start();
        }
        for (Dealer dealer : dealers) {
            new Thread(dealer).start();
        }
        new Thread(controller).start();
    }


    public int getNumEngines() {
        return engineStorage.getNumEngines();
    }

    public int getNumAllCars() {
        return carStorage.getNumAllCars();
    }

    public int getNumCars() {
        return carStorage.getNumCars();
    }

    public int getNumAccessories() {
        return accessoryStorage.getNumAccessories();
    }

    public int getNumCarBody() {
        return carBodyStorage.getNumCarBody();
    }

    public void changeDealerSpeed(int time) {
        for (Dealer dealer : dealers) {
            dealer.changeTime(time);
        }
    }

    public void changeAccessorySupplierSpeed(int time) {
        for (AccessorySupplier accessorySupplier : accessorySuppliers) {
            accessorySupplier.changeTime(time);
        }
    }

    public void changeCarBodySupplierSpeed(int time) {
        carBodySupplier.changeTime(time);
    }

    public void changeEngineSupplierSpeed(int time) {
       engineSupplier.changeTime(time);
    }


    public void stop(){
        controller.stop();
        engineSupplier.stop();
        carBodySupplier.stop();
        for (AccessorySupplier accessorySupplier : accessorySuppliers) {
            accessorySupplier.stop();
        }
        for (Dealer dealer : dealers) {
            dealer.stop();
        }
        workers.stop();
    }


}
