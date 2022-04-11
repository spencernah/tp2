---
layout: page
title: Testing guide
---

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Running tests

There are two ways to run tests.

* **Method 1: Using IntelliJ JUnit test runner**
  * To run all tests, right-click on the `src/test/java` folder and choose `Run 'All Tests'`
  * To run a subset of tests, you can right-click on a test package,
    test class, or a test and choose `Run 'ABC'`
* **Method 2: Using Gradle**
  * Open a console and run the command `gradlew clean test` (Mac/Linux: `./gradlew clean test`)

<div markdown="span" class="alert alert-secondary">:link: **Link**: Read [this Gradle Tutorial from the se-edu/guides](https://se-education.org/guides/tutorials/gradle.html) to learn more about using Gradle.
</div>

--------------------------------------------------------------------------------------------------------------------

## Types of tests

This project has three types of tests:

1. *Unit tests* targeting the lowest level methods/classes.<br>
   e.g. `seedu.address.commons.StringUtilTest`
1. *Integration tests* that are checking the integration of multiple code units (those code units are assumed to be working).<br>
   e.g. `seedu.address.storage.StorageManagerTest`
1. Hybrids of unit and integration tests. These test are checking multiple code units as well as how the are connected together.<br>
   e.g. `seedu.address.logic.LogicManagerTest`

#Unit Tests
The Unit tests utilises files that end with "util" to set up the test data in order for junit to test.
As much as possible, within the unit tests, test data is not shared between individual tests. Only certain tests will use constants within the test class.
For the test data, the unit uses constants from the "util" classes. Most tests use data from `seedu.address.logic.commands.CommandTestUtil` and files from `seedu.address.testutil`.
Of note, would be the file `TypicalPersons`, since that would contain the default typical addressbook that will used for assertions and comparisons.

For the logic tests such as commands, if the command has a critical functionality, most likely the command class itself will have a overridden `equals()` function.
The `equals()` function would be used for testing the instance of that type of command.

To add more Test Data:
Further test data by use of constants in those "util" files/classes. Currently, if a new feature is implemented (such as Groups), the test data should be updated to integrate some of its functionality.
(An example would be the default typical addressbook having default groups for the persons in it).
