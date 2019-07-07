360T PLAYERS CODING ASSIGNMENT
This project has been developed for 360T coding challenge task.
This is a simple program which includes two player who communicates with each other.
Program starts and initiator player starts to send messages to target player.
After that, whenever a player receives a message, then it sends a response to other player and it continues like this.
For the messages BlockingQueue data structure has been used, since it provides wait/notify functions without managing them manually. Some delay/wait has been added into Putting and Getting messages functions in order to track better message transfers via standard output. Standard output has been used instead of logging in order to see messages easily while program running.
Test coverage of this project is 71%.

BUILD AND RUN

This is maven application. You can build and run application by using maven.
You can build the application by using 'mvn package' command.
After build you can run the jar file using 'java -jar communication-0.0.1-SNAPSHOT' 

