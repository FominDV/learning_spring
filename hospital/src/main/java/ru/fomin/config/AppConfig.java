package ru.fomin.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.fomin.hospital.api.DoctorOffice;
import ru.fomin.model.enumeration.SymptomEnum;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan("ru.fomin")
@RequiredArgsConstructor
public class AppConfig {

    private final DoctorOffice optometristOffice;
    private final DoctorOffice surgeonOffice;
    private final DoctorOffice therapistOffice;

    @Bean
    Map<SymptomEnum, DoctorOffice> symptomToDoctorOfficeMap() {
        Map<SymptomEnum, DoctorOffice> symptomToDoctorOfficeMap = new HashMap<>();
        symptomToDoctorOfficeMap.put(SymptomEnum.EYE_PAIN, optometristOffice);
        symptomToDoctorOfficeMap.put(SymptomEnum.INABILITY_TO_SEE, optometristOffice);
        symptomToDoctorOfficeMap.put(SymptomEnum.LEG_PAIN, surgeonOffice);
        symptomToDoctorOfficeMap.put(SymptomEnum.BIG_PIMPLE, surgeonOffice);
        symptomToDoctorOfficeMap.put(SymptomEnum.TEMPERATURE, therapistOffice);
        symptomToDoctorOfficeMap.put(SymptomEnum.HIGH_TEMPERATURE, therapistOffice);
        return symptomToDoctorOfficeMap;
    }

}
