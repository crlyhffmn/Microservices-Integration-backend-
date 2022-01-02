# Microservices-Integration-backend-
Full microservices rig to see integration and backend access

# N.B.
Please see System Integration document to update team POM.xml and application.yml files.

# To Run
1. Download or clone the repository to a folder of your choice. I find that I get errors if the various folders are not in my IdeaProjects folder for IntelliJ but different systems undoubtedly vary.
2. Open each folder as a separate project in IntelliJ or Eclipse - use a different window for each.
3. Create 3 databases called minimint_authentication, minimint_posts and minimint_profiles. These correspond to the databases each team is using but provides a consistent naming protocol.
4. In the 3 services (authorization-server-main, posts-server-main and profile-service-main) open the respective application.properties files and modify the mysql username and password accordingly. While there, take a moment to inspect the application.yml file to see the designated port numbers and names for the various services.
5. Although not yet populated, the minimint_profiles database requires a single entry in a privacy table in order to work. So log into MySql and ...
  * USE minimint_profiles;
  * CREATE TABLE privacy (privacyid bigint PRIMARY KEY, bio BOOLEAN, dob BOOLEAN, email BOOLEAN, gender BOOLEAN, name BOOLEAN);
  * INSERT INTO privacy (privacyid, bio, dob, email, gender, name) VALUES (1, false, false, true, true, true);
6. Fire up the servers in the following order:
  * SERVICE-REGISTRY --> once done enter localhost:8761 on your browser (BTW Eureka insists that this is its port number - will not be changed).
  * CONFIG-SERVER --> this will pull the config-server file from the corresponding Revature-MiniMint config-server file repo.
  * AUTHORIZATION-SERVICE, POSTS-SERVICE and PROFILES-SERVICE --> in no particular order.
  * API-GATEWAY
7. Remember to refresh the browser to see each service as it's added.
8. To test, open Postman and the corresponding README.md files for each deveopment group's repo - this will walk you through the various access paths to test the services.
9. NOTE the API-GATEWAY is on port 20030, so all calls to all services should begin localhost:20030/<service predicate path>. Avoid the temptation to refer to a backend service via it's own port - this works, but misses the point. All front end calls will go via port 20030.
10. If you don't blow up your computer but do encounter problems, please let me know. I have run Postman tests for all of the various calls for each service and all work.
