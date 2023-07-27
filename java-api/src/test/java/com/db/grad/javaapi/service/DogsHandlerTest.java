package com.db.grad.javaapi.service;

import com.db.grad.javaapi.model.Dog;
import com.db.grad.javaapi.repository.DogsRepository;
import com.db.grad.javaapi.repository.DogsRepositoryStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DogsHandlerTest {
    private DogsRepository itsDogRepo = new DogsRepositoryStub();

    @BeforeEach
    public void makeSureRepoIsEmpty() {

    }

    {
        itsDogRepo.deleteAll();
    }

    @Test
    public void add_a_dog_return_number_of_dogs_in_repo_is_one() {
        // arrange
        DogsHandler cut = new DogsHandler(itsDogRepo);
        Dog theDog = new Dog();
        theDog.setName("Bruno");
        cut.addDog(theDog);
        int expectedResult = 1;
        long actualResult = cut.getNoOfDogs();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void get_first_dog_by_Id() {
        // arrange
        Dog expectedDog = new Dog();
        expectedDog.setId(1);
        expectedDog.setName("Bruno");
        DogsRepositoryStub cut = new DogsRepositoryStub();
        Dog theDog = new Dog();
        theDog.setName("Bruno");
        cut.save(theDog);
        theDog = new Dog();
        theDog.setName("Penny");
        cut.save(theDog);

        // act
        Dog actualDog = cut.findById(1);

        // assert
        assertEquals(expectedDog.getName(), actualDog.getName());
        assertEquals(expectedDog.getId(), actualDog.getId());
    }

    @Test
    void get_dog_by_name() {
        // arrange
        Dog expectedDog = new Dog();
        expectedDog.setId(2);
        expectedDog.setName("Penny");
        DogsRepositoryStub cut = new DogsRepositoryStub();
        Dog theDog = new Dog();
        theDog.setName("Bruno");
        cut.save(theDog);
        theDog = new Dog();
        theDog.setName("Penny");
        cut.save(theDog);

        // act
        List<Dog> actualDog = cut.findByName(expectedDog);

        // assert
        assertEquals(1, actualDog.size());
        Dog retrievedDog = actualDog.get(0);
        assertEquals(expectedDog.getName(), retrievedDog.getName());
        assertEquals(expectedDog.getId(), retrievedDog.getId());
    }

    @Test
    public void add_several_dogs_return_number_of_dogs_match_number_added() {
        // arrange
        DogsHandler cut = new DogsHandler(itsDogRepo);
        Dog theDog = new Dog();
        theDog.setName("Bruno");
        cut.addDog(theDog);
        theDog = new Dog();
        theDog.setName("Frank");
        cut.addDog(theDog);
        theDog = new Dog();
        theDog.setName("Penny");
        cut.addDog(theDog);

        int expectedResult = 3;

        // act
        long actualResult = cut.getNoOfDogs();

        // assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void update_dog_that_exists_returns_dog_id() {
        // arrange
        DogsHandler cut = new DogsHandler(itsDogRepo);
        Dog theDog = new Dog();
        theDog.setName("Bruno");
        cut.addDog(theDog);
        theDog = new Dog();
        theDog.setName("Frank");
        long expectedResult = cut.addDog(theDog);
        Dog dogToUpdate = theDog;
        String dogToFind = "Frank";
        theDog = new Dog();
        theDog.setName("Penny");
        cut.addDog(theDog);

        // act
        dogToUpdate.setName("Charlie");
        long actualResult = cut.updateDogDetails(dogToUpdate);

        // assert
        assertEquals(expectedResult, actualResult);
    }

    @Test    public  void    add_dog_and_remove_dog_return_number_of_dogs_is_zero() {
        // arrange
        DogsHandler cut = new DogsHandler(itsDogRepo);
        Dog theDog = new Dog();
        theDog.setName("Bruno");
        long uniqueId = cut.addDog(theDog);

        long expectedResult = 0;
        boolean expectedStatus = true;


        // act
        boolean actualStatus = cut.removeDog(uniqueId);
        long actualResult = cut.getNoOfDogs();

        // assert
        assertEquals(expectedStatus, actualStatus);
        assertEquals(expectedResult, actualResult);
    }

    // This test covers the other logic path in cut.removeDog()
    @Test
    public  void    add_dog_and_remove_dog_that_doess_not_exist_return_number_of_dogs_is_one()
    {
        // arrange
        DogsHandler cut = new DogsHandler( itsDogRepo );
        Dog theDog = new Dog();
        theDog.setName("Bruno");
        long uniqueId = cut.addDog( theDog );

        long expectedResult = 1;

    }

}