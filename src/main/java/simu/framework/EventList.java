package simu.framework;

import java.util.PriorityQueue;

public class EventList {
    private final PriorityQueue<Event> list = new PriorityQueue<>();

    public EventList() {

    }

    public Event remove() {
        Trace.out(Trace.Level.INFO, "Removing event " + list.peek().getType() + " " + list.peek().getTime());
        return list.remove();
    }

    public void add(Event e) {
        Trace.out(Trace.Level.INFO, "Adding event " + e.getType() + " " + e.getTime());
        list.add(e);
    }

    public double getNextTime() {
        return list.peek().getTime();
    }
}
