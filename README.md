# Client-Server-Pattern
This is a simple implementation of client-server-pattern in Java. This repository was built in the course 
'Design of software systems' at the university of Innsbruck (faculty computer science). No GUI - only terminal based.

## Functionalities
The pattern should provide following functionalities:
- Implement two servers (one for cars and the other one for motorcycles) which calculates the discounted price for the given
vehicle.
- Implement a client who represents a sales application for a car dealer.
- The communication between client and the servers should be with 'Java Sockets'.
- Every vehicle has three different equipment version (A, B, C).
- For cars:
  - Cars with equipment version A or B should get a random discount between 10 and 20 percent.
  - Cars with equipment version C should get 5 a discount of 5 percent.
- For motorcycles:
  - Motorcycles with equipment version A or C should get a discount of 30 percent.
  - Motorcycles with equipment version B should get a random discount between 5 and 10 percent.
- The client program should run endless until the user enters 'close'.
- Every input should be correct (e.g. it's not possible to enter a truck or something else)
- The calculation at the server should only be proceed, if and only if the vehicle is correct (meaning: not null or something else)
- The client should print the discounted price from the server at the terminal.
