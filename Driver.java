public class Driver {
	public static void main(String[] args) {
		String time;
		System.out.println("SOLVE: (from (0, 0))\n");
		for (int r = 1; r < 100; r++) {
			KnightBoard b = new KnightBoard(r, r);
			long startTime = System.nanoTime();
			try {
				if (r == 45 || r == 47 || r == 50 || r == 54)
					time = "skipped";
				else if (b.solve(0, 0))
					time = Math.round((System.nanoTime()-startTime)/10000.)/100.+"ms";
				else
					time = "unsolvable";
				System.out.printf("\t%dx%d:\t%s%n", r, r, time);
			} catch (StackOverflowError e) {
				System.out.printf("\t%dx%d:\tstack overflow%n", r, r);
				break;
			}
		}
		System.out.println("\nCOUNT SOLUTIONS: (from (0, 0))\n");
		for (int r = 1; r < 6; r++) {
			for (int c = r; c < 6; c++) {
				KnightBoard b = new KnightBoard(r, c);
				long startTime = System.nanoTime();
				try {
					int n = b.countSolutions(0, 0);
					time = Math.round((System.nanoTime()-startTime)/10000.)/100.+"ms";
					System.out.printf("\t%dx%d:\t%d solutions\t\t%s%n", r, c, n, time);
				} catch (StackOverflowError e) {
					System.out.printf("\t%dx%d:\tstack overflow%n", r, c);
					break;
				}
			}
		}
	}
}
