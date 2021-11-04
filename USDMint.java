public class USDMint extends MintFactory {
    private volatile static USDMint uniqueMint;
    private Coin c;

    private USDMint(double denom, String cc) {
	rand.setSeed((int)denom * 17);
    } 
    //USDMint.getInstance() should be called 
    public static USDMint getInstance() {
	if (uniqueMint == null) {
	    synchronized (USDMint.class) {
		if (uniqueMint == null) {
		    // denomination and  countryCode will be set in the Demo class 
		    uniqueMint = new USDMint(denomination, countryCode); 
		}
	    }
	}
    }
    //To be called in Demo
    public Coin makeCoin(double denom, String cc) {
	switch(denom) {
	    case 0.01:
		c = new USDPenny(0.01, cc, new CompP());
		break;
	    case 0.05:
		c = new USDNickel(0.05, cc, new CompNFFTF());
		break;
	    case 0.1:
		c = new USDDime(0.1, cc, new CompHQD());
		break;
	    case 0.25:
		c = new USDQuarter(0.25, cc, new CompHQD());
		break;
	    case 0.5:
		c = new USDHalfdollar(0.5, cc, new CompHQD());
		break;
	    case 1:
		c = new USDDollar(1, cc, new CompD());
		break;

	}
	c.display();
	manafacture();
	if (!inspection()) return null;
	System.out.println("Passed inspection!");
	if(!smoothing()) return null;
	System.out.println("Passed smoothing!");
	if(!buffing()) return null;
	System.out.println("Passed buffing!");
	return c;
    }

    private void manafacture() {
	c.smelt();
    }
    private boolean inspection() {
	// 1/12 chance of failing inspection 
	return rand.nextInt(12) != 0
    }
    private  boolean smoothing() {
	// 1/1000 chance of failing smoothing
	return rand.nextInt(1000) != 0;	
    }
    private boolean buffing() {
	// 1/1000 chance of failing buffing
	return rand.nextInt(1000) != 0;
    } 
}
