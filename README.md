# Contacts Microservice

![Java](https://img.shields.io/badge/Java-17-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.2-brightgreen.svg)
![GraphQL](https://img.shields.io/badge/GraphQL-v16.7.1-green.svg)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15.3-blue.svg)

Welcome to the Contacts Microservice repository! This microservice is built using Spring Boot Java and GraphQL, allowing you to efficiently manage contacts and their associated phone numbers. The microservice is designed to connect to a PostgreSQL database for secure and reliable data storage.

## Features

- **GraphQL API**: The microservice provides a powerful GraphQL API that allows flexible and intuitive querying of contact and phone number data.
- **CRUD Operations**: Easily perform Create, Read, Update, and Delete operations on contacts and phone numbers.
- **PostgreSQL Integration**: The microservice seamlessly connects to a PostgreSQL database to ensure data persistence and integrity.
- **Scalable and Modular**: Built on Spring Boot, the microservice architecture is scalable and easy to maintain.
- **Unit Testing**: The codebase includes comprehensive unit tests to ensure reliable functionality.

## Getting Started

To get started with the Contacts and Phone Numbers Microservice, follow these steps:

1. **Prerequisites**: Ensure you have Java 17 and Maven installed on your machine.
2. **Database Setup**: Set up a PostgreSQL database and configure the connection properties in the `application.properties` file.
3. **Build**: Use Maven to build the project by running `mvn clean package`.
4. **Run**: Execute the JAR file created in the target folder with `java -jar target/contacts-and-phone-numbers-microservice.jar`.

## API Documentation

The GraphQL API documentation can be accessed by starting the microservice and visiting `http://localhost:8070/graphql-ui` in your web browser.

## Contributing

We welcome contributions to improve and enhance the Contacts and Phone Numbers Microservice. If you find any issues or have new features to propose, please open an issue or submit a pull request.

## License

This microservice is open-source and distributed under the [MIT License](LICENSE). Feel free to use it, modify it, and share it with others.

---

Thank you for choosing the Contacts Microservice! We hope you find it valuable for managing your contact and phone number data. If you have any questions or need assistance, please feel free to reach out.

Happy coding! 🚀
