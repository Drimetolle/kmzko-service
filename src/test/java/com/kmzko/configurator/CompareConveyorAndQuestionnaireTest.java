package com.kmzko.configurator;

import com.kmzko.configurator.domains.Rate;
import com.kmzko.configurator.domains.conveyor.*;
import com.kmzko.configurator.utils.CompareConveyorAndQuestionnaire;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompareConveyorAndQuestionnaireTest {
    private CompareConveyorAndQuestionnaire compare;
    private final int bound = 90;
    private final float interval = 0.95f;
    private Map<String, Float> rates = new HashMap<String, Float>(){{
        put("tape-width", 45f);
        put("tape-length", 45f);
        put("productivity", 1f);
        put("dust", 1f);
    }};

    @Before
    public void setUp() {
        compare = new CompareConveyorAndQuestionnaire(bound, interval, rates,
                new HashSet<>(Arrays.asList("tape-width", "tape-length", "productivity")));
    }

    @Test
    public void numberRateValueNotExact() {
        Characteristic c1 = new Characteristic("1", "tape-width", "1", "kilo");
        Characteristic c2 = new Characteristic("1", "productivity", "2", "kilo");
        Characteristic c3 = new Characteristic("1", "dust", "2", "kilo");

        Rate rate1 = new Rate("", "2", "tape-length");
        Rate rate2 = new Rate("", "1", "productivity");

        Detail d1 = new Detail("1", Arrays.asList(c1, c2, c3));

        assertThat(compare.accumulate(Arrays.asList(d1), Arrays.asList(rate1, rate2))).isEqualTo(0);
    }

    @Test
    public void numberRateValueExact() {
        Characteristic c1 = new Characteristic("1", "tape-width", "1", "kilo");
        Characteristic c2 = new Characteristic("1", "productivity", "2", "kilo");
        Characteristic c3 = new Characteristic("1", "dust", "2", "kilo");

        Rate rate1 = new Rate("", "2", "tape-length");
        Rate rate2 = new Rate("", "2", "productivity");

        Detail d1 = new Detail("1", Arrays.asList(c1, c2, c3));

        assertThat(compare.accumulate(Arrays.asList(d1), Arrays.asList(rate1, rate2))).isEqualTo(1);
    }

    @Test
    public void numberRateValueExactIntervalInBounds() {
        float value = 100;
        float rin = 1 - interval;
        float up = value + rin * value;
        float down = value - rin * value;

        float randomValue = down + ThreadLocalRandom.current().nextFloat() * (up - down);

        Characteristic c1 = new Characteristic("1", "tape-width", "1", "kilo");
        Characteristic c2 = new Characteristic("1", "productivity", String.valueOf(value), "kilo");
        Characteristic c3 = new Characteristic("1", "dust", "2", "kilo");

        Rate rate1 = new Rate("", "2", "tape-length");
        Rate rate2 = new Rate("", String.valueOf(randomValue), "productivity");

        Detail d1 = new Detail("1", Arrays.asList(c1, c2, c3));

        assertThat(compare.accumulate(Arrays.asList(d1), Arrays.asList(rate1, rate2))).isEqualTo(1);
    }

    @Test
    public void numberRateValueExactIntervalInLowerBound() {
        float value = 100;
        float rin = 1 - interval;
        float down = value - rin * value;

        Characteristic c1 = new Characteristic("1", "tape-width", "1", "kilo");
        Characteristic c2 = new Characteristic("1", "productivity", String.valueOf(value), "kilo");
        Characteristic c3 = new Characteristic("1", "dust", "2", "kilo");

        Rate rate1 = new Rate("", "2", "tape-length");
        Rate rate2 = new Rate("", String.valueOf(down), "productivity");

        Detail d1 = new Detail("1", Arrays.asList(c1, c2, c3));

        assertThat(compare.accumulate(Arrays.asList(d1), Arrays.asList(rate1, rate2))).isEqualTo(1);
    }

    @Test
    public void numberRateValueExactIntervalInUpperBound() {
        float value = 100;
        float rin = 1 - interval;
        float up = value + rin * value;

        Characteristic c1 = new Characteristic("1", "tape-width", "1", "kilo");
        Characteristic c2 = new Characteristic("1", "productivity", String.valueOf(value), "kilo");
        Characteristic c3 = new Characteristic("1", "dust", "2", "kilo");

        Rate rate1 = new Rate("", "2", "tape-length");
        Rate rate2 = new Rate("", String.valueOf(up), "productivity");

        Detail d1 = new Detail("1", Arrays.asList(c1, c2, c3));

        assertThat(compare.accumulate(Arrays.asList(d1), Arrays.asList(rate1, rate2))).isEqualTo(1);
    }

    @Test
    public void stringRateValueExact() {
        Characteristic c1 = new Characteristic("1", "tape-width", "1", "kilo");
        Characteristic c2 = new Characteristic("1", "productivity", "qwe", "kilo");
        Characteristic c3 = new Characteristic("1", "dust", "2", "kilo");

        Rate rate1 = new Rate("", "2", "tape-length");
        Rate rate2 = new Rate("", "qwe", "productivity");

        Detail d1 = new Detail("1", Arrays.asList(c1, c2, c3));

        assertThat(compare.accumulate(Arrays.asList(d1), Arrays.asList(rate1, rate2))).isEqualTo(1);
    }

    @Test
    public void stringRateValueExactActualStringCompareFloat() {
        Characteristic c1 = new Characteristic("1", "tape-width", "1", "kilo");
        Characteristic c2 = new Characteristic("1", "productivity", "qwe", "kilo");
        Characteristic c3 = new Characteristic("1", "dust", "2", "kilo");

        Rate rate1 = new Rate("", "2", "tape-length");
        Rate rate2 = new Rate("", "100", "productivity");

        Detail d1 = new Detail("1", Arrays.asList(c1, c2, c3));

        assertThat(compare.accumulate(Arrays.asList(d1), Arrays.asList(rate1, rate2))).isEqualTo(0);
    }

    @Test
    public void stringRateValueExactActualFloatCompareString() {
        Characteristic c1 = new Characteristic("1", "tape-width", "1", "kilo");
        Characteristic c2 = new Characteristic("1", "productivity", "123", "kilo");
        Characteristic c3 = new Characteristic("1", "dust", "2", "kilo");

        Rate rate1 = new Rate("", "2", "tape-length");
        Rate rate2 = new Rate("", "qwe", "productivity");

        Detail d1 = new Detail("1", Arrays.asList(c1, c2, c3));

        assertThat(compare.accumulate(Arrays.asList(d1), Arrays.asList(rate1, rate2))).isEqualTo(0);
    }

    @Test
    public void lessBound() {
        Characteristic c1 = new Characteristic("1", "tape-width", "1", "kilo");
        Characteristic c2 = new Characteristic("1", "tape-length", "2", "kilo");
        Characteristic c3 = new Characteristic("1", "dust", "2", "kilo");
        List<Characteristic> characteristicList = Arrays.asList(c1, c2, c3);

        Rate rate1 = new Rate("", "2", "tape-length");
        Rate rate2 = new Rate("", "1", "tape-width");

        Detail d1 = new Detail("1", characteristicList);

        Conveyor conv = new Conveyor();
        conv.setNodes(Arrays.asList(new Node("", Arrays.asList(d1))));

        boolean result = characteristicList.stream().map(i -> rates.get(i.getMark())).reduce(Float::sum).get() > bound;

        assertThat(compare.proximity(conv, Arrays.asList(rate1, rate2))).isEqualTo(result);
    }

    @Test
    public void largerBound() {
        Characteristic c1 = new Characteristic("1", "tape-width", "1", "kilo");
        Characteristic c2 = new Characteristic("1", "productivity", "1", "kilo");
        Characteristic c3 = new Characteristic("1", "dust", "2", "kilo");
        List<Characteristic> characteristicList = Arrays.asList(c1, c2, c3);

        Rate rate1 = new Rate("", "2", "tape-length");
        Rate rate2 = new Rate("", "5435", "tape-width");

        Detail d1 = new Detail("1", characteristicList);

        Conveyor conv = new Conveyor();
        conv.setNodes(Arrays.asList(new Node("", Arrays.asList(d1))));

        boolean result = characteristicList.stream().map(i -> rates.get(i.getMark()))
                .filter(Objects::nonNull).reduce(Float::sum).get() > bound;

        assertThat(compare.proximity(conv, Arrays.asList(rate1, rate2))).isEqualTo(result);
    }

    @Test
    public void characteristicNotContainsKey() {
        Characteristic c1 = new Characteristic("1", "tape-width", "1", "kilo");
        Characteristic c2 = new Characteristic("1", "productivity", "1", "kilo");
        Characteristic c3 = new Characteristic("1", "hgreherwewtgwegwg", "2", "kilo");
        List<Characteristic> characteristicList = Arrays.asList(c1, c2, c3);

        Rate rate1 = new Rate("", "2", "tape-length");
        Rate rate2 = new Rate("", "5435", "tape-width");

        Detail d1 = new Detail("1", characteristicList);

        Conveyor conv = new Conveyor();
        conv.setNodes(Arrays.asList(new Node("", Arrays.asList(d1))));

        boolean result = characteristicList.stream().map(i -> rates.get(i.getMark()))
                .filter(Objects::nonNull).reduce(Float::sum).get() > bound;

        assertThat(compare.proximity(conv, Arrays.asList(rate1, rate2))).isEqualTo(result);
    }

    @Test
    public void rateNotContainsKey() {
        Characteristic c1 = new Characteristic("1", "tape-width", "1", "kilo");
        Characteristic c2 = new Characteristic("1", "productivity", "1", "kilo");
        Characteristic c3 = new Characteristic("1", "dust", "2", "kilo");
        List<Characteristic> characteristicList = Arrays.asList(c1, c2, c3);

        Rate rate1 = new Rate("", "2", "tape-length");
        Rate rate2 = new Rate("", "5435", "tape-widthfdsdgsdggsdggsdsdg");

        Detail d1 = new Detail("1", characteristicList);

        Conveyor conv = new Conveyor();
        conv.setNodes(Arrays.asList(new Node("", Arrays.asList(d1))));

        boolean result = characteristicList.stream().map(i -> rates.get(i.getMark()))
                .filter(Objects::nonNull).reduce(Float::sum).get() > bound;

        assertThat(compare.proximity(conv, Arrays.asList(rate1, rate2))).isEqualTo(result);
    }
}
