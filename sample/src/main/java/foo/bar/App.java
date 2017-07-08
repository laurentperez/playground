package foo.bar;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {

        System.out.println(Person.builder().lastname("toto").email("toto@toto.com").occupation("eat").occupation("sleep").build());


        Predicate<String> startsWithJ = (n) -> n.startsWith("J");
        Predicate<String> fourLetterLong = (n) -> n.length() == 4;

        Arrays.asList("John","Bob","Eric").stream()
                .filter(startsWithJ.and(fourLetterLong).negate())
                .map(String::toUpperCase)
                .map(func(String::toLowerCase))
                .forEach(System.out::println);
    }

    static<T,R> Function<T,R> func(Function<T,R> f) {
        return f;
    }

    @FunctionalInterface
    public interface A {
        default void printSomething() {
            System.out.println("something");
        }
        void print(String s);
    }

    public class Implementation implements A {
        @Override
        public void print(String s) {

        }
    }

}
