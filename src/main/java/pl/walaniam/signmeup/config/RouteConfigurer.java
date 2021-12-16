package pl.walaniam.signmeup.config;

import com.google.common.collect.ImmutableMap;
import io.javalin.Javalin;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.web3j.protocol.core.RemoteFunctionCall;
import pl.walaniam.signmeup.generated.contracts.SignMeUp;

@RequiredArgsConstructor
@Slf4j
public class RouteConfigurer {

    private final SignMeUp signMeUpContract;

    public void configure(Javalin app) {

        app.get("/", ctx -> ctx.result("Hello World"));

        app.get("/signmeup/v1/info", ctx -> ctx.json(ImmutableMap.of(
                "name", valueCall(signMeUpContract.name()),
                "symbol", valueCall(signMeUpContract.symbol()),
                "owner", valueCall(signMeUpContract.owner()),
                "entriesCount", valueCall(signMeUpContract.getEntriesCount()),
                "entryPriceWei", valueCall(signMeUpContract.entryPriceWei())
        )));
    }

    private <T> T valueCall(RemoteFunctionCall<T> call) {
        try {
            return call.send();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
