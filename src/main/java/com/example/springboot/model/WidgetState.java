package com.example.springboot.model;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

public class WidgetState {
    private final AtomicInteger widgetId = new AtomicInteger();
	private int maxZIndex = 0;
	private Map<Integer, Widget> widgetHashMap = new HashMap<Integer, Widget>();
    private TreeMap<Integer, Widget> widgetTree = new TreeMap<Integer,Widget>();

    public int getMaxZIndex() {
        return maxZIndex;
    }

    public TreeMap<Integer, Widget> getWidgetTree() {
        return widgetTree;
    }

    public AtomicInteger getWidgetId() {
        return widgetId;
    }

    public Map<Integer, Widget> getWidgetHashMap() {
        return widgetHashMap;
    }

    public void addNewWidget() {

    }

    public void updateState() {

    }

    public void removeWidgetById(int id) {
        Widget deletedWidget = this.widgetHashMap.get(id);
        this.widgetTree.remove(deletedWidget.getZIndex());
        if (deletedWidget.getZIndex() == this.maxZIndex) {
            this.maxZIndex = this.widgetTree.lastKey();
        }
        deletedWidget = null;
        this.widgetHashMap.remove(id);
    }
}
