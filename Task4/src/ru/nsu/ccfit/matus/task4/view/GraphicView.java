package ru.nsu.ccfit.matus.task4.view;

import ru.nsu.ccfit.matus.task4.factory.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class GraphicView {

    private final Timer timer;

    public GraphicView(Factory factory) {

        JFrame window = new JFrame();
        window.setTitle("Factory");
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window.setPreferredSize(new Dimension(420, 470));
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                factory.stop();
                timer.stop();
            }
        });

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JSlider engineSlider = new JSlider(JSlider.HORIZONTAL, 0, 10, 2);
        engineSlider.setMajorTickSpacing(1);
        engineSlider.setPaintTicks(true);
        engineSlider.setPaintLabels(true);
        engineSlider.addChangeListener(e -> {
            JSlider source = (JSlider)e.getSource();
            factory.changeEngineSupplierSpeed(source.getValue() * 1000);
        });

        JSlider carBodySlider = new JSlider(JSlider.HORIZONTAL, 0, 10, 3);
        carBodySlider.setMajorTickSpacing(1);
        carBodySlider.setPaintTicks(true);
        carBodySlider.setPaintLabels(true);
        carBodySlider.addChangeListener(e -> {
            JSlider source = (JSlider)e.getSource();
            factory.changeCarBodySupplierSpeed(source.getValue() * 1000);
        });

        JSlider accessorySlider = new JSlider(JSlider.HORIZONTAL, 0, 10, 5);
        accessorySlider.setMajorTickSpacing(1);
        accessorySlider.setPaintTicks(true);
        accessorySlider.setPaintLabels(true);
        accessorySlider.addChangeListener(e -> {
            JSlider source = (JSlider)e.getSource();
            factory.changeAccessorySupplierSpeed(source.getValue() * 1000);
        });

        JSlider dealersSlider = new JSlider(JSlider.HORIZONTAL, 0, 20, 10);
        dealersSlider.setMajorTickSpacing(2);
        dealersSlider.setPaintTicks(true);
        dealersSlider.setPaintLabels(true);
        dealersSlider.addChangeListener(e -> {
            JSlider source = (JSlider)e.getSource();
            factory.changeDealerSpeed(source.getValue() * 1000);
        });

        JLabel enginesLabel = new JLabel("Number of engines in engine storage:" + factory.getNumEngines());
        JLabel carBodiesLabel = new JLabel("Number of car bodies in car body storage:" + factory.getNumCarBody());
        JLabel accessoriesLabel = new JLabel("Number of accessories in accessory storage:" + factory.getNumAccessories());
        JLabel carsLabel = new JLabel("Number of cars in car storage:" + factory.getNumCars());
        JLabel allCarsLabel = new JLabel("Number of produced cars:" + factory.getNumAllCars());

        mainPanel.add(new JLabel("Engine supplier speed:"), BorderLayout.CENTER);
        mainPanel.add(engineSlider, BorderLayout.CENTER);
        mainPanel.add(new JLabel("Car body supplier speed:"), BorderLayout.CENTER);
        mainPanel.add(carBodySlider, BorderLayout.CENTER);
        mainPanel.add(new JLabel("Accessory suppliers speed:"), BorderLayout.CENTER);
        mainPanel.add(accessorySlider, BorderLayout.CENTER);
        mainPanel.add(new JLabel("Dealers speed:"), BorderLayout.CENTER);
        mainPanel.add(dealersSlider, BorderLayout.CENTER);
        mainPanel.add(enginesLabel, BorderLayout.CENTER);
        mainPanel.add(carBodiesLabel, BorderLayout.CENTER);
        mainPanel.add(accessoriesLabel, BorderLayout.CENTER);
        mainPanel.add(carsLabel, BorderLayout.CENTER);
        mainPanel.add(allCarsLabel, BorderLayout.CENTER);

        window.add(mainPanel);
        window.pack();
        window.setVisible(true);

        ActionListener taskPerformer = e -> {
            enginesLabel.setText("Number of engines in engine storage:" + factory.getNumEngines());
            carBodiesLabel.setText("Number of car bodies in car body storage:" + factory.getNumCarBody());
            accessoriesLabel.setText("Number of accessories in accessory storage:" + factory.getNumAccessories());
            carsLabel.setText("Number of cars in car storage:" + factory.getNumCars());
            allCarsLabel.setText("Number of produced cars:" + factory.getNumAllCars());
        };
        timer = new Timer(500, taskPerformer);
        timer.start();

    }


}
