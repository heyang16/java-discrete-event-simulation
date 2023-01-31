package ticks;

import simulation.Simulation;

public class Ticks extends Simulation<Ticks> {

  @Override
  public Ticks getState() {
    return this;
  }

  public static void main(String[] args) {
    double duration = Double.parseDouble(args[0]);
    Ticks t = new Ticks();
    for (double i = 1; i < duration; i++) {
      t.schedule(new Tick(), i);
    }
    t.simulate();
  }
}
