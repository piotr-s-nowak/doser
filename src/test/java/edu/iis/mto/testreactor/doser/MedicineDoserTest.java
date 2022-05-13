package edu.iis.mto.testreactor.doser;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MedicineDoserTest {


    @Test
    void shouldThrowUnavailableMedicineExceptionWhenTrayDoesNotContainMedicineSpecifiedInReceipt() {

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
