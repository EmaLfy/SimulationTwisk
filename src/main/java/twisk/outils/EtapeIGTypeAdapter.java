package twisk.outils;

import com.google.gson.*;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.ActiviteIG;
import twisk.mondeIG.GuichetIG;

import java.lang.reflect.Type;

public class EtapeIGTypeAdapter implements JsonSerializer<EtapeIG>, JsonDeserializer<EtapeIG> {
    @Override
    public JsonElement serialize(EtapeIG src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.add("type", new JsonPrimitive(src.getClass().getSimpleName()));
        result.add("properties", context.serialize(src, src.getClass()));

        return result;
    }

    @Override
    public EtapeIG deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();

        if (type.equals(ActiviteIG.class.getSimpleName())) {
            return context.deserialize(jsonObject.get("properties"), ActiviteIG.class);
        } else if (type.equals(GuichetIG.class.getSimpleName())) {
            return context.deserialize(jsonObject.get("properties"), GuichetIG.class);
        }

        throw new JsonParseException("Unknown element type: " + type);
    }
}