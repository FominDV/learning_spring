package ru.fomin.hospital.impl.office;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.fomin.hospital.api.Doctor;
import ru.fomin.hospital.api.DoctorOffice;

@Component("surgeonOffice")
public class SurgeonDoctorOffice extends DoctorOffice {

    @Autowired
    @Override
    @Qualifier("optometrist")
    public void setDoctor(Doctor doctor) {
        super.setDoctor(doctor);
    }
}
