package controller;

import simu.entity.Variables;

import java.util.List;

public interface IControllerForV {
        /**
         * Starts the simulation.
         */
        void startSimulation();

        /**
         * Speed up the simulation.
         */
        void speedUp();

        /**
         * Slow down the simulation.
         */
        void slowDown();

        /**
         * Returns the number of customers that were served.
         * @return The number of customers that were served.
         */
        int getCustomerCount();

        /**
         * Set the settings for the simulation.
         *
         * @param values The settings for the simulation.
         */
        void setSettings(int[] values);

        /**
         * Returns the results of the simulation.
         * @return The results of the simulation.
         */
        List<Variables> getResults();
}
