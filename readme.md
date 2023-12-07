## API Documentation
https://documenter.getpostman.com/view/29427105/2s9YeK39SX

## TODO
- [ ] Fix bidirectional getting of objects, when getting orders, customers does not "follow along", same with addresses. I think these are the only two bidirectional relationships we have.
- [ ] Should tests be dependent on data from the commandlinerunner in the application?
- [ ] More E2E tests, have same "tests" for all entity types
- [ ] Better cleanup? So that the spring context doesn't have to be loaded multiple times in tests

## Functionality
- [X] Get one by ID for all entity types
- [X] Get all with pagination for all entity types
- [X] Create new for all entity types
- [X] Delete one for all entity types
- [X] Update one for all entity types


## Addititonal information 
- We have used faker to add some data to the database on application startup. Some of our tests assume there is data in the database, these tests will fail if you remove the commandlinerunner in the entrypoint of the application. 

## Additional functionality
- [X] Can add existing address to a customer (see API documentation)
- [X] Can add existing parts to a subassembly (see API documentation)
- [X] Can add exisiting subassembly to machine (see API documentation)
- [X] Can create a order, then add this order to an existing customer (see API documentation)
- [X] Can add existing machines to an order (see API documentation)
- [X] Not possible to update non-existing entities, returns message in response and 404 status code
- [X] Throwing and handling exceptions if trying to get a non-exisiting entity by id, returns message in resposne and 404 status code
- [X] Trying to delete non-existing throws error in service, the exception message is returned in the response with code 404 status code
- [X] Extended Faker for custom faking of partname, partdescription, subassembly name, subassembly description, machine name and machine description.
