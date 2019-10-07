package com.kzhukov.rps.hash;

import lombok.Data;

@Data(staticConstructor = "of")
public class MoveHash {
    private final String secret;
    private final String hash;
}
