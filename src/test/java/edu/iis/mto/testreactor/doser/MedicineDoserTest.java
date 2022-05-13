package edu.iis.mto.testreactor.doser;


import edu.iis.mto.testreactor.doser.infuser.Infuser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

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
        medicineDoser.add(medicinePackage);
        medicineDoser.dose(receipe);
        verify(dosageLog, times(1)).logStart();
        verify(dosageLog, times(1)).logEnd();
        verify(dosageLog, times(receipe.getNumber())).logStartDose(any(Medicine.class), any(Dose.class));
        verify(dosageLog, times(receipe.getNumber())).logEndDose(any(Medicine.class), any(Dose.class));
        verifyNoMoreInteractions(dosageLog);
    }

    @Test
    void catchInsufficientMedicineExceptionWhenRecipeNumberGreaterThanMedicinePackageCapacity() {

    }

    @Test
    void doseReceipeIsNullShouldThrowNPE() {
        medicineDoser.add(medicinePackage);
        assertThrows(NullPointerException.class, () -> medicineDoser.dose(null));
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
