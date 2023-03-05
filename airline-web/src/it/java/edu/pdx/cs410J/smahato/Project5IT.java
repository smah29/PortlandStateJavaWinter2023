package edu.pdx.cs410J.smahato;

import edu.pdx.cs410J.InvokeMainTestCase;
import edu.pdx.cs410J.UncaughtExceptionInMain;
import edu.pdx.cs410J.web.HttpRequestHelper.RestException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.net.HttpURLConnection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.MethodOrderer.MethodName;

/**
 * An integration test for {@link ProjectUnused} that invokes its main method with
 * various arguments
 */
@TestMethodOrder(MethodName.class)
class Project5IT extends InvokeMainTestCase {
    private static final String HOSTNAME = "localhost";
    private static final String PORT = System.getProperty("http.port", "8080");

    @Test
    @Disabled
    void test0RemoveAllMappings() throws IOException {
      AirlineRestClient client = new AirlineRestClient(HOSTNAME, Integer.parseInt(PORT));
      client.removeAllDictionaryEntries();
    }

    @Test
    @Disabled
    void test1NoCommandLineArguments() {
        MainMethodResult result = invokeMain( ProjectUnused.class );
        assertThat(result.getTextWrittenToStandardError(), containsString(ProjectUnused.MISSING_ARGS));
    }

    @Test
    @Disabled
    void test2EmptyServer() {
        MainMethodResult result = invokeMain( ProjectUnused.class, HOSTNAME, PORT );

        assertThat(result.getTextWrittenToStandardError(), equalTo(""));

        String out = result.getTextWrittenToStandardOut();
        assertThat(out, out, containsString(PrettyPrinter.formatWordCount(0)));
    }

    @Test
    @Disabled
    void test3NoDefinitionsThrowsAppointmentBookRestException() {
        String word = "WORD";
        try {
            invokeMain(ProjectUnused.class, HOSTNAME, PORT, word);
            fail("Should have thrown a RestException");

        } catch (UncaughtExceptionInMain ex) {
            RestException cause = (RestException) ex.getCause();
            assertThat(cause.getHttpStatusCode(), equalTo(HttpURLConnection.HTTP_NOT_FOUND));
        }
    }

    @Test
    @Disabled
    void test4AddDefinition() {
        String word = "WORD";
        String definition = "DEFINITION";

        MainMethodResult result = invokeMain( ProjectUnused.class, HOSTNAME, PORT, word, definition );

        assertThat(result.getTextWrittenToStandardError(), equalTo(""));

        String out = result.getTextWrittenToStandardOut();
        assertThat(out, out, containsString(Messages.definedWordAs(word, definition)));

        result = invokeMain( ProjectUnused.class, HOSTNAME, PORT, word );

        assertThat(result.getTextWrittenToStandardError(), equalTo(""));

        out = result.getTextWrittenToStandardOut();
        assertThat(out, out, containsString(PrettyPrinter.formatDictionaryEntry(word, definition)));

        result = invokeMain( ProjectUnused.class, HOSTNAME, PORT );

        assertThat(result.getTextWrittenToStandardError(), equalTo(""));

        out = result.getTextWrittenToStandardOut();
        assertThat(out, out, containsString(PrettyPrinter.formatDictionaryEntry(word, definition)));
    }
}