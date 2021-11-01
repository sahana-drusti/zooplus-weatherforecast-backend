# zooplus-weatherforecast-backend

Used JAVA 17

Steps to use code:
1. clone the repository (git clone https://github.com/sahana-drusti/zooplus-weatherforecast-backend)
2. import the project as 'Existing Maven Project', providing the cloned directory in an IDE.
3. run as 'Spring Boot App' in the IDE.
4. can check the end point (get) http://localhost:8080/api/v1/weather/{city} eg: http://localhost:8080/api/v1/weather/Munich

Assumptions:
1. using onecall to fetch the weather forecast, as it require longitude and latitude, have called one more api 'direct' which gives longitude and latitude, for the given city.
2. But I have used abstract caching, for caching the 'direct' response.
3. There is an API which gives response directly for a given city, but it is not available in open tire.
4. I have used wiremock to mock the response in JUnit.
