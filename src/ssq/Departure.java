package ssq;

import simulation.Event;

public class Departure implements Event<SingleServerQueue> {

  @Override
  public void invoke(SingleServerQueue simulation) {
    simulation.decrementQueue();
    simulation.incrementTotalService();
    System.out.println("Departure at " + simulation.getCurrentTime() + " new population = "
        + simulation.getQueueLength());
    if (simulation.getQueueLength() > 0) {
      simulation.schedule(new Departure(), SingleServerQueue.SERVICE_TIME);
    }
  }
}
