package edu.iis.mto.testreactor.doser;

import java.util.concurrent.TimeUnit;

public class DoseBuilder {

    private Capacity capacity = Capacity.of(12, CapacityUnit.MILILITER);
    private Period per = Period.of(5, TimeUnit.DAYS);

    public DoseBuilder withCapacity(Capacity capacity) {
        this.capacity = capacity;
        return this;
    }

    public DoseBuilder withPer(Period per) {
        this.per = per;
        return this;
    }

    public Dose build() {
        return Dose.of(capacity, per);
    }
}