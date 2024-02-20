package com.goit.jdbc.storage;

import com.goit.jdbc.prefs.Prefs;
import com.goit.dto.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DatabaseQueryService {
    private final Database database;

    public DatabaseQueryService(Database database) {
        this.database = database;
    }

    List<LongestProject> findLongestProject() {
        String longestProjectFilename = new Prefs().getString(Prefs.FIND_LONGEST_PROJECT_FILEPATH);
        List<LongestProject> list = new ArrayList<>();
        try (Statement st = database.getConnection().createStatement()) {
            String sql = String.join("\n",
                    Files.readAllLines(Paths.get(longestProjectFilename))
            );
            try (ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    int projectID = rs.getInt("id");
                    int duration = rs.getInt("duration");
                    LongestProject longestProject = new LongestProject(projectID, duration);
                    list.add(longestProject);
                }
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    List<MaxProjectCountClient> findMaxProjectsClient() {
        String maxProjectsFilename = new Prefs().getString(Prefs.FIND_MAX_PROJECTS_CLIENT_FILEPATH);
        List<MaxProjectCountClient> list = new ArrayList<>();
        try (Statement st = database.getConnection().createStatement()) {
            String sql = String.join("\n",
                    Files.readAllLines(Paths.get(maxProjectsFilename))
            );
            try (ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    String clientName = rs.getString("name");
                    int numProjects = rs.getInt("project_count");
                    MaxProjectCountClient maxProjectCountClient = new MaxProjectCountClient(clientName, numProjects);
                    list.add(maxProjectCountClient);
                }
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    List<MaxSalaryWorker> findMaxSalaryWorker() {
        String maxSalaryWorkerFilename = new Prefs().getString(Prefs.FIND_MAX_SALARY_WORKER_FILEPATH);
        List<MaxSalaryWorker> list = new ArrayList<>();
        try (Statement st = database.getConnection().createStatement()) {
            String sql = String.join("\n",
                    Files.readAllLines(Paths.get(maxSalaryWorkerFilename))
            );
            try (ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    String workerName = rs.getString("name");
                    int salary = rs.getInt("salary");
                    MaxSalaryWorker maxSalaryWorker = new MaxSalaryWorker(workerName, salary);
                    list.add(maxSalaryWorker);
                }
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    List<YoungestEldestWorker> findYoungestEldestWorker() {
        String youngestEldestWorkerFilename = new Prefs().getString(Prefs.FIND_YOUNGEST_ELDEST_WORKERS_FILEPATH);
        List<YoungestEldestWorker> list = new ArrayList<>();
        try (Statement st = database.getConnection().createStatement()) {
            String sql = String.join("\n",
                    Files.readAllLines(Paths.get(youngestEldestWorkerFilename))
            );
            try (ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    String workerName = rs.getString("name");
                    Date birthday = rs.getDate("birthday");
                    YoungestEldestWorker youngestEldestWorker = new YoungestEldestWorker(workerName, birthday);
                    list.add(youngestEldestWorker);
                }
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    List<PrintPrices> printProjectPrices() {
        String printProjectPricesFilename = new Prefs().getString(Prefs.PRINT_PROJECT_PRICES_FILEPATH);
        List<PrintPrices> list = new ArrayList<>();
        try (Statement st = database.getConnection().createStatement()) {
            String sql = String.join("\n",
                    Files.readAllLines(Paths.get(printProjectPricesFilename))
            );
            try (ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    int projectID = rs.getInt("ProjectID");
                    int cost = rs.getInt("ProjectCost");
                    PrintPrices printPrices = new PrintPrices(projectID, cost);
                    list.add(printPrices);
                }
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static void main(String[] args) {
        Database database = Database.getInstance();
        DatabaseQueryService dqs = new DatabaseQueryService(database);
        for (LongestProject project1 : dqs.findLongestProject()) {
            System.out.println("ProjectId " + project1.getID() + " - " + project1.getCountOfMonths() + " months");
        }
        for (MaxProjectCountClient project : dqs.findMaxProjectsClient()) {
            System.out.println(project.getName() + " = " + project.getProjectCount() + " projects");
        }
        for (MaxSalaryWorker worker : dqs.findMaxSalaryWorker()) {
            System.out.println(worker.getName() + " = " + worker.getSalary() + "$");
        }
        for (YoungestEldestWorker worker : dqs.findYoungestEldestWorker()) {
            System.out.println(worker.getName() + " = " + worker.getBirthday().toLocalDate());
        }
        for (PrintPrices price : dqs.printProjectPrices()) {
            System.out.println("ProjectId " + price.getProjectID() + " = " + price.getProjectCost() + "$");
        }
    }

}
