package si.fri.rsobook.rest.health;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import si.fri.rsobook.rest.config.PetApiConfigProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Health
@ApplicationScoped
public class ConfigHealthCheck implements HealthCheck {

    @Inject
    private PetApiConfigProperties petApiConfigProperties;

    @Override
    public HealthCheckResponse call() {

        if(petApiConfigProperties.getListsPets() == null){
            return HealthCheckResponse.named(ConfigHealthCheck.class.getSimpleName()).down().build();
        }

        return HealthCheckResponse.named(ConfigHealthCheck.class.getSimpleName()).up().build();
    }

}
