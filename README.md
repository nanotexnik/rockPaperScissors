# rockPaperScissors
You are a senior developer at ACME Games an online casual gaming company. The company
has more than 1 million daily active users playing games online. The site however does not have
a game of rock, paper, scissors. This is now the most requested game by the users of the site.
The CTO of the company wants you to develop the game and deploy it to production.

Task


Write a program that plays Rock, Paper, Scissors against a human. Try to exploit that humans
are very bad at generating random numbers.
You are only required to code the server-side components. A user interface is not expected.

Deliverable
1. The assignment should be delivered as a REST API that allows the user to
start the game, make moves, terminate the game and observe the statistics.
2. This is an open assignment in terms of how you structure the solution. You will be
judged on the overall quality of the code (simplicity, presentation, performance).


# Additional features implemented
In addition to the standard features, the integrity control was implemented. 

# How integrity control works
The main idea of integrity control is to allow user be sure that out company is not cheating and not used player's move in order to choose own one. 
It is realized with the following way: 
1. Out bot generates move before player move and send md5 hash to the player.
2. The hash contains information about the move and a random number. The pair of the number and the move is a secret.
3. After player makes move, he gets secret from the server and he can use md5 generator (eg. https://www.md5online.org/) in order to compare md5 hash from the first step and hash that is gotten from the generator.

# What else should be implemented
1. Now the algorithm that tries to exploit a player is predictable. If the player gets knowing about the using algorithm, he can exploit this information in his own goals.
2. Implement storing data in database (statistics, ...)
3. Authentication 
4. Closing inactive game sessions
5. Add a good description into a swagger api (use @ApiModel, @ApiResponse, etc.)

# API Description
Api description can be found at http://localhost:8080/swagger-ui.html when project is started 
