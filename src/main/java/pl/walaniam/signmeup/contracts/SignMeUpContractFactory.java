package pl.walaniam.signmeup.contracts;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.walaniam.signmeup.config.Web3jConfig;
import pl.walaniam.signmeup.generated.contracts.SignMeUp;

@Slf4j
@RequiredArgsConstructor
public class SignMeUpContractFactory {

    private final Web3jConfig web3jConfig;
    private final String contractAddress;

    public SignMeUp newReadOnlyContract(String callerAddress) {
        return SignMeUp.load(
                contractAddress,
                web3jConfig.getWeb3j(),
                web3jConfig.readonlyTransactionManagerOf(callerAddress),
                web3jConfig.getGasProvider()
        );
    }
}
