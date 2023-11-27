package com.revolvingmadness.testing;

import com.revolvingmadness.testing.loader.ResourceLoader;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.impl.resource.loader.ResourceManagerHelperImpl;
import net.minecraft.resource.ResourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Testing implements ModInitializer {
	public static final String ID = "testing";
    public static final Logger LOGGER = LoggerFactory.getLogger(ID);

	@Override
	public void onInitialize() {
		ResourceManagerHelperImpl.get(ResourceType.SERVER_DATA).registerReloadListener(new ResourceLoader());
	}
}