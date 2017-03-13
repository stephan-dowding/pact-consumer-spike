//package pact;
//
//import au.com.dius.pact.consumer.ConsumerPactTest;
//import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
//import au.com.dius.pact.model.PactFragment;
//import pact.spike.Consumer;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.hamcrest.core.IsEqual.equalTo;
//import static org.junit.Assert.assertThat;
//
//public class ExampleJavaConsumerPactTest extends ConsumerPactTest {
//
//    @Override
//    protected PactFragment createFragment(PactDslWithProvider builder) {
//        Map<String, String> headers = new HashMap<String, String>();
//        headers.put("testreqheader", "testreqheadervalue");
//
//        return builder
//                .given("test state") // NOTE: Using provider states are optional, you can leave it out
//                .uponReceiving("ExampleJavaConsumerPactTest test interaction")
//                .path("/")
//                .method("GET")
//                .headers(headers)
//                .willRespondWith()
//                .status(200)
//                .headers(headers)
//                .body("{\"responsetest\": true, \"name\": \"harry\"}")
//                .given("test state 2") // NOTE: Using provider states are optional, you can leave it out
//                .uponReceiving("ExampleJavaConsumerPactTest second test interaction")
//                .method("OPTIONS")
//                .headers(headers)
//                .path("/second")
//                .body("")
//                .willRespondWith()
//                .status(200)
//                .headers(headers)
//                .body("")
//                .toFragment();
//    }
//
//
//    @Override
//    protected String providerName() {
//        return "test_provider";
//    }
//
//    @Override
//    protected String consumerName() {
//        return "test_consumer";
//    }
//
//    @Override
//    protected void runTest(String url) throws IOException {
////        Assert.assertEquals(new ConsumerClient(url).options("/second"), 200);
////        Map expectedResponse = new HashMap();
////        expectedResponse.put("responsetest", true);
////        expectedResponse.put("name", "harry");
////        assertEquals(new ConsumerClient(url).getAsMap("/", ""), expectedResponse);
////        assertEquals(new ConsumerClient(url).options("/second"), 200);
//        Consumer consumer = new Consumer(url);
//        String response = consumer.run("/");
//        assertThat(response, equalTo("hello"));
//    }
//}