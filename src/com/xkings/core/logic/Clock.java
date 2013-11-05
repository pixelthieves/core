package com.xkings.core.logic;

import com.badlogic.gdx.math.MathUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * Internal clock system that triggers system function in an interval. Clock can be triggered manually or by a {@link
 * Thread}. This system ensures that simulation will run at constant speed, eliminating spikes.
 */
public class Clock implements Runnable {

    private static final float MAX_MULTIPLIER = 16;
    private static final float MIN_MULTIPLIER = 1 / MAX_MULTIPLIER;
    private float speedMultiplier = 1f;
    private final String name;
    private final boolean sleep;
    private boolean quit = false;
    private final List<Updateable> services = new LinkedList<Updateable>();
    private static final long BILLION = 1000000000;
    private static final float originalDelta = .0166f;
    private static final double maxStep = originalDelta * MAX_MULTIPLIER;
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

    public static Clock createInstance(String name) {
        return createInstance(name, true, false);
    }


    public static Clock createInstance(String name, boolean sleep, boolean manualExecution) {
        Clock instance = new Clock(name, sleep, manualExecution);
        if (!manualExecution) {
            new Thread(instance, instance.getName()).start();
        }
        return instance;
    }

    /**
     * If manualExecution is set to {@code true} one cycle is triggered, infinite while cycle is triggered otherwise.
     * <p/>
     * {@inheritDoc}
     */
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
        double frameTime = (double) (newTime - currentTime) * speedMultiplier / BILLION;
        currentTime = newTime;

        float delta = originalDelta * speedMultiplier;
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

    /**
     * Adds service into the system.
     *
     * @param service to be added
     */
    public void addService(Updateable service) {
        services.add(service);
    }


    /**
     * Removes service from the system.
     *
     * @param service to be removed
     */
    public void removeService(Updateable service) {
        services.remove(service);
    }

    /**
     * Returns number or clocks since start of the system.
     *
     * @return number of clocks
     */
    public long getClocks() {
        return clocks;
    }

    public float getFPS() {
        return clocks / ((System.currentTimeMillis() - initTime) / 1000f);
    }

    public String getName() {
        return name;
    }

    /**
     * Sets speed multiplier for the game.
     *
     * @param speedMultiplier If multiplier is greater then 1, game will run faster, slower if multiplier is smaller.
     */
    public void setSpeedMultiplier(float speedMultiplier) {
        this.speedMultiplier = MathUtils.clamp(speedMultiplier, MIN_MULTIPLIER, MAX_MULTIPLIER);
    }

    public float getSpeedMultiplier() {
        return speedMultiplier;
    }
}
