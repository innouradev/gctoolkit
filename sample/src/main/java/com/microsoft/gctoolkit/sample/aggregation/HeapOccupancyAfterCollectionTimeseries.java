package com.microsoft.gctoolkit.sample.aggregation;

import com.microsoft.gctoolkit.event.GarbageCollectionTypes;
import com.microsoft.gctoolkit.sample.collections.XYDataSet;
import com.microsoft.gctoolkit.time.DateTimeStamp;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@SuppressWarnings({ "requires-automatic", "requires-transitive-automatic" })
public class   HeapOccupancyAfterCollectionTimeseries extends HeapOccupancyAfterCollectionAggregation {

    private final Map<GarbageCollectionTypes, XYDataSet> aggregations = new ConcurrentHashMap<>();

    public HeapOccupancyAfterCollectionTimeseries() {}
    @SuppressWarnings({ "requires-automatic", "requires-transitive-automatic" })
    public void addDataPoint(GarbageCollectionTypes gcType, DateTimeStamp timeStamp, long heapOccupancy) {
        aggregations.computeIfAbsent(gcType, key -> new XYDataSet()).add(timeStamp.getTimeStamp(),heapOccupancy);
    }
    @SuppressWarnings({ "requires-automatic", "requires-transitive-automatic" })
    public Map<GarbageCollectionTypes, XYDataSet> get() {
        return aggregations;
    }

    @Override
    public boolean hasWarning() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return aggregations.isEmpty();
    }
   
    @Override
    public String toString() {

        return "Collected " + aggregations.size() + " different collection types";
    }
   
     
}
