package paging;

import sort.Sorter;

public interface Pagable {
	Integer getPage();

	Integer getOffset();

	Integer getLimit();


}
