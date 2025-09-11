package util.validation;

import dto.validationDto.PageFilterContextDto;
import exception.InvalidFilterFormat;
import exception.InvalidPageFormat;
import exception.MissingRequiredParameterException;
import util.AppMassages;

public class MatchesValidator {

    public void validate(PageFilterContextDto validationContext) {
        String page = validationContext.currentPage();
        String filter = validationContext.filter();

        if (page == null || filter == null) {
            throw new MissingRequiredParameterException(AppMassages.PAGE_FILTER_REQUIRED);
        }
        if (!page.matches("^[1-9][0-9]*$") && !page.isBlank()) {
            throw new InvalidPageFormat(AppMassages.INVALID_PAGE);
        }
        if (!filter.matches("^[a-zA-Zа-яА-ЯёЁ\\\\s]+$") && !filter.isBlank()) {
            throw new InvalidFilterFormat(AppMassages.INVALID_FILTER);
        }
    }
}
