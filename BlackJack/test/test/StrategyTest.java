package test;

import static org.junit.Assert.*;

import org.junit.Test;

import io.ExcelReader;
import strategy.Strategy;

public class StrategyTest {

	@Test
	public void test() {
		ExcelReader reader = new ExcelReader();
		Strategy strat = new Strategy();
		
		strat.setStategyMap(reader.strategyExcelReader("input/blackJackStrat.xlsx"));
		
		strat.printMap();
	}

}
