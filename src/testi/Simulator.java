package testi;

import simu.framework.Engine;
import simu.framework.Trace;
import simu.framework.Trace.Level;
import simu.model.OwnEngine;


public class Simulator {
    public static void main(String[] args) {
        Trace.setTraceLevel(Level.INFO);
        Engine m = new OwnEngine();
        m.setSimulationTime(100);
        m.run();
    }
}
