package controller;

public interface IControllerForV {
        void startSimulation();

        void speedUp();

        void slowDown();

        int getCustomerCount();

        void setSettings(int[] values);
}
