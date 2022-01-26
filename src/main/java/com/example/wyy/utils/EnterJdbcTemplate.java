package com.example.wyy.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
public class EnterJdbcTemplate  {
    private static JdbcTemplate jdbcTemplate;
    @Autowired
    public EnterJdbcTemplate (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public static JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

}

