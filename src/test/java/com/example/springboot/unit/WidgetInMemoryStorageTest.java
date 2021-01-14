package com.example.springboot.unit;

import static org.junit.Assert.assertArrayEquals;
import java.util.ArrayList;
import java.util.List;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import com.example.springboot.model.Widget;
import com.example.springboot.repository.WidgetInMemoryStorage;

import org.junit.Test;
import org.junit.runner.RunWith;

import junit.framework.TestCase;

@RunWith(DataProviderRunner.class)
public class WidgetInMemoryStorageTest extends TestCase 
{
    WidgetInMemoryStorage storage;

    @Test
    @UseDataProvider("createWidgetDataProvider")
    public void testCreateWidgetInsert(Widget widgetInsert, List<Widget> widgetList, Integer[] expectedOrder)
    {
        this.storage = new WidgetInMemoryStorage();
        for (int i = 0; i < widgetList.size(); i++) {
            storage.saveWidget(widgetList.get(i));
        }

        storage.saveWidget(widgetInsert);
        assertEquals(expectedOrder.length, storage.getWidgetTree().size());
        assertArrayEquals(storage.getWidgetTree().keySet().toArray(new Integer[0]), expectedOrder);
    }

    @Test
    @UseDataProvider("updateWidgetDataProvider")
    public void testUpdateWidgetInsert(Widget updatedWidget, List<Widget> widgetList, Integer[] expectedOrder)
    {
        this.storage = new WidgetInMemoryStorage();
        for (int i = 0; i < widgetList.size(); i++) {
            storage.saveWidget(widgetList.get(i));
        }
        
        this.storage.updateWidget(updatedWidget, storage.findWidgetById(updatedWidget.getId()));
        assertEquals(expectedOrder.length, storage.getWidgetTree().size());
        assertArrayEquals(storage.getWidgetTree().keySet().toArray(new Integer[0]), expectedOrder);
    }


    @Test
    @UseDataProvider("getWidgetByIdDataProvider")
    public void testGetWidgetById(List<Widget> widgetList, Widget expectedWidget, int searchId)
    {
        this.storage = new WidgetInMemoryStorage();
        for (int i = 0; i < widgetList.size(); i++) {
            storage.saveWidget(widgetList.get(i));
        }
        
        Widget widget = this.storage.findWidgetById(searchId);
        assertEquals(expectedWidget.getHeigth(), widget.getHeigth());
        assertEquals(expectedWidget.getId(), widget.getId());
        assertEquals(expectedWidget.getId(), widget.getId());
        
        assertEquals(expectedWidget, widget);
    }


    @DataProvider
    public static Object[] createWidgetDataProvider() 
    {
        Integer[] expectedIndexOrder1 = {0,1,2,3,4,5};
        List<Widget> widgetList1 = new ArrayList<Widget>();
        widgetList1.add(getWidgetFor(0, 0));
        widgetList1.add(getWidgetFor(1, 1));
        widgetList1.add(getWidgetFor(2, 2));
        widgetList1.add(getWidgetFor(3, 3));
        widgetList1.add(getWidgetFor(4, 4));
        Widget widgetInsert1 = WidgetInMemoryStorageTest.getWidgetFor(100, 2);

        Integer[] expectedIndexOrder2 = {1,2,3,4};
        List<Widget> widgetList2 = new ArrayList<Widget>();
        widgetList2.add(getWidgetFor(1, 1));
        widgetList2.add(getWidgetFor(2, 2));
        widgetList2.add(getWidgetFor(4, 4));
        Widget widgetInsert2 = WidgetInMemoryStorageTest.getWidgetFor(100, 3);

        Integer[] expectedIndexOrder3 = {0,1,2,3};
        List<Widget> widgetList3 = new ArrayList<Widget>();
        widgetList3.add(getWidgetFor(0, 0));
        widgetList3.add(getWidgetFor(1, 1));
        widgetList3.add(getWidgetFor(2, 2));
        Widget widgetInsert3 = WidgetInMemoryStorageTest.getWidgetFor(100, 0);

        Integer[] expectedIndexOrder4 = {1,4,10};
        List<Widget> widgetList4 = new ArrayList<Widget>();
        widgetList4.add(getWidgetFor(1, 1));
        widgetList4.add(getWidgetFor(4, 4));
        Widget widgetInsert4 = WidgetInMemoryStorageTest.getWidgetFor(100, 10);

        Integer[] expectedIndexOrder5 = {1,3,4};
        List<Widget> widgetList5 = new ArrayList<Widget>();
        widgetList5.add(getWidgetFor(3, 3));
        widgetList5.add(getWidgetFor(4, 4));
        Widget widgetInsert5 = WidgetInMemoryStorageTest.getWidgetFor(100, 1);

        Integer[] expectedIndexOrder6 = {0,1,2,3,4,5};
        List<Widget> widgetList6 = new ArrayList<Widget>();
        widgetList6.add(getWidgetFor(0, 0));
        widgetList6.add(getWidgetFor(1, 1));
        widgetList6.add(getWidgetFor(2, 2));
        widgetList6.add(getWidgetFor(3, 3));
        widgetList6.add(getWidgetFor(4, 4));
        Widget widgetInsert6 = WidgetInMemoryStorageTest.getWidgetFor(100, 3);

        return new Object[][]{
            {widgetInsert1, widgetList1, expectedIndexOrder1},
            {widgetInsert2, widgetList2, expectedIndexOrder2},
            {widgetInsert3, widgetList3, expectedIndexOrder3},
            {widgetInsert4, widgetList4, expectedIndexOrder4},
            {widgetInsert5, widgetList5, expectedIndexOrder5},
            {widgetInsert6, widgetList6, expectedIndexOrder6},
        };
    }

    @DataProvider
    public static Object[] updateWidgetDataProvider() 
    {
        Integer[] expectedIndexOrder1 = {1,2,3};
        List<Widget> widgetList1 = new ArrayList<Widget>();
        widgetList1.add(getWidgetFor(0, 0));
        widgetList1.add(getWidgetFor(1, 1));
        widgetList1.add(getWidgetFor(2, 2));
        Widget updatedWidget1 = getWidgetFor(0, 2);

        Integer[] expectedIndexOrder2 = {1,3,5};
        List<Widget> widgetList2 = new ArrayList<Widget>();
        widgetList2.add(getWidgetFor(0, 1));
        widgetList2.add(getWidgetFor(1, 2));
        widgetList2.add(getWidgetFor(2, 3));
        Widget updatedWidget2 = getWidgetFor(1, 5);

        Integer[] expectedIndexOrder3 = {1,2,3,4};
        List<Widget> widgetList3 = new ArrayList<Widget>();
        widgetList3.add(getWidgetFor(0, 1));
        widgetList3.add(getWidgetFor(1, 2));
        widgetList3.add(getWidgetFor(2, 3));
        widgetList3.add(getWidgetFor(3, 4));
        Widget updatedWidget3 = getWidgetFor(2, 1);

        Integer[] expectedIndexOrder4 = {0,1};
        List<Widget> widgetList4 = new ArrayList<Widget>();
        widgetList4.add(getWidgetFor(0, 0));
        widgetList4.add(getWidgetFor(1, 1));
        Widget updatedWidget4 = getWidgetFor(1, 0);
        
        Integer[] expectedIndexOrder5 = {1,2};
        List<Widget> widgetList5 = new ArrayList<Widget>();
        widgetList5.add(getWidgetFor(0, 0));
        widgetList5.add(getWidgetFor(1, 1));
        Widget updatedWidget5 = getWidgetFor(0, 1);

        Integer[] expectedIndexOrder6 = {0,1,2};
        List<Widget> widgetList6 = new ArrayList<Widget>();
        widgetList6.add(getWidgetFor(0, 0));
        widgetList6.add(getWidgetFor(1, 1));
        widgetList6.add(getWidgetFor(2, 2));
        Widget updatedWidget6 = getWidgetFor(2, 2);

        Integer[] expectedIndexOrder7 = {0,1,2};
        List<Widget> widgetList7 = new ArrayList<Widget>();
        widgetList7.add(getWidgetFor(0, 0));
        widgetList7.add(getWidgetFor(1, 1));
        widgetList7.add(getWidgetFor(2, 2));
        Widget updatedWidget7 = getWidgetFor(1, 1);

        Integer[] expectedIndexOrder8 = {0,1,2};
        List<Widget> widgetList8 = new ArrayList<Widget>();
        widgetList8.add(getWidgetFor(0, 0));
        widgetList8.add(getWidgetFor(1, 1));
        widgetList8.add(getWidgetFor(2, 2));
        Widget updatedWidget8 = getWidgetFor(0, 0);

        return new Object[][]{
            {updatedWidget1, widgetList1, expectedIndexOrder1},
            {updatedWidget2, widgetList2, expectedIndexOrder2},
            {updatedWidget3, widgetList3, expectedIndexOrder3},
            {updatedWidget4, widgetList4, expectedIndexOrder4},
            {updatedWidget5, widgetList5, expectedIndexOrder5},
            {updatedWidget6, widgetList6, expectedIndexOrder6},
            {updatedWidget7, widgetList7, expectedIndexOrder7},
            {updatedWidget8, widgetList8, expectedIndexOrder8},
            
        };
    }
    
    @DataProvider
    public static Object[] getWidgetByIdDataProvider() 
    {
        List<Widget> widgetList1 = new ArrayList<Widget>();
        widgetList1.add(getWidgetFor(0, 0));
        widgetList1.add(getWidgetFor(1, 1));
        widgetList1.add(getWidgetFor(2, 2));
        Widget expectedWidget1 = widgetList1.get(0);
        int searchId1 = 0;

        List<Widget> widgetList2 = new ArrayList<Widget>();
        widgetList2.add(getWidgetFor(0, 0));
        widgetList2.add(getWidgetFor(1, 1));
        widgetList2.add(getWidgetFor(2, 2));
        Widget expectedWidget2 = widgetList2.get(1);
        int searchId2 = 1;

        List<Widget> widgetList3 = new ArrayList<Widget>();
        widgetList3.add(getWidgetFor(0, 0));
        widgetList3.add(getWidgetFor(1, 1));
        widgetList3.add(getWidgetFor(2, 2));
        Widget expectedWidget3 = widgetList3.get(2);
        int searchId3 = 2;

        return new Object[][]{
            {widgetList1, expectedWidget1, searchId1},
            {widgetList2, expectedWidget2, searchId2},
            {widgetList3, expectedWidget3, searchId3},
            
        };
    }

    private static Widget getWidgetFor(int id, int zIndex)
    {
        return new Widget(id, id, id, id, id, zIndex);
    }
}
