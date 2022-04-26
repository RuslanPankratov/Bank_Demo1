package bank.demo.dto.core.validation;

import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public class CoreError {
        private String message;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoreError coreError = (CoreError) o;
        return Objects.equals(message, coreError.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return "CoreError{" +
                "message='" + message + '\'' +
                '}';
    }
}
