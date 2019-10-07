package com.kzhukov.rps.hash;

import com.kzhukov.rps.game.Move;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Component
public class MoveHashGenerator {
    private static final MessageDigest md;

    static {
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    public MoveHash computeHash(Move move) {
        //This uuid is used to ensure that the user doesn't know our move before he makes his own.
        UUID uuid = UUID.randomUUID();
        String secret = String.format("%s%s", move, uuid);
        return MoveHash.of(secret, computeHash(secret));
    }

    private String computeHash(String string) {
        md.update(string.getBytes());
        byte[] digest = md.digest();
        return DatatypeConverter.printHexBinary(digest).toUpperCase();
    }
}
