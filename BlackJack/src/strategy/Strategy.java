package strategy;

import java.util.HashMap;
import java.util.Map;

public class Strategy {

	private Map<StrategyCombo, Choice> stategyMap;

	public Map<StrategyCombo, Choice> getStategyMap() {
		return stategyMap;
	}

	public void setStategyMap(Map<StrategyCombo, Choice> stategyMap) {
		this.stategyMap = stategyMap;
	}
	
	public void printMap() {
		for (Map.Entry entry : stategyMap.entrySet()) {
		    System.out.println(entry.getKey() + ", " + entry.getValue());
		}
	}
}
