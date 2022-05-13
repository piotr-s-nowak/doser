package edu.iis.mto.testreactor.doser;


import edu.iis.mto.testreactor.doser.infuser.Infuser;
import edu.iis.mto.testreactor.doser.infuser.InfuserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        medicineDoser.add(medicinePackage);
        receipe = new RecipeBuilder().withNumber(25).build();
        assertThrows(InsufficientMedicineException.class, () -> medicineDoser.dose(receipe));
    }

    @Test
    void doseReceipeIsNullShouldThrowNPE() {
        medicineDoser.add(medicinePackage);
        assertThrows(NullPointerException.class, () -> medicineDoser.dose(null));
    }

    @Test
    void shouldInvokeInfuserDispenseSpecifiedInRecipeNumberOfTimes() throws InfuserException {
        int dummy_number = 4;
        receipe = new RecipeBuilder().withNumber(dummy_number).build();
        medicineDoser.add(medicinePackage);
        medicineDoser.dose(receipe);
        verify(infuser, times(4)).dispense(any(MedicinePackage.class), any(Capacity.class));
    }

    @Test
    void doseMethodShouldCatchExceptionAndSetDosingResultToAnError() {
        medicineDoser.add(medicinePackage);
        medicineDoser.dose(receipe);
        doThrow(new NullPointerException()).when(clock).wait(any(Period.class));
        DosingResult result = medicineDoser.dose(receipe);
        assertEquals(DosingResult.ERROR, result);
    }

    @Test
    void shouldSetDosingResultToSuccessIfMedicineInRecipeWasDosedSuccessfully() {

    }

    @Test
    void shouldInvokeClockWaitAfterDosing() {

    }

}
