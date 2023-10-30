package org.pokesplash.fabrictemplate.event;

import org.pokesplash.fabrictemplate.event.events.ExampleEvent;
import org.pokesplash.fabrictemplate.event.obj.Event;

/**
 * Class that holds all of the events.
 */
public abstract class Events {
	public static Event<ExampleEvent> EXAMPLE = new Event<>();

}