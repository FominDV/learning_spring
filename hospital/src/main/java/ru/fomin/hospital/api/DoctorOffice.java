package ru.fomin.hospital.api;

import ru.fomin.model.Patient;
import ru.fomin.model.enumeration.DiseaseEnum;

import java.util.ArrayList;
import java.util.List;

public interface DoctorOffice {

    void showToDoctor(Patient patient);

    default void printDiagnosis(Patient patient, DiseaseEnum disease){
        System.out.printf(
                "Patient %s is sick by %s.\nHim symptom is %s.\n",
                patient.getName(),
                disease.toString().toLowerCase(),
                patient.getSymptom().toString().toLowerCase()
        );
    }

}
