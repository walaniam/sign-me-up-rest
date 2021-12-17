package pl.walaniam.signmeup.contracts;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.CompletableFuture;

@AllArgsConstructor(staticName = "of")
@Getter
public class FutureValue<T> {
    private final String name;
    private final CompletableFuture<T> value;
}
