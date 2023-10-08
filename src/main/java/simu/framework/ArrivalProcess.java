package simu.framework;

import eduni.distributions.ContinuousGenerator;

public class ArrivalProcess {
    private final ContinuousGenerator generator;
    private final EventList eventList;
    private final IEventType type;

    public ArrivalProcess(ContinuousGenerator g, EventList el, IEventType type) {
        this.generator = g;
        this.eventList = el;
        this.type = type;
    }

    public void generateNext() {
        Event e = new Event(type, Clock.getInstance().getTime() + generator.sample());
        eventList.add(e);
    }
}
