# Data Structure Design - PR2: Practice 2

## PR2 - Statement

Below we present the statement of the second part of the practice (PR2) of the course. The practice consists of an evolutionary programming exercise based on the first practical exercise (PR1). It is MANDATORY that the coding of this exercise is based on the data structures defined in the official solution of CAA1 and CAA2; and use the PR1 coding (official solution) as a starting point. DO NOT use your solution. If any additional structure is necessary for any of the functionalities, it can be added justifying this decision.

### Description of the PR2 to be carried out

In PR2 we will basically work with the following entities and concepts:

- Worker: The workers that exist in the system
- Company: Existing companies
- JobOffer: Existing job offers
- Request: Job application
- Enrollment: Registration for a job offer
- Rating: Evaluation of a job offer
- Equipment: Material that the platform manages and that can be assigned to the rooms
- Room: Rooms managed by the platform
- Employee: Existing employees

### Features

The functionalities required for PR2 are:

 1. addRole(roleId, description)
 2. addEmployee(dni, name, surname, birthday, roleId)
 3. addRoom(roomId, name, description, type)
 4. assignEmployee(id, roomId )
 5. getEmployeesByRoom( roomId ): Iterator
 6. getEmployeesByRole( roleId ) : Iterator
 7. addEquipment(equipmentId, name, description)
 8. assign Equipment(equipmentId, roomId)
 9. getLevel( workerI d): Level
 10. getWorkersByJobOffer(jobOfferId): Iterator
 11. getSubstitutesByJobOffer(jobOfferId): Iterator
 12. getRoomsWithoutEmployees(): Iterator
 13. best5EquippedRooms(): Iterator
 14. addFollower(employeeDni, employeeFollowerDni)
 15. getFollowers(employeeDni): Iterator
 16. getFollowings(employeeDni): Iterator
 17. recommendations(employeeDni): Iterator
 18. getUnfollowedColleagues(employeeDni): Iterator

Additionally, new operations have been defined that allow inspecting the data structure and validating test sets

- numRoles(): int
- numEmployees(): int
- numEmployeesByRole(String roleId): int
- numRooms(): int
- numEquipments(): int
- numEquipmentsByRoom(String roomId): int
- whereIs(String equipmentId): Room
- getRole(String role): Role
- getEquipment(String equipmentId): Equipment

All operations required in PR2 are defined in the CTTCompaniesJobsPR2 interface , which is provided along with this document.

### Project launch

As indicated in the Resources section, a base project is provided to carry out the exercise. Specific:

1. src/main/java/uoc.ds.pr. CTTCompaniesJobs / CTTCompaniesJobsPR2.java : Interface that specifies the TAD operations to implement
2. src/test/java/uoc.ds.pr. FactoryCTTCompaniesJobs.java : Class that implements the factory design pattern and initializes data structures with initial values.
3. CTTCompaniesJobsPR1Test.java, CTTCompaniesJobsPR2Test.java : TAD test classes CTTCompaniesJobs
4. lib/DSLib-2.1.3.jar : Version 2.1.3 of the DSLib.
5. Classes pending implementation:
   - CTTCompaniesJobsImpl, CTTCCompaniesJobsPR2Impl , and the defined model entities
   - Additionally, all exceptions defined in the interface that must inherit from the DSDException class provided in the statement must be implemented.

### Facility

The main steps to launch the project of this practice are the following:

1. Download, unzip the .zip with the PR2 statement and import the project into the corresponding IDE.
2. Incorporate the solution of PR1 on this statement.
3. Implementation of the parts pending implementation.
4. Run the JUnit tests src/test/java

NOTE: Contact the teaching staff of the subject's Laboratory classroom for any questions on this point and others related to the coding of this practice.

### Planning

The planning that we propose for PR2 is the following:

1. Analysis of the solution of CAA1, CAA2, and PR1, and identification of the TADs of the library.
2. Analysis of the test set and validation that the data structures proposed in CAA1 and CAA2 are sufficient to meet the requirements of the test set. If there is any modification, it must be indicated in the delivery text document. With this action, we are following a test-driven development methodology (TDD).
3. Implementation of the manager with the operations defined by the provided interface.
NOTE: For the implementation of tests related to the social network (CTTCompaniesJobsPR2TestPlus), it is recommended to previously analyze the SocialNetworkWithDirectedGraphTest unit test from the DSLib library that can be consulted through the GIT repository.
4. Testing, validation of test set, and updating if necessary.

### Delivery format

The delivery must be made in a compressed file (ZIP), through the "Delivery and EC registration" space, organized as follows:

- A text document (readme.txt) indicating the scope of delivery, modifications, and updates made to the initially proposed design (official CAA2 solution) with its justification in case any additional data structure has been necessary, problems, and additional comments.
- A presentation of the tests executed. Remember that you do not have to stay only with the test set that we provide you: if you consider it appropriate, you can expand said test set. They must be included directly in the ZIP.
- The project with its sources: code (*.java) maintains the structure of the project (src/main folder, src/test, and package structure. DO NOT ATTACH* class files or the TADs library of the subject in the ZIP. For practical purposes, you can zip the project without the binaries.
- IT IS PROHIBITED TO MODIFY the code of the test files (src/test) or the contracts defined in the interfaces.

### Previous Solutions

#### CAA1

##### CAA1 - Statement

A Technology and Telecommunications Center (CTT) provides its facilities to accommodate workers employed by various companies in the sector, allowing them to perform their duties effectively. We have been approached by CTT to collaborate on the development of an application that not only manages essential data structures but also offers the functionality required for seamless operations.
CTT has a diverse range of facilities, which it extends to different companies in the sector to enable their recruited personnel to perform their tasks efficiently. These facilities include individual offices, rooms suitable for up to ten or twelve individuals, and rooms equipped with video conferencing capabilities, among others. To secure the use of a facility, a company must confirm the availability of a facility with the specific attributes required.
These attributes cover the type of facility, the specific number of occupants, and the duration spanning the start and end dates. If CTT possesses a facility that aligns with these attributes, it results in a favorable evaluation for the request. Subsequently, the company specifies the number of job positions to be filled within that facility. To allocate these job positions, the company issues an offer for different workers to apply. Job access is granted on a first-come, first-served basis, provided the worker meets the minimum qualifications. No additional evaluation criteria are considered. In the course of executing the projects associated with their job positions, workers encounter a series of interrelated activities that must be carried out in a specific sequence to achieve the desired objectives of each project. Upon completion of their respective tasks, workers can rate their performance with a numerical score and provide comments. The overall job assessment is determined by the average of all these evaluations.
This assessment holds significant importance as it enables the management company to make necessary adjustments for future hiring decisions

In addition to all of the above, there is information regarding the elements that are part of the application to be developed:

- The number of workers (W) is known and relatively small, a few hundred.
- The number of companies (C) is known and small, a few dozen.
- The number of applications for jobs (A) is small and indeterminate.
- The number of job offers (JO) is known and relatively small, a few hundred.
- The number of job offers from a company (CJO) is small and indeterminate.
- The number of job offers for which a worker is hired (WJO) is small and indeterminate.
- The number of evaluations of a job offer (VJO) is small and indeterminate.
- The number of registrations for a job offer (RJO) is relatively small, in the hundreds.

NOTE: In general, throughout the exercise, the efficiency of the query operations should be optimized, although this may penalize write operations.

##### CAA1 - Exercise 1

Specify a CTTCompaniesJobs ADT that allows the following operations:

1. Add a new worker to the system. For each worker, we gather information on their unique identifier, first and last names, date of birth, and qualifications, which are categorized from the lowest to the highest levels as follows: compulsory, high school, vocational training, university master's, and doctorate. In case an employee with the same identifier already exists, their information will be updated
2. Add a company to the system. We have information regarding each company, including its identifier, name, and a descriptive overview. If the company with the same identifier is already present, its data will be modified.
3. Create a job application. From the application we know its identifier, the identifier of the job offer, the identifier of the company offering it the description, the minimum qualification required, the number of jobs, the start date and the end date. It is known in advance that the job offer does not exist. If the company does not exist, an error is indicated. For a job offer to be finalized, the CTT is required to issue a favorable response based on the availability of its facilities. It is at this time that the request will be stored in the system. To do this, the following steps are necessary:

    Within the application, we have access to the identifier of the application itself, the job offer identifier, the identifier of the offering company, a description of the offer, the minimum qualification level required, the number of job positions available, the start date, and the end date. It is important to note that, by design, the job offer does not currently exist. If the associated company is not registered, an error is indicated. To finalize a job offer, the CTT must issue a positive response, contingent on the availability of its facilities. At this time, the request will be stored within the system. To achieve this, the following steps must be followed:
        a. A request is created for a job offer in the CTT by a company.
        b. CTT processes incoming requests in the order they are received and responds with either an affirmative or negative decision depending on the availability of its facilities
        c. When CTT provides a positive response to the application, it is stored in the system, and the corresponding job offer will be executed at their facilities.
        d. In the event of an unfavorable response, the request is dismissed and documented as a rejected application

4. Provide an outcome for a request, which may be either favorable or unfavorable. In both instances, the date of the decision is documented. If the decision is unfavorable, the reason is indicated. If there is no request, an error is reported.
5. Enroll a worker for a job offer. In cases where either the worker or the offer is nonexistent, an error will be indicated. Registrations are processed on a first-come, first-served basis, as long as the worker meets the minimum qualification criteria for the respective offer. If the maximum number of available job positions has been reached, an error is triggered, but these additional registrations are stored as substitute workers.
6. Check the percentage of rejected applications.
7. Consult the job offers of a company. It is known in advance that the company exists. If the company has no job openings, an error is indicated.
8. Consult all job offers in the system. If they do not exist, an error is indicated.
9. Consult the job offers in which a worker has worked. It is known in advance that the worker exists. If there are no job offers, an error is indicated.
10. Add a rating in numerical format (1-10) and a comment to a job by a worker. If the worker or position does not exist, an error is indicated. If the worker has not been part of the job offer, an error is indicated.
11. Consult the rating of a job offer. If the job offer does not exist, an error is indicated. If there are no ratings, an error is also indicated.
12. Consult the most active worker. The worker who has been working the longest is returned. That is, the sum of all the periods of all the job offers in which you have worked. If none exist, an error is indicated.
13. Get about the job offer rated most favorably by workers. In the absence of such an offer, an error is reported

###### CAA1 - Section 1-A

Specify the signature of the CTTCompaniesJobs ADT. That is, indicate the name of the operations associated with each of the previously mentioned functionalities. Indicate also, what are the input parameters and the type of data returned in each case.

1. addWorker(id, name, surname, dateOfBirth, qualification)
2. addCompany(id, name, description)
3. addRequest(id, jobOfferId, companyId, description, minQualification, maxWorkers, startDate, endDate)
4. updateRequest(status, date, description): Request
5. signUpJobOffer(workerId, jobOfferId)
6. getPercentageRejectedRequests(): number
7. getJobOffersByCompany(companyId): Iterator
8. getAllJobOffers(): Iterator
9. getJobOffersByWorker(workerId): Iterator
10. addRating(workerId, jobOfferId, value, message)
11. getRatingsByJobOffer(jobOfferId): Iterator
12. getMostActiveWorker(): Worker
13. getBestJobOffer(): JobOffer

###### CAA1 - Section 1-B

Make the contractual specification of the operations of the ADT CTTCompaniesJobs. Specifically, you must define the initial conditions that the operations must have (@pre) and the conditions on the state (@post) that the system remains (variables, data structures, return values) after its execution.
Take as a reference the Abstract data types (ADT) specifications in the learning resources. It will be valued:

- Concision (absence of redundant or unnecessary elements).
- Precision (correct definition of the result of operations).
- Completion (consideration of all possible cases in which each operation can be executed).
- Not contain ambiguities (exact knowledge of how each operation behaves in all possible cases).

NOTE: It is important to use a conditional description and not a procedural one, and although it is not always easy to distinguish between the two descriptions, that is why this aspect is mentioned.
For example, the following is the conditional (correct) description of starting a vehicle:

- @pre The vehicle's engine is off.
- @post The vehicle's engine is running.
- However, a procedural description (incorrect for this section) could be:
- @pre The vehicle's engine should be off, if it is not.
- @post The engine cranks until it starts to run.
- In describing the ADT, it should also be noted that a contract should have an invariant if necessary.

ADT CTTCompaniesJobs {

1. addWorker(id, name, surname, dateOfBirth, qualification)
   - @pre True.
   - @post If the worker code is new, the workers will be the same plus a new one.
   - If not, the worker's data will have been updated.
2. addCompany(id, name, description)
   - @pre True.
   - @post If the company code is new, the companies will be the same plus one new one.
   - If not, the company data will have been updated.
3. addRequest(id, jobOfferId, companyId, description, minQualification, maxWorkers, startDate, endDate)
   - @pre The job application and offer do not exist.
   - @post The requests will be the same plus a new one.
   - If the company does not exist, the error will be reported
4. updateRequest(status, date, description)
   - @pre True.
   - @post The status of the first request is modified
   - Returns the result request.
   - The count of pending requests awaiting a response will decrease by one. If the application receives a favorable response, the count of job offers will increase by one. If the response is unfavorable, the count of rejected applications will increase by one.
   - If there are no pending requests, an error will be reported.
5. signUpJobOffer(workerId, jobOfferId)
   - @pre True.
   - @post The count of workers enrolled for a job offer will increase by one. If the worker lacks the minimum qualifications, they will be excluded.
   - If the worker was already registered for that job offer, an error will be reported. If the maximum capacity has been reached, an error will be reported, and it will be added as a substitute. If either the job offer or the worker does not exist, an error will be reported.
6. getPercentageRejectedRequests(): number
   - @pre True.
   - @post Provides a real number representing the percentage of denied requests
7. getJobOffersByCompany(companyId): Iterator
   - @pre The company exists.
   - @post Returns an iterator over a company's job offers.
   - If they do not exist, the error will be reported.
8. getAllJobOffers(): Iterator
   - @pre True.
   - @post Returns an iterator over all job offers.
   - If they do not exist, the error will be reported.
9. getJobOffersByWorker(workerId): Iterator
   - @pre The worker exists.
   - @post Returns an iterator over the job offers in which a worker has enrolled or participated.
   - If they do not exist, an error is returned.
10. addRating(workerId, jobOfferId, value, message)
    - @pre True.
    - @post The ratings will be the same plus one.
    - If the worker or the job offer does not exist, the error will be reported.
    - If the worker has not been enrolled in the job offer, an error will be reported
11. getRatingsByJobOffer(jobOfferId): Iterator
    - @pre True.
    - @post Returns an iterator over the ratings of a job offer.
    - If the job offer does not exist, the error will be reported.
    - If there are no ratings, the error will be reported.
12. getMostActiveWorker(): Worker
    - @pre True.
    - @post Returns the most active worker, the one who has been working the longest.
    - If it does not exist, the error will be reported.
13. getBestJobOffer(): JobOffer
    - @pre True.
    - @post Returns the highest rated job offer.
    - If it does not exist, the error will be reported.
  
}

##### CAA1 - Exercise 2

Carry out the design of the data structures of the CTTCompaniesJobs ADT. It must be made as efficient as possible, both temporally and spatially.
NOTE: Only sequential structures will be used; therefore, neither trees, nor scatter tables, nor graphs can be used.

###### CAA1 - Section 2-A

To store workers:
Provide a rationale for determining the optimal choice among a vector, a linked list, or an ordered linked list.

SOLUTION:

- Since the number of workers is known and relatively small, a few hundred, the best option is a static vector, since we can assign it a fixed initial capacity that allows us to store all the elements.

What changes, if necessary, would have to be made in this structure so that the temporal efficiency in the worker consultation operation is constant?

SOLUTION:

- Each worker would have to be stored in the position of the vector that marks its identifier. Consequently, the size of the vector would increase in size. It would go from a size equal to the number of workers to a size equal to the maximum existing identifier among the collection of workers.

###### CAA1 - Section 2-B

To store a company's job offers:
Justify which is the best option between a vector, a linked list, or an ordered linked list.

SOLUTION:

- Since the number is small and indeterminate, the best option is a linked list. The ordered linked list is discarded since the elements don't need to be ordered a priori. A static structure like a vector has a predefined space and would not allow elements to be added beyond its initial capacity.

Justify if it is possible to perform binary searches in the previous data structure.

SOLUTION:

Binary or dichotomous searches are not possible on linked lists. Not even if its elements were ordered according to some criteria. This is because you cannot access a specific element directly, through an index, as you could in a static vector, but rather you need to traverse the linked list until you reach the specific element.

###### CAA1 - Section 2-C

Justify every one of the data structures to store the rest of the elements, taking into account everything commented on in the statement and in the description of the operations. To do this, it is convenient to use the following format:
“To save XXX we choose an ordered linked list since the number of elements is not known in advance, it is not very large, and we need ordering traversals”.

SOLUTION:

- For companies, we choose a vector, since its number is known and small, a few dozen.
- For applications (requests) we choose a queue, since they are taken in order of arrival.
- For job offers, we will use a vector, since its number is known and relatively small, a few hundred.
- For job offers in which a worker is hired, we select a linked list since it is a small and indeterminate number.
- For the evaluations of a job offer, we use a linked list, since it is a small and indeterminate number.
- For worker registrations for a job offer, we use a queue, since they are done in order of arrival.
- For the total number of requests, we use an integer.
- For the total number of rejected requests, we use an integer.
- For the most active worker, we choose a pointer.
- For the highest rated job offer an ordered vector.

###### CAA1 - Section 2-D

Make a graphical representation of the global data structure of the CTTCompaniesJobs ADT.
Every one of the data structures that have been chosen for each of the elements must be represented, as well as the existing relationships between them.

```text
+-------------------+        +---------------------+        +------------+
| Workers: Vector   |        | Job Offers: Linked  |        |   Worker   |
|                   |        | List                |        |            |
+---------+---------+        +----------+----------+        +-----+------+
          |                              |                         |
          |                              |                         |
          |                              |                         |
          v                              v                         |
   +------+-----------+        +---------+---------+               |
   | Most active worker:       | workingDays: int |---------------+
   | pointer           |       +---------+---------+
   +-------------------+                 |
                                        v
+-------------------+        +---------+---------+        +------------+
| Companies: Vector |        | Job Offers: Linked|        |  Company   |
|                   |        | List              |        |            |
+---------+---------+        +----------+----------+        +-----+------+
          |                              |                         |
          |                              |                         |
          |                              |                         |
          v                              v                         |
   +------+-----------+        +---------+---------+               |
   | Best Job Offers:          | ratings: Linked   |---------------+
   | Ordered Vector   |        | List              |
   +-------------------+        +---------+---------+
                                        |
                                        v
                               +--------+--------+
                               | enrollments:    |
                               | Queue           |
                               +--------+--------+
                                        |
                                        v
                               +--------+--------+
                               | substitutes:    |
                               | Queue           |
                               +--------+--------+

+-------------------+        +------------------------+
| requests: Queue   |        | Rejected requests :    |
|                   |        | Enter                  |
+---------+---------+        +------------------------+
```

###### CAA1 - Section 2-E

What differences might exist between the information presented in the statement and an actual scenario?

SOLUTION:

Some of the most relevant could be the following:

- In the statement all the information related to the sizes of the data structures is previously known: for example, known and relatively small, a few tens or hundreds, etc. This is usually complicated in a real case in which a priori there is no evidence of the necessary size for the structures, although there could be an estimate as to their magnitude.
- A real problem may require the design of data structures other than “traditional” ones (ad hoc structures) or involving a combination of them. Let's think, for example, of a group of people waiting at a ticket office to buy a ticket, but some of them have a preferential or VIP pass that allows them to make the purchase without having to wait for all the people in the group who arrived previously. Would its management be with a queue, a stack, a combination of both, or a new structure with that specific operation?
- The information related to the real problem is changing over time compared to a theoretical statement, which makes analysis and subsequent design difficult, often making it necessary to redo part of the work already prepared.
- Time estimation may not be appropriate in a real situation due to its changing nature and unforeseen difficulties, which could delay the release of the application. In a theoretical statement, the amount of work and its difficulty are adjusted to pre-established content and calendar.
- The need to work in a team when we are in a real environment, with the advantages and disadvantages that this entails.

With these points we wish to demonstrate the dimension involved in the development of an application in a real environment. Obviously we are in a training stage in which it is essential to acquire good skills and abilities. Among them, knowledge of data structures is crucial. And all this in order to build a good foundation that allows us to face the difficulties that we are undoubtedly going to encounter.

##### CAA1 - Exercise 3

This exercise involves specifying the algorithm to implement some of the operations that have been specified above and carrying out a study of its efficiency. It must be kept in mind that the implementation of each of the operations to be developed is closely related to the data structure selected in each case.

###### CAA1 - Section 3-A

Specify the algorithm and study the efficiency of each of the following operations:

- Register a worker in a job offer.
- Add a new worker.
- Add a review by a worker to a job offer.

To specify each step of the algorithm, its behavior must be briefly described, for example: “Insert into the vector, delete from the linked list, query the element of the ordered list, ...” as well as the asymptotic efficiency associated with such a step. It is also necessary to specify the global efficiency resulting from each operation.

SOLUTION:

1. Register a worker in a job offer:
   - Find the worker in the vector of workers: O(W).
   - Search for the job offer in the vector of job offers: O(JO).
   - Check that the job offer is not in their list of job offers O(WJO)
   - Check that the maximum number of workers has not been exceeded: O(1).
   - Add an element to the worker queue of a job offer: O(1).
   - Add an element to the chained list of job offers for a worker: O(1).
   - If necessary, update the pointer of the most active worker: O(1)
   - Total: O(W + JO + WJO).
  
2. Add a new worker:
   - Find the worker in the vector of workers: O(W).
   - If found, update the worker with the new data: O(1)
   - If not found, Create a new worker: O(1).
   - Add the new worker to the vector of workers: O(1).
   - Total: O(W).
  
3. Add a review by a worker to a job offer:
   - Find the worker in the vector of workers: O(W).
   - Search for the job offer in the vector of job offers: O(JO).
   - Add a review to the linked list of reviews: O(1).
   - Modify the ordered vector of top-rated job offers: O(JO).
   - Total: O(W + JO + JO) = O(W + 2JO) = O(W + JO).

###### CAA1 - Section 3-B

For various elements of the application, a linked list has been used to store them. This data structure,
as we know it, is it as efficient as possible in terms of spatial efficiency?

SOLUTION:

- It can be indicated that its spatial efficiency is optimal since it only maintains as many cells as the data you wish to store. However, it is not maximum , since it is necessary to have an extra pointer in each cell to be able to reference the next one. These pointers do not store data per se, but are used to make sense of the structure and be able to traverse all the data.

And a double linked one?

SOLUTION:

- If it were a doubly linked list, the number of extra pointers would be double, which would result in a worsening of spatial efficiency, although it would also mean the advantage of being able to perform routes in both directions.

##### CAA1 - Exercise 4

Indicate which ADT from the library <https://eimtgit.uoc.edu/DS/DSLib> would be used to implement each of the data structures defined in the CTTCompaniesJobs ADT. If there is no implementation already made in said library, indicate how it would be implemented.

SOLUTION:

- Workers: Java Array: Worker[].
- Job offers from a company: Linked List: LinkedList.
- Companies: Java Array: Company[].
- Requests : Queue: New ADT that implements an unbounded queue as an extension of a linked list and implements the Queue interface: QueueLinkedList.
- Job Offers: Java Array: JobOffer[].
- Job offers for a worker: Linked List: LinkedList.
- Ratings: Linked List: LinkedList.
- Enrollments : Queue: QueueLinkedList.
- Substitutes: Queue: QueueLinkedList
- Total requests : Integer: Integer.
- Total rejected requests : Integer: Integer.
- Most active worker : Pointer: Worker.
- Top Rated Job Offer: New ADT OrderedVector that implements the FiniteContainer interface and keeps all elements of its internal container sorted according to a sorting criterion specified by a comparator. OrderedVector.

#### PR1 Code Solution

#### CAA2

##### CAA2 - Statement

In this CAA we are going to continue with the design of the data structure of the fictitious application intended for Technology and Telecommunications Center (CTT). We will expand the functionalities to be able to work with nonlinear structures, which will allow us to use larger volumes of information.
Following the launch of the application, certain issues and deficiencies have been brought to our attention through user complaints, encompassing both companies and workers. Despite initial assumptions that specific features would cater to a limited user base, the application has generated more interest than anticipated. In response, the CTT has entrusted us with a set of modifications to address these issues and enhance the overall functionality of the application.
To begin with, we will integrate the capability to manage CTT rooms, including the materials available and assigned to each room. This enhancement aims to provide better control and prevent complaints arising from material shortages. Additionally, to ensure the optimal condition of the rooms, the CTT has requested that the application be equipped to manage its personnel undertaking various tasks (security, cleaning, maintenance, etc.) in each room.
A recurrent concern voiced by workers pertains to the registration process. Securing work opportunities becomes challenging if one is not vigilant enough with job offers. While the CTT believes in rewarding efficiency, it is not contemplating an overhaul of the registration system. However, it deems it prudent to refine the substitute registration process to prioritize individuals with less work experience. To achieve this, a tiered level system will be implemented, categorizing workers as follows:

- Expert : Workers who have accumulated 1000 or more hours.
- Senior : Workers with 500 or more accumulated hours and less than 1000.
- Junior : Workers with 200 or more accumulated hours and less than 500.
- Intern : Workers with 10 or more accumulated hours and less than 200.
- Beginner : Workers with less than 10 accumulated hours.

In addition to everything indicated above, to carry out this activity consider the following aspects:

1. Modifications regarding CAA1:

   - The number of workers will be very large and will increase.
   - The number of job offers will be very large and is expected to increase.
   - The number of companies will be large but stable.
   - At the request of the CTT, the operation “consult the best valued job offer” will not return a single offer, but the 10 best valued offers.

2. CAA2 news:

   - The number of rooms (R) will be very large and known.
   - The number of employees (E) of the CTT will be known and large.
   - The number of roles (R) will be small, determined to be a few dozen.
   - The number of employees who have a role ( ER ) is very large and indeterminate.
   - The number of materials (M) will be very large and will grow over time.

3. NOTE:

   - The starting point for developing this CAA is the official solution of CAA1 published in the classroom.
   - In general, in all activities it is necessary to optimize the efficiency of query operations even if this penalizes write operations.
   - If there is any error condition that is not indicated in the statement, and you think it is necessary to add it, you can add it.

##### CAA2 - Exercise 1

Specify the CTTCompaniesJobs ADT that allows the following operations:

- CAA1 operations that must modify their behavior

NOTE: From CAA1 you should review that, although the signature of the operations does not change, the implementation may do so due to the new choice of data structures.

1. Create job request : The behavior is as described in CAA1, but the requested room is provided in this case.
2. Issue a result to a request : The behavior is as described in CAA1 but taking into account that a room cannot be used at the same time for two offers. If an attempt is made to approve a request in which the room is already being used, the error will be reported.
3. Sign up for an offer by a worker : The behavior of this operation will be similar to that specified in CAA1 but taking into account the criteria established when managing substitutes.
4. Consult the 10 best-rated offers : If there is no offer, an error should be indicated.

- Operations added in CAA2

1. Add a role : For each role we will know its identifier and name. If a role with this identifier already exists, we update its data.
2. Add an employee : For each employee we will know their ID, their first name, their last name, date of birth and their role. It is known that the role indicated is among those existing in the system. If there is already an employee with that ID, we update their data.
3. Add a room : For each room, its identifier, name, description and type (laboratory, office or co-working) are known. If a room with that identifier already exists, its data is updated.
4. Assign an employee to a room : If the room or employee does not exist, an error should be reported. An error will also be reported if the employee is already assigned to that room. An employee may be assigned to more than one room at a time.
5. Query employees assigned to a room : Returns all employees in a room. If the room does not exist or there are no employees, an error must be reported.
6. Query all employees with a certain role: It is known that the role exists in the system. If there are no employees with this role, an error must be reported.
7. Add material : For each material we know its identifier, name and description. If a material with that identifier already exists, its data is updated.
8. Assign material to a room : If the room or material does not exist, an error should be reported. An error will also be reported if the material is already assigned to that room. If the material was already assigned to a different room, it will be detached from it and assigned to the new one.
9. Query the level of a worker : Returns the current level of the worker. If the worker does not exist, the error must be indicated.
10. Consult the workers registered for an offer (not substitutes) : Returns all workers registered for the offer. If the offer does not exist or there are no registered workers, an error will be indicated.
11. Consult the substitute workers registered for an offer : Returns all the substitute workers for an offer. If the offer does not exist or there are no substitute workers, an error will be indicated.
12. Consult rooms without assigned employees : If there are no rooms and/or all have assigned employees, the error must be indicated.
13. Consult the 5 best prepared rooms (with the most material) : If there is no room and/or none have material, the error must be indicated.
14. Add an employee as a follower of another . If the employee to be added or the employee who receives a new follower does not exist, an error will be indicated. It is assumed that there is no prior relationship between employees.
15. Consult the list of followers (followers). Provides an employee's employee followers. If the employee does not exist, the error must be indicated. If there are no follower employees, the error must be indicated.
16. View the list of employees you follow. Provides the list of employees that a given employee follows. If the employee does not exist, the error must be indicated. If there are no employees you follow, the error must be indicated.
17. Suggest employees to follow : Given an employee, the list of employees who are followed by those followed by the given employee is provided. If the employee does not exist or there are no followers, the error must be indicated. For example, in the figure below, the suggestion about Juan would return Patricia, Martha, and Pedro.
18. Show employees assigned to the same rooms and who are not followed: Provides a list of employees assigned to the same rooms as a given employee and who are not among the employee's followers. If the employee does not exist, the error must be indicated. If there are no employees still assigned to the same rooms, the error must be indicated.

NOTE FOR OPERATIONS 14 to 18: As a measure to facilitate communication and foster camaraderie among employees, the CTT has requested to include some social functions in the application. Specifically, employees will be able to follow each other in a similar way as on any social network. For the following operations, we will consider that employees can follow each other (be followers and followed) on the social network.

###### CAA2 - Section 1-A

Specify the signature of the CTTCompaniesJobs ADT. That is, indicate the name of the operations described above. Also indicate what the input parameters are and the type of data returned in each case.

1. addRole(roleId, description)
2. addEmployee(dni, name, surname, birthday, roleId)
3. addRoom(roomId, name, description, type)
4. assignEmployee(dni, roomId)
5. getEmployeesByRoom(roomId): Iterator
6. getEmployeesByRole(roleId): Iterator
7. addEquipment(equipmentId, name, description)
8. assignEquipment(equipmentId, roomId)
9. getLevel(workerId): Level
10. getWorkersByJobOffer(jobOfferId): Iterator
11. getSubstitutesByJobOffer(jobOfferId): Iterator
12. getRoomsWithoutEmployees(): Iterator
13. best5EquippedRooms(): Iterator
14. addFollower(employeeDni, employeeFollowerDni)
15. getFollowers(employeeDni): Iterator
16. getFollowings(employeeDni): Iterator
17. recommendations(employeeDni): Iterator
18. getUnfollowedColleagues(employeeDni): Iterator

###### CAA2 - Section 1-B

Carry out the contractual specification of the operations of the ADT CTTCompaniesJobs. Specifically, as in CAA1, the initial conditions that the operations must have (@pre) and the conditions on the state (@post) that remains in the system (variables, data structures, return values) must be specified. after its execution. Take as a reference the specification of section 1.2.3 of Module 1 of the teaching materials. It will be valued that the specification is:

- Concise (absence of redundant or unnecessary elements).
- Precise (correct definition of the result of operations).
- Complete (consideration of all possible cases in which each operation can be executed).
- And does not contain ambiguities (exact knowledge of how each operation behaves in all possible cases).

NOTE: It is important to use a conditional description and not a procedural one, although it is not always easy to distinguish between both descriptions, which is why this aspect is mentioned.  For example, the following is the conditional (correct) description of starting a vehicle:

- @pre The vehicle's engine is off.
- @post The vehicle's engine is running.
However, a procedural description (incorrect for this section) could be:
- @pre The vehicle's engine should be off if it shouldn't be.
- @post The engine cranks until it starts to run.
To describe the ADT, it must also be taken into account that a contract should have an invariant if necessary. Solution
- @pre true.
- @post if the role code is new, the roles will remain the same plus a new role with the indicated data. If not, the role data will have been updated with the new data.

SOLUTION:

1. addRole(roleId, description)
   - @pre true.
   - @post if the role code is new, the roles will remain the same plus a new role with the indicated data. If not, the role data will have been updated with the new data.
2. addEmployee(dni, name, surname, birthday, roleId)
   - @pre the role exists.
   - @post if the employee's ID is new, the employees will remain the same plus a new employee; the number of employees will be the same plus one, and the number of employees in a role will be the same plus one. If not, the employee data will have been updated with the new data, and if the role is modified, the number of employees in the old role will be the same minus one, and the number of employees in the new role will be the same plus one.
3. addRoom(roomId, name, description, type)
   - @pre true.
   - @post if the roomId is new, the rooms will remain the same plus a new room with the indicated data. If not, the room data will have been updated with the new data.
4. assignEmployee(dni, roomId)
   - @pre true.
   - @post if the employee is not assigned to the room, the employees in the room will remain the same plus a new one. If the employee is already assigned to the room, an error will    be displayed. If the employee or the room does not exist, an error will be indicated.
5. getEmployeesByRoom(roomId): Iterator
   - @pre true.
   - @post returns an iterator to traverse all employees assigned to a room. In case there are no employees or the room does not exist, an error should be indicated.
6. getEmployeesByRole(roleId): Iterator
   - @pre the role exists.
   - @post returns an iterator to traverse all employees of a role. If there are no employees with that role, an error should be indicated.
7. addEquipment(equipmentId, name, description)
   - @pre true.
   - @post if the equipmentId is new, the equipments will remain the same plus new equipment with the indicated data. If not, the equipment data will have been updated with the new data
8. assignEquipment(equipmentId, roomId)
   - @pre true.
   - @post if the equipment is not assigned to the room, the equipments in the room will remain the same plus a new one. If the equipment is already assigned to the room, an error will be displayed. If the equipment is already assigned to another room, it will be unlinked from it and assigned to the new one, and the number of equipments in the old room is the same minus one, and the number of equipments in the new assigned room is the same plus one. If the equipment or the room does not exist, an error will be indicated.
9. getLevel(workerId): Level
    - @pre true.
    - @post returns the level associated with the worker. If the worker does not exist, an error should be indicated.
10. getWorkersByJobOffer(jobOfferId): Iterator
    @pre true.
    @post returns an iterator to traverse all non-substitute workers enrolled in the job offer jobOfferId. If the job offer does not exist or no worker is enrolled, an error should be indicated.
11. getSubstitutesByJobOffer(jobOfferId): Iterator
    - @pre true.
    - @post returns an iterator to traverse all substitute workers enrolled in the job offer jobOfferId. If the job offer does not exist or no substitute is enrolled, an error should be indicated.
12. getRoomsWithoutEmployees(): Iterator
    - @pre true.
    - @post returns an iterator to traverse all rooms that have no assigned employees. If there are no rooms without any employees, an error should be indicated.
13. best5EquippedRooms(): Iterator
    - @pre true.
    - @post returns an iterator to traverse the top 5 rooms with the most assigned materials. If there are no rooms or no materials assigned, an error will be indicated.
14. addFollower(employeeDni, employeeFollowerDni)
    - @pre employeeDni is not a follower of employeeFollowerDni.
    - @post the number of followers of employeeDni will be the same plus one, and the number of employees followed (followings) by employeeFollowerDni will be the same plus one. In case the employee to follow or the followed employee does not exist, an error will be indicated.
15. getFollowers(employeeDni): Iterator
    - @pre true.
    - @post returns an iterator to traverse the followers of an employee. If the employee does not exist or has no followers, an error should be indicated.
16. getFollowings(employeeDni): Iterator
    - @pre true.
    - @post returns an iterator to traverse the employees that an employee is following. If the employee does not exist or has no employees following, an error should be indicated.
17. recommendations(employeeDni): Iterator
    - @pre true.
    - @post returns an iterator to traverse suggested employees who are followers of employees that employeeDni is following and who are currently not followers. If the employee does not exist or has no employees following, an error will be indicated.
18. getUnfollowedColleagues(employeeDni): Iterator
    - @pre true.
    - @post returns an iterator to traverse employees working in the same rooms as employeeDni and whom he/she is not following. If the employee does not exist or there are no employees not followed assigned to the same rooms, an error will be indicated.

##### CAA2 - Exercise 2

Next, we will design the associated data structures , based on the CTTCompaniesJobs ADT specification , taking into account the volumes of information and the restrictive specifications described in the statement. The system should be as efficient as possible both temporally and spatially. To make this design, take into account only the operations that are requested in the statement.

###### CAA2 - Section 2-A

We hesitate between using a vector, an AVL, a linked list or a hash table to store the workers . Justify which you think is the best option.

SOLUTION:

- The best option is an AVL since the number of workers becomes very large and unlimited. With a list, search operations would be linear, and with large volumes of information, it is not acceptable. A hash table is also not suitable if the information we want to store is not limited. We choose an AVL, which will provide logarithmic costs for queries and allow us to work with very large and unlimited volumes of information

###### CAA2 - Section 2-B

What structure would be ideal for storing substitute entries : a queue, a stack or a list? Justify which you think is the best option.

SOLUTION:

- As substitute registrations must be stored according to a specific criterion, the criterion for substitute registrations is based on the worker's level, leading us to choose a priority queue

###### CAA2 - Section 2-C

What structure would be ideal to store CTT employees , an AVL, a linked list or a hash table?
Justify which you think is the best option and what specific structure you would use taking into account that the searches will be carried out by ID.

SOLUTION:

- As the volume of data is large, we discard using a vector or list, and we must choose between an AVL tree and a hash table. Since we are informed that the number of employees is known and large, we will choose a hash table to achieve constant query access through the employee's ID.

###### CAA2 - Section 2-D

What data structure would you use to store the rooms ? And for the equipments ? Justify which you think is the best option in each case.

SOLUTION:

- Rooms: We know that, like employees, the number of organizing entities will be very large and known. With this information, we can determine that the best choice would be a hash table, which would guarantee efficient access.

- Equipments: A hash table would allow us to achieve constant query access even when working with very large data volumes, as would be the case with materials. However, we know that the number of these equipments is indeterminate and therefore would not be a good choice. The best option for storing equipments is an AVL tree, which will provide logarithmic costs for queries and allow us to work with very large and unlimited volumes of information

###### CAA2 - Section 2-E

Justify each one of the data structures to store the rest of the elements , taking into account everything discussed in the statement and in the description of the operations. Only for mandatory operations (1-13). To do this, it is necessary to use the following format:
"To save XXX we choose an ordered linked list since the number of elements is not very large, and we need ordered traversals."

SOLUTION:

1. For companies, we will choose a hash table since the container size will be large but stable, and efficient access is desired.
2. For requests, we choose an unbounded queue since they are taken in the order of arrival, and their size is indeterminate.
3. For job offers, we will use an AVL tree since it will be very large and expected to grow.
4. For job offers where a worker is hired, we select a linked list as the number is small and indeterminate.
5. To store roles, we will use a Java array (Object[]).
6. To store employees associated with a role, we will use a linked list.
7. To store employees assigned to a room, we will select a linked list.
8. To store materials assigned to a room, we will select a linked list.
9. To store the rooms to which a material is assigned, we will use a pointer.
10. To store rooms without assigned employees, we will use a linked list since their number is not known in advance.
11. For evaluations of a job offer, we use a linked list since it is a small and indeterminate number.
12. For worker registrations for a job offer, we use an unbounded queue since they are processed in the order of arrival.
13. For the total number of requests, we use an integer.
14. For the total number of rejected requests, we use an integer.
15. For the most active worker, we choose a pointer.
16. To store the top 10 rated job offers, we will use an ordered vector.
17. To store the top 5 best-equipped rooms, we will use an ordered vector.

###### CAA2 - Section 2-F

Indicate additional data structures to implement operations (14-18) justifying your choice briefly (max 2 paragraphs).

SOLUTION:

- To store the relationship between followers and followed employees, a directed graph will be used. To provide the list of employees assigned to the same rooms and those who are not followed, it is necessary for the employee to maintain the list of rooms to which they are assigned through a linked list. The alternative would be to traverse all rooms and the employees assigned to these rooms, which would not be scalable

###### CAA2 - Section 2-G

Make a graphical representation of the global data structure by the CTTCompaniesJobs ADT where the data structures chosen to represent each of the parts and the relationships between them are seen. You must represent the complete structure, with all the structures that allow you to implement the operations defined in the specification.

SOLUTION:

```text
  JobOffers: AVL
         |
         v
  Ratings: LinkedList -- Enrollments: QueueLinkedList
         |                          |
         |                          v
         |               substituteEnrollments: PriorityQueue
         |
         +-- Employees: HashTable -- Rooms: LinkedListEncadenada
         |               |                            |
         |               |                            v
         |               |                     Employees: SocialNetwork
         |               |                            |
         |               |                            v
         |               |                     Roles: Vector
         |               |                            |
         |               |                            v
         |               |                     Requests: Queue
         |               v
         +-- Rooms: HashTable
         |               |
         |               v
         +-- Companies: HashTable
                         |
                         v
               Job offers: LinkedList
                         |
                         v
                   Workers: AVL

```

##### CAA2 - Exercise 3

In exercise 1 we have defined the specification of the CTTCompaniesJobs ADT with its operations, and in exercise 2 we have chosen the data structures to implement each part of the ADT. In this exercise we ask you to look at the algorithms that will be used to implement some of the operations specified above and the study of their efficiency . Keep in mind that the implementation of the
operations is closely linked to the choice of data structures you have made.

###### CAA2 - Section 3-A

Describe and carry out the efficiency study for the following operations:

- Add a room.
- Assign material to a room.
- Assign an employee to a room.

To do so, you must briefly describe the behavior of the operation, indicating the steps that comprise it. You can use pseudocode or phrases like: "insert into vector / delete from linked list / query element in sorted linked list /...", indicating the asymptotic efficiency of each step and calculating the total efficiency of the operation.

SOLUTION:

1. Add a room:
   - Search for the room in the CTT's room hash table => O(1).
   - Insert the room into the CTT's room hash table => O(1).
   - Total: O(1).

2. Assign equipment to a room:
   - Search for the equipment in the AVL of equipment => O(log E).
   - Search for the room in the CTT's room hash table => O(1).
   - Add the equipment to the linked list of equipment in a room => O(1).
   - Update the pointer of the room assigned to the equipment => O(1).
   - Total: O(log E).

3. Assign an employee to a room:
   - Search for the room in the CTT's room hash table => O(1).
   - Search for the employee in the CTT's employee hash table => O(1).
   - Search for the employee in the linked list of employees in a room => O(ER).
   - Delete the employee in the linked list of employees in a room => O(1).
   - Add the employee to the linked list of employees in a room => O(1).
   - Total: O(ER).

Note: In case the room to which the employee has been assigned was previously empty, it would be necessary to search in the list of empty rooms O(ER) and delete it O(1). In case the original room becomes empty when abandoned by the employee, it would be necessary to insert it into the list of empty rooms O(1)

###### CAA2 - Section 3-B

Currently, no operation allows a worker to withdraw from an offer, so registrations and substitute registrations never decrease. In a real application it would be essential to have this functionality. Describe in detail what the behavior of said registration operation would be like.

SOLUTION:

- Firstly, it would be necessary to check whether the job offer exists. To do this, we must traverse the AVL of job offers. If the offer is not found, we should report an error. Next, we should verify that the worker who is resigning from a specific job offer indeed has a registration in that offer. To do this, we must traverse the unbounded queue of registrations and, if not found there, also the queue of substitute registrations. If the worker's registration is not found in the offer, we should report an error. Otherwise, two situations could arise:

  - If the registration is in the unbounded queue of registrations: In this case, upon finding the registration in this queue, we would need to:

    - Search for the job offer in the linked list of the worker.
    - Remove the job offer from the linked list of the worker.
    - Remove the worker's registration from the unbounded queue of the job offer.
    - Extract the first registration from the priority queue of substitute registrations.
    - Insert the registration into the unbounded queue of the job offer.
    - Add the offer to the linked list of job offers for the worker.

If the registration is in the priority queue of substitute registrations: In this case, it would be sufficient to remove the registration from the priority queue.

##### CAA2 - Exercise 4

###### CAA2 - Section 4-A

Indicate which ADT from the ADT library <https://eimtgit.uoc.edu/DS/DSLib> seem more suitable for use in the implementation of each of the data structures defined by the CTTCompaniesJobs ADT for operations 1-13 . If there is no implementation already made in the ADT library of the subject, briefly indicate how you would implement it.

SOLUTION:

- Workers: DictionaryAVLImpl.
- Job Offers of a Company: Linked List.
- Companies: Hash Table.
- Requests: QueueLinkedList.
- Job Offers: DictionaryAVLImpl.
- Job Offers of a Worker: LinkedList.
- Ratings: LinkedList.
- Registrations: QueueLinkedList.
- Substitute Registrations: PriorityQueue.
- Employees: HashTable.
- Rooms: HashTable.
- Equipments: DictionaryAVLImpl.
- Employees Assigned to a Room: LinkedList.
- Equipments Assigned to a Room: LinkedList.
- Room where Equipment is Assigned: Pointer - Room.
- Rooms without Employees: LinkedList.
- Roles: Java Array - Roles[].
- Employees Associated with a Role: LinkedList.
- Total Requests: Integer.
- Total Rejected Requests: Integer.
- Most Active Worker: Pointer - Worker.
- Top-Rated Job Offer: OrderedVector.
- Best-Equipped Rooms: OrderedVector.

###### CAA2 - Section 4-B

Indicate which ADT from the subject's ADT library you think is most appropriate for use in the implementation of each of the data structures defined by the CTTCompaniesJobs ADT for operations options 14-18 . If there is no implementation already made in the ADT library of the subject, briefly comment on how you would implement it.

SOLUTION:

- Rooms Assigned to an Employee: Linked List
- Social Network among Employees: DirectedGraphImpl.
