package com.kmzko.configurator;

import com.kmzko.configurator.domains.questionnaire.Rate;
import com.kmzko.configurator.domains.conveyor.*;
import com.kmzko.configurator.utils.CompareConveyorAndQuestionnaire;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

import java.util.*;

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
    public void lessBound() {
        Characteristic c1 = new Characteristic("1", "1", "tape-width", "kilo");
        Characteristic c2 = new Characteristic("1", "2", "tape-length", "kilo");
        Characteristic c3 = new Characteristic("1", "2", "dust", "kilo");
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
        Characteristic c1 = new Characteristic("1", "1", "tape-width", "kilo");
        Characteristic c2 = new Characteristic("1", "1", "tape-length", "kilo");
        Characteristic c3 = new Characteristic("1", "2", "dust", "kilo");
        List<Characteristic> characteristicList = Arrays.asList(c1, c2, c3);

        Rate rate1 = new Rate("", "2", "tape-length");
        Rate rate2 = new Rate("", "5435", "tape-width");

        Detail d1 = new Detail("1", characteristicList);

        Conveyor conv = new Conveyor();
        conv.setNodes(Arrays.asList(new Node("", Arrays.asList(d1))));

        boolean result = false;

        assertThat(compare.proximity(conv, Arrays.asList(rate1, rate2))).isEqualTo(result);
    }

    @Test
    public void characteristicNotContainsKey() {
        Characteristic c1 = new Characteristic("1", "1", "tape-width", "kilo");
        Characteristic c2 = new Characteristic("1", "1", "tape-length", "kilo");
        Characteristic c3 = new Characteristic("1", "2", "hgreherwewtgwegwg", "kilo");
        List<Characteristic> characteristicList = Arrays.asList(c1, c2, c3);

        Rate rate1 = new Rate("", "2", "tape-length");
        Rate rate2 = new Rate("", "5435", "tape-width");

        Detail d1 = new Detail("1", characteristicList);

        Conveyor conv = new Conveyor();
        conv.setNodes(Arrays.asList(new Node("", Arrays.asList(d1))));

        boolean result = false;

        assertThat(compare.proximity(conv, Arrays.asList(rate1, rate2))).isEqualTo(result);
    }

    @Test
    public void rateNotContainsKey() {
        Characteristic c1 = new Characteristic("1", "1", "tape-width", "kilo");
        Characteristic c2 = new Characteristic("1", "1", "productivity", "kilo");
        Characteristic c3 = new Characteristic("1", "2", "dust", "kilo");
        List<Characteristic> characteristicList = Arrays.asList(c1, c2, c3);

        Rate rate1 = new Rate("", "2", "tape-length");
        Rate rate2 = new Rate("", "5435", "tape-widthfdsdgsdggsdggsdsdg");

        Detail d1 = new Detail("1", characteristicList);

        Conveyor conv = new Conveyor();
        conv.setNodes(Arrays.asList(new Node("", Arrays.asList(d1))));

        boolean result = false;

        assertThat(compare.proximity(conv, Arrays.asList(rate1, rate2))).isEqualTo(result);
    }
}
