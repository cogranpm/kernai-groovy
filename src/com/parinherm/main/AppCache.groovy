package com.parinherm.main

import org.eclipse.jface.resource.ImageDescriptor
import org.eclipse.jface.resource.ImageRegistry
import org.eclipse.swt.graphics.Image

@Singleton
class AppCache {
	private Random random = new Random()
	final static int UPPER_LIMIT_INT_RANDOM = 400 
	
	ImageRegistry imageRegistry = new ImageRegistry()
	public final static String IMAGE_ACTVITY_SMALL = "activitysmall"
	public final static String IMAGE_ACTIVITY_LARGE = "activitylarge"
	public final static String IMAGES_PATH = "/images/"
	
	int getRandomInt() {
		random.nextInt(UPPER_LIMIT_INT_RANDOM)
	}
	
	int getRandomInt(int upper) {
		random.nextInt(upper)
	}
	
	private def setupImages() {
		imageRegistry.put(IMAGE_ACTVITY_SMALL, ImageDescriptor.createFromFile(AppCache.class, String.format("%s%s", IMAGES_PATH, "Activity_16xSM.png")));
		imageRegistry.put(IMAGE_ACTIVITY_LARGE, ImageDescriptor.createFromFile(AppCache.class, String.format("%s%s", IMAGES_PATH, "Activity_32x.png")));
	}
	
	Image getImage(String name) {
		imageRegistry.get(name)
	}
}
