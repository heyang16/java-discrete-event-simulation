package simulation;

import java.util.PriorityQueue;

public class Simulation<S> {

  protected double currentTime;
  protected PriorityQueue<ScheduledEvent<S>> diary;

  public Simulation() {
    this.currentTime = 0;
    this.diary = new PriorityQueue<>();
  }

  /**
   * This method should be overwritten within subclasses to describe the stopping condition if
   * needed.
   * @return Returns true if the stopping condition has been reached
   */
  protected boolean stop() {
    return false;
  }

  /**
   * Adds a scheduled event to the diary.
   * @param e The event to be scheduled
   * @param offset How far ahead of the current virtual time the event is scheduled to run
   */
  public void schedule(Event<S> e, double offset) {
    diary.add(new ScheduledEvent<>(e, currentTime + offset));
  }

  public void simulate() {
    while (!(diary.isEmpty() || stop())) {
      // Gets the next event
      ScheduledEvent<S> currentEvent = diary.poll();
      // Updates the current virtual time
      currentTime = currentEvent.getTime();
      // Invokes the event
      currentEvent.getEvent().invoke(getState());
    }
  }

  /**
   * Returns the state of the simulation
   * @return Subclasses should override this to return itself.
   */
  public S getState() {
    return null;
  }

  public double getCurrentTime() {
    return currentTime;
  }
}
