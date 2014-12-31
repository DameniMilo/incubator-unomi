package org.oasis_open.contextserver.impl.conditions.initializers;

import org.oasis_open.contextserver.api.Metadata;
import org.oasis_open.contextserver.api.conditions.initializers.ChoiceListInitializer;
import org.oasis_open.contextserver.api.conditions.initializers.ChoiceListValue;
import org.oasis_open.contextserver.api.services.SegmentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 */
public class SegmentsChoiceListInitializer implements ChoiceListInitializer {

    SegmentService segmentService;

    public void setSegmentService(SegmentService segmentService) {
        this.segmentService = segmentService;
    }

    public List<ChoiceListValue> getValues(Object context) {
        List<ChoiceListValue> choiceListValues = new ArrayList<ChoiceListValue>();
        Set<Metadata> profileProperties = segmentService.getSegmentMetadatas();
        for (Metadata profileProperty : profileProperties) {
            choiceListValues.add(new ChoiceListValue(profileProperty.getIdWithScope(), profileProperty.getName()));
        }
        return choiceListValues;
    }
}
