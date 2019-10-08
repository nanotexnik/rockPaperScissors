package com.kzhukov.rps.gamesession.statistics.controller;

import com.kzhukov.rps.gamesession.statistics.GameSessionStatistics;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class GameSessionStatisticsController {
    private final GameSessionStatisticsService service;

    @GetMapping("/statistics/{uuid}")
    public @ResponseBody
    GameSessionStatistics getGameSessionStatistics(@PathVariable("uuid") String gameSessionUuid) {
        return service.getGameSessionStatistics(gameSessionUuid);
    }
}
