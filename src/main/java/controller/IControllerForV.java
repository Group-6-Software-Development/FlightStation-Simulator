package controller;

import simu.entity.Variables;

import java.util.List;

public interface IControllerForV {
        void startSimulation();

        void speedUp();

        void slowDown();

        int getCustomerCount();

        void setSettings(int[] values);

        List<Variables> getResults();
}
