package ru.fomin;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ru.fomin.config.AppConfig;
import ru.fomin.hospital.api.Reception;
import ru.fomin.model.Patient;
import ru.fomin.model.enumeration.SymptomEnum;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Component
@RequiredArgsConstructor
@Log4j2
public class Main {

    private List<Patient> patientList;

    private final Reception reception;

    @PostConstruct
    public void init() {
        patientList = List.of(
                Patient.of("Dima", SymptomEnum.EYE_PAIN),
                Patient.of("Mike", SymptomEnum.EYE_PAIN),
                Patient.of("Tom", SymptomEnum.INABILITY_TO_SEE),
                Patient.of("Sasha", SymptomEnum.LEG_PAIN),
                Patient.of("Sara", SymptomEnum.BIG_PIMPLE),
                Patient.of("Gendalf", SymptomEnum.TEMPERATURE),
                Patient.of("Frodo", SymptomEnum.HIGH_TEMPERATURE)
        );
        System.out.println("patients have come to reception");
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Main main = context.getBean("main", Main.class);
        main.patientList.forEach(main.reception::sendForTreatment);
    }

    @PreDestroy
    public void destroy() {
        System.out.println("the end");
    }

}