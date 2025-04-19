Our first pattern was a Strategy Pattern.

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

Our next pattern is the factory pattern.

1. We refactored the panel creation process in the TableFrame class using the Factory Method Pattern.
2.This pattern centralizes and simplifies the creation of different GUI components (JPanel objects) in a single class, called PanelFactory.
3. This makes the project easier to extend (i.e. adding new panels)




