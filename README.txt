Name: Richie von der Schmidt
NetID: rev5
Hours Spent: Give an exact (decimal) number here: 15
Consulted With: I have talked to kids in class about Nbody (not sure all names), and Efe my TA has been an awesome resource. 
Resources Used: 
	I read the Princeton documentation here 
		http://www.cs.princeton.edu/courses/archive/spr15/cos126/assignments/nbody.html
		(That seems to be where the assignment originated)
		
	Book: Java 
	

Impressions: 


	This is an empowering first assignment.

Question 1: What is the final position of the planets after 1,000,000
seconds with a timestep of 25,000?

-6.1693879017391205E10
1.3623638588516254E11
1.1121701460006107E11
1.9831521210186224E11
-2.3740127849232388E10
5.234764234848517E10
1213453.9629152862
1457675.469811881
-1.0733376883232101E11
-1.2427747974008219E10
1.4457253808312594E22

That is in alternating xy form of the final positions of the planets, in the order they are given in the scanner


Question 2: For what values of timeStep, does the simulation no longer behave correctly? 

This whole model is predicated on being able to account for infinitesimally small differences in time. 
You are integrating velocity- you are taking the area under the velocity curve by finding the limit
as n goes towards infinity of a sum starting at i ending at n of the velocity function. This assumption
is no longer warranted when we arent considering a case with n approaching infinity. QED




Using the kinematics equations, we approximate the movement of bodies around each other, depending on the input file. 

A successful demonstration of the code's functionality can be found in using the planets.txt file when prompted. 

It should show the 9 planets orbiting the sun for a period in time. 
