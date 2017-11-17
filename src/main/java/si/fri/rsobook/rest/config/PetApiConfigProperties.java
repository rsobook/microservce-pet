package si.fri.rsobook.rest.config;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@ConfigBundle("properties")
public class PetApiConfigProperties {

    @ConfigValue(watch = true)
    private Integer listsPets;

    public Integer getListsPets() {
        return listsPets;
    }

    public void setListsPets(Integer listsPets) {
        this.listsPets = listsPets;
    }
}
