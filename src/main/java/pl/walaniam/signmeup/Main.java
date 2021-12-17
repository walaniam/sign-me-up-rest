package pl.walaniam.signmeup;

import io.javalin.Javalin;
import lombok.extern.slf4j.Slf4j;
import org.web3j.protocol.http.HttpService;
import pl.walaniam.signmeup.config.RouteConfigurer;
import pl.walaniam.signmeup.config.Web3jConfig;
import pl.walaniam.signmeup.contracts.SignMeUpContractClient;
import pl.walaniam.signmeup.contracts.SignMeUpContractFactory;
import pl.walaniam.signmeup.generated.contracts.SignMeUp;

import java.util.Optional;

@Slf4j
public class Main {

	public static void main(String[] args) {

		String callerAddress = getRequiredEnv("CALLER_ADDRESS");
		SignMeUp signMeUpContract = contractFactory().newReadOnlyContract(callerAddress);
		SignMeUpContractClient contractClient = new SignMeUpContractClient(signMeUpContract);

		RouteConfigurer routeConfigurer = new RouteConfigurer(contractClient);

		Javalin app = Javalin.create().start(getHerokuAssignedPort());
		routeConfigurer.configure(app);
	}

	private static SignMeUpContractFactory contractFactory() {
		String infuraUrl = Optional.ofNullable(System.getenv("INFURA_URL")).orElse(HttpService.DEFAULT_URL);
		String contractAddress = getRequiredEnv("CONTRACT_ADDRESS");
		return new SignMeUpContractFactory(
				new Web3jConfig(infuraUrl),
				contractAddress
		);
	}

	private static String getRequiredEnv(String name) {
		return Optional.ofNullable(System.getenv(name))
				.orElseThrow(() -> new RuntimeException(name + " not set"));
	}

	private static int getHerokuAssignedPort() {
		int listeningPort = Optional.ofNullable(System.getenv("PORT"))
				.map(Integer::parseInt)
				.orElse(7000);
		log.info("Listening on port={}", listeningPort);
		return listeningPort;
	}
}
