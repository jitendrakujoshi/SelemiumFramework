package jitendrajoshi.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

    // ✅ JSON → List of HashMap
    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

        // 1️⃣ Read JSON file into String
        String jsonContent = FileUtils.readFileToString(
                new File(filePath),
                StandardCharsets.UTF_8
        );

        // 2️⃣ Convert String → HashMap using Jackson
        ObjectMapper mapper = new ObjectMapper();

        List<HashMap<String, String>> data =
                mapper.readValue(
                        jsonContent,
                        new TypeReference<List<HashMap<String, String>>>() {}
                );

        return data;
    }
}
