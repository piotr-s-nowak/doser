package edu.iis.mto.testreactor.doser;

public class MedicinePackageBuilder {

    private Medicine medicine = Medicine.of("drug");
    private Capacity capacity = Capacity.of(80, CapacityUnit.MILILITER);

    public MedicinePackageBuilder withMedicine(Medicine medicine) {
        this.medicine = medicine;
        return this;
    }

    public MedicinePackageBuilder withCapacity(Capacity capacity) {
        this.capacity = capacity;
        return this;
    }

    public MedicinePackage build() {
        return MedicinePackage.of(medicine, capacity);
    }
}