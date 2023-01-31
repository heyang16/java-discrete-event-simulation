package ssq;

import simulation.Event;

public class Arrival implements Event<SingleServerQueue> {

  @Override
  public void invoke(SingleServerQueue simulation) {
    simulation.incrementQueue();
    System.out.println("Arrival at " + simulation.getCurrentTime() + " new "
        + "population = " + simulation.getQueueLength());
    // Schedule the next arrival after a random amount of time
    simulation.schedule(new Arrival(), simulation.getNextArrival());
    // If queue is empty, schedule the next departure
    if (simulation.getQueueLength() == 1) {
      simulation.schedule(new Departure(), SingleServerQueue.SERVICE_TIME);
    }
  }
}
