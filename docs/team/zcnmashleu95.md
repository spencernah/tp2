---
layout: page
title: Lam Sze Chun's Project Portfolio Page
---

### Project: Contacts Address Book

Contacts Address Book (CAB) is a **desktop app that manages various contacts with added functionality.**
Users can input via a command line interface and access features that help with contacts management.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-tic4002-ay2122s2.github.io/tp-dashboard/?Author=zcnmashleu95&tabRepo=AY2122S2-TIC4002-F18-1%2Ftp2%5Bmaster%5D&search=&sort=groupTitle&sortWithin=title&since=2022-02-11&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=zcnmashleu95&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Coordinated work for the User Stories and Target Users

* **Enhancements to existing features**:
  * Enhanced and refactored `seedu.address/commons/util/StringUtil` functions. Originally, the matching function could only match
    with the exact word. After the enhancement, it matched with partial words as well. This function would improve the find functionality
    and be utilised in the newly added Find Remark and Find Group commands included from my teammate as well.
  * Improved the Find Command Functionality. Originally, it only matched with exact words and was limited to names only. Now, it is like an intuitive search function, whereby the user could search for contacts that contains the keyword partially
    in its details. The Find Command Functionality is also applied to contact details instead of names now. Contact details include phone numbers, addresses and emails in the same command.This was implemented through the refactored predicate ContactDetailsContainsKeywordsPredicate.
  * Refactored parsers such as AddCommand Parser and CreateGroup Parser with better try-catch statements/exception handling
  * Refined existing test cases in the StringUtil and Find Command. Added more assertions in test cases.
  * Added Unit Testing for critical new features such as Assign Group
  * Fixed bugs related to the unit test cases for the CommandsTests and ParserTests
  * Refactored the test cases to be able to integrate with the new feature Groups

* **Documentation**:
  * User Guide:
    * Edited documentation for the feature `find`
    * Brainstormed and formed the target user and user stories for the submission
  *Testing.md:
    *Elaborated further about the unit tests and their setup


* **Community**:
  * Reviewed and approved PRS. Some of them include:

  [Pull 77](https://github.com/AY2122S2-TIC4002-F18-1/tp2/pull/77),
  [Pull 71](https://github.com/AY2122S2-TIC4002-F18-1/tp2/pull/71)
  [Pull 28](https://github.com/AY2122S2-TIC4002-F18-1/tp2/pull/28)

* **Tools**:
  * Github workflow
    - Enabled setting whereby continue-on-error is false (switchable). If the setting is false, when an error occurs in the CI/CD process, the following task will not continue.
      This feature may be useful depending on the circumstances (if CI/CD runs are costly) or on the stage of development (likelihood of encountering errors during pushes)
  * JUnit Test


