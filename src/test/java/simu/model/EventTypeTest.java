package simu.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTypeTest {

    @Test
    void testEventName() {
        // test each enum value
        assertEquals("ARRIVAL", EventType.getEventName(EventType.ARR1));
        assertEquals("CHECK-IN", EventType.getEventName(EventType.CHECKIN));
        assertEquals("BAG-DROP", EventType.getEventName(EventType.BAGDROP));
        assertEquals("SECURITY CHECK", EventType.getEventName(EventType.SECURITYCHECK));
        assertEquals("PASSPORT CHECK", EventType.getEventName(EventType.PASSPORTCHECK));
        assertEquals("TICKET INSPECTION", EventType.getEventName(EventType.TICKETINSPECTION));
    }
}