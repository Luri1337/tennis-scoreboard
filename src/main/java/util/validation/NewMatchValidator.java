package util.validation;

import dto.validationDto.MatchPlayersContext;
import exception.InvalidNameFormat;
import exception.MissingRequiredParameterException;
import exception.SelfMatchNotAllowedException;
import util.AppMassages;

public class NewMatchValidator {
    public void validate(MatchPlayersContext validationContext) {
        String player1 = validationContext.player1();
        String player2 = validationContext.player2();

        if (player1 == null || player2 == null || player1.isBlank() || player2.isBlank()) {
            throw new MissingRequiredParameterException(AppMassages.NAME_REQUIRED);
        }
        if (!player1.matches("^[a-zA-Zа-яА-ЯёЁ\\\\s]+$")
                || !player2.matches("^[a-zA-Zа-яА-ЯёЁ\\\\s]+$")) {
            throw new InvalidNameFormat(AppMassages.INVALID_NAME);
        }
        if (player1.equals(player2)) {
            throw new SelfMatchNotAllowedException(AppMassages.SELF_MATCH);
        }
    }
}
