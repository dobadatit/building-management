package paging;

import sort.Sorter;

public class PageRequest implements Pagable {
	private Integer page;
	private Integer limit;


	

	public PageRequest(Integer page, Integer limit) {
		super();
		this.page = page;
		this.limit = limit;
	}

	@Override
	public Integer getPage() {
		return this.page;
	}

	@Override
	public Integer getOffset() {
		if (this.page != null && this.limit != null) {
			return (this.page - 1) * this.limit;
		}
		return null;
	}

	@Override
	public Integer getLimit() {
		return this.limit;
	}
	
}
