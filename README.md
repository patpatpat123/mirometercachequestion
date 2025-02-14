Minimal reproducible ( I hope it is minimal enough)

Details

- it runs on JDK23
- SpringBoot 3.4.2

This is a springboot project downloaded from initializer.
The dependencies are just those (to keep minimal)
- spring-boot-starter-web
- spring-boot-starter-actuator
- spring-boot-starter-cache
- and micrometer


The structure is a maven multi-module project

- module 1 = fakeExpensiveCustomerCall
This is a fake service which does nothing but expose one REST endpoint.
The business logic here is a dummy.
There is a thread.sleep here to simulate an expensive logic.

- Same for module 2.
I added two modules because a) The issue is for multiple caches
b) I wanted to save you from having to install a DB

- module 3 is where the minimal code is (again, hope it is minimal enough)
It is a modulith like project, which does nothing but:

There is a controler which takes a request input, which is just two IDs.
It will delegate the business logic to a service layer.

The service layer does nothing but delegate to two business logic services for the actual work.

It is invoking the service one after another (to keep it simple) and aggregating the two results at the end.

module A and module B are very similar (the duplication is needed here to show two caches)

It has a business service, which will invoke the spring HttpExchangeInterface (See Olga's talk)
This is the "thing to cache"

You can see the cacheable annotation there.
This is where the issue resides.


Question 1: Before going further, are you able to download and compile and maybe even run the project?
mvn clean install should be enough.
When all the services are running, I added a file [1noncachecall.http](question/src/main/resources/1noncachecall.http) to trigger the test.
Before anything else, is it working?

Question 2: Could you please look at [CaffeineCacheConfiguration.java](question/src/main/java/com/example/micrometercache/customer/CaffeineCacheConfiguration.java)
I understand you believe the entire class is not needed.
I take criticism very well, please feel free to let me know about what you think of this code.
I was not able to get the cache working with the example properties you sent.


I will later have questions regarding duplicate metrics, the error messages, but what do you think of the current code?
