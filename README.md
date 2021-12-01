# Country Router REST Web Service
Repository demonstrating simple web service written in Spring that is able to calculate any possible land route from one country to another.

# Algorithm efficiency
The routing is done using Shortest Path Faster Algorithm, it is an optimization of Bellman Ford algorithm. I chose this algorithm over Dijkstra because it is more robust for future changes. We can easily model negative cost with suggested model and in doing so we can encourage using one country over other.

The worst-case running time of the algorithm is O(|V|*|E|), just like the standard Bellman-Ford algorithm. Experiments suggest that the average running time is O(|E|).
The preprocessing of countries is done in linear time for time complexity. Searching one country is done with O(1) time complexity.


# Running the application

The application can be run using terminal and IDE of your choice. You can run the application with bash terminal with this command
`./mvnw spring-boot:run`

The application has integration tests, without running the server. Tests are done for various components in application context in mocked environment.
The most interesting tests are in CountryRouteResourceTest, following the specificiation provided.

You can run tests with command

`./mvnw test`

