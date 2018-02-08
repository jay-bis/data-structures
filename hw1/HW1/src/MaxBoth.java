public class MaxBoth {
		
		public static void main(String[] args) {
				int[] a1 = {2,4,6,8,10,12,14};
				int[] b1 = {7,7,5,4};

				// checks both orders of arguments since order doesn't matter
				// || means "or"
				if (max_both(a1,b1) != 4 || max_both(b1,a1) != 4) {
						System.out.println("FAILED TEST 1");
						System.exit(1);
				}
				
				int[] a2 = {2,4,6,8};
				int[] b2 = {3,5,7};
				if (max_both(a2,b2) != -1 || max_both(b2,a2) != -1) {
						System.out.println("FAILED TEST 2");
						System.exit(1);
				}
				
				int[] a3 = {7,6,22,4,4};
				int[] b3 = {4,5,5,25,6};
				if (max_both(a3,b3) != 6 || max_both(b3,a3) != 6) {
						System.out.println("FAILED TEST 3");
						System.exit(1);
				}
				
				int[] a4 = {10,20,30,40,50,60,70};
				int[] b4 = {50,40,70,60,10};
				if (max_both(a4,b4) != 70 || max_both(b4,a4) != 70) {
						System.out.println("FAILED TEST 4");
						System.exit(1);
				}

				int[] a5 = {5, 5, 5, 5, 5, 6};
                                int[] b5 = {5, 5, 5, 5, 5, 6};
                                if (max_both(a5, b5) != 6 || max_both(b5, a5) != 6) {
                                    System.out.println("FAILED TEST 5");
                                    System.exit(1);
                                }
				
				
                                
                                int[] a6 = {20};
                                int[] b6 = {3, 8, 2, 1, 3, 7, 100, 20};
                                if (max_both(a6, b6) != 20 || max_both(b6, a6) != 20) {
                                    System.out.println("FAILED TEST 6");
                                    System.exit(1);
                                }
                                
                                
                                System.out.println("Tests passed"); 
		}
		
		public static int max_both(int[] x, int[] y) {	
                    int bothMax = 0;    
                    for (int i = 0; i < x.length; i++) {
                        int temp = x[i];
                        for (int j = 0; j < y.length; j++) {
                            if (y[j] == temp && temp > bothMax) {
                                bothMax = temp;
                            }
                        }   
                    }
                    if (bothMax > 0) 
                        return bothMax;
                    else 
                        return -1;
		}
}
