package ru.geekbrains.summer.market.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.geekbrains.summer.market.aspects.ServiceProfilingAspect;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StatisticService {

    final ServiceProfilingAspect serviceProfilingAspect;

    public Map<String, Long> getServiceStatistic() {
        return serviceProfilingAspect.getServiceProfilingMap().entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().getTotalTimeNanos()));
    }

}
