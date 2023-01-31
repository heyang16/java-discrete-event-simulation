package ssq;

import java.util.Random;
import simulation.Simulation;

public class SingleServerQueue extends Simulation<SingleServerQueue> {

  private final Random generator;
  private final double duration;
  private int queueLength;
  private double totalQueueTime;
  private int totalCustomerAmount;
  public final static double SERVICE_TIME = 0.25;

  public SingleServerQueue(long seed, double duration) {
    super();
    this.generator = new Random(seed);
    this.duration = duration;
    this.queueLength = 0;
    this.totalQueueTime = 0;
    this.totalCustomerAmount = 0;
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

  public void incrementQueue() {
    queueLength += 1;
  }

  public void decrementQueue() {
    queueLength -= 1;
  }

  public int getQueueLength() {
    return queueLength;
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
    System.out.println("SIMULATION COMPLETE");
  }
}
