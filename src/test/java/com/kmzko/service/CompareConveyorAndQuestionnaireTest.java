package com.kmzko.service;

import com.kmzko.service.domains.Rate;
import com.kmzko.service.domains.conveyor.*;
import com.kmzko.service.utils.CompareConveyorAndQuestionnaire;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompareConveyorAndQuestionnaireTest {
    @Autowired
    private CompareConveyorAndQuestionnaire compare;

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
        float rin = 1 - compare.getInterval();
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
        float rin = 1 - compare.getInterval();
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
        float rin = 1 - compare.getInterval();
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

        Rate rate1 = new Rate("", "2", "tape-length");
        Rate rate2 = new Rate("", "1", "tape-width");

        Detail d1 = new Detail("1", Arrays.asList(c1, c2, c3));

        Conveyor conv = new Conveyor();
        conv.setNodes(Arrays.asList(new Node("", Arrays.asList(d1))));

        assertThat(compare.proximity(conv, Arrays.asList(rate1, rate2))).isEqualTo(true);
    }

    @Test
    public void largerBound() {
        Characteristic c1 = new Characteristic("1", "tape-width", "1", "kilo");
        Characteristic c2 = new Characteristic("1", "productivity", "1", "kilo");
        Characteristic c3 = new Characteristic("1", "dust", "2", "kilo");

        Rate rate1 = new Rate("", "2", "tape-length");
        Rate rate2 = new Rate("", "5435", "tape-width");

        Detail d1 = new Detail("1", Arrays.asList(c1, c2, c3));

        Conveyor conv = new Conveyor();
        conv.setNodes(Arrays.asList(new Node("", Arrays.asList(d1))));

        assertThat(compare.proximity(conv, Arrays.asList(rate1, rate2))).isEqualTo(false);
    }
}
