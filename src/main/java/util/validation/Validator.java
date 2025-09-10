package util.validation;

import dto.ValidationContextDto;
//TODO сделать валидацию для оеквест параметров в сервлетах
public interface Validator {
    void validate(ValidationContextDto validationContext);
}
