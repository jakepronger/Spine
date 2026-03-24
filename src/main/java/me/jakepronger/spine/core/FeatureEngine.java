package me.jakepronger.spine.core;

import me.jakepronger.spine.api.SpineAPI;

import java.lang.reflect.Constructor;

public class FeatureEngine {

    private final SpineAPI api;

    public FeatureEngine(SpineAPI api) {
        this.api = api;
    }

    // todo: is this ok? (REFLECTION)
    public void load(Class<? extends Feature> clazz) {
        try {
            Constructor<? extends Feature> constructor =
                    clazz.getDeclaredConstructor();

            Feature feature = constructor.newInstance();
            feature.load(api);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load feature: " + clazz.getSimpleName(), e);
        }
    }

}
