package outlier.detection.spark.processor;

import java.io.IOException;

import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class IsoDateSerializer extends JsonSerializer<DateTime> {
    @Override
    public void serialize(DateTime value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        String isoDate = ISODateTimeFormat.dateTime().print(value);
        String text = "{ \"$date\" : \""+   isoDate   +"\"}";
        jgen.writeRawValue(text);
    }
}
