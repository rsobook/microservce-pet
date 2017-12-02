package si.fri.rsobook.rest;

import com.kumuluz.ee.logs.cdi.Log;
import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.annotation.Metric;
import si.fri.rsobook.config.PetApiConfigProperties;
import si.fri.rsobook.core.database.dto.AuthEntity;
import si.fri.rsobook.core.database.exceptions.BusinessLogicTransactionException;
import si.fri.rsobook.core.database.impl.DatabaseImpl;
import si.fri.rsobook.core.model.Pet;
import si.fri.rsobook.core.restComponenets.resource.CrudResource;
import si.fri.rsobook.service.DatabaseService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.UUID;

@RequestScoped
@Path("Pet")
public class PetResource extends CrudResource<UUID, Pet> {

    @Inject
    private PetApiConfigProperties petApiConfigProperties;

    @Inject
    @Metric(name = "pets_created")
    private Counter petsCreatedCounter;

    @Inject
    private DatabaseService databaseService;

    public PetResource() {
        super(Pet.class);
    }

    @PostConstruct
    private void postInit() {
        if(petApiConfigProperties.getListsPets() != null){
            defaultMaxLimit = petApiConfigProperties.getListsPets();
        }
    }

    @Log
    @Override
    public Response create(Boolean xContent, Pet entity) throws BusinessLogicTransactionException {
        petsCreatedCounter.inc();
        return super.create(xContent, entity);
    }

    @Override
    protected UUID parseId(String s) {
        return UUID.fromString(s);
    }

    @Override
    protected AuthEntity getAuthorizedEntity() {
        return null;
    }

    @Override
    protected DatabaseImpl getDatabaseService() {
        return databaseService;
    }
}
