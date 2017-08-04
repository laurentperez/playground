package foo.bar;

import lombok.val;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

        val x = "";
        StringWriter sw = new StringWriter();

        String tenStrings = IntStream.rangeClosed(1, 10).mapToObj(i -> i == 1 ? "de " + i : " à " + i).collect(Collectors.joining());
        System.out.println("collect = " + tenStrings);
        Stream.of("John","Bob","Eric")
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
