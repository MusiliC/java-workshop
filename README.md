# Spring Modulith Workshop

This is a minimal Spring Boot application structured as a Modulith, following the official [Spring Modulith documentation](https://docs.spring.io/spring-modulith/docs/current/reference/html/).

## Features
- **Modular Structure:** Inventory and Orders modules, each with its own controller, service, and domain classes.
- **Spring Modulith:** Clear module boundaries, event-based communication, and module documentation.
- **API Documentation:** Generated from integration tests using Spring REST Docs and AsciiDoc.
- **Static HTML Docs:** API documentation is available as a static HTML file served by Spring Boot.

## Project Structure
```
src/
  main/
    java/
      com/cee/workshop/
        ShopApplication.java
        inventory/
        orders/
    resources/
      static/
        docs/
          api.html
  test/
    java/
      com/cee/workshop/
        inventory/
        orders/
```

## How It Works
- **Spring Modulith** helps you organize your code into logical modules (Java packages).
- **API documentation** is generated from tests and assembled with AsciiDoc.
- **Maven plugins** copy the generated HTML documentation to `src/main/resources/static/docs/api.html`.
- **Spring Boot** serves the documentation at:  
  [http://localhost:8080/docs/api.html](http://localhost:8080/docs/api.html)

## How to Build and View Documentation

1. **Run tests and generate docs:**
   ```bash
   ./mvnw clean verify
   ```
2. **Start the application:**
   ```bash
   ./mvnw spring-boot:run
   ```
3. **View API documentation:**
   [http://localhost:8080/docs/api.html](http://localhost:8080/docs/api.html)

## Customization
- Edit `src/docs/asciidoc/api.adoc` to change the API documentation content.
- Add more tests to generate additional REST Docs snippets.

---

For more, see the [Spring Modulith Reference Guide](https://docs.spring.io/spring-modulith/docs/current/reference/html/).
