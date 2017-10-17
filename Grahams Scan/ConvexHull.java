import java.util.Arrays;
public class ConvexHull {

    /*
     * This is used to check whether it makes a counter-clockwise move.
     */
	public static long cross(Point O, Point A, Point B) {
		return (A.x - O.x) * (B.y - O.y) - (A.y - O.y) * (B.x - O.x);
	}

	/*
	 * This function takes in the coordinates and calculates the convex hull.
	 */
	public static Point[] calculateCH(Point[] P){
		if(P.length > 1){ //If there is more than one coordinate.
			int n = P.length, k = 0;
			Point[] H = new Point[2 * n];

			Arrays.sort(P);

			// Build lower hull (Start at rightmost point)
			//Go through each of the coordinates from right to left.
			//If at any given point it does not make a counter-clockwise turn...
			//Remove the last point from the list.
			for(int i = 0; i < n; ++i) {
				while (k >= 2 && cross(H[k - 2], H[k - 1], P[i]) <= 0){
					k--;
				}
				H[k++] = P[i];
			}

			// Build upper hull (Start at leftmost point)
			//Go through each of the coordinates from left to right.
			//If at any given point it does not make a counter-clockwise turn...
			//Remove the last point from the list.
			for (int i = n - 2, t = k + 1; i >= 0; i--) {
				while(k >= t && cross(H[k - 2], H[k - 1], P[i]) <= 0){
					k--;
				}
				H[k++] = P[i];
			}
			
			if(k > 1){
				H = Arrays.copyOfRange(H, 0, k - 1); //Get rid of the points that do not belong to the convex hull.
			}
			return H;
		} 
		else if(P.length <= 1){ //If it only has one point
			return P;
		}
		else{ //No points
			return null;
		}
	}
}