import io.tarantool.client.TarantoolClient;
import io.tarantool.driver.api.TarantoolClientFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DataServiceApp {
    public static void main(String... args) {
        TarantoolClient client = (TarantoolClient) TarantoolClientFactory.createClient()
                .withAddress("localhost", 3301)
                .withCredentials("guest", "")
                .build();
        SpringApplication.run(DataServiceApp.class, args);
    }
}
