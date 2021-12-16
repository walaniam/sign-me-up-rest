package pl.walaniam.signmeup.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.ReadonlyTransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

@Slf4j
@RequiredArgsConstructor
@Getter
public class Web3jConfig {

    private final Web3j web3j;
    private final ContractGasProvider gasProvider = new DefaultGasProvider();

    public Web3jConfig(String nodeUrl) {
        this.web3j = Web3j.build(httpServiceOf(nodeUrl));
    }

    protected HttpService httpServiceOf(String nodeUrl) {
        log.info("Creating http service with target url: {}", nodeUrl);
        return new HttpService(nodeUrl);
    }

    public ReadonlyTransactionManager readonlyTransactionManagerOf(String fromAddress) {
        log.info("Creating readonly transaction manager with fromAddress={}", fromAddress);
        return new ReadonlyTransactionManager(web3j, fromAddress);
    }
}
