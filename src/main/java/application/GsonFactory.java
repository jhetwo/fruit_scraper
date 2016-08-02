package application;

import com.google.gson.*;
import model.FileSize;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class GsonFactory {

    public Gson get(){
        return new GsonBuilder()
                .setPrettyPrinting()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(BigDecimal.class,
                        (JsonSerializer<BigDecimal>) (src, typeOfSrc, context) -> new JsonPrimitive(src.setScale(2, BigDecimal.ROUND_UP)))
                .registerTypeAdapter(FileSize.class,
                        (JsonSerializer<FileSize>) (src, typeOfSrc, context) -> new JsonPrimitive(new DecimalFormat("0.##kb").format(src.getSize())))
                .create();
    }
}
