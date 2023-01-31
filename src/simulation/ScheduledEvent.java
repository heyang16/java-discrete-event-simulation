package simulation;

public class ScheduledEvent<S> implements Comparable<ScheduledEvent<S>> {

  private final Event<S> event;
  private final double time;

  public ScheduledEvent(Event<S> e, double time) {
    this.event = e;
    this.time = time;
  }

  @Override
  public int compareTo(ScheduledEvent scheduledEvent) {
    return Double.compare(time, scheduledEvent.getTime());
  }

  public double getTime() {
    return time;
  }

  public Event<S> getEvent() {
    return event;
  }
}
