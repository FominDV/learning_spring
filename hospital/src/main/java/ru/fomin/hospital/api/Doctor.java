package ru.fomin.hospital.api;

import ru.fomin.model.Patient;
import ru.fomin.model.enumeration.DiseaseEnum;
import ru.fomin.model.enumeration.SymptomEnum;

import java.util.HashMap;
import java.util.Map;

public interface Doctor {

   Map<SymptomEnum, DiseaseEnum> symptomToDiseaseMap = new HashMap<>();

   void heal(Patient patient);
   DiseaseEnum getDisease(SymptomEnum symptom);

}
