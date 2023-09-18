package simu.model;

import simu.framework.IEventType;

public enum EventType implements IEventType{
    // Uudet tyypit en ole kokeillu vaihtaa DEP esimerkiksi Bag dropiksi.
    ARR1, CHECKIN, BAGDROP, SECURITYCHECK, RANDOMINSPECTION, PASSPORTCHECK, TICKETINSPECTION
}
