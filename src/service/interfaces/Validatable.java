package interfaces;

import exception.InvalidInputException;

public interface Validatable<T> {

    void validate(T t);

    static void notBlank(String value, String message) {
        if (value == null || value.isBlank()) {
            throw new InvalidInputException(message);
        }
    }
}
