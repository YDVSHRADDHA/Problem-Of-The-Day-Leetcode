class Solution {
    public double angleClock(int hour, int minutes) {
     
    //    calculate the angle made by the hour hand with respect to 12:00
       double hourAngle =  60 * hour + minutes;

    // calculate the angle made by the minute hand with respect to 12:00
    double minuteAngle = 12*minutes;

    // Find the absolute difference between the two angles
    double angDiff = Math.abs(hourAngle-minuteAngle);

    // Return the smaller angle
   return (int) Math.min(angDiff, 720-angDiff)/2.0;   
    }
}