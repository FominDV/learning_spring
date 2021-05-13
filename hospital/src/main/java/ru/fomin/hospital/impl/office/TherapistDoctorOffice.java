package ru.fomin.hospital.impl.office;

import org.springframework.stereotype.Component;
import ru.fomin.hospital.api.DoctorOffice;
import ru.fomin.model.Patient;

@Component("therapistOffice")
public class TherapistDoctorOffice implements DoctorOffice {

    @Override
    public void showToDoctor(Patient patient) {

    }
}
