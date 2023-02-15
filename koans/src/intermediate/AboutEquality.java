package intermediate;

import com.sandwich.koan.Koan;


import static com.sandwich.util.Assert.assertEquals;

public class AboutEquality {
    // This suite of Koans expands on the concepts introduced in beginner.AboutEquality

    @Koan
    public void sameObject() {
        Object a = new Object();
        Object b = a;
        assertEquals(a == b, true);
    }

    @Koan
    public void equalObject() {
        Integer a = new Integer(1);
        Integer b = new Integer(1);
        assertEquals(a.equals(b), true);
        assertEquals(b.equals(a), true);
    }

    @Koan
    public void noObjectShouldBeEqualToNull() {
        assertEquals(new Object().equals(null), false);
    }

    static class Car {
        private String name = "";
        private int horsepower = 0;

        public Car(String s, int p) {
            name = s;
            horsepower = p;
        }

        @Override
        public boolean equals(Object other) {
            // Change this implementation to match the equals contract
            // Car objects with same horsepower and name values should be considered equal
            // http://download.oracle.com/javase/6/docs/api/java/lang/Object.html#equals(java.lang.Object)
            if (!(other instanceof Car))
                return false;
            return ((Car) other).horsepower == this.horsepower
                && ((Car) other).name.equals(this.name);
        }

        @Override
        public int hashCode() {
            // @see http://download.oracle.com/javase/6/docs/api/java/lang/Object.html#hashCode()
            return horsepower + ((name == null) ? 0 : name.hashCode());
        }
    }

    @Koan
    public void equalForOwnObjects() {
        Car car1 = new Car("Beetle", 50);
        Car car2 = new Car("Beetle", 50);
        // @see Car.equals (around line 45) for the place to solve this
        assertEquals(car1.equals(car2), true);
        assertEquals(car2.equals(car1), true);
    }

    @Koan
    public void unequalForOwnObjects() {
        Car car1 = new Car("Beetle", 50);
        Car car2 = new Car("Porsche", 300);
        // @see Car.equals (around line 45) for the place to solve this
        assertEquals(car1.equals(car2), false);
    }

    @Koan
    public void unequalForOwnObjectsWithDifferentType() {
        Car car1 = new Car("Beetle", 50);
        String s = "foo";
        // @see Car.equals (around line 45) for the place to solve this
        assertEquals(car1.equals(s), false);
    }

    @Koan
    public void equalNullForOwnObjects() {
        Car car1 = new Car("Beetle", 50);
        // @see Car.equals (around line 45) for the place to solve this
        assertEquals(car1.equals(null), false);
    }

    @Koan
    public void ownHashCode() {
        // As a general rule: When you override equals you should override
        // hash code
        // Read the hash code contract to figure out why
        // http://download.oracle.com/javase/6/docs/api/java/lang/Object.html#hashCode()

        // Implement Car.hashCode around line 51 so that the following assertions pass
        Car car1 = new Car("Beetle", 50);
        Car car2 = new Car("Beetle", 50);
        assertEquals(car1.equals(car2), true);
        assertEquals(car1.hashCode() == car2.hashCode(), true);
    }

    static class Chicken {
        String color = "green";

        @Override
        public int hashCode() {
            return color == null ? 0 : color.hashCode();
        }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Chicken))
                return false;
            return ((Chicken) other).color.equals(color);
        }
    }

    @Koan
    public void ownHashCodeImplementationPartTwo() {
        Chicken chicken1 = new Chicken();
        chicken1.color = "black";
        Chicken chicken2 = new Chicken();
        assertEquals(chicken1.equals(chicken2), false);
        assertEquals(chicken1.hashCode() == chicken2.hashCode(), false);
        // Does this still fit the hashCode contract? Why (not)?
        // Fix the Chicken class to correct this.
    }

}
