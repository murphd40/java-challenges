package challenges;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KnapsackSolver {

  public static List<Item> simpleRecursive(List<Item> items, int capacity) {

    /**
     * optimal solution for N items is the better of:
     * - the optimal solution of remaining N-1 items given that item N has been selected
     * - the optimal solution of remaining N-1 items given that item N has not been selected
     */

    // base condition

    if (items.isEmpty()) {
      return items;
    }

    //

    Item item = items.get(items.size() - 1);

    List<Item> subList = items.subList(0, items.size() - 1);

    List<Item> itemNotSelected = simpleRecursive(subList, capacity);

    // is it possible to select the item?
    if (item.getWeight() > capacity) {
      return itemNotSelected;
    }

    Integer value1 = itemNotSelected.stream().map(Item::getValue).reduce(0, Integer::sum);

    List<Item> itemSelected = new ArrayList<>(simpleRecursive(subList, capacity - item.getWeight()));
    itemSelected.add(item);
    Integer value2 = itemSelected.stream().map(Item::getValue).reduce(0, Integer::sum);

    return value1 >= value2 ? itemNotSelected : itemSelected;

  }

  public static List<Item> memoizedRecursive(List<Item> items, int capacity) {
    return memoizedRecursiveInternal(items, capacity, new Memo(items.size(), capacity));
  }

  private static class Memo {

    private final Object[][] mem;

    public Memo(int size, int capacity) {
      mem = new Object[size + 1][capacity + 1];
    }

    public boolean check(List<Item> items, int capacity) {
      return mem[items.size()][capacity] != null;
    }

    public void saveResult(List<Item> items, int capacity, List<Item> result) {
      mem[items.size()][capacity] = result;
    }

    public List<Item> retrieveResult(List<Item> items, int capacity) {
      return (List<Item>) mem[items.size()][capacity];
    }


  }
  private static List<Item> memoizedRecursiveInternal(List<Item> items, int capacity, Memo memo) {
    // base condition

    if (items.isEmpty()) {
      return items;
    }

    if (memo.check(items, capacity)) {
      return memo.retrieveResult(items, capacity);
    }

    //

    Item item = items.get(items.size() - 1);

    List<Item> subList = items.subList(0, items.size() - 1);

    List<Item> itemNotSelected = memoizedRecursiveInternal(subList, capacity, memo);

    // is it possible to select the item?
    if (item.getWeight() > capacity) {
      return itemNotSelected;
    }

    Integer value1 = itemNotSelected.stream().map(Item::getValue).reduce(0, Integer::sum);

    List<Item> itemSelected = new ArrayList<>(memoizedRecursiveInternal(subList, capacity - item.getWeight(), memo));
    itemSelected.add(item);
    Integer value2 = itemSelected.stream().map(Item::getValue).reduce(0, Integer::sum);

    List<Item> result = value1 >= value2 ? itemNotSelected : itemSelected;
    memo.saveResult(items, capacity, result);
    return result;
  }

  public static List<Item> bottomUp(List<Item> items, int capacity) {
    if (items.isEmpty() || capacity == 0) {
      return Collections.emptyList();
    }

    Memo memo = new Memo(items.size(), capacity);

    return null;
  }

}
