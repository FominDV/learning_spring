package ru.geekbrains.summer.market.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.summer.market.services.StatisticService;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/statistic")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StatisticController {

    final StatisticService statisticService;

    @Value("${statistic.divisor}")
    BigDecimal divisor;

    @Value("${statistic.timeUnit}")
    String timeUnit;

    @GetMapping
    public Map<String, String> getStatistic() {
        return statisticService.getServiceStatistic().entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> getTimeValue(entry.getValue())));
    }

    private String getTimeValue(Long nano) {
        return String.format(
                "%.0f %s",
                BigDecimal.valueOf(nano).divide(divisor).setScale(0, RoundingMode.HALF_UP),
                timeUnit
        );
    }

}
