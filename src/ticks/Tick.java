package ticks;

import simulation.Event;

public class Tick implements Event<Ticks> {

  @Override
  public void invoke(Ticks simulation) {
    System.out.println("Tick at: " + simulation.getCurrentTime());
  }
}
