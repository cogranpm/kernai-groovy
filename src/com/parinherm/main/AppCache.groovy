package com.parinherm.main

import org.eclipse.jface.resource.ImageDescriptor
import org.eclipse.jface.resource.ImageRegistry
import org.eclipse.swt.graphics.Image


class AppCache {
	
	
	private Random random
	final static int UPPER_LIMIT_INT_RANDOM = 400 
	
	private ImageRegistry imageRegistry = null
	public final static String IMAGE_ACTVITY_SMALL = "activitysmall"
	public final static String IMAGE_ACTIVITY_LARGE = "activitylarge"
	public final static String IMAGE_STOCK_INFO = "stock_info"
	public final static String IMAGE_STOCK_EXIT = "stock_exit"
	public final static String IMAGE_GOUP = "goup"
	public final static String IMAGES_PATH = "/images/"
	
	AppCache() {
		random = new Random()
		
	}
	
	int getRandomInt() {
		random.nextInt(UPPER_LIMIT_INT_RANDOM)
	}
	
	int getRandomInt(int upper) {
		random.nextInt(upper)
	}
	
	private def setupImages() {
		try {
		imageRegistry = new ImageRegistry()
		putImage(IMAGE_ACTVITY_SMALL, "Activity_16xSM.png")
		putImage(IMAGE_ACTIVITY_LARGE, "Activity_32x.png")
		putImage(IMAGE_GOUP, "go-up.png")
		putImage(IMAGE_STOCK_EXIT, "stock_exit_24.png")
		putImage(IMAGE_STOCK_INFO, "stock_save_24.png")
		}
		catch(Exception e) {
			println e
		}
	}
	
	private void putImage(String key, String filename) {
		try {
			String path = "$IMAGES_PATH$filename" as String
			imageRegistry.put(key, ImageDescriptor.createFromFile(AppCache.class, path)) 
		} catch (Exception e) {
			println e
		}
	}
	
	Image getImage(String name) {
		imageRegistry.get(name)
	}
}
