package com.xkings.core.logic;

import java.util.LinkedList;
import java.util.List;

public class Clock implements Runnable {

    private static final double SPEED_FACTOR = 1f;
    private final String name;
    private final boolean sleep;
    private boolean quit = false;
    private final List<Updateable> services = new LinkedList<Updateable>();
    private static final long BILLION = 1000000000;
    private static final float delta = .0166f;
    private static final float maxStep = .25f;
    private final boolean manualExecution;
    private long currentTime = System.nanoTime();
    private float accumulator = 0.0f;
    private long clocks;
    private long initTime;

    private Clock(String name, boolean sleep, boolean manualExecution) {
        this.name = name;
        this.sleep = sleep;
        this.manualExecution = manualExecution;
        initTime = System.currentTimeMillis();
    }

    public static Clock createInstance(String name, boolean sleep, boolean manualExecution) {
        Clock instance = new Clock(name, sleep, manualExecution);
        if (!manualExecution) {
            new Thread(instance, instance.getName()).start();
        }
        return instance;
    }

    @Override
    public void run() {
        if (!manualExecution) {
            while (!quit) {
                iterate();
            }
        } else {
            iterate();
        }
    }

    private void iterate() {
        long newTime = System.nanoTime();
        double frameTime = (double) (newTime - currentTime) * SPEED_FACTOR / BILLION;
        currentTime = newTime;

        frameTime = Math.min(frameTime, maxStep); // note: max frame time to avoid spiral of death

        accumulator += frameTime;

        if (accumulator < delta) {
            if (sleep) {
                try {
                    // Takes a nap for the remaining time.
                    Thread.sleep((long) ((delta - accumulator) * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    quit = true;
                }

            }
        } else {
            while (accumulator >= delta) {
                for (Updateable service : services) {
                    if (service.isActive()) {
                        service.update(delta);
                    }
                }
                accumulator -= delta;
                clocks++;
            }

        }
    }

    public void addService(Updateable service) {
        services.add(service);
    }

    public void removeService(Updateable service) {
        services.remove(service);
    }

    public long getClocks() {
        return clocks;
    }

    public float getFPS() {
        return clocks / ((System.currentTimeMillis() - initTime) / 1000f);
    }

    public String getName() {
        return name;
    }

    public void process() {
        if (manualExecution) {
            run();
        }
    }
}
