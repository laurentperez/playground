package foo.bar;

import lombok.*;

import java.util.List;

/**
 * Created by laurent on 05/07/2017.
 */
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@Builder
@ToString
public class Person {
    @Builder.Default private final String firstname = "nom par défaut";
    private final String lastname;
    private final String email;
    private @Singular
    List<String> occupations;
}
