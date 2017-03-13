package junit;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRule;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.PactFragment;
import org.junit.Rule;
import org.junit.Test;
import pact.spike.Consumer;

import java.io.IOException;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by stephandowding on 9/3/17.
 */
public class PactTest {
    @Rule
    public PactProviderRule mockProvider = new PactProviderRule("Some Provider", "localhost", 8080, this);

    @Pact(provider="Some Provider", consumer="Some Consumer")
    public PactFragment createFragment(PactDslWithProvider builder) {
        return builder
                .given("test state")
                .uponReceiving("ExampleJavaConsumerPactRuleTest test interaction")
                .path("/")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body("Blah")
                .toFragment();
    }

    @Test
    @PactVerification("Some Provider")
    public void runTest() throws IOException {
        assertThat(new Consumer("http://localhost:8080").run("/"), equalTo("Blah"));
    }

}
