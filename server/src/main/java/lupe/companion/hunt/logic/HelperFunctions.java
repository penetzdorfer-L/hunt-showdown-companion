package lupe.companion.hunt.logic;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AllArgsConstructor
public class HelperFunctions {
    private final Random random;

    public int getRandomInteger(int origin, int boundPlusOne) {
        return random.nextInt(origin, boundPlusOne);
    }

    public boolean isDualwield() {
        int flip = random.nextInt(0, 101);
        return flip >= 80;
    }
}
