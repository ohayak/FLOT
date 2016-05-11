package vrp;

public class MainVRP {
	
	public static void main(String args[]) {
		VRPinstance vp = new VRPinstance();
		vp.parser("hk");
		ClarkeWright cw = new ClarkeWright(vp.getMatrix(), vp.getDemands(), vp.getCapacity(), vp.getN());
		cw.computeSolution(vp);
	}
}
