package com.example.userservice.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.example.userservice.util.AggregationUtil.roleAggregation;
import static com.example.userservice.util.AggregationUtil.signUpsAggregation;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ElasticsearchClient elasticsearchClient;

    public User create(User user) {
        return userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public SearchResponse<User> getUserAggregations() throws IOException {
        return elasticsearchClient.search(s -> s
                        .index("microservice-logs-*")
                        .size(0)
                        .aggregations("by_role", roleAggregation())
                        .aggregations("signups_over_time", signUpsAggregation()
                        )
                , User.class);
    }
}
