package ru.geekbrains.summer.market.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.summer.market.services.StatisticService;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/statistic")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StatisticController {

    final StatisticService statisticService;

    @Value("${statistic.timeUnit}")
    String statisticTimeUnit;

    @GetMapping
    public Map<String, String> getStatistic() {
        return statisticService.getServiceStatistic().entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue() + " " + statisticTimeUnit));
    }

}
