package com.goit.jdbc.prefs;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Prefs {
    public static final String DB_JDBC_CONNECTION_URL = "dbUrl";
    public static final String INIT_DB_SQL_FILE_PATH = "initDbSQL";
    public static final String POPULATE_DB_SQL_FILEPATH = "populateDbSQL";
    public static final String FIND_LONGEST_PROJECT_FILEPATH = "findLongestProject";
    public static final String FIND_MAX_PROJECTS_CLIENT_FILEPATH = "findMaxProjectsClient";
    public static final String FIND_MAX_SALARY_WORKER_FILEPATH = "findMaxSalaryWorker";
    public static final String FIND_YOUNGEST_ELDEST_WORKERS_FILEPATH = "youngestEldestWorker";
    public static final String PRINT_PROJECT_PRICES_FILEPATH = "printPrices";
    public static final String DEFAULT_PREFS_FILENAME = "prefs.json";
    private Map<String, Object> preferences = new HashMap<>();

    public Prefs() {
        this(DEFAULT_PREFS_FILENAME);
    }

    public Prefs(String filename) {
        try {
            String json = String.join(
                    "\n",
                    Files.readAllLines(Paths.get(filename))
            );

            TypeToken<?> typeToken = TypeToken.getParameterized(
                    Map.class,
                    String.class,
                    Object.class);

            preferences = new Gson().fromJson(json, typeToken.getType());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String getString(String key) {
        return getPref(key).toString();
    }

    public Object getPref(String key) {
        return preferences.get(key);
    }
}
