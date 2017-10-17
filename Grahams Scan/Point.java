class Point implements Comparable<Point>{
    int x, y;

    /*
     * This is used to shift/transform all the points.
     */
    public int compareTo(Point p){
        if (this.x == p.x) {
            return this.y - p.y;
        } else {
            return this.x - p.x;
        }
    }
    
    /*
     * Print the coordinates after the convex hull.
     */
    public String toString(){
        return "("+x + "," + y+")";
    }

}