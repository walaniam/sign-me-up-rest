package pl.walaniam.signmeup.contracts;

import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.walaniam.signmeup.generated.contracts.SignMeUp;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class SignMeUpContractClient {

    private final SignMeUp contract;

    public Map<String, ?> getInfo() {

        log.info("Getting info");

        List<FutureValue> futureValues = ImmutableList.of(
                FutureValue.of("name", contract.name().sendAsync()),
                FutureValue.of("symbol", contract.symbol().sendAsync()),
                FutureValue.of("owner", contract.owner().sendAsync()),
                FutureValue.of("entriesCount", contract.getEntriesCount().sendAsync()),
                FutureValue.of("entryPriceWei", contract.entryPriceWei().sendAsync())
        );

        CompletableFuture.allOf(
                futureValues.stream()
                .map(FutureValue::getValue)
                .toArray(size -> new CompletableFuture[size])
        ).join();

        log.info("Got info");

        return futureValues.stream().collect(Collectors.toMap(
                it -> it.getName(),
                it -> it.getValue().join()
        ));
    }

}
