package me.jakepronger.spine.core;

import me.jakepronger.spine.Spine;

import java.lang.reflect.Constructor;

public class FeatureEngine {

    private final Spine spine;

    public FeatureEngine(Spine spine) {
        this.spine = spine;
    }

    public void load(Class<? extends Feature> clazz) {
        try {
            Constructor<? extends Feature> constructor =
                    clazz.getDeclaredConstructor();

            Feature feature = constructor.newInstance();
            feature.load(spine);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load feature: " + clazz.getSimpleName(), e);
        }
    }

}
