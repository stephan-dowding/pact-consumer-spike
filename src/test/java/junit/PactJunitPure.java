package junit;

import au.com.dius.pact.consumer.*;
import au.com.dius.pact.model.MockProviderConfig;
import au.com.dius.pact.model.PactFragment;
import org.junit.Test;
import pact.spike.Consumer;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by stephandowding on 9/3/17.
 */
public class PactJunitPure {

    @Test
    public void testPact() {
        PactFragment pactFragment = ConsumerPactBuilder
                .consumer("Some Consumer")
                .hasPactWith("Some Provider")
                .uponReceiving("a request to say Hello")
                .path("/hello")
                .method("POST")
                .body("{\"name\": \"harry\"}")
                .willRespondWith()
                .status(200)
                .body("{\"hello\": \"harry, how are you?\"}")
                .toFragment();

        MockProviderConfig config = MockProviderConfig.createDefault();
        VerificationResult result = pactFragment.runConsumer(config, new TestRun() {
            @Override
            public void run(MockProviderConfig config) {
                try {
                    assertThat(new Consumer(config.url()).hello("{\"name\": \"harry\"}"),
                            equalTo("{\"hello\": \"harry, how are you?\"}"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        if (result instanceof PactError) {
            throw new RuntimeException(((PactError)result).error());
        }

        assertThat(result, is(ConsumerPactTest.PACT_VERIFIED));
    }

}
