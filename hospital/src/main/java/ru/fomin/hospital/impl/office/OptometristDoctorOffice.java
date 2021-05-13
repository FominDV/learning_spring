package ru.fomin.hospital.impl.office;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.fomin.hospital.api.Doctor;
import ru.fomin.hospital.api.DoctorOffice;
import ru.fomin.model.Patient;
import ru.fomin.model.enumeration.DiseaseEnum;

@Component("optometristOffice")
@Qualifier("optometrist")
public class OptometristDoctorOffice implements DoctorOffice {

   private Doctor doctor;

    @Override
    public void showToDoctor(Patient patient) {
        DiseaseEnum disease = doctor.getDisease(patient.getSymptom());
        printDiagnosis(patient, disease);
    }

    @Autowired
    @Qualifier("optometrist")
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

}
