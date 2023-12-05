## API Documentation
https://documenter.getpostman.com/view/29427105/2s9YeK39SX

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
- [X] Not possible to update non-existing entities
- [X] Throwing and handling exceptions if trying to get a non-exisiting entity by id
- [X] Extended Faker for custom faking of partname and partdescription.
