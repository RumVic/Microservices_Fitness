package by.it_akademy.fitness.serializator;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class DateDeSerializer extends StdDeserializer<Date> {
    public DateDeSerializer() {
        super(Date.class);
    }

    @Override
    public Date deserialize(JsonParser jsonParser,
                            DeserializationContext deserializationContext) throws IOException, JacksonException {
        String value = jsonParser.readValueAs(String.class);
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(value);
        } catch (DateTimeParseException | ParseException e) {
            throw new IllegalArgumentException("Not valid birthday");
        }
    }
}
