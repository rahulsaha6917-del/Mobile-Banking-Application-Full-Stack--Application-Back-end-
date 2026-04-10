@SpringBootApplication
public class OnlineBankingBackendApplication {

    public static void main(String[] args) {

        // 🔥 Add this line
        System.out.println("DB URL: " + System.getenv("SPRING_DATASOURCE_URL"));

        SpringApplication.run(OnlineBankingBackendApplication.class, args);
    }
}
