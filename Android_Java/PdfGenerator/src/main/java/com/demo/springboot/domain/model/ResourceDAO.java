package com.demo.springboot.domain.model;

import com.demo.springboot.domain.dto.FileData;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ResourceDAO implements Resource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceDAO.class);

    @Override
    public List<FileData> findAll(String path) {

        List<FileData> files = new ArrayList<>();
        String fileUrl = path + Resource.fileName;

        try {
            final List<String> lines = Files.readAllLines(Paths.get(fileUrl), StandardCharsets.UTF_8);

            for (final String line: lines) {
                FileData fileData = convertLineToData(line.split(","));
                files.add(fileData);
            }

        } catch (IOException e) {
            LOGGER.error("file {} not found", Resource.fileName);
        }

        return files;
    }

    private FileData convertLineToData(final String[] line) {

        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        ZoneId zoneId = ZoneId.of("Europe/Warsaw");

        String fileName = line[0];
        Long size = Long.valueOf(line[1]);
        DateTime creationTime = formatter.parseDateTime(line[2]);
        ZonedDateTime creationDate = ZonedDateTime.ofInstant(creationTime.toDate().toInstant(), zoneId);

        return new FileData(fileName, size, creationDate);
    }

    @Override
    public void saveOne(FileData fileData, String path) {

        String fileUrl = path + Resource.fileName;

        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(new File(fileUrl), true));
            pw.write(fileData.getFileName() + "," + fileData.getSize() + "," + fileData.getCreationDate() + "\n");
            pw.close();

        } catch (FileNotFoundException e) {
            LOGGER.error("file {} not found", Resource.fileName);
        }
    }
}
