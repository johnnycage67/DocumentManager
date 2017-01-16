package ru.ordm.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class JsonDateSerializer extends JsonSerializer<LocalDate> {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public void serialize(LocalDate date, JsonGenerator gen, SerializerProvider provider)
            throws IOException, JsonProcessingException {
        String formattedDate = date.format(DateTimeFormatter.BASIC_ISO_DATE);
        gen.writeString(formattedDate);
    }
}

