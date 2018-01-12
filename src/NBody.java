import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;

import princeton.StdAudio;
import princeton.StdDraw;

public class NBody{
	
    public static final double G = 6.67E-11;

    /**
     * returns Euclidean distance between (x1, y1) and (x2, y2)
     *
     * @param x1
     *            x-coordinate of point 1
     * @param y1
     *            y-coordinate of point 1
     * @param x2
     *            x-coordinate of point 2
     * @param y2
     *            y-coordinate of point 2
     * @return Euclidean distance between (x1, y1) and (x2, y2)
     */
    public double distance(double x1, double y1, double x2, double y2) {
    	//TODO: Complete distance
    		double dx = x1 - x2;
    		double dy = y1 - y2;

        return Math.sqrt(dx * dx + dy * dy);
        
        
    }
    
    
    /**
     * return the magnitude of the gravitational force between two bodies of
     * mass m1 and m2 that are distance r apart
     *
     * @param m1
     *            mass of body 1 in kg
     * @param m2
     *            mass of body 2 in kg
     * @param r
     *            distance in m
     * @return force between m1 and m2 that are distance r apart
     */
    public double force(double m1, double m2, double r) {
        //TODO: Complete force
    	
    	return (G * m1 * m2) / (r * r);
    }


    /**
     * Returns the x positions and y positions of bodies
     * @param totalTime The total amount of universe time to run for
     * @param timeStep The value of delta t in the equations to calculate position
     * @param info The scanner with info about the initial conditions of the
     * bodies
     * @return an array whose first element is the x and y position of the first body, and so on
     * t = totalTime
     * 
     */
    
    public double[][] positions(Scanner info, int totalTime,
            int timeStep) {
double counter = 0.0;
// System.out.println(info.hasNextInt()); // Print to ask if there is another int
int numberbodies = info.nextInt();
//This tells me the number of bodies, and therefore the size of certain loops
double radius = info.nextDouble();
// This is the radius of the universe in question
System.out.println(info.nextLine()); // Have to call next line to move to the doubles

//Declare My Arrays Here
double[] px = new double[numberbodies];
double[] py = new double[numberbodies];
double[] vx = new double[numberbodies];
double[] vy = new double[numberbodies];
double[] mass = new double[numberbodies];
String[] image = new String[numberbodies];

/*-----------------------------------------------------------------------------------------*/
System.out.println("Now Populating Arrays");
for (int k = 0; k < numberbodies; k++){

px[k] = info.nextDouble();
System.out.println(px[k]);
py[k] = info.nextDouble();
System.out.println(py[k]);
vx[k] = info.nextDouble();
System.out.println(vx[k]);
vy[k] = info.nextDouble();
System.out.println(vy[k]);
mass[k] = info.nextDouble();
System.out.println(mass[k]);
image[k] = info.next();
System.out.println(image[k]);
}
/*-----------------------------------------------------------------------------------------*/
StdDraw.setXscale(-radius, radius);
StdDraw.setYscale(-radius, radius);

StdDraw.picture(0.5, 0.5, "data/starfield.jpg");

for(int ii = 0 ; ii < numberbodies ; ii ++) {

StringBuilder path = new StringBuilder("data/");

path.append(image[ii]);

String built = path.toString();

StdDraw.picture(px[ii], py[ii], built);




}

StdDraw.show(30);

double[] fx = new double[numberbodies];
double[] fy = new double[numberbodies];

/*-----------------------------------------------------------------------------------------*/
while(counter < totalTime){  

counter += timeStep;



StdDraw.setXscale(-radius, radius);
StdDraw.setYscale(-radius, radius);
StdDraw.picture(0.5, 0.5, "data/starfield.jpg");


/*-----------------------------------------------------------------------------------------*/        	

/*-----------------------------------------------------------------------------------------*/        	
for (int i = 0; i < numberbodies; i++){

fx[i] = 0.0;
fy[i] = 0.0;

for (int j = 0; j < numberbodies; j++) {

if (i != j) {
	
	double dx = px[j]-px[i];
	
	double dy = py[j]-py[i];
	
	double euclid = distance(px[j],py[j],px[i],py[i]);
	
	double bigforce =  force(mass[i], mass[j], euclid);
	
	fx[i] += bigforce * dx / euclid; 
	
	fy[i] += bigforce * dy /euclid;
	
	
}

}

// Right before the outer loop ends
}
/*-----------------------------------------------------------------------------------------*/
double ax = 0;
double ay = 0;

for (int k = 0; k < numberbodies; k++) { 

ax = (fx[k] / (mass[k]));
ay = (fy[k] / (mass[k]));
fx[k] = 0;
fy[k] = 0;

vx[k] += ax*timeStep;
vy[k] += ay*timeStep;

px[k] += vx[k]*timeStep;
py[k] += vy[k]*timeStep;

}


for(int ii = 0 ; ii < numberbodies ; ii ++) {

StringBuilder path = new StringBuilder("data/");

path.append(image[ii]);

String built = path.toString();

StdDraw.picture(px[ii], py[ii], built);



}
StdDraw.show(30);

} // While loop ends here
/*-----------------------------------------------------------------------------------------*/



double[][] output = new double[numberbodies][2]; //Replace 0 with the number of
                          //bodies, read from the file
System.out.println("Right about to return output");

for (int kk = 0; kk < numberbodies ; kk ++) {
output[kk][0] = px[kk];
System.out.println(px[kk]);
output[kk][1] = py[kk];
System.out.println(py[kk]);
//
}






return output;


}
		
    public static void main(String[] args) {
        Scanner info = openFile();
        int time = 1000000;
        int dt = 25000;
        
        
        
        if (info != null) {
            //StdAudio.play("data/2001.mid");
            NBody myNBody = new NBody();
            double[][] results = myNBody.positions(info, time, dt);
            for(int i = 0; i < results.length; i++) {
                for(int j = 0; j < results[i].length; j++) {
                    System.out.print(results[i][j]+" ");
                }
                System.out.println();
            }
            StdAudio.close();
        }
    }
    /**
     * Displays file chooser for browsing in the project directory. and opens
     * the selected file
     *
     * @return a new Scanner that produces values scanned from the selected
     *         file. null if file could not be opened or was not selected
     */
    
    public static Scanner openFile() {
        Scanner scan = null;
        System.out.println("Opening file dialog.");
        JFileChooser openChooser = new JFileChooser(System.getProperties()
                                                    .getProperty("user.dir"));
        
        int retval = openChooser.showOpenDialog(null);
        if (retval == JFileChooser.APPROVE_OPTION) {
            File file = openChooser.getSelectedFile();
            System.out.println(file.getAbsolutePath());
            try {
                scan = new Scanner(file);
                System.out.println("Opening: " + file.getName() + ".");
            } catch (FileNotFoundException e) {
                System.out.println("Could not open selected file.");
                e.printStackTrace();
            }
        } else {
            System.out.println("File open canceled.");
            System.exit(0);
        }
        
        return scan;
    }
}