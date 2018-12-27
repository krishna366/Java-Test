import java.util.List;

// TODO: complete this object/class

public class PaginationHelper<I> {

  /**
   * The constructor takes in an array of items and a integer indicating how many
   * items fit within a single page
   */
  private List<I> collection;
  private int itemsPerPage;
  
  public PaginationHelper(List<I> collection, int itemsPerPage) {
     this.collection = collection;
     this.itemsPerPage = itemsPerPage;
  }
  
  /**
   * returns the number of items within the entire collection
   */
  public int itemCount() {
    
      return collection.size();
  
  }
  
  /**
   * returns the number of pages
   */
  public int pageCount() {
      return collection.size() % itemsPerPage == 0 ? collection.size() / itemsPerPage : (collection.size() / itemsPerPage) + 1;
  }
  
  /**
   * returns the number of items on the current page. page_index is zero based.
   * this method should return -1 for pageIndex values that are out of range
   */
  public int pageItemCount(int pageIndex) {
    int pageCount1 = pageCount();
    if(0<=pageIndex && pageIndex < pageCount1 - 1)
        return itemsPerPage;
    else if(pageIndex == pageCount1 -1)
          return collection.size() - (pageCount1 * itemsPerPage) == 0 ? itemsPerPage : collection.size() - ((pageCount1-1) * itemsPerPage);
         else return -1;
  }
  
  /**
   * determines what page an item is on. Zero based indexes
   * this method should return -1 for itemIndex values that are out of range
   */
  public int pageIndex(int itemIndex) {
    if(0<=itemIndex && itemIndex < collection.size()){
        int index = itemIndex + 1;
        return index % itemsPerPage == 0 ? index / itemsPerPage - 1 : index / itemsPerPage ;
    }
    else return -1;
  }
}
