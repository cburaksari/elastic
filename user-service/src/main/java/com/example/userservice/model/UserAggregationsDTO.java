package com.example.userservice.model;

import lombok.Data;

import java.util.List;

@Data
public class UserAggregationsDTO {

    private List<RoleAggregation> byRole;
    private List<SignupsOverTime> signupsOverTime;
    @Data
    public static class RoleAggregation {
        private String role;
        private long count;
        private double avgAge;
    }

    @Data
    public static class SignupsOverTime {
        private String date;
        private long count;
    }
}
