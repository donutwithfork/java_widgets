package com.example.springboot.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Widget 
{
	private final int id;

	private int xCoord;

	private int yCoord;

	private int width;

	private int heigth;

	private int zIndex;

	@JsonCreator
	public Widget(
		@JsonProperty("id") int id, 
		@JsonProperty("xCoord") int xCoord, 
		@JsonProperty("yCoord") int yCoord, 
		@JsonProperty("width") int width, 
		@JsonProperty("heigth") int heigth, 
		@JsonProperty("zIndex") int zIndex
	) {
		this.id = id;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.width = width;
		this.heigth = heigth; // > 0
		this.zIndex = zIndex;
	}

	public Widget(int id, int zIndex, Widget widgetDto) 
	{
		this.id = id;
		this.xCoord = widgetDto.getXCoord();
		this.yCoord = widgetDto.getYCoord();
		this.width = widgetDto.getWidth();
		this.heigth = widgetDto.getHeigth();
		this.zIndex = zIndex;
	}

	@Override
	public boolean equals(Object object) {

        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        // 3
        Widget otherWidget = (Widget) object;
        return id == otherWidget.id &&
               zIndex == otherWidget.zIndex &&
               xCoord == otherWidget.xCoord &&
               yCoord == otherWidget.yCoord &&
               heigth == otherWidget.heigth &&
               width == otherWidget.width;
    }

	public void updateData(Widget widgetDto)
	{
		this.xCoord = widgetDto.getXCoord();
		this.yCoord = widgetDto.getYCoord();
		this.width = widgetDto.getWidth();
		this.heigth = widgetDto.getHeigth();
		this.zIndex = widgetDto.getZIndex();
	}

	@JsonProperty("id")
	public int getId() 
	{
		return id;
	}

	@JsonProperty("xCoord")
	public int getXCoord() 
	{
		return xCoord;
	}

	@JsonProperty("yCoord")
	public int getYCoord() 
	{
		return yCoord;
	}
	
	@JsonProperty("width")
	public int getWidth()
	{
		return width;
	}

	@JsonProperty("heigth")
	public int getHeigth()
	{
		return heigth;
	}

	@JsonProperty("zIndex")
	public int getZIndex() {
		return zIndex;
	}

	public void updateZIndex(int newZIndex) {
		this.zIndex = newZIndex;
  	}

	public void incrementZIndex() {
		this.zIndex++;
	  }
}
