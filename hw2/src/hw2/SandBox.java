package hw2;

public class SandBox {
   private final double length;     // doesn't change after SandBox is constructed   
   private final double width;      // doesn't change after SandBox is constructed
   private final double height;     // doesn't change after SandBox is constructed   

   // current height of the sand within the box
   private double sandHeight;  
   
    /*
     * Constructor 
     * Initially there is no sand in this SandBox
     */
    public SandBox(double length, double width, double height){
        this.length = length; 
        this.width = width; 
        this.height = height;
    }
    
    // Constructor
    // Creates a SandBox with all dimensions 1
    // Initially there is no sand in this SandBox
    public SandBox(){
        height = 1; length = 1; width = 1;
    }
		
	// getLength: returns the length of this SandBox
    public double getLength() {
        return length;
    }

	// getWidth: returns the width of this SandBox
    public double getWidth() {
        return width;
    }

	// getHeight: returns the height of this SandBox
    public double getHeight() {
        return height;
    }

	// getVolume: returns the total volume of this SandBox
    public double getVolume() {
        return length*height*width;
    }

	// getSandVolume: returns the volume of sand in this SandBox
    public double getSandVolume() {
        return length*width*sandHeight;
    }
    
    // toString: returns a String describing this SandBox
    //   for example, for the SandBox with length=10, width=12, height=2, sandHeight=1
    //   the String will be "SandBox(volume=240, sandVolume=120)"
	@Override
    public String toString() {
        return "SandBox(volume=" + getVolume() +", sandVolume=" + getSandVolume() + ")"; 
    }

    // scoopSandFrom
    // Move sand from other into this SandBox.
    // If you would overflow this SandBox, *only* move the volume
    // of sand that would fill this SandBox to the top.
    public void scoopSandFrom(SandBox other){
		// Don't remove these lines. They help you find errors more quickly
		// when debugging.
		this.checkState();	
		other.checkState();
                double emptyVol = this.getVolume() - this.getSandVolume();
                if (emptyVol < other.getSandVolume()) {
                    other.sandHeight = (other.getSandVolume() - emptyVol)/(other.length*other.width);
                    this.sandHeight = height;
                } else {
                    this.sandHeight = other.getSandVolume()/(this.length*this.width);
                    other.sandHeight = 0;
                }
    }

	private void checkState() {
		if (this.sandHeight > this.height) { throw new IllegalArgumentException("SandBox overflowed!"); }
		if (this.sandHeight < 0) { throw new IllegalArgumentException("SandBox contains anti-sand!"); }
	}

    // completely fills this SandBox with sand
    public void fillToTop() {
        sandHeight = height;
    }
    
    // completely empties this SandBox
    public void empty() {
        sandHeight = 0;
    }
}
