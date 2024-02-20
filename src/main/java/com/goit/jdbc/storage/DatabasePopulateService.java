package com.goit.jdbc.storage;

import com.goit.jdbc.prefs.Prefs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DatabasePopulateService {
    public void populateDB(Database database) {
        String populateDbFilename = new Prefs().getString(Prefs.POPULATE_DB_SQL_FILEPATH);
        try {
            String sql = String.join("\n",
                    Files.readAllLines(Paths.get(populateDbFilename))
            );
            database.executeUpdate(sql);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Database database = Database.getInstance();
        new DatabasePopulateService().populateDB(database);
    }
}
