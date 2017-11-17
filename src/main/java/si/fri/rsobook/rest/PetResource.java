package si.fri.rsobook.rest;

import si.fri.rsobook.core.database.dto.AuthEntity;
import si.fri.rsobook.core.database.impl.DatabaseImpl;
import si.fri.rsobook.core.model.Pet;
import si.fri.rsobook.core.restComponenets.resource.CrudResource;
import si.fri.rsobook.rest.config.PetApiConfigProperties;
import si.fri.rsobook.rest.service.DatabaseService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import java.util.UUID;

@RequestScoped
@Path("Pet")
public class PetResource extends CrudResource<UUID, Pet> {

    @Inject
    private PetApiConfigProperties petApiConfigProperties;

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
