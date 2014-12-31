package org.oasis_open.contextserver.impl.actions;

import org.oasis_open.contextserver.api.Event;
import org.oasis_open.contextserver.api.actions.Action;
import org.oasis_open.contextserver.api.actions.ActionExecutor;
import org.oasis_open.contextserver.api.services.ProfileService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by loom on 08.08.14.
 */
public class AllEventToProfilePropertiesAction implements ActionExecutor {

    private ProfileService profileService;

    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }

    public boolean execute(Action action, Event event) {
        boolean changed = false;
        Map<String, Object> properties = new HashMap<String,Object>();
        if (event.getProperties() != null) {
            properties.putAll(event.getProperties());
        }
        if (event.getTarget() != null && event.getTarget().getProperties() != null) {
            properties.putAll(event.getTarget().getProperties());
        }
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            if (event.getProfile().getProperty(entry.getKey()) == null || !event.getProfile().getProperty(entry.getKey()).equals(event.getProperty(entry.getKey()))) {
                String propertyMapping = profileService.getPropertyTypeMapping(entry.getKey());
                if (propertyMapping != null) {
                    event.getProfile().setProperty(propertyMapping, entry.getValue());
                } else {
                    event.getProfile().setProperty(entry.getKey(), entry.getValue());
                }
                changed = true;
            }
        }
        return changed;
    }
}
