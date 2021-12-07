package pl.walaniam.signmeup;

import io.javalin.Javalin;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class Main {

	public static void main(String[] args) {
		Javalin app = Javalin.create().start(getHerokuAssignedPort());
		app.get("/", ctx -> ctx.result("Hello World"));
	}

	private static int getHerokuAssignedPort() {
		int listeningPort = Optional.ofNullable(System.getenv("PORT"))
				.map(Integer::parseInt)
				.orElse(7000);
		log.info("Listening on port={}", listeningPort);
		return listeningPort;
	}
}
