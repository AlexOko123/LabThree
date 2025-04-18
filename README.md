1. Before the Strategy Pattern
All filtering logic was hardcoded inside DataProcessor.applyFilters().

Every time you wanted new filtering behavior (e.g., by region, by GDP, by another year):

You would have to open and edit DataProcessor.java.

Code was rigid and hard to extend.

2. After Adding Strategy Pattern
Filtering logic is outsourced to different FilterStrategy classes.

DataProcessor no longer cares how filtering is done — it just calls .apply() on a FilterStrategy.

If you want new filtering behavior (e.g., filter by GDP, continent, or literacy rate):

 You just create a new GDPFilterStrategy, ContinentFilterStrategy class.

No need to touch or modify DataProcessor anymore!
