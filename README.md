# Assiment Project

For this assignment, the task was to develop an __EJB project__ with a Person entity and a Stateless Person Repository, that should had simple CRUD operation methods.

#

## My Working Proces

Before I even started coding, I created something that I called a "project map." The purpose of this map was to make it clearer for me and for others how did I understand the assignment.
After each new review that I received, I created a new map. In this file, I will show you only the final version after which I started working on the project.

![Map](/Images/Map.png)

#

## Entity and DB

This project has only one Entity, which is Person. All communication with the database is done through EntityManager. I used an H2 Database and
modified persistence.xml and glassfish-resources.xml (for creating a pool for the DB) to work with this DB.

| Person    |           |
| :-------- | :-------- | 
| `Long`    | `personId`| 
| `String`  | `name`    | 
| `Sex`     | `sex`     |  
| `Date`    | `birthday`| 

#

## WebService server

I have created a WebService with WebMethod that uses methods from Remote PersonService.

#### Methods:

##### createPerson
Return: void

| Parameter | Type      | Description                                            |
| :-------- | :-------- | :----------------------------------------------------- |
| `String`  | `name`    |  name of the Person                                    |
| `String`  | `sex`     |  sex of the Person in format: MALE, FEMALE, OTHER      |
| `String`  | `birthday`|  birthday of the Person in format:  DD.MM.YYYY         |

##### deletePerson
Return: void

| Parameter | Type      | Description                                            |
| :-------- | :-------- | :----------------------------------------------------- |
| `Long`    | `personid`|  id of the deleting Person                             |

##### getPersonForName
Return: Optional<Person>

| Parameter | Type      | Description                                            |
| :-------- | :-------- | :----------------------------------------------------- |
| `String`  | `name`    |  name of the Person you want to get                    |

##### getAllPerson
Return: List<Person>

| Parameter | Type      | Description                                            |
| :-------- | :-------- | :----------------------------------------------------- |
|   None    |   None    |  None                                                  |

##### updatePerson
Return: void

| Parameter | Type      | Description                                            |
| :-------- | :-------- | :----------------------------------------------------- |
| `Long`    | `personid`|  id of the existing Person                             |
| `String`  | `name`    |  updated name of the Person                            |

## Tests
Because the project is small, I made only 2 tests for the whole project. One is for PersonService, and the second is for testing PersonWebService.




