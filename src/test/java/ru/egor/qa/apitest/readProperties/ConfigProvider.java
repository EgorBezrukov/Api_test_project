package ru.egor.qa.apitest.readProperties;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface ConfigProvider {
    Config conf = readConf();

    static Config readConf() {
        return ConfigFactory.load("application.conf");
    }

    String BASE_URL_SWAPI = readConf().getString("Swapi.baseUrl");



}
