package cs5004.animator.model;

import java.util.Comparator;

/**
 * Comparator to sort by the start time.
 */
public class ChangeComparator implements Comparator<AbstractChange> {

  /**
   * Method to sort by the start time for changes.
   * @param a the first change being compared.
   * @param b the second change being compared.
   * @return positive is a is greater than b, negative otherwise.
   */
  public int compare(AbstractChange a, AbstractChange b) {
    if (a.getStartTime() > b.getStartTime()) {
      return 1;
    } else if (a.getStartTime() == b.getStartTime()) {
      return a.toString().compareTo(b.toString());
    } else {
      return -1;
    }
  }
}
