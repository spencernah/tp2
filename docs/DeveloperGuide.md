---
layout: page
title: Developer Guide
---
* Table of Contents:
* *[Acknowledgements](DeveloperGuide.md#acknowledgements)*
* *[Setting up, getting started](DeveloperGuide.md#setting-up-getting-started)*
* *[Design](DeveloperGuide.md#design)*
* *[Implementation](DeveloperGuide.md#implementation)*
* *[Documentation, logging, testing, configuration, dev-ops](DeveloperGuide.md#documentation-logging-testing-configuration-dev-ops)*
* *[Appendix: Requirements](DeveloperGuide.md#appendix-requirements)*
* *[Appendix: Instructions for manual testing](DeveloperGuide.md#appendix-instructions-for-manual-testing)*

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* Thanks to Prof Damith C.Rajapakse and Boyd Anderson provide consultation for our team.
* Thanks to the project team AY2122S2-TIC4002-F18-1.

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2122S2-TIC4002-F18-1/tp2/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/AY2122S2-TIC4002-F18-1/tp2/blob/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2122S2-TIC4002-F18-1/tp2/blob/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.


**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2122S2-TIC4002-F18-1/tp2/blob/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2122S2-TIC4002-F18-1/tp2/blob/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `AddressBookParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2122S2-TIC4002-F18-1/tp2/blob/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>


### Storage component

**API** : [`Storage.java`](https://github.com/AY2122S2-TIC4002-F18-1/tp2/blob/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both address book data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</div>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/CommitActivityDiagram.png" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire address book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* has a need to manage a significant number of contacts
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

Name: Jason Tan <br>
Marriage Status: Married <br>
Age: 32 years old <br>
Occupation: Supplier <br>
Hobbies: Sports such as football, tennis and basketball. <br>
Traits:
Comfortable with technology. He uses a smartphone and has multiple apps. He has multiple contacts ranging from colleagues to external family members that he wishes to keep in contact with. He also keeps in contact with his clients, and prefers to categorise them.


**Value proposition**:

Morph: Better email management (multiple emails) Expansion of information fields of contacts Better categorisation of Contacts (via tags) <br>

Evolve: Better searchability (search based on certain criteria) Provide alternatives for saving the data file (csv) More options in clearing contacts (clear certain fields or contacts selectively)


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                 | I want to …​                                                                                | So that I…​                                                       |
| -------- | ------------------------------------------ |------------------------------------------------------------------------------------------------|----------------------------------------------------------------------|
| `* * *`  | new user                                   | see usage instructions                                                                         | can refer to instructions when I forget how to use the App           |
| `* * *`  | user                                       | add a new contact                                                                              | can keep record of the person contact                                |
| `* * *`  | user                                       | record the basic details of a person such as name, address, phone number and email             | do not remember these details by heart                               |
| `* * *`  | user                                       | delete an existing contact                                                                     | can remove contacts that I no longer need                            |
| `* *`    | user                                       | delete multiple existing contact                                                               | do not need to do it one-by-one                                      |
| `* * *`  | user                                       | edit an existing contact                                                                       | can update the contact when there are changes                        |
| `* *`    | user                                       | add a remark to an existing contact                                                            | can add notes related to that contact                                |
| `* *`    | user                                       | update a remark to an existing contact                                                         | can make changes to existing remarks of a contact                    |
| `* *`    | user                                       | delete a remark to an existing contact                                                         | can remove any existing remarks of a contact                         |
| `* *`    | user                                       | favourite an existing contact                                                                  | can differentiate the contact from others                            |
| `* * *`  | user                                       | create different contact groups                                                                | can group the contacts based different purposes and needs            |
| `* * *`  | user                                       | assign an existing contact to an existing group                                                | can group the contacts based different purposes and needs            |
| `* *`    | user                                       | rename an existing group                                                                       | can make update the group name when needed                           |
| `* * *`  | user                                       | delete a group                                                                                 | can remove the group that is no longer needed                        |
| `* * *`  | user                                       | view a list of all existing contacts                                                           | can review the contacts or refer to the contacts' details            |
| `* * *`  | user                                       | view a list of existing groups                                                                 | can review, update existing ones or delete redundant ones            |
| `* *`    | user                                       | view all contact(s) associated to a specific group                                             | know who is in that group                                            |
| `* *`    | user                                       | view all favourite contact(s)                                                                  | can quickly access my favourite contacts                             |
| `* * *`  | user                                       | find a contact by name                                                                         | can locate contact(s) without having to go through the entire list   |
| `* *`    | user                                       | find a contact by content of the remarks                                                       | can locate contact(s) with certain remarks                           |
| `* *`    | user                                       | find a contact by the group name                                                               | can locate contact(s) with similar group names                       |
| `* *`    | linux user                                 | close the program using keywords like quit                                                     | can easily terminate the app                                         |
| `*`      | user                                       | sort persons by name                                                                           | can organise my contacts                                             |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `Contacts Address Book` and the **Actor** is the `user`, unless specified otherwise)

**Use case: Adding a person**
1. User keys in person details
2. CAB records the contact
3. CAB shows the person added successfully
4. Data files updated automatically

**Use case: Delete a person**
1.  User requests to list persons
2.  CAB shows a list of persons
3.  User requests to delete a specific person in the list
4.  CAB deletes the person

**Use case: Remark a person**
1.  User requests to list persons
2.  CAB shows a list of persons
3.  User requests to remark a specific person in the list
4.  CAB remark the person

**Use case: Creating a group**
1. User keys in the name of group
2. CAB records group name
3. CAB shows the group added successfully
4. Data files updated automatically

**Use case: List all the group name**
1. User requests to list all the groups name
2. CAB shows a list of groups

**Use case: Assign a person to a group**
1. User keys in the name and group
2. CAB assign the person to the group
3. CAB shows the person assigned to the group successfully
4. Data files updated automatically

**Use case: List all person in a group**
1. User requests to list all the person in a group
2. CAB shows a list of person from the group

**Use case: Delete a group**
1.  User requests to list all the group
2.  CAB shows a list of group name
3.  User requests to delete a specific group in the list
4.  CAB deletes the group

**Use case: Rename a group**
1.  User requests to list all the group
2.  CAB shows a list of group name
3.  User requests to rename a specific group in the list
4.  CAB rename the group
**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. AddressBook shows an error message.

      Use case resumes at step 2.

*{More to be added}*

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
1. Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
1. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
1. The UI page must load within 2 seconds.
1. The app is safe to use and able to protect sensitive data.
1. The app must be able to transfer data from one computer to another easily.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Adding a contact

1. Adding a new contact (no duplicate)

    1. Adding a contact with all parameters <br>
       Test case: `add n/Spencer Tan p/90008000 e/spencer@gmail.com a/Tampines t/student`<br>
       Expected: New contact is added with all parameters reflected. Details of the new contact shown in the status message.
   
    2. Adding a contact with non-integer `PHONE NUMBER` <br>
       Test case: `add n/Spencer p/abc e/spencer@gmail.com a/Tampines t/student` <br>
       Expected: No contact is added. Error details shown in the status message.

    3. Adding a contact with invalid format for `EMAIL` <br>
       Test case: `add n/Spencer p/abc e/spencer a/Tampines t/student` <br>
       Expected: No contact is added. Error details shown in the status message.
   
    4. Adding a contact with no `NAME` <br>
       Test case: `add n/ p/90008000 e/spencer@gmail.com a/Tampines t/student` <br>
       Expected: No contact is added. Error details shown in the status message.

    5. Adding a contact with missing parameter, `NAME` <br>
       Test case: `add p/90008000 e/spencer@gmail.com a/Tampines t/student` <br>
       Expected: No contact is added. Error details shown in the status message.

    6. Adding a contact with no `PHONE NUMBER` <br>
       Test case: `add n/Spencer p/ e/spencer a/Tampines t/student` <br>
       Expected: No contact is added. Error details shown in the status message.
   
    7. Adding a contact with missing parameter, `PHONE NUMBER` <br>
       Test case: `add n/Spencer e/spencer a/Tampines t/student` <br>
       Expected: No contact is added. Error details shown in the status message.
   
    8. Adding a contact with no `EMAIL` <br>
       Test case: `add n/Spencer p/90008000 e/ a/Tampines t/student` <br>
       Expected: No contact is added. Error details shown in the status message.

    9. Adding a contact with missing parameter, `EMAIL` <br>
       Test case: `add n/Spencer p/90008000  a/Tampines t/student` <br>
       Expected: No contact is added. Error details shown in the status message.
   
    10. Adding a contact with no `ADDRESS` <br>
        Test case: `add n/Spencer p/90008000 e/spencer@gmail.com a/ t/student` <br>
        Expected: No contact is added. Error details shown in the status message.

    11. Adding a contact with missing parameter, `ADDRESS` <br>
        Test case: `add n/Spencer p/90008000 e/spencer@gmail.com t/student` <br>
        Expected: No contact is added. Error details shown in the status message.
   
    12. Adding a contact with no `TAG` <br>
        Test case: `add n/Spencer p/90008000 e/spencer@gmail.com a/Tampines t/` <br>
        Expected: No contact is added. Error details shown in the status message.

    13. Adding a contact with missing parameter, `TAG` <br>
        Test case: `add n/Spencer p/90008000 e/spencer@gmail.com a/Tampines` <br>
        Expected: New contact is added with all parameters reflected. Details of the new contact shown in the status message.

2. Adding a new contact (duplicated)

   Prerequisites: Contact with the same name has to be created prior

    1. Adding a contact with all parameters <br>
       Test case: `add n/Spencer p/90008000 e/spencer@gmail.com a/Tampines t/student`<br>
       Expected: No contact is added. Error details shown in the status message.


### Editing a contact

1. Editing an existing contact (no duplicate)

   Prerequisites: At least 1 contact needs to be created

    1. Editing a contact with all parameters declared <br>
       Test case: `edit 1 n/Spencer Wee p/90008000 e/spencer@gmail.com a/Tampines t/student`<br>
       Expected: Parameters of the first contact is updated based on input. Details of the updated contact shown in the status message.

    2. Editing a contact with no parameters declared <br>
       Test case: `edit 1`<br>
       Expected: No update is made. Error details shown in the status message.

    3. Editing a contact with one parameter declared <br>
       Test case: `edit 1 n/Spencer Goh`<br>
       Expected: Declared parameter of the first contact is updated based on input. Details of the updated contact shown in the status message.
   
    4. Editing a contact with invalid index <br>
       Test case: `edit 0 n/Spencer Soh p/90008000 e/spencer@gmail.com a/Tampines t/student`<br>
       Expected: No update is made. Error details shown in the status message.

    5. Editing a contact with invalid index where index > list size <br>
       Test case: `edit x n/Spencer Soh p/90008000 e/spencer@gmail.com a/Tampines t/student` (where x is larger than the list size) <br>
       Expected: No update is made. Error details shown in the status message.

    6. Editing a contact with negative index <br>
       Test case: `edit -1 n/Spencer Soh p/90008000 e/spencer@gmail.com a/Tampines t/student`<br>
       Expected: No update is made. Error details shown in the status message.

    7. Editing a contact with no index <br>
       Test case: `edit n/Spencer Soh p/90008000 e/spencer@gmail.com a/Tampines t/student`<br>
       Expected:  No update is made. Error details shown in the status message.

    8. Editing a contact with misspelled command <br>
       Test case: `edt 1 n/Spencer Soh p/90008000 e/spencer@gmail.com a/Tampines t/student`<br>
       Expected: No update is made. Details of the updated contact shown in the status message.
   
    9. Editing a contact with non-integer `PHONE NUMBER` <br>
       Test case: `edit 1 p/abc` <br>
       Expected: No update is made. Error details shown in the status message.

    10. Editing a contact with invalid format for `EMAIL` <br>
        Test case: `edit 1 e/spencermail` <br>
        Expected: No update is made. Error details shown in the status message.
   
2. Editing an existing contact (duplicate)

   Prerequisites: Contact with the same name has to be created prior

    1. Editing a contact with all parameters declared <br>
       Test case: `edit 1 n/Spencer Wee p/90008000 e/spencer@gmail.com a/Tampines t/student`<br>
       Expected: No update is made. Error details shown in the status message.
   
    2. Editing a contact with `NAME` parameter only <br>
      Test case: `edit 1 n/Spencer Wee`<br>
      Expected: No update is made. Error details shown in the status message.

### Deleting a contact

1. Deleting a contact while all contacts are being displayed

   Prerequisites: List all persons using the `list` command. Multiple persons in the list. At least 1 contact has to be created prior.

   1. Deleting a contact with valid index <br>
      Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message.

   2. Deleting a contact with invalid index <br>
      Test case: `delete 0`<br>
      Expected: No contact is deleted. Error details shown in the status message. 

   3. Deleting a contact with invalid index where index > list size <br>
      Test case: `delete x` (where x is larger than the list size) <br>
      Expected: No contact is deleted. Error details shown in the status message.

   4. Deleting a contact with negative index <br>
      Test case: `delete -1`<br>
      Expected: No contact is deleted. Error details shown in the status message.

   5. Deleting a contact with no index <br>
      Test case: `delete`<br>
      Expected: No contact is deleted. Error details shown in the status message.

   6. Deleting a contact with misspelled command <br>
      Test case: `delet`<br>
      Expected: No contact is deleted. Details of the deleted contact shown in the status message.

### Remarks for a contact

1. Adding a remark for an existing contact

   Prerequisites: At least 1 contact with no remarks has to be created

    1. Adding a remark to a contact with valid index <br>
       Test case: `remark 1 r/This is a test`<br>
       Expected: Remark is added to the first contact. Details of the contact shown in the status message.

2. Updating a remark for an existing contact

   Prerequisites: At least 1 contact with remarks has to be created

    1. Updating a remark to a contact with valid index <br>
       Test case: `remark 1 r/This is a test`<br>
       Expected: Remark of the first contact is updated based on input. Details of the contact shown in the status message.

3. Deleting a remark for an existing contact

   Prerequisites: At least 1 contact with remarks has to be created

    1. Deleting a remark to a contact with valid index <br>
       Test case: `remark 1`<br>
       Expected: Remark of the first contact is deleted. Details of the contact shown in the status message.

    2. Deleting a remark to a contact using no parameters<br>
       Test case: `remark 1 r/`<br>
       Expected: Remark of the first contact is deleted. Details of the contact shown in the status message.

### Favourites contacts

1. Marking a contact as Favourites

   Prerequisites: At least 1 contact has to be created

    1. Marking a contact as favourite with valid index <br>
       Test case: `fav 1`<br>
       Expected: First contact is marked as Favourite. 'Star' (GUI) for the first contact is filled. Details of the contact shown in the status message.

2. Removing a contact as Favourites

   Prerequisites: At least 1 contact that is marked with Favourites has to be created

    1. Removing a contact as favourite with valid index <br>
       Test case: `fav 1`<br>
       Expected: First contact is no longer marked as Favourite. 'Star' (GUI) for the first contact is empty. Details of the contact shown in the status message.

### Creating a new group

1. Creating a new group (no duplicates) <br>

   1. Creating a new group with valid syntax <br>
      Test case: `create g/NUS`<br>
      Expected: New group is created. Details of the new group shown in the status message.
   
   2. Creating a new group with missing parameters <br>
      Test case: `create g/`<br>
      Expected: No group is created. Error details shown in the status message.
   
   3. Creating a new group with no parameters <br>
      Test case: `create`<br>
      Expected: No group is created. Error details shown in the status message.

2. Creating a new group (duplicates) <br>

    1. Creating a new group with valid syntax <br>
       Test case: `create g/NUS`<br>
       Expected: No group is created. Error details shown in the status message.

### Assigning contact to group

1. Assigning a contact to an existing group <br>

   Prerequisites: At least 1 contact with no group has to be created. At least 1 group has to be created.

    1. Assigning a contact to an existing group with valid syntax <br>
       Test case: `assign n/Spencer Soh g/NUS`<br>
       Expected: Contact Spencer Soh is assigned to Group NUS. Details of the contact shown in the status message.

    2. Assigning a contact to an existing group with invalid `NAME` <br>
       Test case: `assign n/Spencer g/NUS`<br>
       Expected: No update is made. Error details shown in the status message.

    3. Assigning a contact to an existing group with missing `NAME` <br>
       Test case: `assign n/ g/NUS`<br>
       Expected: No update is made. Error details shown in the status message.

    4. Assigning a contact to an existing group with no `NAME` <br>
       Test case: `assign g/NUS`<br>
       Expected: No update is made. Error details shown in the status message.
   
    5. Assigning a contact to an existing group with invalid `Group` <br>
       Test case: `assign n/Spencer Soh g/NTU`<br>
       Expected: No update is made. Error details shown in the status message.

    6. Assigning a contact to an existing group with missing `Group` <br>
       Test case: `assign n/Spencer Soh g/`<br>
       Expected: No update is made. Error details shown in the status message.

    7. Assigning a contact to an existing group with no `Group` <br>
       Test case: `assign n/Spencer Soh`<br>
       Expected: No update is made. Error details shown in the status message.

### Renaming a group

1. Renaming an existing group (no duplicates)

   Prerequisites: At least 1 group has to be created.

    1. Renaming a group with valid index <br>
       Test case: `rename 1 g/NUS-SOC`<br>
       Expected: First group is renamed. Details of the group shown in the status message.

    2. Renaming a group with invalid index <br>
       Test case: `rename 0 g/NUS-SOC`<br>
       Expected: No update is made. Error details shown in the status message.

    3. Renaming a group with invalid index where index > list size <br>
       Test case: `rename x g/NUS-SOC` (where x is larger than the list size) <br>
       Expected: No update is made. Error details shown in the status message.

    4. Renaming a group with negative index <br>
       Test case: `rename -1 g/NUS-SOC`<br>
       Expected: No update is made. Error details shown in the status message.

    5. Renaming a group a contact with no index <br>
       Test case: `rename g/NUS-SOC`<br>
       Expected: No update is made. Error details shown in the status message.

2. Renaming an existing group (duplicates)

   Prerequisites: At least 1 group has to be created. Group with the same name has to be created prior.

    1. Renaming a group with valid index <br>
       Test case: `rename 1 g/NUS-SOC`<br>
       Expected: No update is made. Error details shown in the status message.

### Deleting a group

1. Deleting a group

   Prerequisites: At least 1 group has to be created prior.

    1. Deleting a group with valid index <br>
       Test case: `deleteg 1`<br>
       Expected: First group is deleted from the list. Details of the deleted group shown in the status message.

    2. Deleting a group with invalid index <br>
       Test case: `deleteg 0`<br>
       Expected: No group is deleted. Error details shown in the status message.

    3. Deleting a group with invalid index where index > list size <br>
       Test case: `deleteg x` (where x is larger than the list size) <br>
       Expected: No group is deleted. Error details shown in the status message.

    4. Deleting a group with negative index <br>
       Test case: `deleteg -1`<br>
       Expected: No group is deleted. Error details shown in the status message.

    5. Deleting a group with no index <br>
       Test case: `delete`<br>
       Expected: No group is deleted. Error details shown in the status message.

### Lists

1. Listing all contacts

   Prerequisites: At least 1 contact has to be created prior.

    1. Listing all contacts <br>
       Test case: `list`<br>
       Expected: Contacts found in json file should match the contacts in GUI. Details of the action is shown in the status message.
    
2. Listing all groups

   Prerequisites: At least 1 group has to be created prior.

    1. Listing all groups <br>
       Test case: `listgn`<br>
       Expected: Groups found in json file should match the groups in GUI. Details of the action is shown in the status message.

3. Listing all contact(s) in a specific group with at least 1 contact assigned

   Prerequisites: At least 1 contact with group has to be created prior. At least 1 group has to be created prior.

    1. Listing contact(s) in group with valid syntax<br>
       Test case: `listpfg g/NUS`<br>
       Expected: All contacts that is assigned to specified group are displayed. Details of the action is shown in the status message.

    2. Listing contact(s) in group with invalid `GROUP`<br>
       Test case: `listpfg g/NTU`<br>
       Expected: No update is made. Error details shown in the status message.

    3. Listing contact(s) in group with missing `GROUP`<br>
       Test case: `listpfg g/`<br>
       Expected: No update is made. Error details shown in the status message.

    4. Listing contact(s) in group with no `GROUP`<br>
       Test case: `listpfg`<br>
       Expected: No update is made. Error details shown in the status message.

4. Listing all contact(s) in a specific group with no contact assigned

    1. Listing contact(s) in group with valid syntax<br>
       Test case: `listpfg g/NUS-SOC`<br>
       Expected: Empty list displayed. Details of the action is shown in the status message.

6. Listing all Favourite contact(s)

    1. Listing all Favourite contact(s) with at least 1 Favourite contact<br>
       Test case: `listfav`<br>
       Expected: All Favourite contacts are displayed. Details of the action is shown in the status message.
   
    2. Listing all Favourite contact(s) with no Favourite contact<br>
       Test case: `listfav`<br>
       Expected: Empty list displayed. Details of the action is shown in the status message.

### Find

1. Finding by name

   Prerequisites: At least 1 contact has to be created prior.

    1. Finding contact(s) with 1 keyword <br>
       Test case: `find Alex`<br>
       Expected: All contacts with name that contains "Alex" is displayed. Details of the action is shown in the status message.

    2. Finding contact(s) with more than 1 keywords <br>
       Test case: `find Alex Bernice`<br>
       Expected: All contacts with name that contains "Alex" or "Bernice" is displayed. Details of the action is shown in the status message.

2. Finding by remarks

   Prerequisites: At least 1 contact with remarks has to be created prior.

    1. Finding contact(s) with 1 keyword <br>
       Test case: `findr test`<br>
       Expected: All contacts with remark that contains "test" is displayed. Details of the action is shown in the status message.

    2. Finding contact(s) with more than 1 keywords <br>
       Test case: `findr test exam`<br>
       Expected: All contacts with name that contains "test" or "exam" is displayed. Details of the action is shown in the status message.

3. Finding by group name

   Prerequisites: At least 1 contact assigned to a group has to be created prior.

    1. Finding contact(s) with 1 keyword <br>
       Test case: `findg NUS`<br>
       Expected: All contacts assigned to group that contains "NUS" is displayed. Details of the action is shown in the status message.

    2. Finding contact(s) with more than 1 keywords <br>
       Test case: `findg NUS Basketball`<br>
       Expected: All contacts assigned to group that contains "NUS" or "basketball" is displayed. Details of the action is shown in the status message.


