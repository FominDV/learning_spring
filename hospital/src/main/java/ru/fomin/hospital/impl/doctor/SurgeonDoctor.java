package ru.fomin.hospital.impl.doctor;

import org.springframework.stereotype.Component;
import ru.fomin.hospital.api.Doctor;
import ru.fomin.model.Patient;
import ru.fomin.model.enumeration.DiseaseEnum;
import ru.fomin.model.enumeration.SymptomEnum;

@Component("surgeon")
public class SurgeonDoctor implements Doctor {

    @Override
    public void heal(Patient patient) {

    }

    @Override
    public DiseaseEnum getDisease(SymptomEnum symptom) {
        return null;
    }

}
