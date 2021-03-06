package uk.co.littlemike.bitshadow.web;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.dropwizard.Application;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import ru.vyarus.dropwizard.guice.GuiceBundle;
import uk.co.littlemike.bitshadow.web.config.BitShadowConfiguration;
import uk.co.littlemike.bitshadow.web.wiring.BitShadowWebModule;

public class BitShadowWebService extends Application<BitShadowConfiguration> {

    public static void main(String[] args) throws Exception {
        new BitShadowWebService().run("server");
    }

    @Override
    public void initialize(Bootstrap<BitShadowConfiguration> bootstrap) {
        bootstrap.addBundle(GuiceBundle.builder()
                .modules(new BitShadowWebModule())
                .build()
        );
        bootstrap.addBundle(new SwaggerBundle<BitShadowConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(BitShadowConfiguration configuration) {
                return configuration.swaggerBundleConfiguration;
            }
        });

        bootstrap.getObjectMapper()
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE)
                .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                .findAndRegisterModules();
    }

    @Override
    public void run(BitShadowConfiguration configuration, Environment environment) throws Exception {
        JerseyEnvironment jersey = environment.jersey();
        jersey.packages("uk.co.littlemike.bitshadow.web");
    }
}
