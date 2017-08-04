package lsa.sample.rx;

import java.util.function.Supplier;

@FunctionalInterface
public interface ThrowingSupplier<U> extends Supplier<U> {

    @Override
    default U get() {
        try {
            return getThrows();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    U getThrows() throws Exception;
}
