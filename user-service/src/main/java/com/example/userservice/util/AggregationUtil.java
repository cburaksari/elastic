package com.example.userservice.util;

import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;
import co.elastic.clients.elasticsearch._types.aggregations.CalendarInterval;
import co.elastic.clients.util.ObjectBuilder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AggregationUtil {

    public static Function<Aggregation.Builder, ObjectBuilder<Aggregation>> signUpsAggregation() {
        return a -> a
                .dateHistogram(dh -> dh
                        .field("signupDate")
                        .calendarInterval(CalendarInterval.valueOf("month"))
                );
    }

    public static Function<Aggregation.Builder, ObjectBuilder<Aggregation>> roleAggregation() {
        return a -> a
                .terms(t -> t
                        .field("role.keyword")
                        .size(10)
                )
                .aggregations("avg_age", aa -> aa
                        .avg(avg -> avg.field("age"))
                );
    }
}
