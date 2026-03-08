package com.technogise.event_management_system;

import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // This annotation tells Spring Boot not to replace the configured DataSource with an in-memory database during tests.
class EventManagementSystemApplicationTests {

  @Test
  void contextLoads() {}
}
