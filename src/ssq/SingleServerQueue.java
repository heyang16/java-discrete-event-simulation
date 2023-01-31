package ssq;

import java.util.Random;
import simulation.Simulation;

public class SingleServerQueue extends Simulation<SingleServerQueue> {

  private final Random generator;
  private final double duration;
  private int queueLength;
  private double cumulativeQueueLength;
  private double queueLengthRecordedUntil;
  public final static double SERVICE_TIME = 0.25;

  public SingleServerQueue(long seed, double duration) {
    super();
    this.generator = new Random(seed);
    this.duration = duration;
    this.queueLength = 0;
    this.cumulativeQueueLength = 0;
    this.queueLengthRecordedUntil = 0;
    this.schedule(new Arrival(), getNextArrival());
  }

  @Override
  protected boolean stop() {
    return diary.peek().getTime() > duration;
  }

  @Override
  public SingleServerQueue getState() {
    return this;
  }

  public double getNextArrival() {
    return generator.nextDouble();
  }

  /**
   * Called during an arrival. Updates the cumulative queue length and increases the queue length
   * by 1.
   */
  public void incrementQueue() {
    cumulativeQueueLength += (currentTime - queueLengthRecordedUntil) * queueLength;
    queueLength += 1;
    queueLengthRecordedUntil = currentTime;
  }

  /**
   * Called during a departure. Updates the cumulative queue length and decreases the queue
   * length by 1.
   */
  public void decrementQueue() {
    cumulativeQueueLength += (currentTime - queueLengthRecordedUntil) * queueLength;
    queueLength -= 1;
    queueLengthRecordedUntil = currentTime;
  }

  public int getQueueLength() {
    return queueLength;
  }

  public double getMeanQueueLength() {
    cumulativeQueueLength += (duration - getCurrentTime()) * queueLength;
    return cumulativeQueueLength / duration;
  }

  /**
   *
   * @param args first argument is the seed, second is the duration
   */
  public static void main(String[] args) {
    long seed = Long.parseLong(args[0]);
    double duration = Double.parseDouble(args[1]);
    SingleServerQueue s = new SingleServerQueue(seed, duration);
    s.simulate();
    System.out.println("SIMULATION COMPLETE - the mean queue length was " + s.getMeanQueueLength());
  }
}
