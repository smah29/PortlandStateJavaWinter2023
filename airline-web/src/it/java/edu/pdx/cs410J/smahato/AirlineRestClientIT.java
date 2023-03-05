package edu.pdx.cs410J.smahato;

import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.web.HttpRequestHelper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer.MethodName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Integration test that tests the REST calls made by {@link AirlineRestClient}
 */
@TestMethodOrder(MethodName.class)
class AirlineRestClientIT {
  private static final String HOSTNAME = "localhost";
  private static final String PORT = System.getProperty("http.port", "8080");

  private AirlineRestClient newAirlineRestClient() {
    int port = Integer.parseInt(PORT);
    return new AirlineRestClient(HOSTNAME, port);
  }

  @Test
  @Disabled
  void test0RemoveAllDictionaryEntries() throws IOException {
    AirlineRestClient client = newAirlineRestClient();
    client.removeAllDictionaryEntries();
  }

//  @Test
//  @Disabled
//  void test1EmptyServerContainsNoDictionaryEntries() throws IOException, ParserException {
//    AirlineRestClient client = newAirlineRestClient();
//    Map<String, String> dictionary = client.getAllDictionaryEntries();
//    assertThat(dictionary.size(), equalTo(0));
//  }
//
//  @Test
//  @Disabled
//  void test2DefineOneWord() throws IOException, ParserException {
//    AirlineRestClient client = newAirlineRestClient();
//    String testWord = "TEST WORD";
//    String testDefinition = "TEST DEFINITION";
//    client.addDictionaryEntry(testWord, testDefinition);
//
//    String definition = client.getDefinition(testWord);
//    assertThat(definition, equalTo(testDefinition));
//  }
//
//  @Test
//  @Disabled
//  void test4EmptyWordThrowsException() {
//    AirlineRestClient client = newAirlineRestClient();
//    String emptyString = "";
//
//    HttpRequestHelper.RestException ex =
//      assertThrows(HttpRequestHelper.RestException.class, () -> client.addDictionaryEntry(emptyString, emptyString));
//    assertThat(ex.getHttpStatusCode(), equalTo(HttpURLConnection.HTTP_PRECON_FAILED));
//    assertThat(ex.getMessage(), equalTo(Messages.missingRequiredParameter(AirlineServlet2.WORD_PARAMETER)));
//  }}
}