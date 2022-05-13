package edu.iis.mto.testreactor.doser;

public class RecipeBuilder {

    private Medicine medicine = Medicine.of("drug");
    private Dose dose = new DoseBuilder().build();
    private int number = 5;

    public RecipeBuilder withMedicine(Medicine medicine) {
        this.medicine = medicine;
        return this;
    }

    public RecipeBuilder withDose(Dose dose) {
        this.dose = dose;
        return this;
    }

    public RecipeBuilder withNumber(int number) {
        this.number = number;
        return this;
    }

    public Receipe build() {
        return Receipe.of(medicine, dose, number);
    }
}