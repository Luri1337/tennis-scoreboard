package util.validation;

import dto.validationDto.MatchScoreUpdateContext;
import exception.InvalidIdFormat;
import exception.MissingRequiredParameterException;
import util.AppMassages;

public class MatchScoreValidator {

    public void validatePostMethod(MatchScoreUpdateContext validationContext) {
        String winnerId = validationContext.winnerId();

        if (winnerId == null || winnerId.isBlank()) {
            throw new MissingRequiredParameterException(AppMassages.ID_REQUIRED);
        }
        if(!winnerId.matches("^[1-9]\\d*$")) {
            throw new InvalidIdFormat(AppMassages.INVALID_ID);
        }
    }
}
