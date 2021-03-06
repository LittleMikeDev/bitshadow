package uk.co.littlemike.bitshadow.web.appinstances;

import org.junit.Before;
import org.junit.Test;
import uk.co.littlemike.bitshadow.appinstances.TestAppInstance;
import uk.co.littlemike.bitshadow.apps.TestApp;
import uk.co.littlemike.bitshadow.hosts.TestHost;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

public class RegisterAppInstanceRepresentationTest {
    private static final String APP_NAME = "App name";
    private static final String DESCRIPTION = "Description";
    private static final String HOSTNAME = "hostname";

    private RegisterAppInstanceRepresentation update;
    private TestApp app;
    private TestHost host;

    @Before
    public void setUp() throws Exception {
        update = new RegisterAppInstanceRepresentation();
        app = new TestApp();
        host = new TestHost();
    }

    @Test
    public void appliesUpdatesToApp() {
        update.setAppName(APP_NAME);
        update.setAppDescription(DESCRIPTION);

        update.getAppUpdate().applyTo(app);

        assertThat(update.getAppName()).isEqualTo(APP_NAME);
        assertThat(app.getDescription()).contains(DESCRIPTION);
    }

    @Test
    public void appliesUpdatesToHost() {
        update.setHostname(HOSTNAME);

        update.getHostUpdate().applyTo(host);

        assertThat(update.getHostname()).isEqualTo(HOSTNAME);
    }
}