package com.example.springboot.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

import com.example.springboot.exception.WidgetNotFoundException;
import com.example.springboot.model.Widget;


public class WidgetInMemoryStorage implements WidgetStorageInterface
{
	private final AtomicInteger widgetId = new AtomicInteger();
	private int maxZIndex = 0;
	private Map<Integer, Widget> widgetHashMap = new HashMap<Integer, Widget>();
	private TreeMap<Integer, Widget> widgetTree = new TreeMap<Integer,Widget>();

	@Override
	public Collection<Widget> getWidgetList() {
		return this.widgetTree.values();
	}

	@Override
	public void removeWidgetById(int id) throws WidgetNotFoundException {
		synchronized (this) {
			Widget deletedWidget = this.widgetHashMap.get(id);
			if (deletedWidget == null) {
				throw new WidgetNotFoundException();
			}
			this.widgetTree.remove(deletedWidget.getZIndex());
			if (this.widgetTree.size() >= 1 && deletedWidget.getZIndex() == this.maxZIndex) {
				this.maxZIndex = this.widgetTree.lastKey();
			}
			else {
				this.maxZIndex = 0;
			}
			deletedWidget = null;
			this.widgetHashMap.remove(id);
		}
	}

	@Override
	public Widget findWidgetById(int id) {
		return this.widgetHashMap.get(id);
	}
	
	@Override
	public Widget updateWidget(Widget widgetDto, Widget actualWidget) throws WidgetNotFoundException {
		synchronized (this) {
			this.widgetTree.remove(actualWidget.getZIndex());

			boolean isZIndexDuplicate = this.widgetTree.containsKey(widgetDto.getZIndex());
			if (isZIndexDuplicate) {
				this.updateTreeOnUpdate(actualWidget.getZIndex(), widgetDto.getZIndex());
			}

			actualWidget.updateData(widgetDto);
			this.widgetTree.put(actualWidget.getZIndex(), actualWidget);

			return actualWidget;
		}
	}

	@Override
	public Widget addNewWidget(Widget newWidgetDto) {
		synchronized (this) {
			Widget newWidget = this.createWidgetFromDto(newWidgetDto);
			boolean isZIndexDuplicate = this.widgetTree.containsKey(newWidget.getZIndex());
			
			if (isZIndexDuplicate) {
				this.shiftTreeToRight(newWidget.getZIndex());
			}
			
			this.widgetTree.put(newWidget.getZIndex(), newWidget);
			this.widgetHashMap.put(newWidget.getId(), newWidget);

			return newWidget;
		}
	}
	
	// пам пам #ForTestingPurpose
	public TreeMap<Integer, Widget> getWidgetTree() {
		return this.widgetTree;
	}

	private int calculateZIndex(int insertedZindex) {
		int zIndex = insertedZindex;
		if (this.maxZIndex > 0 && zIndex == 0) {
			this.maxZIndex++;
			zIndex = this.maxZIndex;
		} else if(zIndex > this.maxZIndex) {
			this.maxZIndex = zIndex;
		}
		return zIndex;
	}

	private Widget createWidgetFromDto(Widget newWidgetDto) {
		int zIndex = this.calculateZIndex(newWidgetDto.getZIndex());
		return new Widget(widgetId.getAndIncrement(), zIndex, newWidgetDto);
	}

	private void updateTreeOnUpdate(int oldIndex, int newIndex) {	
		if (oldIndex > newIndex) {
			this.shiftTreeValues(oldIndex, newIndex);	
		}
		else {
			this.shiftTreeToRight(newIndex, oldIndex + 1);
		}
	}

	/*
	 * фига тут можно штуки делать, я и отвык от перегрузки методов
	*/
	private void shiftTreeToRight(int index) {
		this.shiftTreeToRight(index, index);
	}

	private void shiftTreeToRight(int index, int oldIndex) {
		int insertIndex = index;
		for (Entry<Integer, Widget> entry : this.widgetTree.entrySet()) {
			insertIndex = entry.getKey() > insertIndex + 1 ? entry.getKey() : insertIndex;
		}

		if (insertIndex <= this.maxZIndex) {
			insertIndex = this.maxZIndex + 1;
		}
		
		this.shiftTreeValues(insertIndex, oldIndex);
	}

	/*
	 * А тут принято аннотации делать?
	 * Метод сдвига значений в дереве. Двигает сугубо по возрастающей (т.к. обратно двигать нет особого резона)
	*/
	private void shiftTreeValues(int shiftLastIndex, int shiftFirstIndex) {
		widgetTree.put(shiftLastIndex, new Widget(0, 0, 0, 0, 0, shiftLastIndex));
		for (int counter = shiftLastIndex; counter > shiftFirstIndex; counter--) {
			Widget movedWidget = this.widgetTree.get(counter - 1);
			movedWidget.incrementZIndex();
			this.widgetTree.replace(counter, movedWidget);
		}
		this.maxZIndex++;
	}
}
