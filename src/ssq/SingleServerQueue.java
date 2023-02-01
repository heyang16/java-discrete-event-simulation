package ssq;

import java.util.Random;
import simulation.Simulation;

public class SingleServerQueue extends Simulation<SingleServerQueue> {

  private final Random generator;
  private final double duration;
  private final int serviceLimit;
  private int queueLength;
  private double cumulativeQueueLength;
  private double queueLengthRecordedUntil;
  private int totalServices;
  public final static double SERVICE_TIME = 0.25;

  public SingleServerQueue(long seed, double duration, int serviceLimit) {
    super();
    this.generator = new Random(seed);
    this.duration = duration;
    this.serviceLimit = serviceLimit;
    this.queueLength = 0;
    this.cumulativeQueueLength = 0;
    this.queueLengthRecordedUntil = 0;
    this.totalServices = 0;
    this.schedule(new Arrival(), getNextArrival());
  }

  @Override
  protected boolean stop() {
    return diary.peek().getTime() > duration || totalServices > serviceLimit;
  }

  @Override
  public SingleServerQueue getState() {
    return this;
  }

  @Override
  public void simulate() {
    super.simulate();
    if (totalServices <= serviceLimit) {
      currentTime = duration;
      updateCumulativeQueueLength();
    }
    System.out.println("SIMULATION COMPLETE - the mean queue length was " + getMeanQueueLength());
  }

  public double getNextArrival() {
    return generator.nextDouble();
  }

  /**
   * Called during an arrival. Updates the cumulative queue length and increases the queue length
   * by 1.
   */
  public void incrementQueue() {
    updateCumulativeQueueLength();
    queueLength += 1;
  }

  /**
   * Called during a departure. Updates the cumulative queue length and decreases the queue
   * length by 1.
   */
  public void decrementQueue() {
    updateCumulativeQueueLength();
    queueLength -= 1;
  }

  public void updateCumulativeQueueLength() {
    cumulativeQueueLength += (currentTime - queueLengthRecordedUntil) * queueLength;
    queueLengthRecordedUntil = currentTime;
  }

  public void incrementTotalService() {
    totalServices += 1;
  }

  public int getQueueLength() {
    return queueLength;
  }

  public double getMeanQueueLength() {
    cumulativeQueueLength += (duration - currentTime) * queueLength;
    return cumulativeQueueLength / currentTime;
  }

  /**
   *
   * @param args first argument is the seed, second is the duration
   */
  public static void main(String[] args) {
    long seed = Long.parseLong(args[0]);
    double duration = Double.parseDouble(args[1]);
    SingleServerQueue s = new SingleServerQueue(seed, duration, Integer.MAX_VALUE);
    s.simulate();
  }

  public static void mainExtension(String[] args) {
    long seed = Long.parseLong(args[0]);
    int serviceLimit = Integer.parseInt(args[1]);
    SingleServerQueue s = new SingleServerQueue(seed, Double.MAX_VALUE, serviceLimit);
    s.simulate();
  }
}
