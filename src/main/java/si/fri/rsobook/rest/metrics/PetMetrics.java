package si.fri.rsobook.rest.metrics;

import com.codahale.metrics.Counter;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PetMetrics {

    /*@Inject
    @Metric(name = "users_returned")*/
    private Counter petsReturned;


    public void addPetsReturned(int count){
        //usersReturned.inc(count);
    }

    public boolean isHealthy(){
        return true;
    }

}
