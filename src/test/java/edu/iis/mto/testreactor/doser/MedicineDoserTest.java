package edu.iis.mto.testreactor.doser;


import edu.iis.mto.testreactor.doser.infuser.Infuser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class MedicineDoserTest {

    @Mock
    Infuser infuser;
    @Mock
    DosageLog dosageLog;
    @Mock
    Clock clock;

    private MedicineDoser medicineDoser;
    private MedicinePackage medicinePackage;
    private Receipe receipe;

    @BeforeEach
    void config() {
        medicineDoser = new MedicineDoser(infuser, dosageLog, clock);
        medicinePackage = new MedicinePackageBuilder().build();
        receipe = new RecipeBuilder().build();
    }


    @Test
    void shouldThrowUnavailableMedicineExceptionWhenTrayDoesNotContainMedicineSpecifiedInReceipt() {
        MedicinePackage medicinePackage = new MedicinePackageBuilder().withMedicine(Medicine.of("drops")).build();
        medicineDoser.add(medicinePackage);
        assertThrows(UnavailableMedicineException.class, () -> medicineDoser.dose(receipe));
    }

    @Test
    void useMethodDoseShouldInvokeLoggingMethods() {

    }

    @Test
    void catchInsufficientMedicineExceptionWhenRecipeNumberGreaterThanMedicinePackageCapacity() {

    }

    @Test
    void doseReceipeIsNullShouldThrowNPE() {

    }

    @Test
    void shouldInvokeInfuserDispenseSpecifiedInRecipeNumberOfTimes() {

    }

    @Test
    void doseMethodShouldCatchExceptionAndSetDosingResultToAnError() {

    }

    @Test
    void shouldSetDosingResultToSuccessIfMedicineInRecipeWasDosedSuccessfully() {

    }

    @Test
    void shouldInvokeClockWaitAfterDosing() {

    }

}
