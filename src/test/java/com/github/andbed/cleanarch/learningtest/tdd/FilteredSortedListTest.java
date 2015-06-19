package com.github.andbed.cleanarch.learningtest.tdd;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.assertj.core.api.Condition;
import org.junit.Ignore;
import org.junit.Test;

public class FilteredSortedListTest {

	@Test
	@Ignore
	public void shouldFilterActiveOnly() {
		MyRepo repo = new MyRepo();

		List<Item> items = repo.findItems();

		assertThat(items).are(activeOnly());
	}

	@Test
	@Ignore
	public void shouldSortList() {
		MyRepo repo = new MyRepo();

		List<Item> items = repo.findItems();

		assertThat(items).isSorted();
	}

	private Condition<Item> activeOnly() {
		return new Condition<FilteredSortedListTest.Item>() {
			@Override
			public boolean matches(Item item) {
				return item.isActive();
			}
		};
	}

	public class MyRepo {

		public List<Item> findItems() {
			List<Item> items = newArrayList(new Item(), new Item());
			List<Item> filteredItems = filter(items);
			return sort(filteredItems);
		}

		public List<Item> findItemsNew() {
			List<Item> items = newArrayList(new Item(), new Item());
			return items.stream()
					.filter(Item::isActive)
					.sorted()
					.collect(Collectors.toList());
		}

		private List<Item> sort(List<Item> items) {
			List<Item> itemsSorted = newArrayList(items);
			itemsSorted.sort(null);
			return itemsSorted;
		}

		private List<Item> filter(List<Item> items) {

			List<Item> filtered = new ArrayList<>();

			for (Item item : items) {
				if (item.isActive()) {
					filtered.add(item);
				}
			}
			return filtered;
		}
	}

	public class Item {

		public boolean isActive() {
			return true;
		}

	}

}
