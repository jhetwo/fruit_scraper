package model.json;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import model.RipeFruit;

import java.io.IOException;
import java.math.MathContext;
import java.math.RoundingMode;

public class RipeFruitTypeAdapter extends TypeAdapter<RipeFruit> {

    @Override
    public void write(JsonWriter out, RipeFruit fruit) throws IOException {
        out.beginObject();
        fruit.getTitle()
                .ifPresent(title -> addStringField("title", title, out));
        fruit.getSize()
                .ifPresent(size -> {
                    String value = size + "kb";
                    addStringField("size", value, out);
                });
        fruit.getPrice()
                .ifPresent(price -> {
                    try {
                        out.name("unit_price").value(price.round(new MathContext(2, RoundingMode.CEILING)));
                        // TODO: precision to 2 dp
                    } catch (IOException e) {
                        // This is not a recoverable state, so best thing is to let it fail
                        throw new RuntimeException(e);
                    }
                });
        fruit.getDescription()
                .ifPresent(desc -> addStringField("description", desc, out));
        out.endObject();
    }

    private void addStringField(String name, String value, JsonWriter out) {
        try {
            out.name(name).value(value);
        } catch (IOException e) {
            // This is not a recoverable state, so best thing is to let it fail
            throw new RuntimeException(e);
        }
    }

    @Override
    public RipeFruit read(JsonReader in) throws IOException {
        throw new RuntimeException("Not Implemented");
    }
}
