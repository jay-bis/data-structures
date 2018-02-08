
package hw2;


public class ConstructionSite {
    
    private SandBox[] boxes;
    private int count; //number of boxs saved in the array
    
    public ConstructionSite( int maxSize ){
    	boxes = new SandBox[maxSize];  
       	count = 0; 
    } 
    
    // add a new empty SandBox of the given width, length, height to the array
    // 
    // But, if the array is already full then just print "ERROR addSandBox"
    // and don't add a new box
    public void addSandBox(double length, double width, double height){
        if (boxes.length == count) {
            System.out.println("ERROR Sandbox");
        } else {
        int i = 0;
        while (boxes[i] != null) {
            i++;
        }
        boxes[i] = new SandBox(length, width, height);
        count++;
        }
    }
    
    // if array contains at least one SandBox delete the last one in the array
    // otherwise do nothing
    public void deleteSandBox(){
        if (boxes.length >= 1) {
            int i = boxes.length - 1;
            while (boxes[i] == null) {
                i--;
            }
            boxes[i] = null;
            count--;
        }
    }

    // scoop sand from input in order until
    // either all of the sand is gone or all boxes have been filled
    //
    // EXAMPLE #1
    // BEFORE
    // boxes {SandBox(volume=10,sandVolume=0), 
    //           SandBox(volume=4,sandVolume=0), 
    //           SandBox(volume=6,sandVolume=0)} 
    // input is SandBox(volume=13, sandVolume=13)
    // 
    // then AFTER
    // boxes {SandBox(volume=10,sandVolume=10), 
    //           SandBox(volume=4,sandVolume=3), 
    //           SandBox(volume=6,sandVolume=0)} 
    // input is SandBox(volume=13,sandVolume=0)
    //
    // EXAMPLE #2
    // BEFORE
    // boxes {SandBox(volume=10,sandVolume=0), 
    //           SandBox(volume=4,sandVolume=0), 
    //           SandBox(volume=6,sandVolume=0)} 
    // input is SandBox(volume=22, sandVolume=22)
    // 
    // then AFTER
    // boxes {SandBox(volume=10,sandVolume=10), 
    //           SandBox(volume=4,sandVolume=4), 
    //           SandBox(volume=6,sandVolume=6)} 
    // input is SandBox(volume=22,sandVolume=2)
    public void fillBoxesInOrder(SandBox input) {
        for (SandBox box : boxes) {
            if (input.getSandVolume() > 0)
                box.scoopSandFrom(input);
            else
                break;
        }
    }

    /*
    Helper function to check if two doubles are close enough to being equal.
    (In general, it is bad to compare doubles for exact equality with ==)
    */
    private static void check(double got, double expected) {
            final double EPSILON = 0.00001;
            if (Math.abs(got - expected) >= EPSILON) {
                    throw new RuntimeException("failed test: got "+ got + " but expected " + expected);
            }
    }

    /*
        Helper function to run a test case

        site: the ConstructionSite to work with
        input: the SandBox to scoop sand out of
        expectedVols: expected volumes of the site's SandBoxes after the scooping
        expectedSandVols: expected sand volumes of the site's SandBoxes after the scooping
        expectedInputVol: expected volume of the input after the scooping
        expectedInputSandVol: expected sand volume of the input after the scooping
    */
    private static void testCase(ConstructionSite site, SandBox input, 
                    double[] expectedVols, double[] expectedSandVols,
                    double expectedInputVol, double expectedInputSandVol) {
            input.fillToTop();
            site.fillBoxesInOrder(input);
            for (int i=0; i<site.count; i++) {
                    System.out.println(site.boxes[i].toString());
                    check(site.boxes[i].getVolume(), expectedVols[i]); 
                    check(site.boxes[i].getSandVolume(), expectedSandVols[i]);
            }
            System.out.println("source: "+input.toString());
            check(input.getVolume(), expectedInputVol);
            check(input.getSandVolume(), expectedInputSandVol);
    }
	
	/*
	Runs a drain simulation on this ConstructionSite's boxes.
	At time=0, a hole is poked in each SandBox, and once per frame this method
	prints the current elapsed time and the boxes.

	framesPerSecond: frames per second, simulation is updated once per frame
	seconds: time to run the simulation for
	holes: array of rates that sand leaves the boxes.
	    For example if holes = {1,2,3} then boxes[0] drains 1 unit volume/second,
	    boxes[1] drains 2 unit volume/second, and boxes[2] drains 3 unit volume/second
	*/
	public void runDrainSimulation(double framesPerSecond, double seconds, double[] holes) {
		double totalFrames = framesPerSecond * seconds;
                int timeCount = 1;
                SandBox[] drainage = new SandBox[count];
                for (int i=0; i<count; i++) {
                    drainage[i] = new SandBox(1, 1, holes[i]/framesPerSecond);
                }
		for (int f=0; f<totalFrames; f++) {
                        System.out.println();
                        System.out.print("Time=" + timeCount/framesPerSecond + ": ");
			for (int i=0; i<count; i++) {
                            drainage[i].scoopSandFrom(boxes[i]);
                            drainage[i].empty();
                            System.out.print(boxes[i].toString() + " ");
                        }
                        timeCount++;

			// DO NOT REMOVE THIS CODE; makes the simulation real time
			try {
				Thread.sleep((long)(1000.0/framesPerSecond));
			} catch (InterruptedException e) { /* ignore this error */ }
		}	
	}

    public static void main(String[] args) {
        //For these tests, the same set of boxs are used for each test. They are not emptied out
        //at the begining of each test
        ConstructionSite site = new ConstructionSite( 3 );
        site.addSandBox(1, 2, 2);
        site.addSandBox(1, 3, 1);
        site.addSandBox(2, 2, 4);
        site.addSandBox(10, 1, 1);  // will not get added because no room

        System.out.println("First box...");
        SandBox input = new SandBox(4, 1, 2);
        double[] expectedVols = {4,3,16};
        double[] expectedSandVolumes = {4,3,1};
        testCase(site, input, expectedVols, expectedSandVolumes, 8, 0);
        System.out.println("...First test passed\n");

        System.out.println("Second box...");
        SandBox input2 = new SandBox(8, 8, 1.125);
        double[] expectedVols2 = {4,3,16};
        double[] expectedSandVolumes2 = {4,3,16};
        testCase(site, input2, expectedVols2, expectedSandVolumes2, 72, 57);
        System.out.println("...Second test passed\n");

        System.out.println("Third box...");
        site.deleteSandBox(); // delete 3rd Cone
        site.deleteSandBox(); // delete 2nd Cone
        site.addSandBox(9, 2, 1.5);
        SandBox input3 = new SandBox(2, 1, 1);
        double[] expectedVols3 = {4,27};
        double[] expectedSandVolumes3 = {4,2};
        testCase(site, input3, expectedVols3, expectedSandVolumes3, 2, 0);
        System.out.println("...Third test passed\n");
        
        System.out.println("Fourth box...");
        site.addSandBox(3.5, 4, 2);
        SandBox input4 = new SandBox(8, 8, 8);
        double[] expectedVols4 = {4, 27, 28};
        double[] expectedSandVols4 = {4, 27, 28};
        testCase(site, input4, expectedVols4, expectedSandVols4, 512, 459);
        System.out.println("...Fourth test passed\n");

		System.out.println("Poking holes in the SandBoxes!");
		site.addSandBox(10, 10, 10); // yes, this SandBox starts empty
		double[] holes = {1, 0.5, 4.5};
		site.runDrainSimulation(2, 5, holes);
        
                System.out.println("Poking more holes in new SandBoxes!");
		ConstructionSite x = new ConstructionSite(4);
                x.addSandBox(2, 4, 6);
                x.addSandBox(1, 2, 90);
                x.boxes[0].fillToTop();
                x.boxes[1].fillToTop();
                double[] holes1 = {2, 20};
                x.runDrainSimulation(1, 3, holes1);
		
    }

}
