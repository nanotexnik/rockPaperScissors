package com.kzhukov.rps.gamesession.controller;

import lombok.Data;

@Data(staticConstructor = "of")
public class CreateGameSessionResponse {
    private final String sessionUuid;
    private final String casinoMoveHashCode;
}
