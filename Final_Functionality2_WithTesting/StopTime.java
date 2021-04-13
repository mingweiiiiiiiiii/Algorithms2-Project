/*************************************************************************
 *  StopTime class (for CSU22012 Project).
 *
 *  @version 1.0 12/4/21
 *
 *  @author Brian Whelan
 *
 *************************************************************************/
public class StopTime implements Comparable<StopTime>
{
	public final Integer tripID;
	public final Time arrivalTime;
	public final Time departureTime;
	public final Integer stopID;
	public final Integer stopSequence;
	public final Integer stopHeadsign;
	public final Integer pickupType;
	public final Integer dropOffType;
	public final Double shapeDistanceTravelled;
	
	/**
     * Create a StopTime object with the specified properties
     * @param stopTimeLine: String containing comma-separated information
     */
	public StopTime(String stopTimeLine)
	{
		String[] values = stopTimeLine.split(",");
		this.tripID = (values[0].equals("") ? null : Integer.parseInt(values[0]));
		this.arrivalTime = new Time(values[1]);
		this.departureTime = new Time(values[2]);
		this.stopID = (values[3].equals("") ? null : Integer.parseInt(values[3]));
		this.stopSequence = (values[4].equals("") ? null : Integer.parseInt(values[4]));
		this.stopHeadsign = (values[5].equals("") ? null : Integer.parseInt(values[5]));
		this.pickupType = (values[6].equals("") ? null : Integer.parseInt(values[6]));
		this.dropOffType = (values[7].equals("") ? null : Integer.parseInt(values[7]));
		this.shapeDistanceTravelled = (values.length < 9 ? null : Double.parseDouble(values[8]));
	}
	
	/**
     * Compare two StopTime objects based on their tripID
     * 
     * @param stopTime: stopTime to compare to
     * @return integer representing result of comparison
     */	
	@Override
    public int compareTo(StopTime stopTime)
    {
        return Integer.valueOf(this.tripID).compareTo(Integer.valueOf(stopTime.tripID));
    }
	
    /**
     * Get String representation of StopTime
     * 
     * @return String containing information on StopTime entry
     */
	@Override
    public String toString()
    {
        return ("Trip ID: " + tripID
        		+ "\nArrival Time: " + arrivalTime
        		+ "\nDeparture Time: " + departureTime
        		+ "\nStop ID: " + stopID
        		+ "\nStop Sequence: " + stopSequence
        		+ "\nStop Headsign: " + stopHeadsign
        		+ "\nPickup Type: " + pickupType
        		+ "\nDrop Off Type: " + dropOffType
        		+ "\nShape Distance Travelled: " + shapeDistanceTravelled + "\n");
    }
}