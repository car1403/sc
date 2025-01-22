package com.hd.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.hd.dto.response.OrderResponseDto;
import com.hd.feign.GetOrder;
import com.hd.feign.GetProduct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeignService {

    private final GetOrder getOrder;
    private final GetProduct getProduct;
    private final ObjectMapper objectMapper;

    public Object getOrder(Long id) throws IOException {
        Gson gson = new Gson();

        JsonNode jsonNode = objectMapper.readTree(gson.toJson(getOrder.get(id)));
        OrderResponseDto orderResponseDto = objectMapper.readValue(jsonNode.get("data").toString(), OrderResponseDto.class);

        return orderResponseDto;
    }
    public Object getOrders() throws IOException {

        Gson gson = new Gson();
//        objectMapper.registerModule(new JavaTimeModule());
        JsonNode jsonNode = objectMapper.readTree(gson.toJson(getOrder.getall()));

        log.info(jsonNode.get("data").toString());
        JSONArray jsonArray = new JSONArray(jsonNode.get("data").toString());
        List<Object> list = jsonArray.toList();
        List<OrderResponseDto> integerList = list.stream()
                .map(o -> {
                    try {
                        return objectMapper.readValue(gson.toJson(o),OrderResponseDto.class);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
        log.info("Size: "+integerList.size());

        return integerList;
    }
    public Object getProduct(){
        return getProduct.get();
    }
}
