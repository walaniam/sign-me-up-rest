package pl.walaniam.signmeup.config;

import io.javalin.Javalin;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.walaniam.signmeup.contracts.SignMeUpContractClient;

@RequiredArgsConstructor
@Slf4j
public class RouteConfigurer {

    private final SignMeUpContractClient contractClient;

    public void configure(Javalin app) {
        app.get("/", ctx -> ctx.result("Hello World"));
        app.get("/signmeup/v1/info", ctx -> ctx.json(contractClient.getInfo()));
    }
}
