package fr.candidat.tondeuse.model;

public enum Direction {
//Orientation Nord
N,
//Orientation Est
E,
//Orientation West
W,
//Orientation Sud
S;
	
    public static Direction byName(String name) {
        switch (name) {
          case "N":
            return N;
          case "E":
            return E;
          case "W":
            return W;
          case "S":
            return S;
          default:
            throw new IllegalArgumentException(
              "No enum constant " + Direction.class.getCanonicalName() + "." + name);
        }
      }
}
