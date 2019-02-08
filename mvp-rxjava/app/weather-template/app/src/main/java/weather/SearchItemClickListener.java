package <%= appPackage %>.weather;

import <%= appPackage %>.data.Search;

/**
 * Interface for delete and search item clicks
 */
public interface SearchItemClickListener {
    void delete(Search search);
    void search(Search search);
}
